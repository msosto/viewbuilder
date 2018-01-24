package com.mercadolibre.supply.supplier;

import com.mercadolibre.dto.catalog.SellCatalogSelection;

/**
 * Created by mforte on 1/24/18.
 */
@FunctionalInterface
public interface SellCatalogSelectionSupplier {

    SellCatalogSelection getSellCatalogSelection();
}
