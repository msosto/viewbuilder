package com.mercadolibre.conceptTest.graphs.builder.view.contracts.attribute.pks;

import com.mercadolibre.conceptTest.graphs.builder.view.InputComponent;
import com.mercadolibre.conceptTest.graphs.builder.view.contracts.attribute.AttributeInput;
import com.mercadolibre.flux.flow.graph.navigation.DataProxy;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by msosto on 1/11/18.
 */
public class PksInput extends InputComponent {

    private List<AttributeInput> pksAttributes;
    private String decimalSeparator;

    public PksInput withPksAttributes(List<DataProxy> attributes) {
        this.pksAttributes = getListOfAttributes(attributes);
        return this;
    }

    public PksInput withDecimalSeparator(String decimalSeparator) {
        this.decimalSeparator = decimalSeparator;
        return this;
    }

    private List<AttributeInput> getListOfAttributes(List<DataProxy> categoryAttributes) {
        return categoryAttributes.stream().map(AttributeInput::new).collect(Collectors.toList());
    }

}
