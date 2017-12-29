package com.mercadolibre.kisc.viewbuilder.template;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abertolo on 29/12/17.
 */
public class ContainerTemplate<VC, Model> extends Template<VC, Model> {


    List<Template> templates;

    public ContainerTemplate add(Template template){
        return add(template, ContainerTemplate.class);
    }

    public <T extends ContainerTemplate<VC, Model>> T add(Template template, Class<T> clazz){
        if (templates != null){
            templates = new ArrayList<>();
        }
        templates.add(template);
        return (T) this;
    }

}
