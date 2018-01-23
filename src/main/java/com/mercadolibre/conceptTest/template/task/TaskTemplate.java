package com.mercadolibre.conceptTest.template.task;

import com.mercadolibre.conceptTest.data.footer.FooterDataBuilder;
import com.mercadolibre.conceptTest.data.header.HeaderDataBuilder;
import com.mercadolibre.kisc.viewbuilder.template.Template;

/**
 * Created by mforte on 1/15/18.
 */
public abstract class TaskTemplate<Model> extends Template<Model> {

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
        addChild().id(getTaskId() + "_FOOTER").uiType(getFooterUIType())
                .dataBuilder(footerModel -> footerDataBuilder.build(getTaskId(), null)); //TODO: Ver lo del Footer y Header;
    }

    protected void addHeader() {
        addChild().id(getTaskId() + "_HEADER").uiType(getHeaderUIType())
                .dataBuilder(headerModel -> headerDataBuilder.buildDefaultHeader(getTaskId()));
    }

    protected abstract void addBody();
}
