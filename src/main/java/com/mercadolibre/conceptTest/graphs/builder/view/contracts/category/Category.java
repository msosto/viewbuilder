package com.mercadolibre.conceptTest.graphs.builder.view.contracts.category;

/**
 * Created by mlizarraga on 11/1/18
 */
public class Category {

    private String id;
    private String name;
    private String output;

    public String getId() {
        return id;
    }

    public Category withId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Category withName(String name) {
        this.name = name;
        return this;
    }

    public String getOutput() {
        return output;
    }

    public Category withOutput(String output) {
        this.output = output;
        return this;
    }
}
