package com.mercadolibre.conceptTest.graphs.model;

/**
 * Created by msosto on 1/10/18.
 */
public class Step1Model {

    public Step1Model() {
        this.finderModel = new FinderModel();
        this.categorySelectionModel = new CategorySelectionModel();
    }

    FinderModel finderModel;
    CategorySelectionModel categorySelectionModel;

    public FinderModel getFinderModel() {
        return finderModel;
    }

    public CategorySelectionModel getCategorySelectionModel() {
        return categorySelectionModel;
    }

    public Step1Model withCategorySelectionModel(CategorySelectionModel categorySelectionModel) {
        this.categorySelectionModel = categorySelectionModel;
        return this;
    }
}
