package com.mercadolibre.conceptTest.graphs.builder.view.contracts.finder;

import com.mercadolibre.kisc.viewbuilder.ViewContract;

/**
 * Created by msosto on 1/10/18.
 */
public class FinderViewContract implements ViewContract {

    String value;

    public FinderViewContract withValue(String title) {
        this.value = title;
        return this;
    }
}
