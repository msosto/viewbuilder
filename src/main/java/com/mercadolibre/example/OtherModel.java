package com.mercadolibre.example;

import com.mercadolibre.kisc.viewbuilder.ViewContract;

/**
 * Created by abertolo on 05/01/18.
 */
public class OtherModel implements ViewContract{

    String termsAndCondition;

    public String getTermsAndCondition() {
        return termsAndCondition;
    }

    public OtherModel withTermsAndCondition(String termsAndCondition) {
        this.termsAndCondition = termsAndCondition;
        return this;
    }
}
