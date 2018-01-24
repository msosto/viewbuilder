package com.mercadolibre.supply.supplier;

import com.mercadolibre.dto.item.ItemAttribute;

import java.util.List;

/**
 * Created by mforte on 1/24/18.
 */
@FunctionalInterface
public interface ItemAttributesSupplier {

    List<ItemAttribute> getItemAttributes();
}
