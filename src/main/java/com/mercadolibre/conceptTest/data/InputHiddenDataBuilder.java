package com.mercadolibre.conceptTest.data;

/**
 * Created by mlizarraga on 16/1/18
 */
public class InputHiddenDataBuilder {

    public InputHiddenViewContract build(String output, Object value) {
        InputHiddenViewContract viewContract = new InputHiddenViewContract();
        viewContract.withOutput(output);
        viewContract.withValue(value);
        return viewContract;
    }
}
