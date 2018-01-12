package com.mercadolibre.example.contract;


/**
 * Created by abertolo on 03/01/18.
 */
public class Label  {

    String text;

    public String getText() {
        return text;
    }

    public Label withText(String text) {
        this.text = text;
        return this;
    }
}
