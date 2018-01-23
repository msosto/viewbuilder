package com.mercadolibre.conceptTest.model.component;

import com.mercadolibre.flux.api.dto.InputComponentInstanceDTO;
import com.mercadolibre.flux.flow.graph.navigation.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.Objects.nonNull;

/**
 * Created by msosto on 1/17/18.
 */
public abstract class InputModelBuilder {

    protected List<Map<String, Object>> obtainValidationErrors(Context context, String id) {
        List<Map<String, Object>> validationErrors = new ArrayList<>();
        context.getStepProxy().getSingleStep().getComponent(id).getInstances().forEach(instance -> {
            List<Map<String, Object>> errors = ((InputComponentInstanceDTO) instance).getValidationErrors();
            if (nonNull(errors))
                validationErrors.addAll(errors);
        });
        return validationErrors;
    }

}
