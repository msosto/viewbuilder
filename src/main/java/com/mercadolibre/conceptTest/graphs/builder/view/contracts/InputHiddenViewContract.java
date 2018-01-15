package com.mercadolibre.conceptTest.graphs.builder.view.contracts;

import com.mercadolibre.conceptTest.graphs.builder.view.InputComponent;

/**
 * Created by mlizarraga on 12/1/18
 */
public class InputHiddenViewContract extends InputComponent {

    private String output;
    private String value;

    public String getOutput() {
        return output;
    }

    public InputHiddenViewContract withOutput(String output) {
        this.output = output;
        return this;
    }

    public String getValue() {
        return value;
    }

    public InputHiddenViewContract withValue(String value) {
        this.value = value;
        return this;
    }
}
