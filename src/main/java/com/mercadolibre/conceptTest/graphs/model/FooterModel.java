package com.mercadolibre.conceptTest.graphs.model;

import com.mercadolibre.conceptTest.graphs.builder.view.contracts.FooterViewContract;

/**
 * Created by mlizarraga on 11/1/18
 */
public class FooterModel {

    private String buttonText;
    private String buttonConnection;

    public String getButtonText() {
        return buttonText;
    }

    public FooterModel withButtonText(String buttonText) {
        this.buttonText = buttonText;
        return this;
    }

    public String getButtonConnection() {
        return buttonConnection;
    }

    public FooterModel withButtonConnection(String buttonConnection) {
        this.buttonConnection = buttonConnection;
        return this;
    }
}
