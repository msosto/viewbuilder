package com.mercadolibre.conceptTest.graphs.model;

import com.mercadolibre.dto.Category;
import com.mercadolibre.dto.catalog.SellCatalogSelection;

/**
 * Created by mlizarraga on 11/1/18
 */
public class CategorySelectionModel extends TaskModel {

    private Category category;
    private SellCatalogSelection sellCatalogSelection;
    private String contextId;
    private String categoryId;
    private String catalogProductId;

    public CategorySelectionModel() {
        super();
    }

    public Category getCategory() {
        return category;
    }

    public CategorySelectionModel setCategory(Category category) {
        this.category = category;
        return this;
    }

    public String getContextId() {
        return contextId;
    }

    public CategorySelectionModel setContextId(String contextId) {
        this.contextId = contextId;
        return this;
    }

    public SellCatalogSelection getSellCatalogSelection() {
        return sellCatalogSelection;
    }

    public CategorySelectionModel setSellCatalogSelection(SellCatalogSelection sellCatalogSelection) {
        this.sellCatalogSelection = sellCatalogSelection;
        return this;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public CategorySelectionModel setCategoryId(String categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    public String getCatalogProductId() {
        return catalogProductId;
    }

    public CategorySelectionModel setCatalogProductId(String catalogProductId) {
        this.catalogProductId = catalogProductId;
        return this;
    }

}
