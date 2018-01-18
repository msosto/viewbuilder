package com.mercadolibre.conceptTest.template;

import com.mercadolibre.conceptTest.graphs.builder.data.pks.PksDataBuilder;
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
        return "PKS";
    }

    @Override
    protected String getHeaderUIType() {
        return HEADER_UI_TYPE;
    }

    @Override
    protected String getFooterUIType() {
        return FOOTER_IN_UI_TYPE;
    }

    @Override
    protected void addBody() {
        addChild()
                .id("PKS").uiType("PKS")
                .apply(pksModel -> !CollectionUtils.isEmpty(pksModel.getPksCategoryAttributes()))
                .dataBuilder(pksModel -> new PksDataBuilder().build(pksModel));
    }
}
