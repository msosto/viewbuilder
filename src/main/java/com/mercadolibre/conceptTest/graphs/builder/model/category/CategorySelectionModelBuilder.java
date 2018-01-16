package com.mercadolibre.conceptTest.graphs.builder.model.category;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.mercadolibre.ActionsModule;
import com.mercadolibre.actions.CatalogUtils;
import com.mercadolibre.actions.CategoryUtils;
import com.mercadolibre.conceptTest.graphs.builder.view.contracts.category.BreadcrumbCategory;
import com.mercadolibre.conceptTest.graphs.builder.view.contracts.category.CategorySelectionColumn;
import com.mercadolibre.conceptTest.graphs.builder.view.contracts.category.CategorySelectionRow;
import com.mercadolibre.conceptTest.graphs.model.CategorySelectionModel;
import com.mercadolibre.conceptTest.graphs.model.FooterModel;
import com.mercadolibre.conceptTest.graphs.model.HeaderModel;
import com.mercadolibre.config.Config;

import com.mercadolibre.dto.Category;
import com.mercadolibre.dto.catalog.SellCatalogSelection;
import com.mercadolibre.dto.catalog.SellCatalogSelectionColumn;
import com.mercadolibre.dto.catalog.SellCatalogSelectionRow;
import com.mercadolibre.flux.flow.graph.navigation.Context;
import com.mercadolibre.util.providers.CatalogProductProvider;
import com.mercadolibre.util.providers.CategoryProvider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.mercadolibre.util.ContextUtils.withDots;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 * Created by mlizarraga on 11/1/18
 */
public class CategorySelectionModelBuilder {

    public static final String HEADER_TITLE = "CATEGORY_SELECTION_HEADER_TITLE";
    public static final String FOOTER_BUTTON_TEXT = "CATEGORY_SELECTION_FOOTER_BUTTON_TEXT";
    public static final String CATALOG = "catalog";
    public static final String MIN_QUANTITY_FOR_FILTER = "min_quantity_for_filter";
    public static final String ITEM_CATEGORY_ID = "item.category_id";

    private CategorySelectionModel model;
    private CategoryUtils categoryUtils;
    private CatalogUtils catalogUtils;

    public CategorySelectionModelBuilder() {
        this.model = new CategorySelectionModel();
        this.categoryUtils = ActionsModule.get().getInstance(CategoryUtils.class);
        this.catalogUtils = ActionsModule.get().getInstance(CatalogUtils.class);
    }

    public CategorySelectionModel build(Context context) {
        model.withCategoryId(CategoryProvider.DATA_ITEM.getId(context))
                .withCatalogProductId(CatalogProductProvider.DATA_ITEM.getId(context))
                .setLeaf(isLeaf(context))
                .withAdultContent(getAdultContent(context));
        addColumn(context);
        addBreadcrumbCategories(context);
        addHeader(model.getHeaderModel());
        if (model.getLeaf()) {
            addFooter(model.getFooterModel());
        }
        return model;
    }

    private void addBreadcrumbCategories(Context context) {
        List<BreadcrumbCategory> breadcrumbCategories = Lists.newArrayList();
        Category category = categoryUtils.getCategory(context, CategoryProvider.DATA_ITEM);
        if(nonNull(category)){
            category.getPathFromRoot().forEach(cat -> {
                    breadcrumbCategories.add(new BreadcrumbCategory()
                            .withId(cat.getId())
                            .withName(cat.getName())
                            .withOutput(withDots(context.getId(), ITEM_CATEGORY_ID)));
            });
        }
        model.withBreadcrumbCategories(breadcrumbCategories);
        model.withShowCategoryBreadcrumbComponent(!breadcrumbCategories.isEmpty());
    }

    private void addColumn(Context context) {
        SellCatalogSelection selection = getSelection(context);
        SellCatalogSelectionColumn lastColumn = Iterables.getLast(selection.getColumns());
        boolean shouldContinue = Boolean.TRUE.equals(selection.getShouldContinue());

        CategorySelectionColumn column = new CategorySelectionColumn();
        column.withRows(processRows(context.getId(), lastColumn));
        column.withFilter(shouldContinue ? false : isFilterAvailable(lastColumn));
        column.setLeaf(shouldContinue);

        if (shouldContinue) {
            column.withRows(Lists.newArrayList());
        }

        model.withColumn(column);
        model.withShowCategorySelectionComponent(!shouldContinue);
    }

    private void addHeader(HeaderModel headerModel) {
        headerModel.withTitle(HEADER_TITLE);
    }

    private void addFooter(FooterModel footerModel) {
        footerModel.withButtonText(FOOTER_BUTTON_TEXT);
        footerModel.withButtonConnection("continue");
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

    /**
     * Process the rows given a column.
     *
     * @param column
     */
    private List<CategorySelectionRow> processRows(String contextId, SellCatalogSelectionColumn column) {
        List<CategorySelectionRow> rows = Lists.newArrayList();
        column.getRows().forEach(sellCatalogSelectionRow -> {
            CategorySelectionRow row = new CategorySelectionRow()
                    .withName(sellCatalogSelectionRow.getName())
                    .withOutput(getOutput(contextId, sellCatalogSelectionRow.getOutput()))
                    .withCustomOutput(nonNull(sellCatalogSelectionRow.getCustomOutput()) ? withDots(sellCatalogSelectionRow.getCustomOutput()) : null)
                    .withTags(sellCatalogSelectionRow.getTags());
            rows.add(row);
        });
        return rows;
    }

    /**
     * Getter for the output. Copy map on custom_output to the output.
     *
     * @param contextId
     * @param customOutput
     * @return
     */
    private Map<String, Object> getOutput(String contextId, Map<String, Object> customOutput) {
        if (isNull(customOutput))
            return null;

        final HashMap<String, Object> output = new HashMap<>();
        customOutput.forEach((k, v) -> output.put(withDots(contextId, k), v));

        return output;
    }

    /**
     * Specify if a column has filters available or not only if column resource is catalog.
     * The filter is used to limit the number of rows (categories) of a selection when there are many.
     *
     * @param column
     * @return
     */
    private Boolean isFilterAvailable(SellCatalogSelectionColumn column) {
        return (CATALOG.equalsIgnoreCase(column.getResource())
                && column.getRows().size() > Config.get().getInt(MIN_QUANTITY_FOR_FILTER, 20));
    }

    /**
     * Get adult content from category
     *
     * @param context
     * @return
     */
    private Boolean getAdultContent(Context context) {
        final Category category = categoryUtils.getCategory(context, CategoryProvider.DATA_ITEM);
        if (Objects.isNull(category))
            return false;
        return category.getSettings().getAdultContent();
    }

    /**
     * Get the first sell catalog selection in model.
     *
     * @param context
     * @return
     */
    public SellCatalogSelection getSelection(Context context) {
        return catalogUtils.getFirstSellCatalogSelectionInModel(context).orElse(null);
    }
}
