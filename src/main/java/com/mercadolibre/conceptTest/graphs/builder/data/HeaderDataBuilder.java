package com.mercadolibre.conceptTest.graphs.builder.data;

import com.mercadolibre.conceptTest.graphs.builder.view.contracts.HeaderViewContract;
import com.mercadolibre.conceptTest.graphs.model.HeaderModel;

/**
 * Created by mlizarraga on 17/1/18
 */
public class HeaderDataBuilder {

    public HeaderViewContract build(HeaderModel model) {
        HeaderViewContract viewContract = new HeaderViewContract()
                .withTitle(model.getTitle())
                .withTitleParams(model.getTitleParams())
                .withSubtitle(model.getSubtitle())
                .withLinkText(model.getLinkText())
                .withLinkConnection(model.getLinkConnection())
                .withImageURL(model.getImageURL());
        return viewContract;
    }
}
