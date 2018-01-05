package com.mercadolibre.kisc.viewbuilder;

import com.mercadolibre.kisc.viewbuilder.template.Template;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Created by abertolo on 22/12/17.
 */
public class ViewBuilder<Model> {

    //final ViewBuilder<>

    final Class<Model> modelType;

    final Map<String, Template> templates;

    public ViewBuilder(Class<Model> modelType) {
        this.modelType = modelType;
        this.templates = new HashMap<>();
        this.templates.put(null, new Template<>(this, modelType, modelType, null));
    }

    public <Model, OriginalModel> ViewBuilder(Class<Model> modelType, ViewBuilder<OriginalModel> viewBuilder) {

    }

    public Template<Model, Model> add(String templateId) {
        return add(templateId, (String) null, modelType);
    }

    public <T> Template<T, Model> add(String templateId, Class<T> newModel) {
        return add(templateId, (String) null, newModel);
    }


    public Template<Model, Model> add(String templateId, String parentId) {
        return add(templateId, parentId, modelType);
    }

    public <T> Template<T, Model> add(String templateId, String parentId, Class<T> newModel) {
        final Template<T, Model> template = new Template<>(this, newModel, modelType, templateId);
        templates.put(templateId, template);

        final Template parent = templates.get(parentId);
        if (parent == null) {
            throw new IllegalArgumentException("No parent found with id:" + parentId);
        }
        parent.add(template);
        return template;
    }

    public Template<Model, Model> add(String templateId, Template parent) {
        return add(templateId, parent.getTemplateId());
    }


    public <T> Template<T, Model> add(String templateId, Template parent, Class<T> newModel) {
        return add(templateId, parent.getTemplateId(), newModel);
    }


    public Component build(Model model) {
        final Component cmp = new Component();
        subComponents(templates.get(null), model, cmp);
        return cmp;
    }


    private void subComponents(Template template, Model model, Component cmp) {
        final List<Template> templates = template.getTemplates();
        templates.forEach(t -> {
            final Optional<List<Component>> toComponents = t.toComponents(model);
            toComponents
                    .ifPresent(components -> components.forEach(component -> cmp.add(component)));
        });
    }


}
