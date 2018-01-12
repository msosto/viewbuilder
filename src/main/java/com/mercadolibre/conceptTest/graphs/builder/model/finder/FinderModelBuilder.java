package com.mercadolibre.conceptTest.graphs.builder.model.finder;

import com.mercadolibre.conceptTest.graphs.model.FinderModel;
import com.mercadolibre.conceptTest.graphs.model.FooterModel;
import com.mercadolibre.conceptTest.graphs.model.HeaderModel;
import com.mercadolibre.flux.flow.graph.navigation.Context;

/**
 * Created by msosto on 1/11/18.
 */
public class FinderModelBuilder {

    public static final String HEADER_TITLE = "FINDER_HEADER_TITLE";
    public static final String HEADER_SUBTITLE = "FINDER_HEADER_SUBTITLE";
    public static final String HEADER_LINK = "FINDER_HEADER_LINK";
    public static final String FOOTER_BTN_TEXT = "FINDER_FOOTER_BUTTON_TEXT";
    public static final String FINDER_DATA_KEY = "item.title";

    public FinderModel build(Context context){
        FinderModel model = new FinderModel();
        addHeader(model.getHeaderModel());
        model.setTitle(getFinderValue(context));
        model.setRequired(false);
        model.setDisabled(false);
        addFooter(model.getFooterModel());
        return model;
    }

    private void addHeader(HeaderModel headerModel) {
        headerModel.withTitle(HEADER_TITLE)
                .withSubtitle(HEADER_SUBTITLE)
                .withLinkConnection("back_connection");
    }

    private void addFooter(FooterModel footerModel) {
        footerModel.withButtonText(FOOTER_BTN_TEXT)
                .withButtonConnection("continue");
    }

    public String getFinderValue(Context context) {
        return context.getDataProxy().getSafe("data." + FINDER_DATA_KEY).getStringValue();
    }
}
