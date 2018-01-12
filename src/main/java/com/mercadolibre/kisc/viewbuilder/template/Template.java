package com.mercadolibre.kisc.viewbuilder.template;

import com.mercadolibre.kisc.viewbuilder.Component;
import com.mercadolibre.kisc.viewbuilder.Final;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

/**
 * Created by abertolo on 29/12/17.
 */
public class Template<Model, OriginalModel> {

    final Final<Template<OriginalModel, ?>> parent;

    final Class<Model> modelType;

    final Class<OriginalModel> originalModelType;

    final List<Template> templates;

    Optional<String> id;

    Optional<String> uiType;

    Optional<Function<Model, String>> idBuilder;

    Optional<Function<OriginalModel, Boolean>> apply;

    Optional<Function<Model, Object>> dataBuilder;

    Optional<Function<OriginalModel, Model>> modelSupplier;

    Optional<Function<OriginalModel, List<Model>>> spread;


    protected Template(Template<OriginalModel, ?> parent, Class<Model> modelType, Class<OriginalModel> originalModelType) {
        this.parent = new Final(parent);
        this.modelType = modelType;
        this.originalModelType = originalModelType;

        templates = new ArrayList<>();
        idBuilder = Optional.empty();
        id = Optional.empty();
        uiType = Optional.empty();
        apply = Optional.empty();
        dataBuilder = Optional.empty();
        modelSupplier = Optional.empty();
        spread = Optional.empty();
    }

    public static <T> Template<T, T> create(Class<T> clazz) {
        return new Template<>(null, clazz, clazz);
    }

    public static <M, O> Template<M, O> create(Template parent, Class<M> mClazz, Class<O> oClazz) {
        return new Template<>(parent, mClazz, oClazz);
    }

    public Template<OriginalModel, OriginalModel> addSibling() {
        return parent.get().addChild();
    }

    public <NewModel> Template<NewModel, OriginalModel> addSibling(Class<NewModel> clazz, Function<OriginalModel, NewModel> transformer) {
        return parent.get().addChild(clazz, transformer);
    }

    public Template<Model, Model> addChild() {
        final Template<Model, Model> t = create(this, modelType, modelType);
        templates.add(t);
        return t;
    }

    public <NewModel> Template<NewModel, Model> addChild(Class<NewModel> clazz, Function<Model, NewModel> transformer) {
        final Template<NewModel, Model> t = create(this, clazz, modelType);
        t.modelSupplier(transformer);
        templates.add(t);
        return t;
    }

    public <NewModel> Template<NewModel, Model> addChild(Template<NewModel, Model> t){
        t.parent.set(this);
        templates.add(t);
        return t;
    }


    public <NewModel> Template<NewModel, Model> addChildren(Class<NewModel> clazz, Function<Model, List<NewModel>> transformer) {
        final Template<NewModel, Model> t = create(this, clazz, modelType);
        t.spread(transformer);
        templates.add(t);
        return t;
    }

    private void setParent(Template<OriginalModel, ?> p){
        parent.set(p);
    }

    public Template<OriginalModel, OriginalModel> parent() {
        return (Template<OriginalModel, OriginalModel>) parent.get();
    }

    public <T> Template<OriginalModel, T> parent(Class<T> tClass) {
        final Class<?> parentOriginalModelType = parent.get().getOriginalModelType();
        if (!parentOriginalModelType.equals(tClass)) {
            throw new RuntimeException("The class " + tClass + " is not compatible with the parent original type " + parentOriginalModelType);
        }
        return (Template<OriginalModel, T>) parent.get();
    }

    public List<Template> getChildren() {
        return templates;
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

    public Template<Model, OriginalModel> dataBuilder(Function<Model, Object> mapper) {
        this.dataBuilder = Optional.ofNullable(mapper);
        return this;
    }

    public Template<Model, OriginalModel> idBuilder(Function<Model, String> idBuilder) {
        this.idBuilder = Optional.ofNullable(idBuilder);
        return this;
    }

    public Template<Model, OriginalModel> modelSupplier(Function<OriginalModel, Model> transform) {
        this.modelSupplier = Optional.ofNullable(transform);
        return this;
    }

    public Template<Model, OriginalModel> spread(Function<OriginalModel, List<Model>> spread) {
        this.spread = Optional.ofNullable(spread);
        return this;
    }

    public Optional<List<Component>> toComponents(final OriginalModel model, Component<Model> father) {
        final Boolean shouldApply = apply
                .map(f -> f.apply(model))
                .orElse(true);

        if (shouldApply) {
            List<Component> components = new ArrayList<>();

            List<Model> models = getModels(model, father);
            System.out.println("father: " + father + " | models: " + models);

            models.forEach(newModel -> {
                final String cmpId = id
                        .orElseGet(() -> idBuilder.map(f -> f.apply(newModel))
                                .orElse(null));

                final Object viewContract = dataBuilder.map(f -> f.apply(newModel)).orElse(null);

                components.add(
                        new Component<Model>()
                                .withId(cmpId)
                                .withData(viewContract)
                                .withUiType(uiType.orElse(null))
                                .withModel(newModel)
                );
            });

            return Optional.of(components);
        }
        return Optional.empty();
    }

    private List<Model> getModels(OriginalModel model, Component<Model> father) {
        System.out.println(originalModelType + " == " + model.getClass());

        final Model fatherModel = Optional.ofNullable(father).map(Component::getModel).orElse(null);

        System.out.println("father.getModel():" + fatherModel + " | model:" + model);

        final java.lang.Object m = !model.getClass().equals(fatherModel) ? fatherModel : model;

        return spread.map(f -> f.apply((OriginalModel) m))
                .orElseGet(() -> modelSupplier.map(f -> toList(f.apply((OriginalModel) m)))
                        .orElseGet(() -> toList((Model) m)));

    }

    private List<Model> toList(Model model) {
        List<Model> models = new ArrayList<>();
        models.add(model);
        return models;
    }


    public Class<OriginalModel> getOriginalModelType() {
        return originalModelType;
    }
}
