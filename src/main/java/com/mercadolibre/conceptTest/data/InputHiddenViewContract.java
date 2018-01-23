package com.mercadolibre.conceptTest.data;

/**
 * Created by mlizarraga on 12/1/18
 */
public class InputHiddenViewContract extends InputComponent {

    private Object value;

    public Object getValue() {
        return value;
    }

    public InputHiddenViewContract withValue(Object value) {
        this.value = value;
        return this;
    }
}
