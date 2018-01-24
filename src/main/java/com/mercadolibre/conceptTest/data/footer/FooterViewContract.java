package com.mercadolibre.conceptTest.data.footer;

/**
 * Created by mlizarraga on 11/1/18
 */
public class FooterViewContract {

    private String buttonText;
    private String buttonConnection;

    public String getButtonText() {
        return buttonText;
    }

    public FooterViewContract withButtonText(String buttonText) {
        this.buttonText = buttonText;
        return this;
    }

    public String getButtonConnection() {
        return buttonConnection;
    }

    public FooterViewContract withButtonConnection(String buttonConnection) {
        this.buttonConnection = buttonConnection;
        return this;
    }

}
