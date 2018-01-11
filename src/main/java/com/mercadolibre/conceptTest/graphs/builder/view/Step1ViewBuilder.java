package com.mercadolibre.conceptTest.graphs.builder.view;

import com.mercadolibre.conceptTest.graphs.builder.view.contracts.FinderInput;
import com.mercadolibre.conceptTest.graphs.model.FinderModel;
import com.mercadolibre.conceptTest.graphs.model.Step1Model;
import com.mercadolibre.conceptTest.graphs.model.TaskModel;
import com.mercadolibre.kisc.viewbuilder.Component;
import com.mercadolibre.kisc.viewbuilder.ViewBuilder;
import com.mercadolibre.kisc.viewbuilder.template.Template;

import java.util.function.Function;

/**
 * Created by msosto on 1/10/18.
 */
public class Step1ViewBuilder {

    public Component build(Step1Model model) {

        ViewBuilder step1View = new ViewBuilder<Step1Model, Step1Model>(Step1Model.class);
        addFinderTaskStep1(step1View);


        return step1View.build(model);
    }

    private <MODEL_TASK extends TaskModel> void addTaskView(ViewBuilder<Step1Model, Step1Model> stepView, Class<MODEL_TASK> ModelClass, Function<Step1Model, MODEL_TASK> transformer, Function<ViewBuilder, Template> bodyBuilder) {
        final ViewBuilder<MODEL_TASK, Step1Model> branch = stepView.add("task", ModelClass).transform(transformer).id("task").uiType("task")
                .branch().add("header", "task").id("header").uiType("header").branch();
        stepView = bodyBuilder.apply(stepView.);
        stepView.add("footer", "task").id("footer").uiType("footer").root();
    }

    private Template<FinderModel, FinderModel> addFinderTaks(ViewBuilder<FinderModel, FinderModel> stepView) {
        return stepView.add("finder", "task").uiType("finder").id("finder_ID")
                .mapper(m -> new FinderInput().withValue(m.getTitle()));
    }

    private void addFinderTaskStep1(ViewBuilder<Step1Model, Step1Model> stepView) {
        addTaskView(stepView, FinderModel.class, Step1Model::getFinderModel, this::addFinderTaks);
    }

}
