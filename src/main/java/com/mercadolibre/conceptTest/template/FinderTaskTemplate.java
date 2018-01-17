package com.mercadolibre.conceptTest.template;

import com.mercadolibre.conceptTest.graphs.builder.view.contracts.finder.FinderViewContract;
import com.mercadolibre.conceptTest.graphs.model.FinderModel;

/**
 * Created by mforte on 1/15/18.
 */
public class FinderTaskTemplate extends TaskTemplate<FinderModel> {

    public FinderTaskTemplate() {
        super(FinderModel.class);
    }

    @Override
    protected String getTaskId() {
        return "FINDER";
    }

    @Override
    protected String getHeaderUIType() {
        return HEADER_UI_TYPE;
    }

    @Override
    protected String getFooterUIType() {
        return FOOTER_IN_UI_TYPE;
    }

    @Override
    protected void addBody() {
        addChild().id("FINDER").uiType("FINDER").dataBuilder(finderModel ->
                new FinderViewContract()
                        .withValue(finderModel.getTitle())
                        .withDisabled(finderModel.getDisabled())
                        .withValidationErrors(finderModel.getValidationErrors()));
    }
}
