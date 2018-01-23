package com.mercadolibre.conceptTest.data.attribute.pks;

import com.mercadolibre.conceptTest.data.attribute.AttributeDataBuilder;
import com.mercadolibre.conceptTest.model.component.InputModel;
import com.mercadolibre.conceptTest.template.task.PksTaskProvider;
import com.mercadolibre.dto.Country;
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

    public PksViewContract build(PksTaskProvider pksModel) {
        PksViewContract pksView = new PksViewContract();
        String categoryId = pksModel.getCategoryId();
        if (nonNull(categoryId)) {
            final List<CategoryAttribute> sortedPKAttributes = pksModel.getCategoryAttributes();
            final List<ItemAttribute> itemAttributes = pksModel.getItemAttributes();
            final Vertical vertical = pksModel.getVertical();
            final String siteId = pksModel.getSiteId();
            final List<DataProxy> modelPksAttributes = getCategoryAttributesValues(sortedPKAttributes, itemAttributes, vertical, siteId);

            pksView.setPksAttributes(modelPksAttributes);
            pksView.setDecimalSeparator(pksModel.getCountry().getDecimalSeparator());
            pksView.withValidationErrors(pksModel.getPKsInputModel().getValidationErrors());
        }
        return pksView;
    }

    public interface Provider extends AttributeDataBuilder.Provider {
        InputModel getPKsInputModel();

        String getCategoryId();

        Country getCountry();
    }

    //TODO: Refactor para independizarlo del contexto
//    private List<CategoryAttribute> getPksCategoryAttributes(Context context) {
//        final List<CategoryAttribute> pkAttributes = categoryAttributeUtils.getPKsCategoryAttributesFromCategory(context, categoryProvider);
//        return categoryAttributeService.sortAttributes(pkAttributes);
//    }

}
