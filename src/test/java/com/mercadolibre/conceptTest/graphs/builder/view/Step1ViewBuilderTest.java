package com.mercadolibre.conceptTest.graphs.builder.view;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mercadolibre.conceptTest.model.step.Step1Model;
import com.mercadolibre.conceptTest.template.step1.Step1Template;
import com.mercadolibre.dto.category.AttributeValueType;
import com.mercadolibre.dto.category.CategoryAttribute;
import com.mercadolibre.dto.category.CategoryAttributeHierarchy;
import com.mercadolibre.flux.flow.graph.navigation.DataProxy;
import com.mercadolibre.kisc.viewbuilder.Component;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

/**
 * Created by mforte on 1/12/18.
 */
public class Step1ViewBuilderTest {

    final Gson gson = new GsonBuilder().create();

    @Test
    public void testStep1() {
        Step1Model model = buildModel();
        final Component build = new Step1Template().build(model);

        System.out.println(gson.toJson(build));
    }

    private Step1Model buildModel() {
        return new Step1Model();
    }

//    private CategorySelectionTaskSupplier getCategorySelectionModel() {
//        final CategorySelectionTaskSupplier categorySelectionModel = new CategorySelectionTaskSupplier()
//                .withCategoryId("MLA3530")
//                .withAdultContent(false)
//                .withShowCategorySelectionComponent(false)
//                .withShowCategoryBreadcrumbComponent(false);
//        categorySelectionModel.getHeaderModel().withTitle("CATEGORY_SELECTION_TITLE");
//        categorySelectionModel.getFooterModel().withButtonConnection("SAME_STEP_CONNECTION");
//        return categorySelectionModel;
//    }
//
//    private TitleModel getFinderModel() {
//        final TitleModel finderModel = new TitleModel().setTitle("Iphone 6s").setRequired(true);
//        finderModel.getHeaderModel().withTitle("FINDER_TITLE");
//        finderModel.getFooterModel().withButtonConnection("SAME_STEP_CONNECTION");
//        return finderModel;
//    }
//
//    private PksModel getPksModel() {
//        final PksModel pksModel = new PksModel()
//                .setPksAttributes(getListOfAttributes())
//                .setDecimalSeparator(",");
//        pksModel.getHeaderModel().withTitle(PksModelBuilder.HEADER_TITLE);
//        pksModel.getFooterModel().withButtonText(PksModelBuilder.FOOTER_BTN_TEXT);
//        return pksModel;
//    }

    private List<DataProxy> getListOfAttributes() {

        List list = Lists.newArrayList();
        CategoryAttribute modelAttr = new CategoryAttribute()
                .setId("MODEL")
                .setName("Modelo")
                .setValueType(AttributeValueType.STRING)
                .setTags(Collections.singletonMap("product_pk", true))
                .setValueMaxLength(60)
                .setHierarchy(CategoryAttributeHierarchy.PARENT_PK)
                .setAttributeGroupId("MAIN")
                .setAttributeGroupName("Principales");

        list.add(modelAttr.toMap());

        return list;
    }
}