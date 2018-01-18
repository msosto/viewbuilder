package com.mercadolibre.conceptTest.graphs.builder.model.attribute;

import com.mercadolibre.conceptTest.graphs.builder.model.attribute.pks.AttributeModelBuilder;
import com.mercadolibre.conceptTest.graphs.model.PksModel;
import com.mercadolibre.dto.category.CategoryAttribute;
import com.mercadolibre.flux.flow.graph.navigation.Context;

import java.util.List;

import static java.util.Objects.nonNull;

/**
 * ModelBuilder is in charge of creating a MOdel with all the neccessary data for the dataBuilder. This class knows how to get the data from the
 * context. It's coupled to flux.
 * Created by msosto on 1/11/18.
 */
public class PksModelBuilder extends AttributeModelBuilder {

    public PksModel build(Context context) {
        PksModel model = new PksModel();
        final String categoryId = getCategoryProvider().getId(context);
        if(nonNull(categoryId)){    // In case Finder couldn't predict any category.
            model.setCategoryId(categoryId);
            model.setPksCategoryAttributes(getPksCategoryAttributes(context));
            model.setItemAttributes(categoryAttributeUtils.getItemAttributes(context));
            model.setVertical(getVertical(context));
            model.setSiteId(getSite(context).getId());
            model.setDecimalSeparator(obtainDecimalSeparator(context));
            model.setValidationErrors(obtainValidationErrors(context,"pks_attribute"));
        }
        return model;
    }

    private List<CategoryAttribute> getPksCategoryAttributes(Context context) {
        final List<CategoryAttribute> pkAttributes = categoryAttributeUtils.getPKsCategoryAttributesFromCategory(context, categoryProvider);
        return categoryAttributeService.sortAttributes(pkAttributes);
    }

}
