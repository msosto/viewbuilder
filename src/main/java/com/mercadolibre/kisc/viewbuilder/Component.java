package com.mercadolibre.kisc.viewbuilder;

import com.mercadolibre.kisc.viewbuilder.template.Template;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abertolo on 22/12/17.
 */
public class Component<VC> {

    String id;

    String uiType;

    VC data;

    List<Component> components;

    public String getId() {
        return id;
    }

    public Component withId(String id) {
        this.id = id;
        return this;
    }

    public String getUiType() {
        return uiType;
    }

    public Component withUiType(String uiType) {
        this.uiType = uiType;
        return this;
    }

    public VC getData() {
        return data;
    }

    public Component withData(VC data) {
        this.data = data;
        return this;
    }

    public List<Component> getComponents() {
        return components;
    }

    public void add(Component cmp){
        if (components == null){
            components = new ArrayList<>();
        }
        components.add(cmp);
    }

}
