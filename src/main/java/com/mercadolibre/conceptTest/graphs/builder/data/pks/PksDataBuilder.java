package com.mercadolibre.conceptTest.graphs.builder.data.pks;

import com.mercadolibre.conceptTest.graphs.builder.view.contracts.attribute.pks.PksViewContract;
import com.mercadolibre.conceptTest.graphs.model.PksModel;
import com.mercadolibre.dto.category.CategoryAttribute;
import com.mercadolibre.dto.category.Vertical;
import com.mercadolibre.dto.item.ItemAttribute;
import com.mercadolibre.flux.flow.graph.navigation.DataProxy;

import java.util.List;

import static java.util.Objects.nonNull;

/**
 * Created by msosto on 1/16/18.
 */
public class PksDataBuilder extends AttributeDataBuilder {

    public PksDataBuilder() {
        super();
    }

    public PksViewContract build(PksModel pksModel) {
        PksViewContract pksView = new PksViewContract();
        String categoryId = pksModel.getCategoryId();
        if (nonNull(categoryId)) {
            final List<CategoryAttribute> sortedPKAttributes = pksModel.getPksCategoryAttributes();
            final List<ItemAttribute> itemAttributes = pksModel.getItemAttributes();
            final Vertical vertical = pksModel.getVertical();
            final String siteId = pksModel.getSiteId();
            final List<DataProxy> modelPksAttributes = getCategoryAttributesValues(sortedPKAttributes, itemAttributes, vertical, siteId);

            pksView.setPksAttributes(modelPksAttributes);
            pksView.setDecimalSeparator(pksModel.getDecimalSeparator());
        }
        return pksView;
    }

}
