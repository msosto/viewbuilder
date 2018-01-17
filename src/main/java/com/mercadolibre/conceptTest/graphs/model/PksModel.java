package com.mercadolibre.conceptTest.graphs.model;

import com.mercadolibre.dto.category.CategoryAttribute;
import com.mercadolibre.dto.category.Vertical;
import com.mercadolibre.dto.item.ItemAttribute;

import java.util.List;

/**
 * Created by msosto on 1/11/18.
 */
public class PksModel extends TaskModel {

    List<CategoryAttribute> pksCategoryAttributes;
    List<ItemAttribute> itemAttributes;
    String siteId;
    Vertical vertical;
    String categoryId;
    String decimalSeparator;

    public String getSiteId() {
        return siteId;
    }

    public PksModel setSiteId(String siteId) {
        this.siteId = siteId;
        return this;
    }

    public String getDecimalSeparator() {
        return decimalSeparator;
    }

    public PksModel setDecimalSeparator(String decimalSeparator) {
        this.decimalSeparator = decimalSeparator;
        return this;
    }

    public List<CategoryAttribute> getPksCategoryAttributes() {
        return pksCategoryAttributes;
    }

    public PksModel setPksCategoryAttributes(List<CategoryAttribute> pksCategoryAttributes) {
        this.pksCategoryAttributes = pksCategoryAttributes;
        return this;
    }

    public List<ItemAttribute> getItemAttributes() {
        return itemAttributes;
    }

    public PksModel setItemAttributes(List<ItemAttribute> itemAttributes) {
        this.itemAttributes = itemAttributes;
        return this;
    }

    public Vertical getVertical() {
        return vertical;
    }

    public PksModel setVertical(Vertical vertical) {
        this.vertical = vertical;
        return this;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public PksModel setCategoryId(String categoryId) {
        this.categoryId = categoryId;
        return this;
    }
}
