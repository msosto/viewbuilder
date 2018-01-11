package com.mercadolibre.conceptTest.graphs.model;

/**
 * Created by msosto on 1/10/18.
 */
public class Step1Model {

    FinderModel finderModel;
    PksModel pksModel;

    public Step1Model() {
        this.finderModel = new FinderModel(); // TODO: quitar esta instancia de aca
    }


    public FinderModel getFinderModel() { return finderModel;  }

    public Step1Model setFinderModel(FinderModel finderModel) {  this.finderModel = finderModel;
        return this;
    }

    public PksModel getPksModel() { return pksModel;  }

    public Step1Model setPksModel(PksModel pksModel) {
        this.pksModel = pksModel;
        return this;
    }

}
