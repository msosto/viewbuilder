package com.mercadolibre.conceptTest.graphs.builder.model;

import com.mercadolibre.conceptTest.graphs.builder.model.attribute.pks.PksModelBuilder;
import com.mercadolibre.conceptTest.graphs.builder.model.finder.FinderModelBuilder;
import com.mercadolibre.conceptTest.graphs.model.Step1Model;
import com.mercadolibre.flux.flow.graph.navigation.Context;

/**
 * Created by msosto on 1/10/18.
 */
public class Step1ModelBuilder {

    private PksModelBuilder pksModelBuilder;
    private FinderModelBuilder finderModeBuilder;

    public Step1ModelBuilder() {
        pksModelBuilder = new PksModelBuilder();
        finderModeBuilder = new FinderModelBuilder();
    }

    public Step1Model getModel(Context context) {
        Step1Model model = new Step1Model();
        model.setPksModel(pksModelBuilder.build(context));
        model.setFinderModel(finderModeBuilder.build(context));
        return model;
    }

}
