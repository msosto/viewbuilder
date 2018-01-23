package com.mercadolibre.conceptTest.template.task;

import com.mercadolibre.conceptTest.todo.QuantityModel;

/**
 * Created by mforte on 1/15/18.
 */
public class QuantityTaskTemplate extends TaskTemplate<QuantityModel> {

    public QuantityTaskTemplate() {
        super(QuantityModel.class);
    }

    @Override
    protected String getTaskId() {
        return "QUANTITY";
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
        addChild().id("QUANTITY").uiType("QUANTITY");
        //TODO: Agregar el DataBuilder
    }
}
