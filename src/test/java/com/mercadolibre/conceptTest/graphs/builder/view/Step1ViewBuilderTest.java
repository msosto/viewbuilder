package com.mercadolibre.conceptTest.graphs.builder.view;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mercadolibre.conceptTest.graphs.model.CategorySelectionModel;
import com.mercadolibre.conceptTest.graphs.model.FinderModel;
import com.mercadolibre.conceptTest.graphs.model.Step1Model;
import com.mercadolibre.kisc.viewbuilder.Component;
import org.junit.Test;

/**
 * Created by mforte on 1/12/18.
 */
public class Step1ViewBuilderTest {

    final Gson gson = new GsonBuilder().create();

    @Test
    public void testStep1() {
        Step1Model model = buildModel();
        final Component build = new Step1ViewBuilder().build(model);

        System.out.println(gson.toJson(build));
    }

    private Step1Model buildModel() {
        return new Step1Model()
                .setFinderModel(getFinderModel())
                .setCategorySelectionModel(getCategorySelectionModel());
    }

    private CategorySelectionModel getCategorySelectionModel() {
        final CategorySelectionModel categorySelectionModel = new CategorySelectionModel()
                .withCategoryId("MLA3530")
                .withAdultContent(false)
                .withShowCategorySelectionComponent(false)
                .withShowCategoryBreadcrumbComponent(false);
        categorySelectionModel.getHeaderModel().withTitle("CATEGORY_SELECTION_TITLE");
        categorySelectionModel.getFooterModel().withButtonConnection("SAME_STEP_CONNECTION");
        return categorySelectionModel;
    }

    private FinderModel getFinderModel() {
        final FinderModel finderModel = new FinderModel().setTitle("Iphone 6s").setRequired(true);
        finderModel.getHeaderModel().withTitle("FINDER_TITLE");
        finderModel.getFooterModel().withButtonConnection("SAME_STEP_CONNECTION");
        return finderModel;
    }
}