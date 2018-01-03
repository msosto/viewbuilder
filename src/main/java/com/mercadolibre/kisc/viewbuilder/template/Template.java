package com.mercadolibre.kisc.viewbuilder.template;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

/**
 * Created by abertolo on 29/12/17.
 */
public class Template<VC, Model> {

    String uiType;

    Optional<String> id;

    Optional<Function<Model, String>> idBuilder;

    Optional<Function<Model, Boolean>> apply;

    Optional<Function<Model, VC>> mapper;

    Optional<Function<Object, Model>> transform;

    Optional<Function<Object, List<Model>>> transformToList;


    List<Template> templates;

    public Template(String uiType) {
        this.uiType = uiType;

        templates = new ArrayList<>();
        apply = Optional.empty();
        mapper = Optional.empty();
        idBuilder = Optional.empty();
        id = Optional.empty();
        transform = Optional.empty();
        transformToList = Optional.empty();
    }

    public List<Template> getTemplates() {
        return templates;
    }


    public Template add(Template template) {
        templates.add(template);
        return this;
    }

    public Optional<String> getId() {
        return id;
    }

    public Template withId(String id) {
        this.id = Optional.ofNullable(id);
        return this;
    }

    public String getUiType() {
        return uiType;
    }


    public Optional<Function<Model, Boolean>> getApply() {
        return apply;
    }

    public Template withApply(Function<Model, Boolean> apply) {
        this.apply = Optional.ofNullable(apply);
        return this;
    }

    public Optional<Function<Model, VC>> getMapper() {
        return mapper;
    }

    public Template withMapper(Function<Model, VC> mapper) {
        this.mapper = Optional.ofNullable(mapper);
        return this;
    }

    public Optional<Function<Model, String>> getIdBuilder() {
        return idBuilder;
    }

    public Template withIdBuilder(Function<Model, String> idBuilder) {
        this.idBuilder = Optional.ofNullable(idBuilder);
        return this;
    }

    public Optional<Function<Object, Model>> getTransform() {
        return transform;
    }

    public Template withTransform(Function<Object, Model> transform) {
        this.transform = Optional.ofNullable(transform);
        return this;
    }

    public Optional<Function<Object, List<Model>>> getTransformToList() {
        return transformToList;
    }

    public Template withTransformToList(Function<Object, List<Model>> transformToList) {
        this.transformToList = Optional.ofNullable(transformToList);
        return this;
    }
}
