package com.mercadolibre.conceptTest.graphs.model;

import com.mercadolibre.conceptTest.graphs.builder.view.contracts.HeaderViewContract;

import java.util.List;

/**
 * Created by mlizarraga on 11/1/18
 */
public class HeaderModel {

    private String title;
    private List<String> titleParams;
    private String subtitle;
    private String linkText;
    private String linkConnection;
    private String imageURL;

    public String getTitle() {
        return title;
    }

    public HeaderModel withTitle(String title) {
        this.title = title;
        return this;
    }

    public List<String> getTitleParams() {
        return titleParams;
    }

    public HeaderModel withTitleParams(List<String> titleParams) {
        this.titleParams = titleParams;
        return this;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public HeaderModel withSubtitle(String subtitle) {
        this.subtitle = subtitle;
        return this;
    }

    public String getLinkText() {
        return linkText;
    }

    public HeaderModel withLinkText(String linkText) {
        this.linkText = linkText;
        return this;
    }

    public String getLinkConnection() {
        return linkConnection;
    }

    public HeaderModel withLinkConnection(String linkConnection) {
        this.linkConnection = linkConnection;
        return this;
    }

    public String getImageURL() {
        return imageURL;
    }

    public HeaderModel withImageURL(String imageURL) {
        this.imageURL = imageURL;
        return this;
    }
}
