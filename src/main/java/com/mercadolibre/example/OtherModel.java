package com.mercadolibre.example;


/**
 * Created by abertolo on 05/01/18.
 */
public class OtherModel {

    String termsAndCondition;

    public String getTermsAndCondition() {
        return termsAndCondition;
    }

    public OtherModel withTermsAndCondition(String termsAndCondition) {
        this.termsAndCondition = termsAndCondition;
        return this;
    }
}
