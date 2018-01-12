package com.mercadolibre.kisc.viewbuilder.template;

import java.util.List;
import java.util.function.Function;

/**
 * Created by abertolo on 04/01/18.
 */
public class TemplateBuilder<Model, OriginalModel, ParentOriginalModel> {

    final Template<Model, OriginalModel> template;

    final Class<Model> modelType;

    final Class<OriginalModel> originalModelType;

    final Class<ParentOriginalModel> parentOriginalModelType;

    private TemplateBuilder(Class<Model> modelType, Class<OriginalModel> originalModelType, Class<ParentOriginalModel> parentOriginalModelType) {
        this.modelType = modelType;
        this.originalModelType = originalModelType;
        this.parentOriginalModelType = parentOriginalModelType;
        this.template = new Template<Model, OriginalModel>();
    }


}
