package com.mercadolibre.conceptTest.data.attribute;

import com.mercadolibre.ActionsModule;
import com.mercadolibre.actions.CategoryAttributeUtils;
import com.mercadolibre.config.Config;
import com.mercadolibre.dto.catalog.CatalogProductAttribute;
import com.mercadolibre.dto.category.CategoryAttribute;
import com.mercadolibre.dto.category.Vertical;
import com.mercadolibre.dto.item.ItemAttribute;
import com.mercadolibre.flux.flow.graph.navigation.DataProxy;
import com.mercadolibre.supply.supplier.CategoryAttributesSupplier;
import com.mercadolibre.supply.supplier.ItemAttributesSupplier;
import com.mercadolibre.supply.supplier.SiteIdSupplier;
import com.mercadolibre.supply.supplier.VerticalSupplier;
import com.mercadolibre.util.ContextUtils;

import java.util.*;

import static java.util.Objects.nonNull;

/**
 * Created by msosto on 1/17/18.
 */
public abstract class AttributeDataBuilder {

    public static final String SELECTED_VALUES = "selected_values";
    public static final String TYPE = "type";
    public static final String ID_TEXT = "id";
    public static final String NAME_TEXT = "name";
    public static final String VALUE_ID = "value_id";
    public static final String VALUE_TYPE = "value_type";
    public static final String VALUE_NAME = "value_name";
    public static final String METADATA = "metadata";
    public static final String CATALOG_KEY = "from_catalog";
    public static final String VALUES = "values";
    public static final String LIST_EDITABLE = "list_editable";
    public static final String DEFAULT_PRIMARY_COLOR_ID = "83000";
    public static final String COLOR = "COLOR";
    public static final String SIZE = "size";
    protected final CategoryAttributeUtils categoryAttributeUtils;

    public AttributeDataBuilder() {
        this.categoryAttributeUtils = ActionsModule.get().getInstance(CategoryAttributeUtils.class);
    }

    /**
     * Atributos que existen en la categoría
     *
     * @param categoryAttributes
     * @param itemAttributes
     * @param vertical
     * @return list of itemAttributes
     */
    protected List<DataProxy> getCategoryAttributesValues(List<CategoryAttribute> categoryAttributes, List<ItemAttribute> itemAttributes, Vertical vertical, String siteId) {
        return getCategoryAttributesValues(categoryAttributes, itemAttributes, vertical, siteId, Collections.emptyList());
    }

    protected List<DataProxy> getCategoryAttributesValues(List<CategoryAttribute> categoryAttributes, List<ItemAttribute> itemAttributes, Vertical vertical, String siteId, List<CatalogProductAttribute> catalogProductAttributes) {

        final List<DataProxy> categoryAttributesList = new ArrayList<>();

        if (categoryAttributes != null) {
            categoryAttributes.forEach(ca -> {
                categoryAttributesList.add(buildCategoryAttribute(ca, itemAttributes, vertical, catalogProductAttributes, siteId));
            });
        }
        return categoryAttributesList;
    }

    /**
     * Convierte el atributo a DataProxy.
     */
    protected DataProxy buildCategoryAttribute(CategoryAttribute categoryAttribute, List<ItemAttribute> itemAttributes, Vertical vertical, List<CatalogProductAttribute> catalogProdAttrList, String siteId) {
        final List<Map<String, Object>> attributesValues = new ArrayList<>();
        final DataProxy categoryAttrDp = categoryAttribute.toMap();
        if (itemAttributes != null) {
            addMatchingAttributes(categoryAttrDp, categoryAttribute, itemAttributes, attributesValues);
        }
        setAttrValueType(categoryAttrDp, categoryAttribute, siteId);
        searchAndAddKeyToCatalogProdAttributes(categoryAttrDp, categoryAttribute, catalogProdAttrList);    // add "from_catalog" key to those attributes values which are in catalog iterative search.
        if (!attributesValues.isEmpty()) {
            setAttributeType(categoryAttribute, vertical);
        }
        return categoryAttrDp;
    }

    /**
     * Set the corresponding value_type.
     * Special cases: COLOR , SIZE are "list_editable".
     *
     * @param caDP
     * @param ca
     */
    protected void setAttrValueType(DataProxy caDP, CategoryAttribute ca, String siteId) {

        String primaryColorId = Config.get().getString(ContextUtils.withDots("app.apparel.primary.color", siteId.toUpperCase()), DEFAULT_PRIMARY_COLOR_ID);
        final String attributeId = ca.getId();
        if (attributeId != null) {
            if (isColorOrPrimaryColor(attributeId, primaryColorId) || isSize(ca)) {
                caDP.resetPointer().putAt(VALUE_TYPE, LIST_EDITABLE);
            }
        }

    }

    private boolean isColorOrPrimaryColor(String attrId, String primaryColorId) {
        return (attrId.equals(primaryColorId) || COLOR.equalsIgnoreCase(attrId));
    }

    private boolean isSize(CategoryAttribute ca) {
        return SIZE.equalsIgnoreCase(ca.getType());
    }

    /**
     * Look for those item attributes which match with category(pk) attributes. If so, then add "selected_values" to the category attribute.
     *
     * @param dp
     * @param itemAttributes
     * @param attributesValues
     */
    private void addMatchingAttributes(DataProxy dp, CategoryAttribute ca, List<ItemAttribute> itemAttributes, List attributesValues) {
        itemAttributes.stream().filter(ia -> ca.getId().equalsIgnoreCase(ia.getId())).forEach(ia -> {
            getAttributesValues(attributesValues, ca, ia);
            dp.resetPointer().putAt(SELECTED_VALUES, attributesValues);
        });
    }

    /**
     * Add key "from_catalog" to those category attribute values which are in catalog iterative search.
     *
     * @param dp
     * @param ca
     * @param catalogProductAttributes
     */
    protected void searchAndAddKeyToCatalogProdAttributes(DataProxy dp, CategoryAttribute ca, List<CatalogProductAttribute> catalogProductAttributes) {

        if (nonNull(catalogProductAttributes)) {
            catalogProductAttributes
                    .stream()
                    .filter(catalogProdAttr -> nonNull(catalogProdAttr.getId()) && nonNull(catalogProdAttr.getValueId()))
                    .filter(catalogProdAttr -> catalogProdAttr.getId().equals(ca.getId()))
                    .forEach(catalogProdAttr -> {
                        if (getListOfValues(dp) != null) {
                            Optional<Map<String, Object>> attrValue = getListOfValues(dp)
                                    .stream()
                                    .filter(value -> catalogProdAttr.getValueId().equals(value.get(ID_TEXT)))
                                    .findFirst();
                            if (attrValue.isPresent()) { // agrego solamente la key
                                addCatalogKeyToValue(dp, attrValue.get());
                            } else {
                                addNewCatalogValueToAttrValues(dp, catalogProdAttr);
                            }

                        } else { // hay que setearle un nueva lista de values unicamente con el del catalogo.
                            Map newCatalogValue = buildCatalogValue(catalogProdAttr);
                            dp.resetPointer().createListAt(VALUES).addToList(newCatalogValue);
                        }
                    });

        }
    }

    private List<Map<String, Object>> getListOfValues(DataProxy dp) {
        return dp.resetPointer().access(VALUES).getSafeListValue();
    }

    private void addCatalogKeyToValue(DataProxy ca, Map<String, Object> attrValue) {
        String valueId = (String) attrValue.get(ID_TEXT);
        ca.resetPointer().access("values<id:" + valueId).putAt(CATALOG_KEY, true);
    }

    private void addNewCatalogValueToAttrValues(DataProxy dp, CatalogProductAttribute catalogProdAttr) {
        final List<Map<String, Object>> listOfValues = getListOfValues(dp);
        listOfValues.add(buildCatalogValue(catalogProdAttr));
        dp.resetPointer().putAt(VALUES, listOfValues);
    }

    private Map<String, Object> buildCatalogValue(CatalogProductAttribute catalogProdAttr) {
        final Map<String, Object> newValue = new HashMap<>();
        newValue.put(ID_TEXT, catalogProdAttr.getValueId());
        newValue.put(NAME_TEXT, catalogProdAttr.getValueName());
        newValue.put(CATALOG_KEY, true);
        return newValue;
    }

    /**
     * Set attribute type depending on the attribute type, value type and the category's vertical.
     *
     * @param ca
     * @param vertical
     */
    private void setAttributeType(CategoryAttribute ca, Vertical vertical) {
        ca.toMap().resetPointer().putAt(TYPE, ca.getVariationType(vertical, ca.getValueType(), ca.getType())); //get type
    }

    /**
     * Get attributes values
     *
     * @param categoryAttribute
     * @param itemAttribute
     * @return This, using fluent pattern.
     */
    private List<Map<String, Object>> getAttributesValues(List<Map<String, Object>> selectedValues, CategoryAttribute categoryAttribute, ItemAttribute itemAttribute) {
        Map<String, Object> value = new HashMap<>();

        //get value_id and value_name
        if (itemAttribute.getValueId() != null && !itemAttribute.getValueId().isEmpty()) {
            value.put(VALUE_ID, itemAttribute.getValueId());
        }

        if (itemAttribute.getValueName() != null && !itemAttribute.getValueName().isEmpty()) {
            final String value_name = categoryAttributeUtils.normalizeAttributeValue(categoryAttribute.getValueType(), itemAttribute.getValueName());
            value.put(VALUE_NAME, value_name);
            value.put(ID_TEXT, itemAttribute.getId());   // if attribute has value then selected_value has value_name,value_id,id and name.
            value.put(NAME_TEXT, itemAttribute.getName());
        }
        addMetadata(categoryAttribute, itemAttribute, value);
        selectedValues.add(value);
        return selectedValues;
    }

    /**
     * Add metadata to selectValues.
     *
     * @param categoryAttribute
     * @param itemAttribute
     * @param selectValues
     */
    private void addMetadata(CategoryAttribute categoryAttribute, ItemAttribute itemAttribute, Map<String, Object> selectValues) {
        if (categoryAttribute.getValues() != null) {
            for (Map<String, Object> v : categoryAttribute.getValues()) {
                if (v.get(METADATA) != null && v.get(ID_TEXT).equals(itemAttribute.getValueId())) {
                    selectValues.put(METADATA, v.get(METADATA));
                    break;
                }
            }
        }
    }

    public interface Provider extends SiteIdSupplier,
            VerticalSupplier,
            CategoryAttributesSupplier,
            ItemAttributesSupplier {
    }
}
