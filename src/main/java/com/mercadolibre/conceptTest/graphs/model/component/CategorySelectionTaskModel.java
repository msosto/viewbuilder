package com.mercadolibre.conceptTest.graphs.model.component;

import com.mercadolibre.dto.Category;
import com.mercadolibre.dto.Country;
import com.mercadolibre.dto.catalog.SellCatalogSelection;

/**
 * Created by mlizarraga on 11/1/18
 */
public interface CategorySelectionTaskModel {

    Category getCategory();

    String getContextId();

    SellCatalogSelection getSellCatalogSelection();

    String getCategoryId();

    String getCatalogProductId();

    Country getCountry();
}
