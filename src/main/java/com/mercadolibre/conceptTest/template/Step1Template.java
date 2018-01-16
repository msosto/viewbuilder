package com.mercadolibre.conceptTest.template;

import com.mercadolibre.conceptTest.graphs.model.Step1Model;
import com.mercadolibre.kisc.viewbuilder.template.Template;

/**
 * Created by mforte on 1/15/18.
 */
public class Step1Template extends Template<Step1Model> {

    public Step1Template() {
        super(Step1Model.class);
    }

    @Override
    protected void createTemplate() {
        id("STEP_1").uiType("STEP");
        addChild(new FinderTaskTemplate(), Step1Model::getFinderModel);
        addChild(new CategorySelectionTaskTemplate(), Step1Model::getCategorySelectionModel);
        addChild(new PKsTaskTemplate(), Step1Model::getPksModel);
    }
}
