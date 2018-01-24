package com.mercadolibre.supply.supplier;

import com.mercadolibre.dto.category.Vertical;

/**
 * Created by mforte on 1/24/18.
 */
@FunctionalInterface
public interface VerticalSupplier {

    Vertical getVertical();
}
