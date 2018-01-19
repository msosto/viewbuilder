package com.mercadolibre.conceptTest.graphs.model.component;

import com.mercadolibre.dto.category.CategoryAttribute;
import com.mercadolibre.dto.category.Vertical;
import com.mercadolibre.dto.item.ItemAttribute;

import java.util.List;

/**
 * Represent the commons Attribute Structure
 * <p>
 * Created by mforte on 1/11/18.
 */
public interface CategoryAttributesProvider {

    String getSiteId();

    Vertical getVertical();

    List<CategoryAttribute> getCategoryAttributes();

    List<ItemAttribute> getItemAttributes();
}
