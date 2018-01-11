package com.mercadolibre.conceptTest.graphs.builder.view.contracts.category;

import java.util.List;

/**
 * Created by mlizarraga on 11/1/18
 */
public class CategorySelectionRow {

    private String name;
    private String output;
    private String customOutput;
    private List<String> tags;

    public String getName() {
        return name;
    }

    public CategorySelectionRow withName(String name) {
        this.name = name;
        return this;
    }

    public String getOutput() {
        return output;
    }

    public CategorySelectionRow withOutput(String output) {
        this.output = output;
        return this;
    }

    public String getCustomOutput() {
        return customOutput;
    }

    public CategorySelectionRow withCustomOutput(String customOutput) {
        this.customOutput = customOutput;
        return this;
    }

    public List<String> getTags() {
        return tags;
    }

    public CategorySelectionRow withTags(List<String> tags) {
        this.tags = tags;
        return this;
    }
}
