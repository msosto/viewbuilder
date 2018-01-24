package com.mercadolibre.conceptTest.todo;

import com.mercadolibre.dto.catalog.CatalogProductAttribute;

import java.util.List;

/**
 * Created by mforte on 1/10/18.
 */
public class SingleVariationModel {

    private List<CatalogProductAttribute> catalogProductAttributes;

    public List<CatalogProductAttribute> getCatalogProductAttributes() {
        return catalogProductAttributes;
    }

    public SingleVariationModel setCatalogProductAttributes(List<CatalogProductAttribute> catalogProductAttributes) {
        this.catalogProductAttributes = catalogProductAttributes;
        return this;
    }
}
