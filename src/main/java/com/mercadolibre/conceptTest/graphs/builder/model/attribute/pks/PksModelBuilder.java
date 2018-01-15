package com.mercadolibre.conceptTest.graphs.builder.model.attribute.pks;

import com.mercadolibre.conceptTest.graphs.builder.model.attribute.AttributeModelBuilder;
import com.mercadolibre.conceptTest.graphs.model.FooterModel;
import com.mercadolibre.conceptTest.graphs.model.HeaderModel;
import com.mercadolibre.conceptTest.graphs.model.PksModel;
import com.mercadolibre.dto.category.CategoryAttribute;
import com.mercadolibre.dto.category.Vertical;
import com.mercadolibre.dto.item.ItemAttribute;
import com.mercadolibre.flux.flow.graph.navigation.Context;
import com.mercadolibre.flux.flow.graph.navigation.DataProxy;
import com.mercadolibre.util.providers.CategoryProvider;

import java.util.List;

import static java.util.Objects.nonNull;

/**
 * Created by msosto on 1/11/18.
 */
public class PksModelBuilder extends AttributeModelBuilder {

    public static final String HEADER_TITLE = "PKS_HEADER_TITLE";
    public static final String FOOTER_BTN_TEXT = "PKS_FOOTER_BUTTON_TEXT";

    public PksModelBuilder() {
        super();
    }

    public PksModel build(Context context) {
        PksModel model = new PksModel();
        String categoryId = CategoryProvider.DATA_ITEM.getId(context);
        if (nonNull(categoryId)) {
            final List<CategoryAttribute> sortedPKAttributes = getPksCategoryAttributes(context);
            final List<ItemAttribute> itemAttributes = categoryAttributeUtils.getItemAttributes(context);
            Vertical vertical = getVertical(context);
            List<DataProxy> modelPksAttributes = getCategoryAttributesValues(sortedPKAttributes, itemAttributes, vertical, getSite(context).getId());

            model.setPksAttributes(modelPksAttributes);
            model.setDecimalSeparator(obtainDecimalSeparator(context));
            addHeader(model.getHeaderModel());
            addFooter(model.getFooterModel());
        }
        return model;
    }

    private String obtainDecimalSeparator(Context context) {
        return getCountry(context).getDecimalSeparator();
    }

    private List<CategoryAttribute> getPksCategoryAttributes(Context context) {
        final List<CategoryAttribute> pkAttributes = categoryAttributeUtils.getPKsCategoryAttributesFromCategory(context, categoryProvider);
        return categoryAttributeService.sortAttributes(pkAttributes);
    }

    private void addHeader(HeaderModel headerModel) {
        headerModel.withTitle(HEADER_TITLE);

    }

    private void addFooter(FooterModel footerModel) {
        footerModel.withButtonText(FOOTER_BTN_TEXT);
        footerModel.withButtonConnection("continue");
    }

}
