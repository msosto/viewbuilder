package com.mercadolibre.kisc.viewbuilder.template;

import com.mercadolibre.kisc.viewbuilder.Component;
import com.mercadolibre.kisc.viewbuilder.Final;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by abertolo on 29/12/17.
 */
public abstract class Template<Model> {


    final Final<Template> parent;

    final Class<Model> modelType;

    final List<Template> templates;

    final List<Group> groups;

    final Map<Group, Function<Model, Boolean>> groupApply;

    Optional<String> id;

    Optional<String> uiType;

    Optional<Function<Model, String>> idBuilder;

    Optional<Function<Model, Boolean>> apply;

    Optional<Function<Model, Object>> dataBuilder;

    Optional<Function<Object, Model>> modelSupplier;

    Optional<Function<Object, List<Model>>> spread;

    protected Template(Class<Model> modelType) {
        this(null, modelType);
    }

    protected Template(Template parent, Class<Model> modelType) {
        this.parent = new Final(parent);
        this.modelType = modelType;

        templates = new ArrayList<>();
        groups = new ArrayList<>();
        groupApply = new HashMap<>();
        idBuilder = Optional.empty();
        id = Optional.empty();
        uiType = Optional.empty();
        apply = Optional.empty();
        dataBuilder = Optional.empty();
        modelSupplier = Optional.empty();
        spread = Optional.empty();

        createTemplate();
    }

    protected abstract void createTemplate();

    public <T> Template<T> create(Class<T> clazz) {
        return new TemplateStruct<>(null, clazz);
    }

    public <M> Template<M> create(Template parent, Class<M> mClazz) {
        return new TemplateStruct<>(parent, mClazz);
    }

    public Template<Model> addSibling() {
        return parent.get().addChild();
    }

    public <NewModel> Template<NewModel> addSibling(Class<NewModel> clazz, Function<Model, NewModel> transformer) {
        return parent.get().addChild(clazz, transformer);
    }

    public Template<Model> addChild() {
        final Template<Model> t = create(this, modelType);
        templates.add(t);
        return t;
    }

    public <NewModel> Template<NewModel> addChild(Class<NewModel> clazz, Function<Model, NewModel> transformer) {
        final Template<NewModel> t = create(this, clazz);
        t.modelSupplier((Function<Object, NewModel>) transformer);
        templates.add(t);
        return t;
    }

    public <NewModel> Template<NewModel> addChild(Template<NewModel> t) {
        setParent(t);
        templates.add(t);
        return t;
    }

    private <NewModel> void setParent(Template<NewModel> t) {
        t.parent.set(this);
    }


    public Template<Model> groups(Group... groups) {
        for (Group group : groups) {
            this.groups.add(group);
        }
        return this;
    }

    public <NewModel> Template<NewModel> addChild(Template<NewModel> t, Function<Model, NewModel> transformer) {
        t.modelSupplier((Function<Object, NewModel>) transformer);
        t.parent.set(this);
        templates.add(t);
        return t;
    }


    public <NewModel> Template<NewModel> addChildren(Class<NewModel> clazz, Function<Model, List<NewModel>> transformer) {
        final Template<NewModel> t = create(this, clazz);
        t.spread((Function<Object, List<NewModel>>) transformer);
        templates.add(t);
        return t;
    }

    public <NewModel> Template<NewModel> addChildren(Template<NewModel> t, Function<Model, List<NewModel>> transformer) {
        t.spread((Function<Object, List<NewModel>>) transformer);
        templates.add(t);
        return t;
    }

    public Template<Model> parent() {
        return (Template<Model>) parent.get();
    }

    public <T> Template<T> parent(Class<T> tClass) {
        final Class<?> parentModelType = parent.get().getModelType();
        if (!parentModelType.equals(tClass)) {
            throw new RuntimeException("The class " + tClass + " is not compatible with the parent original type " + parentModelType);
        }
        return (Template<T>) parent.get();
    }


    public Template<Model> id(String id) {
        this.id = Optional.ofNullable(id);
        return this;
    }

    public Template<Model> uiType(String uiType) {
        this.uiType = Optional.ofNullable(uiType);
        return this;
    }

    public Template<Model> apply(Function<Model, Boolean> apply) {
        this.apply = Optional.ofNullable(apply);
        return this;
    }

    public Template<Model> apply(Group group, Function<Model, Boolean> apply) {
        if (group == null) {
            throw new NullPointerException("Can not add an apply to a null group.");
        }
        this.groupApply.put(group, apply);
        return this;
    }

    public Template<Model> dataBuilder(Function<Model, Object> mapper) {
        this.dataBuilder = Optional.ofNullable(mapper);
        return this;
    }

    public Template<Model> idBuilder(Function<Model, String> idBuilder) {
        this.idBuilder = Optional.ofNullable(idBuilder);
        return this;
    }

    public Template<Model> modelSupplier(Function<Object, Model> transform) {
        this.modelSupplier = Optional.ofNullable(transform);
        return this;
    }

    public Template<Model> spread(Function<Object, List<Model>> spread) {
        this.spread = Optional.ofNullable(spread);
        return this;
    }


    public List<Component> buildList(final Model model) {
        return toComponents(model, null, new HashMap<>(), new HashMap<>())
                .orElse(null);
    }

    public Component build(final Model model) {
        return toComponents(model, null, new HashMap<>(), new HashMap<>())
                .map(components -> components.stream().findFirst().orElse(null))
                .orElse(null);
    }

    protected Optional<List<Component>> toComponents(final Model model, final Component father, final Map<Component, Object> cmpModels, final Map<Group, Boolean> groupApplies) {

        //groupApplies.put()

        groupApply.forEach((group, modelBooleanFunction) -> groupApplies.put(group, modelBooleanFunction.apply(model)));



        List<Model> models = getModels(model, cmpModels.get(father)).stream()
                .filter(m -> apply
                        .map(f -> f.apply(m))
                        .orElse(true)
                )
                .filter(m -> {
                    final boolean present = groups.stream().filter(group -> {
                        final Boolean aBoolean = groupApplies.get(group);
                        return aBoolean != null ? aBoolean : false;
                    })
                    .findAny()
                    .isPresent();
                    System.out.println("present = " + present);
                    return !present;
                })
                .collect(Collectors.toList());

        if (!models.isEmpty()) {
            System.out.println("father: " + father + " | models: " + models);

            List<Component> components = getComponents(models, cmpModels);

            components.forEach(component -> templates.forEach(template -> {
                final Optional<List<Component>> toComponents = template.toComponents(model, component, cmpModels, groupApplies);

                toComponents.ifPresent(thisComponents -> thisComponents.forEach(c -> {
                    component.add(c);
                }));
            }));

            return Optional.of(components);
        }
        return Optional.empty();
    }

    private List<Component> getComponents(List<Model> models, Map<Component, Object> cmpModels) {
        return models.stream().map(newModel -> {
            final String cmpId = id
                    .orElseGet(() -> idBuilder.map(f -> f.apply(newModel))
                            .orElse(null));

            final Object viewContract = dataBuilder.map(f -> f.apply(newModel)).orElse(null);

            final Component component = new Component()
                    .withId(cmpId)
                    .withData(viewContract)
                    .withUiType(uiType.orElse(null));

            cmpModels.put(component, newModel);
            return component;
        }).collect(Collectors.toList());
    }

    private List<Model> getModels(Model model, Object fatherModel) {
        System.out.println(modelType + " == " + model.getClass());

        System.out.println("father.getModel():" + fatherModel + " | model:" + model);

        final Object m = (fatherModel != null && !model.getClass().equals(fatherModel)) ? fatherModel : model;

        return spread.map(f -> f.apply(m))
                .orElseGet(() -> modelSupplier.map(f -> toList(f.apply((Model) m)))
                        .orElseGet(() -> toList((Model) m)));

    }

    private List<Model> toList(Model model) {
        List<Model> models = new ArrayList<>();
        models.add(model);
        return models;
    }


    protected Class<Model> getModelType() {
        return modelType;
    }


    private static class TemplateStruct<Model> extends Template<Model> {

        protected TemplateStruct(Class<Model> modelType) {
            super(modelType);
        }

        protected TemplateStruct(Template parent, Class<Model> modelType) {
            super(parent, modelType);
        }

        @Override
        public void createTemplate() {
        }
    }
}