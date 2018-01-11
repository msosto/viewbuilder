package com.mercadolibre.conceptTest.graphs.builder.view.contracts.category;

import com.mercadolibre.kisc.viewbuilder.ViewContract;

/**
 * Created by mlizarraga on 11/1/18
 */
public class CategorySelectionViewContract implements ViewContract {

    private CategorySelectionColumn column;
    private Boolean adultContent;

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

    public CategorySelectionViewContract setAdultContent(Boolean adultContent) {
        this.adultContent = adultContent;
        return this;
    }
}
