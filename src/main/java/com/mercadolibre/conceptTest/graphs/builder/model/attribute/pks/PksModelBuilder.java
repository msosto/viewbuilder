package com.mercadolibre.conceptTest.graphs.builder.model.attribute.pks;

import com.mercadolibre.conceptTest.graphs.builder.model.attribute.AttributeModelBuilder;
import com.mercadolibre.conceptTest.graphs.model.PksModel;
import com.mercadolibre.dto.category.CategoryAttribute;
import com.mercadolibre.dto.category.Vertical;
import com.mercadolibre.dto.item.ItemAttribute;
import com.mercadolibre.flux.flow.graph.navigation.Context;
import com.mercadolibre.flux.flow.graph.navigation.DataProxy;

import java.util.List;

/**
 * Created by msosto on 1/11/18.
 */
public class PksModelBuilder extends AttributeModelBuilder {


    public PksModel build(Context context) {
        PksModel model = new PksModel();

        final List<CategoryAttribute> sortedPKAttributes = getPksCategoryAttributes(context);
        final List<ItemAttribute> itemAttributes = categoryAttributeUtils.getItemAttributes(context);
        Vertical vertical = getVertical(context);
        List<DataProxy> modelPksAttributes = getCategoryAttributesValues(sortedPKAttributes, itemAttributes, vertical, getSite(context).getId());

        model.setPksAttributes(modelPksAttributes);
        model.setDecimalSeparator(obtainDecimalSeparator(context));

        return model;
    }

    private String obtainDecimalSeparator(Context context) {
        return getCountry(context).getDecimalSeparator();
    }

    private List<CategoryAttribute> getPksCategoryAttributes(Context context) {
        final List<CategoryAttribute> pkAttributes = categoryAttributeUtils.getPKsCategoryAttributesFromCategory(context, categoryProvider);
        return categoryAttributeService.sortAttributes(pkAttributes);
    }
}
