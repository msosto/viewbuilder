package com.mercadolibre.conceptTest.model.component.pks;

import com.mercadolibre.conceptTest.model.component.InputModel;
import com.mercadolibre.conceptTest.model.component.InputModelBuilder;
import com.mercadolibre.flux.flow.graph.navigation.Context;

/**
 * Created by mforte on 1/23/18.
 */
public class PKsInputModelBuilder extends InputModelBuilder {

    public InputModel build(Context context) {
        InputModel model = new InputModel();
        model.setRequired(false);
        model.setDisabled(false);
        model.setValidationErrors(obtainValidationErrors(context, "pks_attribute"));
        return model;
    }
}
