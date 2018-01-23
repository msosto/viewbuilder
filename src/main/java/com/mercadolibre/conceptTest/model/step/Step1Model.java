package com.mercadolibre.conceptTest.model.step;

import com.mercadolibre.conceptTest.model.component.title.TitleModel;
import com.mercadolibre.conceptTest.todo.QuantityModel;
import com.mercadolibre.conceptTest.todo.VariationAttributesProvider;
import com.mercadolibre.conceptTest.todo.component.*;
import com.mercadolibre.conceptTest.template.task.FinderTaskProvider;
import com.mercadolibre.conceptTest.model.component.InputModel;
import com.mercadolibre.conceptTest.template.task.CategorySelectionTaskModel;
import com.mercadolibre.conceptTest.template.task.PksTaskProvider;
import com.mercadolibre.dto.Category;
import com.mercadolibre.dto.Country;
import com.mercadolibre.dto.catalog.SellCatalogSelection;
import com.mercadolibre.dto.category.CategoryAttribute;
import com.mercadolibre.dto.category.Vertical;
import com.mercadolibre.dto.item.ItemAttribute;

import java.util.List;

/**
 * Created by msosto on 1/10/18.
 */
public class Step1Model implements FinderTaskProvider, CategorySelectionTaskModel, PksTaskProvider, VariationAttributesProvider {


    private InputModel pksInputModel;
    private TitleModel titleInputModel;

    //TODO: migrar estos pasos
    private QuantityModel quantityModel;

    private Category category;
    private SellCatalogSelection sellCatalogSelection;
    private String contextId;
    private String categoryId;
    private String catalogProductId;
    private Country country;
    private String siteId;
    private Vertical vertical;
    private List<CategoryAttribute> categoryAttributes;
    private List<ItemAttribute> itemAttributes;
    private VariationAttributesModel variationAttributes;

    @Override
    public TitleModel getTitleInput() {
        return titleInputModel;
    }

    @Override
    public Category getCategory() {
        return category;
    }

    @Override
    public String getContextId() {
        return contextId;
    }

    @Override
    public SellCatalogSelection getSellCatalogSelection() {
        return sellCatalogSelection;
    }

    @Override
    public String getCategoryId() {
        return categoryId;
    }

    @Override
    public String getCatalogProductId() {
        return catalogProductId;
    }

    @Override
    public Country getCountry() {
        return country;
    }

    @Override
    public InputModel getPKsInputModel() {
        return pksInputModel;
    }

    @Override
    public String getSiteId() {
        return siteId;
    }

    @Override
    public Vertical getVertical() {
        return vertical;
    }

    @Override
    public List<CategoryAttribute> getCategoryAttributes() {
        return categoryAttributes;
    }

    @Override
    public List<ItemAttribute> getItemAttributes() {
        return itemAttributes;
    }

    @Override
    public VariationAttributesModel getVariationAttributes() {
        return variationAttributes;
    }

    public Step1Model setVariationAttributesInput(VariationAttributesModel variationAttributes) {
        this.variationAttributes = variationAttributes;
        return this;
    }

    public Step1Model setPksInputModel(InputModel pksInputModel) {
        this.pksInputModel = pksInputModel;
        return this;
    }

    public Step1Model setSiteId(String siteId) {
        this.siteId = siteId;
        return this;
    }

    public Step1Model setVertical(Vertical vertical) {
        this.vertical = vertical;
        return this;
    }

    public Step1Model setCategoryAttributes(List<CategoryAttribute> categoryAttributes) {
        this.categoryAttributes = categoryAttributes;
        return this;
    }

    public Step1Model setItemAttributes(List<ItemAttribute> itemAttributes) {
        this.itemAttributes = itemAttributes;
        return this;
    }

    public Step1Model setTitleInputModel(TitleModel titleInputModel) {
        this.titleInputModel = titleInputModel;
        return this;
    }

    public Step1Model setPKsInputModel(InputModel inputModel) {
        this.pksInputModel = inputModel;
        return this;
    }

    public Step1Model setCategory(Category category) {
        this.category = category;
        return this;
    }

    public Step1Model setSellCatalogSelection(SellCatalogSelection sellCatalogSelection) {
        this.sellCatalogSelection = sellCatalogSelection;
        return this;
    }

    public Step1Model setContextId(String contextId) {
        this.contextId = contextId;
        return this;
    }


    public Step1Model setCategoryId(String categoryId) {
        this.categoryId = categoryId;
        return this;
    }


    public Step1Model setCatalogProductId(String catalogProductId) {
        this.catalogProductId = catalogProductId;
        return this;
    }

    public Step1Model setCountry(Country country) {
        this.country = country;
        return this;
    }

    /*******/

    public QuantityModel getQuantityModel() {
        return quantityModel;
    }

    public Step1Model setQuantityModel(QuantityModel quantityModel) {
        this.quantityModel = quantityModel;
        return this;
    }

    public QuantityModel getQuantityModelWithoutVariation() {
        return quantityModel.newWithOutput("item.available_quantity");
    }

    public QuantityModel getQuantityModelWithVariation() {
        return quantityModel.newWithOutput("item.variations[0].available_quantity");
    }
}
