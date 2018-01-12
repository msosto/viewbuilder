package com.mercadolibre.example.contract;


/**
 * Created by abertolo on 03/01/18.
 */
public class ActionButton {

    String label;
    String link;

    public String getLabel() {
        return label;
    }

    public ActionButton withLabel(String label) {
        this.label = label;
        return this;
    }

    public String getLink() {
        return link;
    }

    public ActionButton withLink(String link) {
        this.link = link;
        return this;
    }
}
