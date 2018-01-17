package com.mercadolibre.conceptTest.template;

import com.mercadolibre.conceptTest.graphs.builder.data.category.CategoryBreadcrumbDataBuilder;
import com.mercadolibre.conceptTest.graphs.builder.data.category.CategorySelectionDataBuilder;
import com.mercadolibre.conceptTest.graphs.builder.data.category.InputHiddenDataBuilder;
import com.mercadolibre.conceptTest.graphs.model.CategorySelectionModel;

/**
 * Created by mforte on 1/15/18.
 */
public class CategorySelectionTaskTemplate extends TaskTemplate<CategorySelectionModel> {

    public static final String ITEM_CATEGORY_ID_OUTPUT = "item.category_id";
    public static final String ITEM_CATALOG_PRODUCT_ID_OUTPUT = "item.catalog_product_id";

    private CategorySelectionDataBuilder categorySelectionDataBuilder;
    private CategoryBreadcrumbDataBuilder categoryBreadcrumbDataBuilder;
    private InputHiddenDataBuilder inputHiddenDataBuilder;

    public CategorySelectionTaskTemplate() {
        super(CategorySelectionModel.class);
        categorySelectionDataBuilder = new CategorySelectionDataBuilder();
        categoryBreadcrumbDataBuilder = new CategoryBreadcrumbDataBuilder();
        inputHiddenDataBuilder = new InputHiddenDataBuilder();
    }


    @Override
    protected String getTaskId() {
        return "CATEGORY_SELECTION";
    }

    @Override
    protected void addBody() {
        addChild()
                .id("BREADCRUMB")
                .uiType("BREADCRUMB")
                //TODO Lógica de showComponent
                .apply(categorySelectionModel -> true)
                .dataBuilder(categorySelectionModel -> categoryBreadcrumbDataBuilder.build(categorySelectionModel));
        addChild()
                .id("CATEGORY_SELECTION")
                .uiType("CATEGORY_SELECTION")
                //TODO Lógica de showComponent
                .apply(categorySelectionModel -> true)
                .dataBuilder(categorySelectionModel -> categorySelectionDataBuilder.build(categorySelectionModel));
        addChild()
                .id("HIDDEN_CATEGORY_ID")
                .uiType("HIDDEN").dataBuilder(categorySelectionModel -> inputHiddenDataBuilder.build(ITEM_CATEGORY_ID_OUTPUT, categorySelectionModel.getCategoryId()));

        addChild()
                .id("HIDDEN_CATALOG_PRODUCT_ID")
                .uiType("HIDDEN").dataBuilder(categorySelectionModel -> inputHiddenDataBuilder.build(ITEM_CATALOG_PRODUCT_ID_OUTPUT, categorySelectionModel.getCatalogProductId()));

    }
}
