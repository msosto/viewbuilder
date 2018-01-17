package com.mercadolibre.conceptTest.graphs.builder.model.attribute;

import com.mercadolibre.conceptTest.graphs.builder.model.attribute.pks.AttributeModelBuilder;
import com.mercadolibre.conceptTest.graphs.model.PksModel;
import com.mercadolibre.dto.category.CategoryAttribute;
import com.mercadolibre.flux.flow.graph.navigation.Context;

import java.util.List;

/**
 * Created by msosto on 1/11/18.
 */
public class PksModelBuilder extends AttributeModelBuilder {

    public PksModel build(Context context) {
        PksModel model = new PksModel();
        model.setCategoryId(getCategoryProvider().getId(context));
        model.setPksCategoryAttributes(getPksCategoryAttributes(context));
        model.setItemAttributes(categoryAttributeUtils.getItemAttributes(context));
        model.setVertical(getVertical(context));
        model.setSiteId(getSite(context).getId());
        model.setDecimalSeparator(obtainDecimalSeparator(context));
        return model;
    }


    private List<CategoryAttribute> getPksCategoryAttributes(Context context) {
        final List<CategoryAttribute> pkAttributes = categoryAttributeUtils.getPKsCategoryAttributesFromCategory(context, categoryProvider);
        return categoryAttributeService.sortAttributes(pkAttributes);
    }


}
