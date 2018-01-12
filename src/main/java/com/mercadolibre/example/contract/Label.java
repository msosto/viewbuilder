package com.mercadolibre.example.contract;

import com.mercadolibre.kisc.viewbuilder.Object;

/**
 * Created by abertolo on 03/01/18.
 */
public class Label implements Object {

    String text;

    public String getText() {
        return text;
    }

    public Label withText(String text) {
        this.text = text;
        return this;
    }
}
