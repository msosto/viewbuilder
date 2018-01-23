package com.mercadolibre.conceptTest.todo.component;

import java.util.List;

/**
 * Created by mforte on 1/22/18.
 */
public class VariationAttributesModel {

    private VariationAttributeInputModel mainAttribute;
    private List<VariationAttributeInputModel> secondaryAttributes;

    public VariationAttributeInputModel getMainAttribute() {
        return mainAttribute;
    }

    public VariationAttributesModel setMainAttribute(VariationAttributeInputModel mainAttribute) {
        this.mainAttribute = mainAttribute;
        return this;
    }

    public List<VariationAttributeInputModel> getSecondaryAttributes() {
        return secondaryAttributes;
    }

    public VariationAttributesModel setSecondaryAttributes(List<VariationAttributeInputModel> secondaryAttributes) {
        this.secondaryAttributes = secondaryAttributes;
        return this;
    }
}
