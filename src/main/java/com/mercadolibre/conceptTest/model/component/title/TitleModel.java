package com.mercadolibre.conceptTest.model.component.title;

import com.mercadolibre.conceptTest.model.component.InputModel;

/**
 * Created by msosto on 1/10/18.
 */
public class TitleModel extends InputModel {

    private String title;

    public String getTitle() {
        return title;
    }

    public TitleModel setTitle(String title) {
        this.title = title;
        return this;
    }
}
