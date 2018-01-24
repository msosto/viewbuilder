package com.mercadolibre.conceptTest.todo.component;

import com.mercadolibre.conceptTest.model.component.InputModel;
import com.mercadolibre.dto.category.CategoryAttribute;
import com.mercadolibre.dto.item.ItemAttribute;

import java.util.List;

/**
 * Created by mforte on 1/22/18.
 */
public class VariationAttributeInputModel extends InputModel {

    private List<ItemAttribute> values;
    private CategoryAttribute attribute;

    public CategoryAttribute getAttribute() {
        return attribute;
    }

    public VariationAttributeInputModel setAttribute(CategoryAttribute attribute) {
        this.attribute = attribute;
        return this;
    }

    public List<ItemAttribute> getValues() {
        return values;
    }

    public VariationAttributeInputModel setValues(List<ItemAttribute> values) {
        this.values = values;
        return this;
    }
}
