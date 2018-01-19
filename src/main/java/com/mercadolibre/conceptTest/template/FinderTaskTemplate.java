package com.mercadolibre.conceptTest.template;

import com.mercadolibre.conceptTest.graphs.builder.view.contracts.finder.FinderViewContract;
import com.mercadolibre.conceptTest.graphs.model.component.TitleModel;
import com.mercadolibre.conceptTest.graphs.model.inter.TitleInputProvider;

/**
 * Created by mforte on 1/15/18.
 */
public class FinderTaskTemplate extends TaskTemplate<TitleInputProvider> {

    public FinderTaskTemplate() {
        super(TitleInputProvider.class);
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
        //TODO: Agregar DataBuilder
        addChild().id("FINDER").uiType("FINDER").dataBuilder(model -> {
            final TitleModel titleInputModel = model.getTitleInput();
            return new FinderViewContract()
                    .withValue(titleInputModel.getTitle())
                    .withDisabled(titleInputModel.getDisabled())
                    .withValidationErrors(titleInputModel.getValidationErrors());
        });
    }
}
