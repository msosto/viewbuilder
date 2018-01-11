package com.mercadolibre.conceptTest.graphs.builder.model;

import com.mercadolibre.conceptTest.graphs.model.Step1Model;
import com.mercadolibre.flux.flow.graph.navigation.Context;

/**
 * Created by msosto on 1/10/18.
 */
public class Step1ModelBuilder {

    private CategorySelectionModelBuilder categorySelectionModelBuilder;

    public Step1ModelBuilder() {
        this.categorySelectionModelBuilder = new CategorySelectionModelBuilder();
    }

    public Step1Model getModel(Context context) {
        Step1Model step1Model = new Step1Model()
                .withCategorySelectionModel(categorySelectionModelBuilder.build(context));
        return step1Model;
    }

}
