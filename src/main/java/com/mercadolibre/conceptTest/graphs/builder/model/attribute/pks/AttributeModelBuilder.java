package com.mercadolibre.conceptTest.graphs.builder.model.attribute.pks;

import com.mercadolibre.ActionsModule;
import com.mercadolibre.actions.CategoryAttributeUtils;
import com.mercadolibre.actions.CategoryUtils;
import com.mercadolibre.actions.SiteUtils;
import com.mercadolibre.conceptTest.graphs.builder.model.ModelBuilder;
import com.mercadolibre.dto.Category;
import com.mercadolibre.dto.Country;
import com.mercadolibre.dto.Site;
import com.mercadolibre.dto.category.Vertical;
import com.mercadolibre.flux.flow.exception.GraphNavigationException;
import com.mercadolibre.flux.flow.graph.navigation.Context;
import com.mercadolibre.service.CategoryAttributeService;
import com.mercadolibre.util.providers.CategoryProvider;

/**
 * Created by msosto on 1/11/18.
 */
public class AttributeModelBuilder extends ModelBuilder{


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

    /**
     * Helper for extracting the site from context.
     *
     * @param context
     * @return
     */
    protected Site getSite(Context context) {
        Site site = siteUtils.getSite(context);
        if (site == null) {
            throw new GraphNavigationException("Site is required to get the country.");
        }

        return site;
    }

    /**
     * Getter to obtain the country from context.
     *
     * @param context
     * @return
     */
    protected Country getCountry(Context context) {
        return getCountry(getSite(context));
    }

    /**
     * Get the country from site.
     *
     * @param site
     * @return
     */
    protected Country getCountry(Site site) {
        Country country = site.getCountry();
        if (country == null) {
            throw new GraphNavigationException("Country is required to obtain decimal separator.");
        }
        return country;
    }

    public CategoryProvider getCategoryProvider() {
        return categoryProvider;
    }


    /**
     * Get the item's vertical.
     *
     * @param context
     * @return
     */
    protected Vertical getVertical(Context context) {
        final Category category = categoryUtils.getCategory(context, CategoryProvider.DATA_ITEM);
        return Vertical.getFromItemVertical(category.getSettings().getVertical());
    }

    protected String obtainDecimalSeparator(Context context) {
        return getCountry(context).getDecimalSeparator();
    }
}
