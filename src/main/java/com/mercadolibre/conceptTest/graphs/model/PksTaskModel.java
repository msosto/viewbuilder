package com.mercadolibre.conceptTest.graphs.model;

import com.mercadolibre.conceptTest.graphs.model.component.CategoryAttributesProvider;
import com.mercadolibre.conceptTest.graphs.model.component.InputModel;
import com.mercadolibre.dto.Country;

/**
 * Created by mforte on 1/19/18.
 */
public interface PksTaskModel extends CategoryAttributesProvider {

    InputModel getPKsInputModel();

    String getCategoryId();

    Country getCountry();
}
