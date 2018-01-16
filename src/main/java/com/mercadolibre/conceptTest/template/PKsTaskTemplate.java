package com.mercadolibre.conceptTest.template;

import com.mercadolibre.conceptTest.graphs.builder.view.contracts.attribute.pks.PksInput;
import com.mercadolibre.conceptTest.graphs.model.PksModel;
import org.apache.commons.collections.CollectionUtils;

/**
 * Created by mforte on 1/15/18.
 */
public class PKsTaskTemplate extends TaskTemplate<PksModel> {

    public PKsTaskTemplate() {
        super(PksModel.class);
    }

    @Override
    protected String getTaskId() {
        return "FINDER";
    }

    @Override
    protected void addBody() {
        addChild()
                .id("PKS").uiType("PKS")
                .apply(pksModel -> !CollectionUtils.isEmpty(pksModel.getPksAttributes()))
                .dataBuilder(pksModel ->
                        new PksInput()
                                .withPksAttributes(pksModel.getPksAttributes())
                                .withDecimalSeparator(pksModel.getDecimalSeparator())
                );

    }
}
