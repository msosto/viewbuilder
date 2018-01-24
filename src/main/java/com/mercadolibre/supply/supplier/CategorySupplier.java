package com.mercadolibre.supply.supplier;

import com.mercadolibre.dto.Category;

/**
 * Created by mforte on 1/24/18.
 */
@FunctionalInterface
public interface CategorySupplier {

    Category getCategory();
}
