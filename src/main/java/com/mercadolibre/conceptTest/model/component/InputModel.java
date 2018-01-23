package com.mercadolibre.conceptTest.model.component;

import java.util.List;
import java.util.Map;

/**
 * Created by mforte on 1/18/18.
 */
public class InputModel {

    private String output;
    private Boolean required;
    private Boolean disabled;
    private List<Map<String, Object>> validationErrors;

    public List<Map<String, Object>> getValidationErrors() {
        return validationErrors;
    }

    public InputModel setValidationErrors(List<Map<String, Object>> validationErrors) {
        this.validationErrors = validationErrors;
        return this;
    }

    public String getOutput() {
        return output;
    }

    public InputModel setOutput(String output) {
        this.output = output;
        return this;
    }

    public Boolean getRequired() {
        return required;
    }

    public InputModel setRequired(Boolean required) {
        this.required = required;
        return this;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public InputModel setDisabled(Boolean disabled) {
        this.disabled = disabled;
        return this;
    }
}
