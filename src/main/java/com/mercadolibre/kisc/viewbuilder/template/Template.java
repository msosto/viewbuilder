package com.mercadolibre.kisc.viewbuilder.template;

import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * Created by abertolo on 29/12/17.
 */
public class Template<VC, Model> {

    String id;

    String type;

    Class<VC> dataType;

    Function<Model, Boolean> apply;

    BiConsumer<Model, VC> mapper;


    public String getId() {
        return id;
    }

    public Template withId(String id) {
        this.id = id;
        return this;
    }

    public String getType() {
        return type;
    }

    public Template withType(String type) {
        this.type = type;
        return this;
    }

    public Class<VC> getDataType() {
        return dataType;
    }

    public Template withDataType(Class<VC> dataType) {
        this.dataType = dataType;
        return this;
    }

    public Function<Model, Boolean> getApply() {
        return apply;
    }

    public Template withApply(Function<Model, Boolean> apply) {
        this.apply = apply;
        return this;
    }

    public BiConsumer<Model, VC> getMapper() {
        return mapper;
    }

    public Template withMapper(BiConsumer<Model, VC> mapper) {
        this.mapper = mapper;
        return this;
    }
}
