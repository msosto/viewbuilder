package com.mercadolibre.conceptTest.template.task;

import com.mercadolibre.ActionsModule;
import com.mercadolibre.conceptTest.data.attribute.pks.PksDataBuilder;
import org.apache.commons.collections.CollectionUtils;

/**
 * Created by mforte on 1/15/18.
 */
public class PKsTaskTemplate extends TaskTemplate<PksTaskSupplier> {

    private final PksDataBuilder pksDataBuilder;

    public PKsTaskTemplate() {
        super(PksTaskSupplier.class);
        pksDataBuilder = ActionsModule.get().getInstance(PksDataBuilder.class);
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
                .apply(pksModel -> !CollectionUtils.isEmpty(pksModel.getCategoryAttributes()))
                .dataBuilder(pksModel -> pksDataBuilder.build(pksModel));
    }
}
