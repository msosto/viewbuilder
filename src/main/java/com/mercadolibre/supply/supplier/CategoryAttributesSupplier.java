package com.mercadolibre.supply.supplier;

import com.mercadolibre.dto.category.CategoryAttribute;

import java.util.List;

/**
 * Created by mforte on 1/24/18.
 */
@FunctionalInterface
public interface CategoryAttributesSupplier {

    List<CategoryAttribute> getCategoryAttributes();
}
