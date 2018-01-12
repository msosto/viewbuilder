package com.mercadolibre.example.contract;

import com.mercadolibre.kisc.viewbuilder.Object;

/**
 * Created by abertolo on 03/01/18.
 */
public class Picture implements Object {

    Integer height;
    Integer width;
    String url;

    public Integer getHeight() {
        return height;
    }

    public Picture withHeight(Integer height) {
        this.height = height;
        return this;
    }

    public Integer getWidth() {
        return width;
    }

    public Picture withWidth(Integer width) {
        this.width = width;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public Picture withUrl(String url) {
        this.url = url;
        return this;
    }
}
