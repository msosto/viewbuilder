package com.mercadolibre.conceptTest.graphs.model;

import java.util.List;
import java.util.Map;

/**
 * Created by msosto on 1/10/18.
 */
public class FinderModel extends TaskModel {

    private String title;
    private Boolean required;
    private Boolean disabled;


    public String getTitle() {
        return title;
    }

    public FinderModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public Boolean getRequired() {
        return required;
    }

    public FinderModel setRequired(Boolean required) {
        this.required = required;
        return this;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public FinderModel setDisabled(Boolean disabled) {
        this.disabled = disabled;
        return this;
    }

}
