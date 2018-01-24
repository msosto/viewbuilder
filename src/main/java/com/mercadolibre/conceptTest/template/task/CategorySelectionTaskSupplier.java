package com.mercadolibre.conceptTest.template.task;

import com.mercadolibre.conceptTest.data.category.breadcrumb.CategoryBreadcrumbDataBuilder;
import com.mercadolibre.conceptTest.data.category.selection.CategorySelectionDataBuilder;
import com.mercadolibre.supply.supplier.CategorySupplier;
import com.mercadolibre.supply.supplier.SellCatalogSelectionSupplier;

/**
 * Created by mlizarraga on 11/1/18
 */
public interface CategorySelectionTaskSupplier extends CategorySupplier,
        SellCatalogSelectionSupplier,
        CategorySelectionDataBuilder.Provider,
        CategoryBreadcrumbDataBuilder.Provider {
}
