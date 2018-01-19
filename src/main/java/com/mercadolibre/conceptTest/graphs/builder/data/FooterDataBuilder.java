package com.mercadolibre.conceptTest.graphs.builder.data;

import com.mercadolibre.conceptTest.graphs.builder.view.contracts.FooterViewContract;
import com.mercadolibre.conceptTest.graphs.model.FooterModel;

/**
 * Created by mlizarraga on 17/1/18
 */
public class FooterDataBuilder {

    public static final String FOOTER_BTN_TEXT_BASE = "_FOOTER_BUTTON_TEXT";

    public FooterViewContract build(String taskId, String connection) {
        FooterViewContract viewContract = new FooterViewContract()
                .withButtonText(taskId + FOOTER_BTN_TEXT_BASE)
                .withButtonConnection(connection);
        return viewContract;
    }
}
