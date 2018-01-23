package com.mercadolibre.conceptTest.template.task;

import com.mercadolibre.conceptTest.data.category.breadcrumb.CategoryBreadcrumbDataBuilder;
import com.mercadolibre.conceptTest.data.category.selection.CategorySelectionDataBuilder;
import com.mercadolibre.dto.Category;
import com.mercadolibre.dto.catalog.SellCatalogSelection;

/**
 * Created by mlizarraga on 11/1/18
 */
public interface CategorySelectionTaskModel extends CategorySelectionDataBuilder.Provider, CategoryBreadcrumbDataBuilder.Provider {

    Category getCategory();

    SellCatalogSelection getSellCatalogSelection();
}
