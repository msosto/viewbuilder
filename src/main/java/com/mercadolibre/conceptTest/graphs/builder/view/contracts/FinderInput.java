package com.mercadolibre.conceptTest.graphs.builder.view.contracts;

import com.mercadolibre.kisc.viewbuilder.ViewContract;

/**
 * Created by msosto on 1/10/18.
 */
public class FinderInput implements ViewContract {

    String value;

    public FinderInput withValue(String title) {
        this.value = title;
        return this;
    }
}
