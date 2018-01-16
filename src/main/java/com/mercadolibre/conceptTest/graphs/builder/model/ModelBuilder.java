package com.mercadolibre.conceptTest.graphs.builder.model;

import com.mercadolibre.flux.api.dto.InputComponentInstanceDTO;
import com.mercadolibre.flux.flow.graph.navigation.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by msosto on 1/16/18.
 */
public abstract class ModelBuilder {

    protected List<Map<String, Object>> obtainValidationErrors(Context context, String id){
        List<Map<String,Object>> validationErrors = new ArrayList<>();
        context.getStepProxy().getSingleStep().getComponent(id).getInstances().forEach(instance -> {
            List<Map<String, Object>> errors = ((InputComponentInstanceDTO) instance).getValidationErrors();
            validationErrors.addAll(errors);
        });
        return validationErrors;
    }
}