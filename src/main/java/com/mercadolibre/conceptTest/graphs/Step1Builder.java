package com.mercadolibre.conceptTest.graphs;

import com.mercadolibre.conceptTest.graphs.builder.model.Step1ModelBuilder;
import com.mercadolibre.conceptTest.graphs.builder.view.Step1ViewBuilder;
import com.mercadolibre.conceptTest.graphs.model.Step1Model;
import com.mercadolibre.flux.flow.action.Action;
import com.mercadolibre.flux.flow.exception.GraphNavigationException;
import com.mercadolibre.flux.flow.graph.navigation.Context;
import com.mercadolibre.flux.flow.graph.relationship.Achieve;
import com.mercadolibre.kisc.viewbuilder.Component;

/**
 * Created by msosto on 1/10/18.
 */
public class Step1Builder extends Action {

    public Step1Builder(Context context, Achieve achieve, Setter setter) {
        super(context, achieve, setter);
    }

    @Override
    protected void runAction() throws GraphNavigationException {
        Step1Model step1Model = new Step1ModelBuilder().getModel(context);
        Component step1View = new Step1ViewBuilder().build(step1Model);
        //TODO Que hacemos con este component?
    }
}
