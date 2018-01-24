package com.mercadolibre.conceptTest.data.finder;

import com.mercadolibre.conceptTest.data.InputComponent;

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
