package com.mercadolibre.conceptTest.data.attribute.pks;

import com.mercadolibre.ActionsModule;
import com.mercadolibre.conceptTest.data.attribute.AttributeDataBuilder;
import com.mercadolibre.conceptTest.model.supplier.PKsInputModelSupplier;
import com.mercadolibre.dto.category.CategoryAttribute;
import com.mercadolibre.dto.category.Vertical;
import com.mercadolibre.dto.item.ItemAttribute;
import com.mercadolibre.flux.flow.graph.navigation.DataProxy;
import com.mercadolibre.service.CategoryAttributeService;
import com.mercadolibre.supply.supplier.CategoryIdSupplier;
import com.mercadolibre.supply.supplier.CountrySupplier;

import java.util.List;

import static java.util.Objects.nonNull;

/**
 * Created by msosto on 1/16/18.
 */
public class PksDataBuilder extends AttributeDataBuilder {

    private final CategoryAttributeService categoryAttributeService;

    public PksDataBuilder() {
        this.categoryAttributeService = ActionsModule.get().getInstance(CategoryAttributeService.class);
    }

    public PksViewContract build(Provider pksModel) {
        PksViewContract pksView = new PksViewContract();
        String categoryId = pksModel.getCategoryId();
        if (nonNull(categoryId)) {
            final List<CategoryAttribute> sortedPKAttributes = getPksCategoryAttributes(pksModel);
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

    private List<CategoryAttribute> getPksCategoryAttributes(Provider model) {
        final List<CategoryAttribute> categoryAttributes = model.getCategoryAttributes();
        //TODO: Separar la logica de buscar la data de filtrar los PKs y reutilizarla
        //final List<CategoryAttribute> pkAttributes = categoryAttributeUtils.getPKsCategoryAttributesFromCategory(context, categoryProvider);
        return categoryAttributeService.sortAttributes(categoryAttributes);
    }

    public interface Provider extends AttributeDataBuilder.Provider,
            CategoryIdSupplier,
            CountrySupplier,
            PKsInputModelSupplier {
    }
}
