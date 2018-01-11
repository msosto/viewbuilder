package com.mercadolibre.conceptTest.graphs.model;

/**
 * Created by msosto on 1/10/18.
 */
public class FinderModel extends TaskModel {

    String title;

    public String getTitle() {
        return title;
    }

    public FinderModel setTitle(String title) {
        this.title = title;
        return this;
    }
}
