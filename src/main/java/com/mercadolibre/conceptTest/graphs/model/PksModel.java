package com.mercadolibre.conceptTest.graphs.model;

import com.mercadolibre.flux.flow.graph.navigation.DataProxy;

import java.util.List;

/**
 * Created by msosto on 1/11/18.
 */
public class PksModel {

    List<DataProxy> pksAttributes;

    public List<DataProxy> getPksAttributes() {
        return pksAttributes;
    }

    public PksModel setPksAttributes(List<DataProxy> pksAttributes) {
        this.pksAttributes = pksAttributes;
        return this;
    }

}
