package com.mercadolibre.conceptTest.graphs.model;

/**
 * Created by mlizarraga on 11/1/18
 */
public class FooterModel {

    private String buttonText;
    private String buttonConnection;
    private boolean showFooter = true;

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

    public FooterModel setShowFooter(boolean showFooter){
        this.showFooter = showFooter;
        return this;
    }

    public boolean show() {
        return showFooter;
    }
}
