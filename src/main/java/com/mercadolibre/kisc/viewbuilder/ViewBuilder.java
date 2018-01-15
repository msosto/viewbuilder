package com.mercadolibre.kisc.viewbuilder;

import com.mercadolibre.kisc.viewbuilder.template.Template;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Created by abertolo on 22/12/17.
 */
public class ViewBuilder {



    /*
        final ViewBuilder<OriginalModel, OriginalModel> parent;

        final Class<OriginalModel> originalModelType;

        final Class<Model> modelType;

        final Template<Model, ?> root;

        final Map<String, Template> templates;

        public ViewBuilder(Class<Model> modelType) {
            this.modelType = modelType;
            this.originalModelType = (Class<OriginalModel>) modelType;
            this.parent = null;
            this.templates = new HashMap<>();
            this.root = new Template<>((ViewBuilder<?, Model>) this, modelType, modelType, null);
            this.templates.put(null, root);

        }

        public ViewBuilder(Class<Model> modelType, Class<OriginalModel> originalModelType, ViewBuilder<OriginalModel, OriginalModel> parent) {
            this.modelType = modelType;
            this.originalModelType = originalModelType;
            this.parent = null;
            this.templates = parent.getTemplates();
            this.root = (Template<Model, ?>) parent.getRoot();
        }

        public Template<Model, OriginalModel> add(String templateId) {
            return add(templateId, root, modelType);
        }

        public <T> Template<T, OriginalModel> add(String templateId, Class<T> newModel) {
            return add(templateId, root, newModel);
        }


        public Template<Model, OriginalModel> add(String templateId, String parentId) {
            return add(templateId, parentId, modelType);
        }

        public <T> Template<T, OriginalModel> add(String templateId, String parentId, Class<T> newModel) {
            return add(templateId, templates.get(parentId), newModel);
        }

        public Template<Model, OriginalModel> add(String templateId, Template parent) {
            return add(templateId, parent, modelType);
        }

        public <T> Template<T, OriginalModel> add(String templateId, Template parent, Class<T> newModel) {
            if (parent == null) {
                throw new IllegalArgumentException("No parent found for template id:" + templateId);
            }

            final Template<T, OriginalModel> template = new Template<>(this, newModel, originalModelType, templateId);
            templates.put(templateId, template);

            parent.add(template);
            return template;
        }


            public Component<Model> build(Model model) {
                final Component cmp = new Component<Model>().withModel(model);
                buildTemplate(root, model, cmp);
                return cmp;
            }


            private void buildTemplate(Template template, Model model, Component<?> father) {
                Optional<List<Component>> thisTemplateComponents = template.toComponents(model, father);
                thisTemplateComponents.ifPresent(components -> components.forEach(component -> {
                    father.add(component);

                    template.getTemplates().forEach(t -> {
                        System.out.println("###father component: " + component);
                        buildTemplate((Template) t, model, component);
                    });
                }));
            }

    protected Map<String, Template> getTemplates() {
        return templates;
    }

    public ViewBuilder<OriginalModel, OriginalModel> getParent() {
        return parent;
    }

    public Template<Model,?> getRoot() {
        return root;
    }
    */
}
