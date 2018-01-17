package com.mercadolibre.conceptTest.graphs.builder.data.category;

import com.mercadolibre.conceptTest.graphs.builder.view.contracts.InputHiddenViewContract;

/**
 * Created by mlizarraga on 16/1/18
 */
public class InputHiddenDataBuilder {

    public InputHiddenViewContract build(String output, String value) {
        InputHiddenViewContract viewContract = new InputHiddenViewContract();
        viewContract.withOutput(output);
        viewContract.withValue(value);
        return viewContract;
    }
}
