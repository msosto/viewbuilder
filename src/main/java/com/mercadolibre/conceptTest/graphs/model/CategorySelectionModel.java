package com.mercadolibre.conceptTest.graphs.model;

import com.mercadolibre.conceptTest.graphs.builder.view.contracts.category.BreadcrumbCategory;
import com.mercadolibre.conceptTest.graphs.builder.view.contracts.category.CategorySelectionColumn;
import com.mercadolibre.dto.Category;

import java.util.List;

/**
 * Created by mlizarraga on 11/1/18
 */
public class CategorySelectionModel extends TaskModel {

    private Category category;
    private String contextId;

    public CategorySelectionModel(){
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
}
