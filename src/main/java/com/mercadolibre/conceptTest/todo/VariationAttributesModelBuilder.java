package com.mercadolibre.conceptTest.todo;

import com.google.common.collect.Iterables;
import com.mercadolibre.ActionsModule;
import com.mercadolibre.actions.CategoryUtils;
import com.mercadolibre.conceptTest.model.component.InputModelBuilder;
import com.mercadolibre.conceptTest.todo.component.VariationAttributeInputModel;
import com.mercadolibre.conceptTest.todo.component.VariationAttributesModel;
import com.mercadolibre.dto.category.CategoryAttribute;
import com.mercadolibre.dto.item.ItemAttribute;
import com.mercadolibre.flux.flow.graph.navigation.Context;
import com.mercadolibre.util.ContextUtils;
import com.mercadolibre.util.providers.CategoryProvider;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by mforte on 1/22/18.
 */
public class VariationAttributesModelBuilder extends InputModelBuilder {

    private static final String SECONDARY_COLOR = "73001";
    private final CategoryUtils categoryUtils;
    private final CategoryProvider categoryProvider;

    public VariationAttributesModelBuilder(CategoryProvider categoryProvider) {
        this.categoryUtils = ActionsModule.get().getInstance(CategoryUtils.class);
        this.categoryProvider = categoryProvider;
    }

    public VariationAttributesModel build(Context context) {
        final VariationAttributesModel model = new VariationAttributesModel();

        final List<Map<String, Object>> validationErrors = obtainValidationErrors(context, "single_variation_attribute");
        final List<CategoryAttribute> variationAttributes = getVariationAttributes(context);

        CategoryAttribute firstAttribute = Iterables.getFirst(variationAttributes, null);   // proceso el primer atributo por separado para evitarme el if en la iteracion.
        if (firstAttribute != null) {
            VariationAttributeInputModel mainAttribute = buildVariationAttribute(firstAttribute, getMainValue(context), validationErrors);
            model.setMainAttribute(mainAttribute);
        }

        final Map<String, Object> secondaryValues = getSecondaryValues(context);

        final List<VariationAttributeInputModel> secondaryAttributes = variationAttributes.stream()
                .skip(1)
                .map(attr -> buildVariationAttribute(attr, (List<ItemAttribute>) secondaryValues.get(attr.getId()), validationErrors))
                .collect(Collectors.toList());

        model.setSecondaryAttributes(secondaryAttributes);

        return model;
    }

    private VariationAttributeInputModel buildVariationAttribute(CategoryAttribute categoryAttribute, List<ItemAttribute> values, List<Map<String, Object>> validationErrors) {
        final VariationAttributeInputModel varModel = new VariationAttributeInputModel();
        //        final SellStepDTO stepDTO = (SellStepDTO) context.getStepProxy().addStep(createStepId(categoryAttribute.getId()));
//        addHeaderData(stepDTO, HEADER_UI_TYPE, HEADER_TITLE_SINGLE_VARIATIONS, Arrays.asList(categoryAttribute.getName()));
//        addFooterData(stepDTO, FOOTER_IN_UI_TYPE, FOOTER_BTN_TEXT, ListCoreVersion.CONTINUE_CONNECTION_ID);
//
//        singleVariationAttributeSetUp.loadComponent(context, stepDTO, categoryAttribute);

        List<Map<String, Object>> attributeValidationErrors = filterValidationErrors(validationErrors, categoryAttribute);
        varModel.setValidationErrors(attributeValidationErrors);
        return varModel;
    }

    protected List<Map<String, Object>> filterValidationErrors(List<Map<String, Object>> validationErrors, CategoryAttribute categoryAttribute) {
        final List<Map<String, Object>> errors = validationErrors.stream()
                .filter(errorMap -> categoryAttribute.getId().equals(errorMap.get("id")))
                .collect(Collectors.toList());
        validationErrors.removeAll(errors);
        return errors;
    }

    private List<ItemAttribute> getMainValue(Context context) {
        return ContextUtils.getGoalData(context, "data.temp.variation_attributes.main", List.class);
    }

    private Map<String, Object> getSecondaryValues(Context context) {
        return ContextUtils.getGoalData(context, "data.temp.variation_attributes.permutations", Map.class);
    }

    protected List<CategoryAttribute> getVariationAttributes(Context context) {
        final List<CategoryAttribute> variationAttributes = categoryUtils.getCategoryAttributesWithAllowVariation(context, categoryProvider);

        //TODO: La api de sellTechnicalSpecification no tendria q devolver mas COLOR SECUNDARIO. Sacar esto cuando no sea mas necesario
        variationAttributes.removeIf(categoryAttribute -> SECONDARY_COLOR.equals(categoryAttribute.getId()));

        variationAttributes.sort(Comparator.comparing(CategoryAttribute::getVariationUiType));
        return variationAttributes;
    }
}
