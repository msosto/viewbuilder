package com.mercadolibre.conceptTest.graphs.builder.data.category;

import com.google.common.collect.Lists;
import com.mercadolibre.conceptTest.graphs.builder.view.contracts.category.BreadcrumbCategory;
import com.mercadolibre.conceptTest.graphs.builder.view.contracts.category.CategoryBreadcrumbViewContract;
import com.mercadolibre.conceptTest.graphs.model.CategorySelectionModel;
import com.mercadolibre.dto.Category;

import java.util.List;

import static com.mercadolibre.util.ContextUtils.withDots;
import static java.util.Objects.nonNull;

/**
 * Created by malizarraga on 16/1/18.
 */
public class CategoryBreadcrumbDataBuilder {

    public static final String ITEM_CATEGORY_ID = "item.category_id";

    public CategoryBreadcrumbViewContract build(CategorySelectionModel model) {
        CategoryBreadcrumbViewContract viewContract = new CategoryBreadcrumbViewContract();
        List<BreadcrumbCategory> breadcrumbCategories = Lists.newArrayList();
        Category category = model.getCategory();
        if (nonNull(category)) {
            category.getPathFromRoot().forEach(cat ->
                    breadcrumbCategories.add(new BreadcrumbCategory()
                            .withId(cat.getId())
                            .withName(cat.getName())
                            .withOutput(withDots(model.getContextId(), ITEM_CATEGORY_ID)))
            );
        }
        viewContract.withCategories(breadcrumbCategories);
        return viewContract;
    }

}
