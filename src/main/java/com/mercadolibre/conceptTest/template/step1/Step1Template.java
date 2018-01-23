package com.mercadolibre.conceptTest.template.step1;

import com.mercadolibre.conceptTest.model.step.Step1Model;
import com.mercadolibre.conceptTest.template.task.CategorySelectionTaskTemplate;
import com.mercadolibre.conceptTest.template.task.FinderTaskTemplate;
import com.mercadolibre.conceptTest.template.task.PKsTaskTemplate;
import com.mercadolibre.conceptTest.template.task.QuantityTaskTemplate;
import com.mercadolibre.conceptTest.todo.component.VariationAttributeInputModel;
import com.mercadolibre.kisc.viewbuilder.template.Group;
import com.mercadolibre.kisc.viewbuilder.template.Template;

/**
 * Created by mforte on 1/15/18.
 */
public class Step1Template extends Template<Step1Model> {

    private static final Group variationsGroup = Group.withId("VARIATIONS");
    private static final Group noVariationsGroup = Group.withId("NO_VARIATIONS");

    public Step1Template() {
        super(Step1Model.class);
    }

    @Override
    protected void createTemplate() {
        id("STEP_1").uiType("STEP");
        apply(variationsGroup, step1Model -> false); //TODO
        apply(noVariationsGroup, step1Model -> !false); //TODO

        addChild(new FinderTaskTemplate());
        addChild(new CategorySelectionTaskTemplate());
        addChild(new PKsTaskTemplate());
        //addChild(Pregunta Variaciones).apply(new)
        addNoVariationGroup();
        addSingleVariationGroup();
    }

    private void addNoVariationGroup() {
        addChild(new QuantityTaskTemplate(), Step1Model::getQuantityModelWithoutVariation).groups(noVariationsGroup);
        //addChild(pictures);
    }

    private void addSingleVariationGroup() {
        addChild(VariationAttributeInputModel.class, step1Model -> step1Model.getVariationAttributes().getMainAttribute()).groups(variationsGroup);
        addChildren(VariationAttributeInputModel.class, step1Model -> step1Model.getVariationAttributes().getSecondaryAttributes()).groups(variationsGroup);
        addChild(new QuantityTaskTemplate(), Step1Model::getQuantityModelWithVariation).groups(variationsGroup);
        //addChild(pictures);
    }
}
