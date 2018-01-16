package com.mercadolibre.conceptTest.template;

import com.mercadolibre.conceptTest.graphs.model.FooterModel;
import com.mercadolibre.conceptTest.graphs.model.HeaderModel;
import com.mercadolibre.conceptTest.graphs.model.TaskModel;
import com.mercadolibre.kisc.viewbuilder.template.Template;

/**
 * Created by mforte on 1/15/18.
 */
public abstract class TaskTemplate<Model extends TaskModel> extends Template<Model> {

    public TaskTemplate(Class<Model> modelType) {
        super(modelType);
    }

    @Override
    protected void createTemplate() {
        id(getTaskId() + "_TASK").uiType("TASK");
        addHeader();
        addBody();
        addFooter();
    }

    protected abstract String getTaskId();

    protected void addFooter() {
        addChild(FooterModel.class, TaskModel::getFooterModel).id(getTaskId() + "FOOTER").uiType("FOOTER");
    }

    protected void addHeader() {
        addChild(HeaderModel.class, TaskModel::getHeaderModel).id(getTaskId() + "HEADER").uiType("HEADER");
    }

    protected abstract void addBody();
}
