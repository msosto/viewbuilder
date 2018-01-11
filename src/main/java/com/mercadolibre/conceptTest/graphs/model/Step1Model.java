package com.mercadolibre.conceptTest.graphs.model;

/**
 * Created by msosto on 1/10/18.
 */
public class Step1Model {

    public Step1Model() {
        this.finderModel = new FinderModel();
    }

    FinderModel finderModel;

    public FinderModel getFinderModel() {
        return finderModel;
    }

}
