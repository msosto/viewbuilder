package com.mercadolibre.conceptTest.template;

import com.mercadolibre.conceptTest.graphs.builder.data.category.CategoryBreadcrumbDataBuilder;
import com.mercadolibre.conceptTest.graphs.builder.data.category.CategorySelectionDataBuilder;
import com.mercadolibre.conceptTest.graphs.builder.data.category.InputHiddenDataBuilder;
import com.mercadolibre.conceptTest.graphs.model.component.CategorySelectionTaskModel;
import com.mercadolibre.dto.Category;
import com.mercadolibre.dto.catalog.SellCatalogSelection;
import org.apache.commons.collections.CollectionUtils;

import static java.util.Objects.nonNull;

/**
 * Created by mforte on 1/15/18.
 */
public class CategorySelectionTaskTemplate extends TaskTemplate<CategorySelectionTaskModel> {

    public static final String ITEM_CATEGORY_ID_OUTPUT = "item.category_id";
    public static final String ITEM_CATALOG_PRODUCT_ID_OUTPUT = "item.catalog_product_id";

    private CategorySelectionDataBuilder categorySelectionDataBuilder;
    private CategoryBreadcrumbDataBuilder categoryBreadcrumbDataBuilder;
    private InputHiddenDataBuilder inputHiddenDataBuilder;

    public CategorySelectionTaskTemplate() {
        super(CategorySelectionTaskModel.class);
        categorySelectionDataBuilder = new CategorySelectionDataBuilder();
        categoryBreadcrumbDataBuilder = new CategoryBreadcrumbDataBuilder();
        inputHiddenDataBuilder = new InputHiddenDataBuilder();
    }

    @Override
    protected String getTaskId() {
        return "CATEGORY_SELECTION";
    }

    @Override
    protected String getHeaderUIType() {
        return HEADER_UI_TYPE;
    }

    @Override
    protected String getFooterUIType() {
        return FOOTER_IN_UI_TYPE;
    }

    @Override
    protected void addBody() {
        addChild()
                .id("BREADCRUMB")
                .uiType("BREADCRUMB")
                .apply(this::showCategoryBreadcrumb)
                .dataBuilder(categorySelectionModel -> categoryBreadcrumbDataBuilder.build(categorySelectionModel.getCategory(), categorySelectionModel.getContextId()));
        addChild()
                .id("CATEGORY_SELECTION")
                .uiType("CATEGORY_SELECTION")
                .apply(this::showCategorySelection)
                .dataBuilder(categorySelectionModel -> categorySelectionDataBuilder.build(categorySelectionModel));
        addChild()
                .id("HIDDEN_CATEGORY_ID")
                .uiType("HIDDEN")
                .apply(categorySelectionModel -> isLeaf(categorySelectionModel.getSellCatalogSelection()))
                .dataBuilder(categorySelectionModel -> inputHiddenDataBuilder.build(ITEM_CATEGORY_ID_OUTPUT, categorySelectionModel.getCategoryId()));
        addChild()
                .id("HIDDEN_CATALOG_PRODUCT_ID")
                .uiType("HIDDEN")
                .apply(categorySelectionModel -> isLeaf(categorySelectionModel.getSellCatalogSelection()))
                .dataBuilder(categorySelectionModel -> inputHiddenDataBuilder.build(ITEM_CATALOG_PRODUCT_ID_OUTPUT, categorySelectionModel.getCatalogProductId()));

    }

    private boolean showCategoryBreadcrumb(CategorySelectionTaskModel categorySelectionTaskModel) {
        Category category = categorySelectionTaskModel.getCategory();
        return nonNull(category) && !CollectionUtils.isEmpty(categorySelectionTaskModel.getCategory().getPathFromRoot());
    }

    private boolean showCategorySelection(CategorySelectionTaskModel categorySelectionTaskModel) {
        return !isLeaf(categorySelectionTaskModel.getSellCatalogSelection());
    }

    private boolean isLeaf(SellCatalogSelection selection) {
        return nonNull(selection) && Boolean.TRUE.equals(selection.getShouldContinue());
    }

    public boolean isLeaf(Category category) {
        return nonNull(category) && !category.isLeaf();
    }
}
