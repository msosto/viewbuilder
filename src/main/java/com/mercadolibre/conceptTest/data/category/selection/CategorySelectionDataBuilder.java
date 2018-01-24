package com.mercadolibre.conceptTest.data.category.selection;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.mercadolibre.config.Config;
import com.mercadolibre.dto.Category;
import com.mercadolibre.dto.catalog.SellCatalogSelection;
import com.mercadolibre.dto.catalog.SellCatalogSelectionColumn;
import com.mercadolibre.supply.supplier.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.mercadolibre.util.ContextUtils.withDots;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 * Created by malizarraga on 16/1/18.
 */

public class CategorySelectionDataBuilder {

    public static final String CATALOG = "catalog";
    public static final String MIN_QUANTITY_FOR_FILTER = "min_quantity_for_filter";

    public CategorySelectionViewContract build(Provider model) {
        SellCatalogSelection selection = model.getSellCatalogSelection();
        SellCatalogSelectionColumn lastColumn = Iterables.getLast(selection.getColumns());
        boolean shouldContinue = Boolean.TRUE.equals(selection.getShouldContinue());

        CategorySelectionColumn column = new CategorySelectionColumn();
        column.withRows(processRows(model.getContextId(), lastColumn));
        column.withFilter(shouldContinue ? false : isFilterAvailable(lastColumn));
        column.setLeaf(shouldContinue);

        if (shouldContinue) {
            column.withRows(Lists.newArrayList());
        }

        CategorySelectionViewContract viewContract = new CategorySelectionViewContract()
                .withCategoryId(model.getCategoryId())
                .withCatalogProductId(model.getCatalogProductId())
                .withColumn(column)
                .withAdultContent(getAdultContent(model.getCategory()));

        return viewContract;
    }

    /**
     * Process the rows given a column.
     *
     * @param column
     */
    private List<CategorySelectionRow> processRows(String contextId, SellCatalogSelectionColumn column) {
        List<CategorySelectionRow> rows = Lists.newArrayList();
        column.getRows().forEach(sellCatalogSelectionRow -> {
            CategorySelectionRow row = new CategorySelectionRow()
                    .withName(sellCatalogSelectionRow.getName())
                    .withOutput(getOutput(contextId, sellCatalogSelectionRow.getOutput()))
                    .withCustomOutput(nonNull(sellCatalogSelectionRow.getCustomOutput()) ? withDots(sellCatalogSelectionRow.getCustomOutput()) : null)
                    .withTags(sellCatalogSelectionRow.getTags());
            rows.add(row);
        });
        return rows;
    }

    /**
     * Getter for the output. Copy map on custom_output to the output.
     *
     * @param contextId
     * @param customOutput
     * @return
     */
    private Map<String, Object> getOutput(String contextId, Map<String, Object> customOutput) {
        if (isNull(customOutput))
            return null;

        final HashMap<String, Object> output = new HashMap<>();
        customOutput.forEach((k, v) -> output.put(withDots(contextId, k), v));

        return output;
    }

    /**
     * Specify if a column has filters available or not only if column resource is catalog.
     * The filter is used to limit the number of rows (categories) of a selection when there are many.
     *
     * @param column
     * @return
     */
    private Boolean isFilterAvailable(SellCatalogSelectionColumn column) {
        return (CATALOG.equalsIgnoreCase(column.getResource())
                && column.getRows().size() > Config.get().getInt(MIN_QUANTITY_FOR_FILTER, 20));
    }

    /**
     * Get adult content from category
     *
     * @return
     */
    private Boolean getAdultContent(Category category) {
        if (Objects.isNull(category))
            return false;
        return category.getSettings().getAdultContent();
    }

    /**
     * Created by mforte on 1/23/18.
     */
    public interface Provider extends CategorySupplier,
            ContextIdSupplier,
            SellCatalogSelectionSupplier,
            CategoryIdSupplier,
            CatalogProductIdSupplier {
    }
}
