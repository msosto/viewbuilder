package com.mercadolibre.conceptTest.data.header;

import java.util.List;

/**
 * Created by mlizarraga on 11/1/18
 */
public class HeaderViewContract {

    private String title;
    private List<String> titleParams;
    private String subtitle;
    private String linkText;
    private String linkConnection;
    private String imageUrl;

    public String getTitle() {
        return title;
    }

    public HeaderViewContract withTitle(String title) {
        this.title = title;
        return this;
    }

    public List<String> getTitleParams() {
        return titleParams;
    }

    public HeaderViewContract withTitleParams(List<String> titleParams) {
        this.titleParams = titleParams;
        return this;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public HeaderViewContract withSubtitle(String subtitle) {
        this.subtitle = subtitle;
        return this;
    }

    public String getLinkText() {
        return linkText;
    }

    public HeaderViewContract withLinkText(String linkText) {
        this.linkText = linkText;
        return this;
    }

    public String getLinkConnection() {
        return linkConnection;
    }

    public HeaderViewContract withLinkConnection(String linkConnection) {
        this.linkConnection = linkConnection;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public HeaderViewContract withImageURL(String imageURL) {
        this.imageUrl = imageURL;
        return this;
    }

}
