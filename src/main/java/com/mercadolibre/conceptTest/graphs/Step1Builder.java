package com.mercadolibre.conceptTest.graphs;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mercadolibre.conceptTest.graphs.builder.model.Step1ModelBuilder;
import com.mercadolibre.conceptTest.graphs.model.Step1Model;
import com.mercadolibre.conceptTest.template.Step1Template;
import com.mercadolibre.flux.flow.action.Action;
import com.mercadolibre.flux.flow.exception.GraphNavigationException;
import com.mercadolibre.flux.flow.graph.navigation.Context;
import com.mercadolibre.flux.flow.graph.relationship.Achieve;
import com.mercadolibre.kisc.viewbuilder.Component;

/**
 * Created by msosto on 1/10/18.
 */
public class Step1Builder extends Action {

    final Gson gson = new GsonBuilder().create();

    public Step1Builder(Context context, Achieve achieve) {
        super(context, achieve, getDefaultThreadSetter("Step1BuilderGroup"));
    }

    @Override
    protected void runAction() throws GraphNavigationException {
        final Step1Template step1Template = new Step1Template();

        Step1Model step1Model = new Step1ModelBuilder().getModel(context);
        Component step1View = step1Template.build(step1Model);
        context.getStepProxy().getSingleStep().setData(Maps.newHashMap());
    }
}
