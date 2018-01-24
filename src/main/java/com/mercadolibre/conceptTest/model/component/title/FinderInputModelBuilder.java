package com.mercadolibre.conceptTest.model.component.title;

import com.mercadolibre.flux.flow.graph.navigation.Context;

/**
 * Created by msosto on 1/11/18.
 */
public class FinderInputModelBuilder {

    public static final String FINDER_DATA_KEY = "item.title";

    public TitleModel build(Context context) {
        TitleModel model = new TitleModel();
        model.setTitle(getFinderValue(context));
        model.setRequired(false);
        model.setDisabled(false);
        return model;
    }

    public String getFinderValue(Context context) {
        return context.getDataProxy().accessSafe("data." + FINDER_DATA_KEY).getStringValue();
    }
}
