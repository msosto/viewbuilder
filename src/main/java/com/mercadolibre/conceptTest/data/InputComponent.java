package com.mercadolibre.conceptTest.data;

import java.util.List;
import java.util.Map;

/**
 * Created by mforte on 1/11/18.
 */
public class InputComponent {

    private Boolean required;
    private Boolean disabled;
    private List<Map<String, Object>> validationErrors;
    private String output;

    public Boolean getRequired() {
        return required;
    }

    public InputComponent withRequired(Boolean required) {
        this.required = required;
        return this;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public InputComponent withDisabled(Boolean disabled) {
        this.disabled = disabled;
        return this;
    }

    public List<Map<String, Object>> getValidationErrors() {
        return validationErrors;
    }

    public InputComponent withValidationErrors(List<Map<String, Object>> validationErrors) {
        this.validationErrors = validationErrors;
        return this;
    }

    public InputComponent clearValidationErrors() {
        this.validationErrors = null;
        return this;
    }

    public String getOutput() {
        return output;
    }

    public InputComponent withOutput(String output) {
        this.output = output;
        return this;
    }
}
