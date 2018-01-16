package com.mercadolibre.conceptTest.template;

import com.mercadolibre.conceptTest.graphs.builder.view.contracts.InputHiddenViewContract;
import com.mercadolibre.conceptTest.graphs.builder.view.contracts.category.CategoryBreadcrumbViewContract;
import com.mercadolibre.conceptTest.graphs.builder.view.contracts.category.CategorySelectionViewContract;
import com.mercadolibre.conceptTest.graphs.model.CategorySelectionModel;

/**
 * Created by mforte on 1/15/18.
 */
public class CategorySelectionTaskTemplate extends TaskTemplate<CategorySelectionModel> {

    public CategorySelectionTaskTemplate() {
        super(CategorySelectionModel.class);
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
                .apply(CategorySelectionModel::isShowCategoryBreadcrumbComponent)
                .dataBuilder(categorySelectionModel ->
                        new CategoryBreadcrumbViewContract().withCategories(categorySelectionModel.getBreadcrumbCategories())
                );
        addChild()
                .id("CATEGORY_SELECTION")
                .uiType("CATEGORY_SELECTION")
                .apply(CategorySelectionModel::isShowCategorySelectionComponent)
                .dataBuilder(categorySelectionModel ->
                        new CategorySelectionViewContract()
                                .withColumn(categorySelectionModel.getColumn())
                                .withAdultContent(categorySelectionModel.getAdultContent()));
        addChild()
                .id("HIDDEN_CATEGORY_ID")
                .uiType("HIDDEN").dataBuilder(categorySelectionModel ->
                new InputHiddenViewContract()
                        .withValue(categorySelectionModel.getCategoryId())
                        .withOutput(CategorySelectionModel.ITEM_CATEGORY_ID_OUTPUT));
        addChild()
                .id("HIDDEN_CATALOG_PRODUCT_ID")
                .uiType("HIDDEN").dataBuilder(categorySelectionModel ->
                new InputHiddenViewContract()
                        .withValue(categorySelectionModel.getCatalogProductId())
                        .withOutput(CategorySelectionModel.ITEM_CATALOG_PRODUCT_ID_OUTPUT));
    }
}
