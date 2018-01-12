package com.mercadolibre.example.contract;

import com.mercadolibre.kisc.viewbuilder.Object;

/**
 * Created by abertolo on 03/01/18.
 */
public class ActionButton implements Object {

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
