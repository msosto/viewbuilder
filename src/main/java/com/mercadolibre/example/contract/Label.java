package com.mercadolibre.example.contract;

import com.mercadolibre.kisc.viewbuilder.ViewContract;

/**
 * Created by abertolo on 03/01/18.
 */
public class Label implements ViewContract {

    String text;

    public String getText() {
        return text;
    }

    public Label withText(String text) {
        this.text = text;
        return this;
    }
}
