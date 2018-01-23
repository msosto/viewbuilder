package com.mercadolibre.conceptTest.data.header;

/**
 * Created by mlizarraga on 17/1/18
 */
public class HeaderDataBuilder {

    public static final String HEADER_TITLE_BASE = "_HEADER_TITLE";
    public static final String HEADER_SUBTITLE_BASE = "_HEADER_SUBTITLE";

    //TODO: Armar otros builders que soporten params y el resto de sus variantes
    public HeaderViewContract buildDefaultHeader(String taskId) {
        HeaderViewContract viewContract = new HeaderViewContract()
                .withTitle(taskId + HEADER_TITLE_BASE)
                //.withTitleParams()
                .withSubtitle(taskId + HEADER_SUBTITLE_BASE);
        //.withLinkText(model.getLinkText())
        //.withLinkConnection(model.getLinkConnection())
        //.withImageURL(model.getImageURL());
        return viewContract;
    }
}
