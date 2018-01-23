package com.mercadolibre.conceptTest.template;

import com.mercadolibre.conceptTest.graphs.model.component.VariationAttributesModel;

/**
 * Created by mforte on 1/15/18.
 */
public class SingleVariationTaskTemplate extends TaskTemplate<VariationAttributesModel> {

    public SingleVariationTaskTemplate() {
        super(VariationAttributesModel.class);
    }

    @Override
    protected String getTaskId() {
        return "SINGLE_VARIATION";
    }

    @Override
    protected String getHeaderUIType() {
        return null;
    }

    @Override
    protected String getFooterUIType() {
        return null;
    }

    @Override
    protected void addBody() {
        addChild().id("SINGLE_VARIATION").uiType("SINGLE_VARIATION");
        //TODO: Agregar el DataBuilder
    }
}
