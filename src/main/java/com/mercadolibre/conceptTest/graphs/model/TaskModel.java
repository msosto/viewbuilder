package com.mercadolibre.conceptTest.graphs.model;

import java.util.List;
import java.util.Map;

/**
 * Cosas comunes a todas la tareas como Header y Footer
 * Created by msosto on 1/10/18.
 */
public class TaskModel {

    private HeaderModel headerModel;
    private FooterModel footerModel;
    private List<Map<String, Object>> validationErrors;

    public List<Map<String, Object>> getValidationErrors() {
        return validationErrors;
    }

    public TaskModel setValidationErrors(List<Map<String, Object>> validationErrors) {
        this.validationErrors = validationErrors;
        return this;
    }

    public TaskModel(){
        this.headerModel = new HeaderModel();
        this.footerModel = new FooterModel();
    }

    public HeaderModel getHeaderModel() {
        return headerModel;
    }

    public FooterModel getFooterModel() {
        return footerModel;
    }

    public void setHeaderModel(HeaderModel headerModel) {
        this.headerModel = headerModel;
    }

    public void setFooterModel(FooterModel footerModel) {
        this.footerModel = footerModel;
    }
}
