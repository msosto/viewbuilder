package com.mercadolibre.example.contract;

/**
 * Created by abertolo on 03/01/18.
 */
public class AutocompleteInput {

    String placeholder;
    String formName;
    String icon;
    String endpoint;

    public String getPlaceholder() {
        return placeholder;
    }

    public AutocompleteInput withPlaceholder(String placeholder) {
        this.placeholder = placeholder;
        return this;
    }

    public String getFormName() {
        return formName;
    }

    public AutocompleteInput withFormName(String formName) {
        this.formName = formName;
        return this;
    }

    public String getIcon() {
        return icon;
    }

    public AutocompleteInput withIcon(String icon) {
        this.icon = icon;
        return this;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public AutocompleteInput withEndpoint(String endpoint) {
        this.endpoint = endpoint;
        return this;
    }
}
