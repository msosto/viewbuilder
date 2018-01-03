package com.mercadolibre.example;

import com.mercadolibre.example.contract.Picture;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by abertolo on 03/01/18.
 */
public class ExampleModelItem {

    String title;
    List<Picture> pictures;
    BigDecimal price;

    String actionUrl;
    String actionLabel;

    Vertical vertical;

    public Vertical getVertical() {
        return vertical;
    }

    public ExampleModelItem withVertical(Vertical vertical) {
        this.vertical = vertical;
        return this;
    }

    public List<Picture> getPictures() {
        return pictures;
    }

    public ExampleModelItem withPictures(List<Picture> pictures) {
        this.pictures = pictures;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public ExampleModelItem withTitle(String title) {
        this.title = title;
        return this;
    }


    public BigDecimal getPrice() {
        return price;
    }

    public ExampleModelItem withPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getActionUrl() {
        return actionUrl;
    }

    public ExampleModelItem withActionUrl(String actionUrl) {
        this.actionUrl = actionUrl;
        return this;
    }

    public String getActionLabel() {
        return actionLabel;
    }

    public ExampleModelItem withActionLabel(String actionLabel) {
        this.actionLabel = actionLabel;
        return this;
    }

    public enum Vertical {
        CORE,
        REAL_ESTATE,
        MOTOR,
        SERVICE;
    }
}
