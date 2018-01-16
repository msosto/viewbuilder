package com.mercadolibre.conceptTest.graphs.builder.view;

import com.mercadolibre.conceptTest.graphs.builder.view.contracts.InputHiddenViewContract;
import com.mercadolibre.conceptTest.graphs.builder.view.contracts.attribute.pks.PksInput;
import com.mercadolibre.conceptTest.graphs.builder.view.contracts.category.CategoryBreadcrumbViewContract;
import com.mercadolibre.conceptTest.graphs.builder.view.contracts.category.CategorySelectionViewContract;
import com.mercadolibre.conceptTest.graphs.builder.view.contracts.finder.FinderViewContract;
import com.mercadolibre.conceptTest.graphs.model.*;
import com.mercadolibre.kisc.viewbuilder.Component;
import com.mercadolibre.kisc.viewbuilder.template.Template;
import org.apache.commons.collections.CollectionUtils;

import java.util.function.Function;

/**
 * Created by msosto on 1/10/18.
 */
public class Step1ViewBuilder {

    public static final String PKS_TASK_ID = "PKS";
    public static final String CATEGORY_SELECTION_TASK_ID = "CATEGORY_SELECTION";
    public static final String FINDER_TASK_ID = "FINDER";

    public Component build(Step1Model step1Model) {
        final Template<Step1Model, Step1Model> step1Template = Template.create(Step1Model.class).id("STEP_1").uiType("STEP");
        step1Template.addChild(getFinderTask());
        step1Template.addChild(getCategorySelectionTask());
        step1Template.addChild(getPKsTask());
        return step1Template.build(step1Model);
    }

    protected <TASK_MODEL extends TaskModel, STEP_MODEL> Template<TASK_MODEL, STEP_MODEL> getTask(Class<STEP_MODEL> stepModelClass, Class<TASK_MODEL> taskModelClass, Template<TASK_MODEL, TASK_MODEL> body, String taskId, Function<STEP_MODEL, TASK_MODEL> taskModelSupplier) {
        final Template<TASK_MODEL, STEP_MODEL> task = Template.create(null, taskModelClass, stepModelClass).modelSupplier(taskModelSupplier).id(taskId + "_TASK").uiType("TASK");
        task.addChild(HeaderModel.class, TaskModel::getHeaderModel).id("HEADER").uiType("HEADER");
        task.addChild(body);
        task.addChild(FooterModel.class, TaskModel::getFooterModel).id("FOOTER").uiType("FOOTER");
        return task;
    }

    protected Template<FinderModel, Step1Model> getFinderTask() {
        final Template<FinderModel, FinderModel> body = Template.create(FinderModel.class).id("BODY").uiType("BODY");
        body.addChild().id("FINDER").uiType("FINDER").dataBuilder(finderModel ->
                new FinderViewContract()
                        .withValue(finderModel.getTitle())
                        .withDisabled(finderModel.getDisabled())
                        .withValidationErrors(finderModel.getValidationErrors())
        );
        return getTask(Step1Model.class, FinderModel.class, body, FINDER_TASK_ID, Step1Model::getFinderModel);
    }

    protected Template<CategorySelectionModel, Step1Model> getCategorySelectionTask() {
        final Template<CategorySelectionModel, CategorySelectionModel> body = Template.create(CategorySelectionModel.class).id("BODY").uiType("BODY");
        body.addChild().id("BREADCRUMB").uiType("BREADCRUMB").apply(CategorySelectionModel::isShowCategoryBreadcrumbComponent).dataBuilder(categorySelectionModel ->
                new CategoryBreadcrumbViewContract().withCategories(categorySelectionModel.getBreadcrumbCategories())
        );
        body.addChild()
                .id("CATEGORY_SELECTION").uiType("CATEGORY_SELECTION")
                .apply(CategorySelectionModel::isShowCategorySelectionComponent)
                .dataBuilder(categorySelectionModel -> new CategorySelectionViewContract()
                        .withColumn(categorySelectionModel.getColumn())
                        .withAdultContent(categorySelectionModel.getAdultContent()));
        body.addChild().id("HIDDEN_CATEGORY_ID").uiType("HIDDEN").dataBuilder(categorySelectionModel -> new InputHiddenViewContract().withValue(categorySelectionModel.getCategoryId()).withOutput(CategorySelectionModel.ITEM_CATEGORY_ID_OUTPUT));
        body.addChild().id("HIDDEN_CATALOG_PRODUCT_ID").uiType("HIDDEN").dataBuilder(categorySelectionModel -> new InputHiddenViewContract().withValue(categorySelectionModel.getCatalogProductId()).withOutput(CategorySelectionModel.ITEM_CATALOG_PRODUCT_ID_OUTPUT));

        return getTask(Step1Model.class, CategorySelectionModel.class, body, CATEGORY_SELECTION_TASK_ID, Step1Model::getCategorySelectionModel);
    }

    protected Template<PksModel, Step1Model> getPKsTask() {
        final Template<PksModel, PksModel> body = Template.create(PksModel.class).id("BODY").uiType("BODY");
        body.addChild().id("PKS").uiType("PKS").apply(pksModel -> !CollectionUtils.isEmpty(pksModel.getPksAttributes())).dataBuilder(pksModel -> new PksInput().withPksAttributes(pksModel.getPksAttributes()).withDecimalSeparator(pksModel.getDecimalSeparator()));
        return getTask(Step1Model.class, PksModel.class, body, PKS_TASK_ID, Step1Model::getPksModel);
    }
}
