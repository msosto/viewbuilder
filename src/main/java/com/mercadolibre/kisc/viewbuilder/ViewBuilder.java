package com.mercadolibre.kisc.viewbuilder;

import com.mercadolibre.kisc.viewbuilder.template.Template;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

/**
 * Created by abertolo on 22/12/17.
 */
public class ViewBuilder<Model> {

    Template template;

    public ViewBuilder(Template template) {
        if (template == null) {
            throw new IllegalArgumentException("ViewTemplate is required.");
        }
        this.template = template;
    }

    public Component build(Model model) {
        final Component cmp = new Component();
        subComponents(template, model, cmp);
        return cmp;
    }


    Optional<List<Component>> buildComponent(Template template, Model model) {
        final Boolean apply = (Boolean) template.getApply()
                .map(f -> ((Function) f).apply(model))
                .orElse(true);

        if (apply) {
            List<Component> components = new ArrayList<>();

            List<Object> models = getModels(template, model);

            models.forEach(newModel -> {

                final String id = (String) template.getId()
                        .orElse(
                                template.getIdBuilder().map(idb -> {
                                    Function idbuilder = (Function) idb;
                                    return idbuilder.apply(newModel).toString();
                                }).orElse(null)
                        );

                final Object data = template.getMapper().map(o -> {
                    Function f = (Function) o;
                    return f.apply(newModel);
                }).orElse(null);

                addComponent(template, (Model) newModel, components, id, data);
            });

            return Optional.of(components);
        }
        return Optional.empty();

    }

    private List<Object> getModels(Template template, Model model) {
        List<Object> models = new ArrayList<>();

        template.getTransform().ifPresent(o -> {
            Function f = (Function) o;
            models.add(f.apply(model));
        });

        template.getTransformToList().ifPresent(o -> {
            Function f = (Function) o;
            models.addAll((Collection) f.apply(model));
        });

        if (models.isEmpty()) {
            models.add(model);
        }
        return models;
    }

    private void addComponent(Template template, Model model, List<Component> components, String id, Object data) {
        Component cmp = new Component()
                .withId(id)
                .withData(data)
                .withUiType(template.getUiType());

        subComponents(template, model, cmp);
        components.add(cmp);
    }

    private void subComponents(Template template, Model model, Component cmp) {
        template.getTemplates().forEach(t ->
                buildComponent((Template) t, model)
                        .ifPresent(components -> components.forEach(component -> cmp.add(component)))
        );
    }

}
