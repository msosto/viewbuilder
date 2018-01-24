package com.mercadolibre.conceptTest.data.category.selection;

/**
 * Created by mlizarraga on 11/1/18
 */
public class CategorySelectionViewContract {

    private CategorySelectionColumn column;
    private Boolean adultContent;
    private String categoryId;
    private String catalogProductId;

    public CategorySelectionColumn getColumn() {
        return column;
    }

    public CategorySelectionViewContract withColumn(CategorySelectionColumn column) {
        this.column = column;
        return this;
    }

    public Boolean getAdultContent() {
        return adultContent;
    }

    public CategorySelectionViewContract withAdultContent(Boolean adultContent) {
        this.adultContent = adultContent;
        return this;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public CategorySelectionViewContract withCategoryId(String categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    public String getCatalogProductId() {
        return catalogProductId;
    }

    public CategorySelectionViewContract withCatalogProductId(String catalogProductId) {
        this.catalogProductId = catalogProductId;
        return this;
    }
}
