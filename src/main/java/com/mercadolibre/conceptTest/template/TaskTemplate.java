package com.mercadolibre.conceptTest.template;

import com.mercadolibre.conceptTest.graphs.builder.data.FooterDataBuilder;
import com.mercadolibre.conceptTest.graphs.builder.data.HeaderDataBuilder;
import com.mercadolibre.conceptTest.graphs.builder.view.contracts.FooterViewContract;
import com.mercadolibre.conceptTest.graphs.model.FooterModel;
import com.mercadolibre.conceptTest.graphs.model.HeaderModel;
import com.mercadolibre.conceptTest.graphs.model.TaskModel;
import com.mercadolibre.kisc.viewbuilder.template.Template;

/**
 * Created by mforte on 1/15/18.
 */
public abstract class TaskTemplate<Model extends TaskModel> extends Template<Model> {

    public static final String HEADER_UI_TYPE = "header";
    public static final String HEADER_LINE_UI_TYPE = "header_line";
    public static final String FOOTER_IN_UI_TYPE = "footer_in";
    public static final String FOOTER_OUT_UI_TYPE = "footer_out";

    protected HeaderDataBuilder headerDataBuilder;
    protected FooterDataBuilder footerDataBuilder;

    public TaskTemplate(Class<Model> modelType) {
        super(modelType);
        headerDataBuilder = new HeaderDataBuilder();
        footerDataBuilder = new FooterDataBuilder();
    }

    @Override
    protected void createTemplate() {
        id(getTaskId() + "_TASK").uiType("TASK");
        addHeader();
        addBody();
        addFooter();
    }

    protected abstract String getTaskId();

    protected abstract String getHeaderUIType();

    protected abstract String getFooterUIType();

    protected void addFooter() {
        addChild(FooterModel.class, TaskModel::getFooterModel).id(getTaskId() + "FOOTER").uiType(getFooterUIType())
                .dataBuilder(footerModel -> footerDataBuilder.build(footerModel))
                .apply(footerModel -> footerModel.show());
    }

    protected void addHeader() {
        addChild(HeaderModel.class, TaskModel::getHeaderModel).id(getTaskId() + "HEADER").uiType(getHeaderUIType())
                .dataBuilder(headerModel -> headerDataBuilder.build(headerModel));
    }

    protected abstract void addBody();
}
