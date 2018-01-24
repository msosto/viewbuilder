package com.mercadolibre.conceptTest.data.category.breadcrumb;

import java.util.List;

/**
 * Created by mlizarraga on 11/1/18
 */
public class CategoryBreadcrumbViewContract {

    private List<BreadcrumbCategory> categories;

    public List<BreadcrumbCategory> getCategories() {
        return categories;
    }

    public CategoryBreadcrumbViewContract withCategories(List<BreadcrumbCategory> categories) {
        this.categories = categories;
        return this;
    }

}
