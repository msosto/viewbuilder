package com.mercadolibre.conceptTest.model.step;

import com.mercadolibre.ActionsModule;
import com.mercadolibre.actions.CatalogUtils;
import com.mercadolibre.actions.CategoryAttributeUtils;
import com.mercadolibre.actions.CategoryUtils;
import com.mercadolibre.actions.SiteUtils;
import com.mercadolibre.conceptTest.model.component.pks.PKsInputModelBuilder;
import com.mercadolibre.conceptTest.model.component.title.FinderInputModelBuilder;
import com.mercadolibre.conceptTest.todo.VariationAttributesModelBuilder;
import com.mercadolibre.dto.Category;
import com.mercadolibre.dto.Country;
import com.mercadolibre.dto.Site;
import com.mercadolibre.dto.catalog.SellCatalogSelection;
import com.mercadolibre.dto.category.Vertical;
import com.mercadolibre.flux.flow.exception.GraphNavigationException;
import com.mercadolibre.flux.flow.graph.navigation.Context;
import com.mercadolibre.service.CategoryAttributeService;
import com.mercadolibre.util.ContextUtils;
import com.mercadolibre.util.providers.CatalogProductProvider;
import com.mercadolibre.util.providers.CategoryProvider;

import static java.util.Objects.nonNull;

/**
 * Contiene toda la informacion en crudo para mostrar el Paso 1.
 * Implementa distintas interfaces para desacoplar.
 * <p>
 * Created by msosto on 1/10/18.
 */
public class Step1ModelBuilder {

    private final FinderInputModelBuilder finderModelBuilder;
    private final PKsInputModelBuilder pKsInputModelBuilder;
    private final VariationAttributesModelBuilder variationAttributesModelBuilder;
    private final SiteUtils siteUtils;
    private final CategoryAttributeUtils categoryAttributeUtils;
    private final CategoryProvider categoryProvider;
    private final CatalogProductProvider catalogProductProvider;
    private final CategoryUtils categoryUtils;
    private final CategoryAttributeService categoryAttributeService;
    private final CatalogUtils catalogUtils;

    public Step1ModelBuilder() {
        this.categoryProvider = CategoryProvider.DATA_ITEM;
        this.finderModelBuilder = new FinderInputModelBuilder();
        this.pKsInputModelBuilder = new PKsInputModelBuilder();
        this.variationAttributesModelBuilder = new VariationAttributesModelBuilder(categoryProvider);
        this.siteUtils = ActionsModule.get().getInstance(SiteUtils.class);
        this.categoryAttributeUtils = ActionsModule.get().getInstance(CategoryAttributeUtils.class);
        this.categoryAttributeService = ActionsModule.get().getInstance(CategoryAttributeService.class);
        this.catalogProductProvider = CatalogProductProvider.DATA_ITEM;
        this.categoryUtils = ActionsModule.get().getInstance(CategoryUtils.class);
        this.catalogUtils = ActionsModule.get().getInstance(CatalogUtils.class);
    }

    public Step1Model getModel(Context context) {
        Step1Model model = new Step1Model()
                .setTitleInputModel(finderModelBuilder.build(context))
                .setPksInputModel(pKsInputModelBuilder.build(context))
                .setVariationAttributesInput(variationAttributesModelBuilder.build(context));

        addCategoryData(model, context);
        addCategorySelectionData(model, context);
        addAttributesData(model, context);
        model.setCountry(getCountry(context));
        return model;
    }

    private void addAttributesData(Step1Model model, Context context) {
        String categoryId = categoryProvider.getId(context);
        if (nonNull(categoryId)) {
            model.setCategoryAttributes(categoryAttributeUtils.getCategoryAttributesFromCategory(context, categoryProvider));
            model.setItemAttributes(categoryAttributeUtils.getItemAttributes(context));
            model.setVertical(getVertical(context));
        }
        model.setSiteId(ContextUtils.getSiteId(context));
    }

    private void addCategoryData(Step1Model model, Context context) {
        Category category = categoryUtils.getCategory(context, categoryProvider);
        model.setCategory(category);
        model.setCategoryId(categoryProvider.getId(context));
    }

    private void addCategorySelectionData(Step1Model model, Context context) {
        SellCatalogSelection selection = getSelection(context);

        model.setSellCatalogSelection(selection);
        model.setContextId(context.getId());
        model.setCatalogProductId(catalogProductProvider.getId(context));
//TODO: Ver como acoplar esto al modelo de la task
//        model.getFooterModel().withButtonConnection(CONTINUE_CONNECTION_ID);
//        model.getFooterModel().setShowFooter(isLeaf(category));
    }

    public SellCatalogSelection getSelection(Context context) {
        return catalogUtils.getFirstSellCatalogSelectionInModel(context).orElse(null);
    }

    /**
     * Helper for extracting the site from context.
     *
     * @param context
     * @return
     */
    protected Site getSite(Context context) {
        Site site = siteUtils.getSite(context);
        if (site == null) {
            throw new GraphNavigationException("Site is required to get the country.");
        }

        return site;
    }

    /**
     * Getter to obtain the country from context.
     *
     * @param context
     * @return
     */
    protected Country getCountry(Context context) {
        return getCountry(getSite(context));
    }

    /**
     * Get the country from site.
     *
     * @param site
     * @return
     */
    protected Country getCountry(Site site) {
        Country country = site.getCountry();
        if (country == null) {
            throw new GraphNavigationException("Country is required to obtain decimal separator.");
        }
        return country;
    }

    /**
     * Get the item's vertical.
     *
     * @param context
     * @return
     */
    protected Vertical getVertical(Context context) {
        final Category category = categoryUtils.getCategory(context, CategoryProvider.DATA_ITEM);
        return Vertical.getFromItemVertical(category.getSettings().getVertical());
    }
}
