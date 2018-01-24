package com.mercadolibre.conceptTest.model.supplier;

import com.mercadolibre.conceptTest.model.component.title.TitleModel;

/**
 * Created by mforte on 1/19/18.
 */
@FunctionalInterface
public interface TitleInputSupplier {

    TitleModel getTitleInput();
}
