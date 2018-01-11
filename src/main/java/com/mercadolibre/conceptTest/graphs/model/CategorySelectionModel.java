package com.mercadolibre.conceptTest.graphs.model;

import com.mercadolibre.conceptTest.graphs.builder.view.contracts.category.BreadcrumbCategory;
import com.mercadolibre.conceptTest.graphs.builder.view.contracts.category.CategorySelectionColumn;

import java.util.List;

/**
 * Created by mlizarraga on 11/1/18
 */
public class CategorySelectionModel extends TaskModel {

    private CategorySelectionColumn column;
    private Boolean adultContent;
    private Boolean leaf;
    private String categoryId;
    private String catalogProductId;
    private List<BreadcrumbCategory> breadcrumbCategories;
    private boolean showCategorySelectionComponent;
    private boolean showCategoryBreadcrumbComponent;

    public CategorySelectionColumn getColumn() {
        return column;
    }

    public CategorySelectionModel withColumn(CategorySelectionColumn column) {
        this.column = column;
        return this;
    }

    public Boolean getAdultContent() {
        return adultContent;
    }

    public CategorySelectionModel withAdultContent(Boolean adultContent) {
        this.adultContent = adultContent;
        return this;
    }

    public Boolean getLeaf() {
        return leaf;
    }

    public CategorySelectionModel setLeaf(Boolean leaf) {
        this.leaf = leaf;
        return this;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public CategorySelectionModel withCategoryId(String categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    public String getCatalogProductId() {
        return catalogProductId;
    }

    public CategorySelectionModel withCatalogProductId(String catalogProductId) {
        this.catalogProductId = catalogProductId;
        return this;
    }

    public boolean isShowCategorySelectionComponent() {
        return showCategorySelectionComponent;
    }

    public CategorySelectionModel withShowCategorySelectionComponent(boolean showCategorySelectionComponent) {
        this.showCategorySelectionComponent = showCategorySelectionComponent;
        return this;
    }

    public boolean isShowCategoryBreadcrumbComponent() {
        return showCategoryBreadcrumbComponent;
    }

    public CategorySelectionModel withShowCategoryBreadcrumbComponent(boolean showCategoryBreadcrumbComponent) {
        this.showCategoryBreadcrumbComponent = showCategoryBreadcrumbComponent;
        return this;
    }

    public List<BreadcrumbCategory> getBreadcrumbCategories() {
        return breadcrumbCategories;
    }

    public void withBreadcrumbCategories(List<BreadcrumbCategory> breadcrumbCategories) {
        this.breadcrumbCategories = breadcrumbCategories;
    }
}
