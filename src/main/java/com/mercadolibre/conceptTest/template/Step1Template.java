package com.mercadolibre.conceptTest.template;

import com.mercadolibre.conceptTest.graphs.model.component.SingleVariationModel;
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
        addChild(new FinderTaskTemplate());
        addChild(new CategorySelectionTaskTemplate());
        addChild(new PKsTaskTemplate());
        //addChild(Pregunta Variaciones).apply(new)
        addNoVariationGroup();
        addVariationGroup();
    }

    private void addNoVariationGroup() {
        addChild(new QuantityTaskTemplate(), Step1Model::getQuantityModelWithoutVariation);
        //addChild(pictures);
    }

    private void addVariationGroup() {
        addChild(SingleVariationModel.class, Step1Model::getMainSingleVariationModel); // new SingleVariationTaskTemplate()?????
        addChildren(SingleVariationModel.class, Step1Model::getSecondarySingleVariationModel); // new SingleVariationTaskTemplate()?????
        addChild(new QuantityTaskTemplate(), Step1Model::getQuantityModelWithVariation);
        //addChild(pictures);
    }
}
