package com.mercadolibre.conceptTest.graphs.builder.model.finder;

import com.mercadolibre.conceptTest.graphs.model.component.TitleModel;
import com.mercadolibre.flux.flow.graph.navigation.Context;

/**
 * Created by msosto on 1/11/18.
 */
public class FinderModelBuilder {

    public static final String FINDER_DATA_KEY = "item.title";

    public TitleModel build(Context context) {
        TitleModel model = new TitleModel();
        model.setTitle(getFinderValue(context));
        model.setRequired(false);
        model.setDisabled(false);
        return model;
    }

//    private void addHeader(HeaderModel headerModel) {
//        headerModel.withTitle(HEADER_TITLE)
//                .withSubtitle(HEADER_SUBTITLE)
//                .withLinkConnection("back_connection");
//    }
//
//    private void addFooter(FooterModel footerModel) {
//        footerModel.withButtonText(FOOTER_BTN_TEXT)
//                .withButtonConnection("continue");
//    }

    public String getFinderValue(Context context) {
        return context.getDataProxy().accessSafe("data." + FINDER_DATA_KEY).getStringValue();
    }
}
