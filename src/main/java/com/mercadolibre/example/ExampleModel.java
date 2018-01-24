package com.mercadolibre.example;

import java.util.List;

/**
 * Created by abertolo on 03/01/18.
 */
public class ExampleModel {

    String searchPlaceholder;
    String searchIcon;
    Integer active;
    Integer paused;
    Integer offset;
    Integer currentPage;
    List<ExampleModelItem> items;
    public String getSearchPlaceholder() {
        return searchPlaceholder;
    }

    OtherModel other;

    public ExampleModel withSearchPlaceholder(String searchPlaceholder) {
        this.searchPlaceholder = searchPlaceholder;
        return this;
    }

    public String getSearchIcon() {
        return searchIcon;
    }

    public ExampleModel withSearchIcon(String searchIcon) {
        this.searchIcon = searchIcon;
        return this;
    }

    public Integer getActive() {
        return active;
    }

    public ExampleModel withActive(Integer active) {
        this.active = active;
        return this;
    }

    public Integer getPaused() {
        return paused;
    }

    public ExampleModel withPaused(Integer paused) {
        this.paused = paused;
        return this;
    }

    public Integer getOffset() {
        return offset;
    }

    public ExampleModel withOffset(Integer offset) {
        this.offset = offset;
        return this;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public ExampleModel withCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
        return this;
    }

    public List<ExampleModelItem> getItems() {
        return items;
    }

    public ExampleModel withItems(List<ExampleModelItem> items) {
        this.items = items;
        return this;
    }

    public OtherModel getOther() {
        return other;
    }

    public ExampleModel withOther(OtherModel other) {
        this.other = other;
        return this;
    }
}
