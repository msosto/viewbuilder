package com.mercadolibre.conceptTest.data.category.selection;

import java.util.List;

/**
 * Created by mlizarraga on 11/1/18
 */
public class CategorySelectionColumn {

    private Boolean isLeaf;
    private Boolean withFilter;
    private List<CategorySelectionRow> rows;

    public Boolean getLeaf() {
        return isLeaf;
    }

    public CategorySelectionColumn setLeaf(Boolean leaf) {
        isLeaf = leaf;
        return this;
    }

    public Boolean getWithFilter() {
        return withFilter;
    }

    public CategorySelectionColumn withFilter(Boolean withFilter) {
        this.withFilter = withFilter;
        return this;
    }

    public List<CategorySelectionRow> getRows() {
        return rows;
    }

    public CategorySelectionColumn withRows(List<CategorySelectionRow> rows) {
        this.rows = rows;
        return this;
    }
}
