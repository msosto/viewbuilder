package com.mercadolibre.conceptTest.graphs.builder.model.category;

import com.mercadolibre.ActionsModule;
import com.mercadolibre.actions.CatalogUtils;
import com.mercadolibre.actions.CategoryUtils;
import com.mercadolibre.conceptTest.graphs.model.CategorySelectionModel;
import com.mercadolibre.dto.Category;
import com.mercadolibre.dto.catalog.SellCatalogSelection;
import com.mercadolibre.flux.flow.graph.navigation.Context;
import com.mercadolibre.util.providers.CatalogProductProvider;
import com.mercadolibre.util.providers.CategoryProvider;

import static java.util.Objects.nonNull;

/**
 * Created by mlizarraga on 11/1/18
 */
public class CategorySelectionModelBuilder {

    public static final String HEADER_TITLE = "CATEGORY_SELECTION_HEADER_TITLE";
    public static final String FOOTER_BUTTON_TEXT = "CATEGORY_SELECTION_FOOTER_BUTTON_TEXT";
    //TODO Mover a un builder abstracto o a una clase mas generica.
    public static final String CONTINUE_CONNECTION_ID = "continue";

    private CategorySelectionModel model;
    private CategoryUtils categoryUtils;
    private CatalogUtils catalogUtils;

    public CategorySelectionModelBuilder() {
        model = new CategorySelectionModel();
        categoryUtils = ActionsModule.get().getInstance(CategoryUtils.class);
        catalogUtils = ActionsModule.get().getInstance(CatalogUtils.class);
    }

    public CategorySelectionModel build(Context context) {
        Category category = categoryUtils.getCategory(context, CategoryProvider.DATA_ITEM);
        SellCatalogSelection selection = getSelection(context);
        model.setCategory(category);
        model.setSellCatalogSelection(selection);
        model.setContextId(context.getId());
        model.setCategoryId(CategoryProvider.DATA_ITEM.getId(context));
        model.setCatalogProductId(CatalogProductProvider.DATA_ITEM.getId(context));
        model.getHeaderModel().withTitle(HEADER_TITLE);
        model.getFooterModel().withButtonText(FOOTER_BUTTON_TEXT);
        model.getFooterModel().withButtonConnection(CONTINUE_CONNECTION_ID);
        model.getFooterModel().setShowFooter(isLeaf(category));
        return model;
    }

    public SellCatalogSelection getSelection(Context context) {
        return catalogUtils.getFirstSellCatalogSelectionInModel(context).orElse(null);
    }

    public boolean isLeaf(Category category) {
        return nonNull(category) && !category.isLeaf();
    }
}