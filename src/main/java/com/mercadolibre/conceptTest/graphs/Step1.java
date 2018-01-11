package com.mercadolibre.conceptTest.graphs;

import com.google.inject.Inject;
import com.mercadolibre.actions.CatalogUtils;
import com.mercadolibre.actions.CategoryAttributeUtils;
import com.mercadolibre.actions.CategoryUtils;
import com.mercadolibre.actions.api.catalog.PostSellCatalogSelection;
import com.mercadolibre.actions.api.category.GetCategory;
import com.mercadolibre.actions.api.category.GetCategoryAttribute;
import com.mercadolibre.config.Config;
import com.mercadolibre.dto.Category;
import com.mercadolibre.dto.catalog.CatalogProductAttribute;
import com.mercadolibre.dto.catalog.SellCatalogSelection;
import com.mercadolibre.dto.catalog.SellCatalogSelectionParams;
import com.mercadolibre.flux.flow.action.AddGoalData;
import com.mercadolibre.flux.flow.action.execution.AchieveLoader;
import com.mercadolibre.flux.flow.action.execution.ActionDependency;
import com.mercadolibre.flux.flow.action.execution.ActionExecution;
import com.mercadolibre.flux.flow.action.execution.Checkpoint;
import com.mercadolibre.flux.flow.graph.navigation.Context;
import com.mercadolibre.flux.flow.graph.node.Step;
import com.mercadolibre.flux.flow.graph.relationship.Achieve;
import com.mercadolibre.flux.flow.graph.template.Template;
import com.mercadolibre.util.providers.CatalogAttributeProvider;
import com.mercadolibre.util.providers.CategoryProvider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.mercadolibre.actions.api.category.GetCategory.CATEGORY_ID;
import static com.mercadolibre.flux.flow.action.AddDataAction.*;
import static java.util.Objects.nonNull;

/**
 * Created by msosto on 1/10/18.
 */
public class Step1 extends Template {

    public static final String DATA_ITEM_CATALOG_PRODUCT_ID = "data.item.catalog_product_id";
    public static final String ITEM_CATALOG_PRODUCT_ID = "item.catalog_product_id";
    public static final String ITEM_CATEGORY_ID_DATA_KEY = "item.category_id";
    public static final String DATA_ITEM_CATEGORY_ID = "data.item.category_id";
    public static final String START_CATEGORY_SELECTION_TEMPLATE = "start_category_selection";
    public static final String CLEAN_CATEGORY = "clean_category";
    public static final String CATEGORY_SELECTION_TASK = "category_selection_task";
    public static final String READY_CATEGORY_STEP = "ready_category_step";
    public static final String CATALOG_PRODUCT_INPUT = "catalog_product_input";
    public static final String CATEGORY_INPUT = "category_input";
    public static final String CATALOG_ATTRIBUTES = "catalog_attributes";

    private final Checkpoint AFTER_INITIAL_CATEGORY = Checkpoint.withId("after_get_category");
    private final Checkpoint AFTER_PRODUCTIZATION = Checkpoint.withId("after_productization");
    private CategoryUtils categoryUtils;

    public Step1() {
        categoryUtils = new CategoryUtils();
    }

    @Override
    public void execute() {

        Step step1 = stepBuilder()
                .setId("step1")
                .build();

        achieveRelationshipBuilder()
                .setNode(step1)
                .setAction(Step1Builder.class)
                .build();

        achieveRelationshipBuilder()
                .setNode(step1)
                .setAction(AchieveLoader.class)
                .setBefore((context, execution) -> {
                    String categoryId = CategoryProvider.DATA_ITEM.getId(context);
                    List<ActionDependency> dependencies = new ArrayList<>();
                    if (nonNull(categoryId)) {
                        dependencies.add(execution.add(GetCategory.class, (ctx, exec) -> {
                            ctx.addConfig(GetCategory.CATEGORY_ID, categoryId);
                        }));
                    }
                    execution.add(AchieveLoader.class, dependencies, (ctx1, exec1) -> {
                        exec1.checkpointReached(AFTER_INITIAL_CATEGORY);
                    });
                })
                .build();

        achieveRelationshipBuilder()
                .setNode(step1)
                .setAction(AchieveLoader.class)
                .addDependence(AFTER_INITIAL_CATEGORY)
                .setBefore((context, execution) -> {
                    if (isLeaf(context)) {
                        addProductizationAchieves(context, execution);
                    } else {
                        addGetSellCatalogSelectionAchieve(execution);
                    }

                })
                .build();


    }

    private void addProductizationAchieves(Context context, ActionExecution execution) {

        List<ActionDependency> dependencies = new ArrayList<>();
        if (hasCatalogAttributes(context)) {
            dependencies.add(tryToProductize(execution));
        }
        List<ActionDependency> checkPointDependencies = Arrays.asList(
                addGetCategory(execution, dependencies),
                addGetCategoryAttributes(execution, dependencies)
        );
        execution.add(
                AchieveLoader.class,
                checkPointDependencies,
                (ctx, e) -> e.checkpointReached(AFTER_PRODUCTIZATION)
        );
    }

    private Achieve tryToProductize(ActionExecution execution) {
        return null;
    }


    private Boolean isLeaf(Context context) {
        String categoryId = CategoryProvider.DATA_ITEM.getId(context);
        if (nonNull(categoryId)) {
            Category category = categoryUtils.getCategory(context, CategoryProvider.DATA_ITEM);
            if (nonNull(category)) {
                return category.isLeaf();
            }
        }
        return false;
    }

    private Boolean hasCatalogAttributes(Context context) {
        List<CatalogProductAttribute> catalogAttributes = CatalogAttributeProvider.DATA.getAttributes(context);
        return nonNull(catalogAttributes) && !catalogAttributes.isEmpty();
    }

    private Achieve addGetSellCatalogSelectionAchieve(ActionExecution exec) {
        Achieve getSellCatalogSelection = exec.add(PostSellCatalogSelection.class,
                // before
                (context, execBefore) -> {
                    final SellCatalogSelectionParams params = new CatalogUtils().getSellCatalogSelectionParams(context, new ListCategoryAttributeUtils().getCategoryAttributesClient());
                    context.addConfig(PostSellCatalogSelection.CONFIG_CATALOG_SELECTION, params);
                }

                ,
                // after
                (contextAfter, execAfter) -> {
                    final SellCatalogSelection selection = new CatalogUtils().getSellCatalogSelection(contextAfter, new ListCategoryAttributeUtils().getCategoryAttributesClient()).get();
                    List<ActionDependency> dependencies = new ArrayList<>();

                    dependencies.add(execAfter.add(AddGoalData.class, (ctx, exe) -> {
                        ctx.addConfig(DATA_KEY, ITEM_CATEGORY_ID_DATA_KEY);
                        ctx.addConfig(OPTIONAL, true);
                        ctx.addConfig(DATA_VALUE, selection.getCategoryId());
                    }));
                    dependencies.add(execAfter.add(AddGoalData.class, (ctx, exe) -> {
                        ctx.addConfig(OPTIONAL, true);
                        ctx.addConfig(DATA_KEY, ITEM_CATALOG_PRODUCT_ID);
                        ctx.addConfig(DATA_VALUE, selection.getCatalogProductId());
                    }));

                    execAfter.add(AchieveLoader.class, dependencies, (context, execution) -> {
                        new CatalogUtils().getSellCatalogSelection(context, new ListCategoryAttributeUtils().getCategoryAttributesClient())
                                .map(s -> s.getCategoryId()).ifPresent(categoryId -> {
                            execution.add(GetCategory.class,    // TODO: ¿ agregar checkpoint ?
                                    (ctx, exe) -> ctx.addConfig(CATEGORY_ID, categoryId));
                        });
                    });

                });

        return getSellCatalogSelection;
    }


    protected Achieve addGetCategory(ActionExecution execution, List<ActionDependency> dependencies) {
        return execution.add(GetCategory.class,dependencies,(ctx, exec) -> {
            ctx.addConfig(GetCategory.CATEGORY_ID, CategoryProvider.DATA_ITEM.getId(ctx));
        });
    }

    protected Achieve addGetCategoryAttributes(ActionExecution execute,List<ActionDependency> dependencies) {
        return execute.add(GetCategoryAttribute.class,dependencies,(context, execution) -> {
            new ListCategoryAttributeUtils().setGetCategoryAttributesConfig(CategoryProvider.DATA_ITEM, context);
        });
    }



    public class ListCategoryAttributeUtils {

        public static final String TECHNICAL_SPECIFICATIONS_LIST_CLIENT_DEFAULT = "syi-list-desktop";

        @Inject
        CategoryAttributeUtils categoryAttributeUtils;

        /**
         * Sets the necesary information on context config to get the Attributes of a category
         *
         * @param categoryProvider
         * @param context
         */
        public void setGetCategoryAttributesConfig(CategoryProvider categoryProvider, Context context) {
            context.addConfig(GetCategoryAttribute.CATEGORY_ID, categoryProvider.getId(context));
            context.addConfig(GetCategoryAttribute.OPTIONS, getCategoryAttributesOptions());
        }


        /**
         * Returns a Map with the option values to put into GetCategoryAttributes Action
         *
         * @return
         */
        private Map<String, Object> getCategoryAttributesOptions() {
            String version = Config.get().getString("category_attributes_endpoint", null);
            String client = getCategoryAttributesClient();
            return categoryAttributeUtils.getOptionsForCategoryAttribute(client, version);
        }


        /**
         * Return the client to send into Category Technical Specifications and Catalog Api Calls
         *
         * @return
         */
        public String getCategoryAttributesClient() {
            return Config.get().getString("category_attributes_client", TECHNICAL_SPECIFICATIONS_LIST_CLIENT_DEFAULT);
        }


    }
}
