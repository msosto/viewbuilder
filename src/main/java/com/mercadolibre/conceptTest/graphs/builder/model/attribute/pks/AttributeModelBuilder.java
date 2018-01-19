package com.mercadolibre.conceptTest.graphs.builder.model.attribute.pks;

import com.mercadolibre.ActionsModule;
import com.mercadolibre.actions.CategoryAttributeUtils;
import com.mercadolibre.actions.CategoryUtils;
import com.mercadolibre.actions.SiteUtils;
import com.mercadolibre.conceptTest.graphs.builder.model.ModelBuilder;
import com.mercadolibre.dto.Category;
import com.mercadolibre.dto.category.Vertical;
import com.mercadolibre.flux.flow.graph.navigation.Context;
import com.mercadolibre.service.CategoryAttributeService;
import com.mercadolibre.util.providers.CategoryProvider;

/**
 * Created by msosto on 1/11/18.
 */
public class AttributeModelBuilder extends ModelBuilder {


    protected final SiteUtils siteUtils;
    protected final CategoryAttributeUtils categoryAttributeUtils;
    protected final CategoryProvider categoryProvider;
    protected final CategoryUtils categoryUtils;
    protected final CategoryAttributeService categoryAttributeService;

    public AttributeModelBuilder() {
        this.siteUtils = ActionsModule.get().getInstance(SiteUtils.class);
        this.categoryAttributeUtils = ActionsModule.get().getInstance(CategoryAttributeUtils.class);
        this.categoryUtils = ActionsModule.get().getInstance(CategoryUtils.class);
        this.categoryAttributeService = ActionsModule.get().getInstance(CategoryAttributeService.class);
        this.categoryProvider = CategoryProvider.DATA_ITEM;
    }

    public CategoryProvider getCategoryProvider() {
        return categoryProvider;
    }



}
