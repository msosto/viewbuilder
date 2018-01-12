package com.mercadolibre.kisc.viewbuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abertolo on 22/12/17.
 */
public class Component<Model> {

    String id;

    String uiType;

    Object data;

    List<Component> components;

    Component father;

    transient Model model;

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

    public Object getData() {
        return data;
    }

    public Component withData(Object data) {
        this.data = data;
        return this;
    }

    public List<Component> getComponents() {
        return components;
    }

    public Model getModel() {
        return model;
    }

    public Component withModel(Model model) {
        this.model = model;
        return this;
    }

    public Component getFather() {
        return father;
    }

    public Component withFather(Component father) {
        this.father = father;
        return this;
    }

    public void add(Component cmp) {
        if (components == null) {
            components = new ArrayList<>();
        }
        components.add(cmp);
    }

    @Override
    public String toString() {
        return "Component{" +
                "id='" + id + '\'' +
                ", uiType='" + uiType + '\'' +
                '}';
    }
}
