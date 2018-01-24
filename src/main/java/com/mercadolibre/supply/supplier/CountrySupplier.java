package com.mercadolibre.supply.supplier;

import com.mercadolibre.dto.Country;

/**
 * Created by mforte on 1/24/18.
 */
@FunctionalInterface
public interface CountrySupplier {

    Country getCountry();
}
