package com.mercadolibre.conceptTest.graphs.builder.data;

import com.mercadolibre.conceptTest.graphs.builder.view.contracts.FooterViewContract;
import com.mercadolibre.conceptTest.graphs.model.FooterModel;

/**
 * Created by mlizarraga on 17/1/18
 */
public class FooterDataBuilder {

    public FooterViewContract build(FooterModel model) {
        FooterViewContract viewContract = new FooterViewContract()
                .withButtonText(model.getButtonText())
                .withButtonConnection(model.getButtonConnection());
        return viewContract;
    }
}
