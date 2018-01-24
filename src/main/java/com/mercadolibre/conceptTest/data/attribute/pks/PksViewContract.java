package com.mercadolibre.conceptTest.data.attribute.pks;

import com.mercadolibre.conceptTest.data.InputComponent;
import com.mercadolibre.conceptTest.data.attribute.AttributeInput;
import com.mercadolibre.flux.flow.graph.navigation.DataProxy;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by msosto on 1/11/18.
 */
public class PksViewContract extends InputComponent {

    private List<AttributeInput> pksAttributes;
    private String decimalSeparator;

    public PksViewContract setPksAttributes(List<DataProxy> attributes) {
        this.pksAttributes = getListOfAttributes(attributes);
        return this;
    }

    public PksViewContract setDecimalSeparator(String decimalSeparator) {
        this.decimalSeparator = decimalSeparator;
        return this;
    }

    private List<AttributeInput> getListOfAttributes(List<DataProxy> categoryAttributes) {
        return categoryAttributes.stream().map(AttributeInput::new).collect(Collectors.toList());
    }

}
