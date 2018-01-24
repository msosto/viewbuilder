package com.mercadolibre.conceptTest.data.category.breadcrumb;

/**
 * Created by mlizarraga on 11/1/18
 */
public class BreadcrumbCategory {

    private String id;
    private String name;
    private String output;

    public String getId() {
        return id;
    }

    public BreadcrumbCategory withId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public BreadcrumbCategory withName(String name) {
        this.name = name;
        return this;
    }

    public String getOutput() {
        return output;
    }

    public BreadcrumbCategory withOutput(String output) {
        this.output = output;
        return this;
    }
}
