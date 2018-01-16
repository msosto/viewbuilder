package com.mercadolibre.conceptTest.graphs.builder.model.category;

import com.mercadolibre.ActionsModule;
import com.mercadolibre.actions.CategoryUtils;
import com.mercadolibre.conceptTest.graphs.model.CategorySelectionModel;

import com.mercadolibre.dto.Category;
import com.mercadolibre.flux.flow.graph.navigation.Context;
import com.mercadolibre.util.providers.CatalogProductProvider;
import com.mercadolibre.util.providers.CategoryProvider;

import static com.mercadolibre.util.ContextUtils.withDots;

/**
 * Created by mlizarraga on 11/1/18
 */
public class CategorySelectionModelBuilder {

    private CategorySelectionModel model;
    private CategoryUtils categoryUtils;

    public CategorySelectionModelBuilder(){
        model = new CategorySelectionModel();
        categoryUtils = ActionsModule.get().getInstance(CategoryUtils.class);
    }

    public CategorySelectionModel build(Context context) {
        Category category = categoryUtils.getCategory(context, CategoryProvider.DATA_ITEM);
        model.setCategory(category);
        model.setContextId(context.getId());
        return model;
    }

}
