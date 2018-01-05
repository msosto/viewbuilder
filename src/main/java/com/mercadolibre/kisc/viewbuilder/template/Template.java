package com.mercadolibre.kisc.viewbuilder.template;

import com.mercadolibre.kisc.viewbuilder.Component;
import com.mercadolibre.kisc.viewbuilder.ViewBuilder;
import com.mercadolibre.kisc.viewbuilder.ViewContract;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

/**
 * Created by abertolo on 29/12/17.
 */
public class Template<Model, OriginalModel> {

    final ViewBuilder<OriginalModel> viewBuilder;

    final Class<Model> modelType;

    final Class<OriginalModel> originalModelType;

    final String templateId;

    final List<Template> templates;

    Optional<String> id;

    Optional<String> uiType;

    Optional<Function<Model, String>> idBuilder;

    Optional<Function<OriginalModel, Boolean>> apply;

    Optional<Function<Model, ViewContract>> mapper;

    Optional<Function<OriginalModel, Model>> transform;

    Optional<Function<OriginalModel, List<Model>>> spread;


    public Template(ViewBuilder<OriginalModel> viewBuilder, Class<Model> modelType, Class<OriginalModel> originalModelType, String templateId) {
        this.viewBuilder = viewBuilder;
        this.modelType = modelType;
        this.originalModelType = originalModelType;
        this.templateId = templateId;

        templates = new ArrayList<>();
        idBuilder = Optional.empty();
        id = Optional.empty();
        uiType = Optional.empty();
        apply = Optional.empty();
        mapper = Optional.empty();
        transform = Optional.empty();
        spread = Optional.empty();
    }

    public String getTemplateId() {
        return templateId;
    }

    public List<Template> getTemplates() {
        return templates;
    }

    public Template<Model, OriginalModel> add(Template template) {
        templates.add(template);
        return this;
    }


    public Template<Model, OriginalModel> id(String id) {
        this.id = Optional.ofNullable(id);
        return this;
    }

    public Template<Model, OriginalModel> uiType(String uiType) {
        this.uiType = Optional.ofNullable(uiType);
        return this;
    }

    public Template<Model, OriginalModel> apply(Function<OriginalModel, Boolean> apply) {
        this.apply = Optional.ofNullable(apply);
        return this;
    }

    public Template<Model, OriginalModel> mapper(Function<Model, ViewContract> mapper) {
        this.mapper = Optional.ofNullable(mapper);
        return this;
    }

    public Template<Model, OriginalModel> idBuilder(Function<Model, String> idBuilder) {
        this.idBuilder = Optional.ofNullable(idBuilder);
        return this;
    }

    public Template<Model, OriginalModel> transform(Function<OriginalModel, Model> transform) {
        this.transform = Optional.ofNullable(transform);
        return this;
    }

    public Template<Model, OriginalModel> spread(Function<OriginalModel, List<Model>> spread) {
        this.spread = Optional.ofNullable(spread);
        return this;
    }

    public Optional<List<Component>> toComponents(final OriginalModel model) {
        final Boolean shouldApply = apply
                .map(f -> f.apply(model))
                .orElse(true);

        if (shouldApply) {
            List<Component> components = new ArrayList<>();

            List<Model> models = getModels(model);

            models.forEach(newModel -> {
                final String cmpId = id
                        .orElseGet(() -> idBuilder.map(f -> f.apply(newModel))
                                .orElse(null));

                final ViewContract viewContract = mapper.map(f -> f.apply(newModel)).orElse(null);

                components.add(
                        new Component()
                                .withId(cmpId)
                                .withData(viewContract)
                                .withUiType(uiType.orElse(null))
                );
            });

            return Optional.of(components);
        }
        return Optional.empty();
    }

    private List<Model> getModels(OriginalModel model) {
        return spread.map(f -> f.apply(model))
                .orElseGet(() -> transform.map(f -> toList(f.apply(model)))
                        .orElseGet(() -> toList((Model) model)));

    }

    private List<Model> toList(Model model) {
        List<Model> models = new ArrayList<>();
        models.add(model);
        return models;
    }

    public ViewBuilder<OriginalModel> done() {
        if (!modelType.equals(originalModelType) && !transform.isPresent() && !spread.isPresent()) {
            throw new RuntimeException("A transformer is required to map " + originalModelType + " into " + modelType);
        }
        return viewBuilder;
    }

    public ViewBuilder<Model> newModel() {
        return new ViewBuilder<>(modelType, viewBuilder);
    }
}
