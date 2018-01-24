package com.mercadolibre.conceptTest.template.task;

import com.mercadolibre.conceptTest.data.finder.FinderViewContract;
import com.mercadolibre.conceptTest.model.component.title.TitleModel;

/**
 * Created by mforte on 1/15/18.
 */
public class FinderTaskTemplate extends TaskTemplate<FinderTaskSupplier> {

    public FinderTaskTemplate() {
        super(FinderTaskSupplier.class);
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
