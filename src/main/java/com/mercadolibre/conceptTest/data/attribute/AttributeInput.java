package com.mercadolibre.conceptTest.data.attribute;

import com.mercadolibre.flux.flow.graph.navigation.DataProxy;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.Objects.nonNull;

/**
 * Created by msosto on 1/11/18.
 */
public class AttributeInput {

    private String id;
    private String name;
    private String type;
    private String valueType;
    private Integer valueMaxLength;
    private Map<String, Object> tags;
    private List<Map<String, Object>> values;
    private String attributeGroupId;
    private String attributeGroupName;
    private List<Map<String, Object>> suggestedValues;
    private List<Map<String, Object>> allowedUnits;
    private String tooltip;
    private String placeholder;
    private Map data;
    private String defaultValue;
    private String hint;
    private List<Map> selected_values;

    /**
     * Constructor for attribute visual contract.
     *
     * @param ca
     */
    public AttributeInput(DataProxy ca) {


        this.setId(ca.resetPointer().access("id").getStringValue());
        this.setName(ca.resetPointer().access("name").getStringValue());
        this.setType(ca.resetPointer().access("type").getStringValue());
        final String value_type = ca.resetPointer().access("value_type").getStringValue();
        this.setValueType(nonNull(value_type) ? value_type.toLowerCase() : null);
        this.setValueMaxLength(ca.resetPointer().access("value_max_length").getSafeIntegerValue());
        this.setTags(ca.resetPointer().access("tags").getSafeMapValue());
        this.setValues(ca.resetPointer().access("values").getSafeListValue());
        this.setAttributeGroupId(ca.resetPointer().access("attribute_group_id").getStringValue());
        this.setAttributeGroupName(ca.resetPointer().access("attribute_group_name").getStringValue());
        this.setSuggestedValues(ca.resetPointer().access("suggested_values").getSafeListValue());
        this.setAllowedUnits(ca.resetPointer().access("allowed_units").getSafeListValue());
        this.setTooltip(ca.resetPointer().access("tooltip").getStringValue());
        this.setPlaceholder(ca.resetPointer().access("example").getStringValue());
        this.setData(ca.resetPointer().access("data").getMapValue());
        this.setDefaultValue(ca.resetPointer().access("default_value").getStringValue());
        this.setHint(ca.resetPointer().access("hint").getStringValue());
        this.setSelected_values(Optional.ofNullable(ca.resetPointer().access("selected_values").getSafeListValue()).orElse(Collections.emptyList()));

    }


    public String getId() {
        return id;
    }

    public AttributeInput setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public AttributeInput setName(String name) {
        this.name = name;
        return this;
    }

    public String getType() {
        return type;
    }

    public AttributeInput setType(String type) {
        this.type = type;
        return this;
    }

    public String getValueType() {
        return valueType;
    }

    public AttributeInput setValueType(String valueType) {
        this.valueType = valueType;
        return this;
    }

    public Integer getValueMaxLength() {
        return valueMaxLength;
    }

    public AttributeInput setValueMaxLength(Integer valueMaxLength) {
        this.valueMaxLength = valueMaxLength;
        return this;
    }

    public Map<String, Object> getTags() {
        return tags;
    }

    public AttributeInput setTags(Map<String, Object> tags) {
        this.tags = tags;
        return this;
    }

    public List<Map<String, Object>> getValues() {
        return values;
    }

    public AttributeInput setValues(List<Map<String, Object>> values) {
        this.values = values;
        return this;
    }

    public String getAttributeGroupId() {
        return attributeGroupId;
    }

    public AttributeInput setAttributeGroupId(String attributeGroupId) {
        this.attributeGroupId = attributeGroupId;
        return this;
    }

    public String getAttributeGroupName() {
        return attributeGroupName;
    }

    public AttributeInput setAttributeGroupName(String attributeGroupName) {
        this.attributeGroupName = attributeGroupName;
        return this;
    }

    public List<Map<String, Object>> getSuggestedValues() {
        return suggestedValues;
    }

    public AttributeInput setSuggestedValues(List<Map<String, Object>> suggestedValues) {
        this.suggestedValues = suggestedValues;
        return this;
    }

    public List<Map<String, Object>> getAllowedUnits() {
        return allowedUnits;
    }

    public AttributeInput setAllowedUnits(List<Map<String, Object>> allowedUnits) {
        this.allowedUnits = allowedUnits;
        return this;
    }

    public String getTooltip() {
        return tooltip;
    }

    public AttributeInput setTooltip(String tooltip) {
        this.tooltip = tooltip;
        return this;
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public AttributeInput setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
        return this;
    }

    public Map getData() {
        return data;
    }

    public AttributeInput setData(Map data) {
        this.data = data;
        return this;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public AttributeInput setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }

    public String getHint() {
        return hint;
    }

    public AttributeInput setHint(String hint) {
        this.hint = hint;
        return this;
    }

    public List<Map> getSelected_values() {
        return selected_values;
    }

    public AttributeInput setSelected_values(List<Map> selected_values) {
        this.selected_values = selected_values;
        return this;
    }

}
