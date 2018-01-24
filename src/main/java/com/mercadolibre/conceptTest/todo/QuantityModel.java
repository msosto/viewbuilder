package com.mercadolibre.conceptTest.todo;

import com.mercadolibre.conceptTest.model.component.InputModel;

import java.math.BigDecimal;

/**
 * Created by mforte on 1/10/18.
 */
public class QuantityModel extends InputModel {

    private BigDecimal value;
    private Long maxLimit;
    private Long minLimit;

    /**
     * Clona el objeto y le setea el output en particular.
     */
    public QuantityModel newWithOutput(String output) {
        return (QuantityModel) new QuantityModel()
                .setValue(value)
                .setMaxLimit(maxLimit)
                .setMinLimit(minLimit)
                .setOutput(output)
                .setValidationErrors(getValidationErrors());
    }

    public BigDecimal getValue() {
        return value;
    }

    public QuantityModel setValue(BigDecimal value) {
        this.value = value;
        return this;
    }

    public Long getMaxLimit() {
        return maxLimit;
    }

    public QuantityModel setMaxLimit(Long maxLimit) {
        this.maxLimit = maxLimit;
        return this;
    }

    public Long getMinLimit() {
        return minLimit;
    }

    public QuantityModel setMinLimit(Long minLimit) {
        this.minLimit = minLimit;
        return this;
    }
}
