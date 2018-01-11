package com.mercadolibre.conceptTest.graphs.builder.view;

import com.mercadolibre.kisc.viewbuilder.ViewContract;

import java.util.List;
import java.util.Map;

/**
 * Created by mforte on 1/11/18.
 */
public class InputComponent implements ViewContract {

    private Boolean required;
    private Boolean disabled;
    private List<Map<String, Object>> validationErrors;

    public Boolean getRequired() {
        return required;
    }

    public InputComponent setRequired(Boolean required) {
        this.required = required;
        return this;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public InputComponent setDisabled(Boolean disabled) {
        this.disabled = disabled;
        return this;
    }

    public List<Map<String, Object>> getValidationErrors() {
        return validationErrors;
    }

    public InputComponent setValidationErrors(List<Map<String, Object>> validationErrors) {
        this.validationErrors = validationErrors;
        return this;
    }

    public InputComponent clearValidationErrors() {
        this.validationErrors = null;
        return this;
    }
}
