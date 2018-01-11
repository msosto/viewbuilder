package com.mercadolibre.conceptTest.graphs.builder.view.contracts.category;

import com.mercadolibre.kisc.viewbuilder.ViewContract;

import java.util.List;

/**
 * Created by mlizarraga on 11/1/18
 */
public class CategoryBreadcrumbViewContract implements ViewContract {

    private List<Category> categories;

    public List<Category> getCategories() {
        return categories;
    }

    public CategoryBreadcrumbViewContract withCategories(List<Category> categories) {
        this.categories = categories;
        return this;
    }

}
