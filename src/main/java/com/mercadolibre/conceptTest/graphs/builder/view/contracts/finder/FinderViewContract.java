package com.mercadolibre.conceptTest.graphs.builder.view.contracts.finder;

import com.mercadolibre.conceptTest.graphs.builder.view.InputComponent;

/**
 * Created by msosto on 1/10/18.
 */
public class FinderViewContract extends InputComponent {

    String value;

    public FinderViewContract withValue(String title) {
        this.value = title;
        return this;
    }
}
