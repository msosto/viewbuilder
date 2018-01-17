package com.mercadolibre.conceptTest.graphs.builder.model;

import com.mercadolibre.conceptTest.graphs.builder.model.attribute.PksModelBuilder;
import com.mercadolibre.conceptTest.graphs.builder.model.category.CategorySelectionModelBuilder;
import com.mercadolibre.conceptTest.graphs.builder.model.finder.FinderModelBuilder;
import com.mercadolibre.conceptTest.graphs.model.Step1Model;
import com.mercadolibre.flux.flow.graph.navigation.Context;

/**
 * Created by msosto on 1/10/18.
 */
public class Step1ModelBuilder {

    private PksModelBuilder pksModelBuilder;
    private FinderModelBuilder finderModeBuilder;
    private CategorySelectionModelBuilder categorySelectionModelBuilder;

    public Step1ModelBuilder() {
        this.pksModelBuilder = new PksModelBuilder();
        this.finderModeBuilder = new FinderModelBuilder();
        this.categorySelectionModelBuilder = new CategorySelectionModelBuilder();
    }

    public Step1Model getModel(Context context) {
        Step1Model model = new Step1Model()
                .setFinderModel(finderModeBuilder.build(context))
                .setCategorySelectionModel(categorySelectionModelBuilder.build(context))
                .setPksModel(pksModelBuilder.build(context));
        return model;
    }

}
