package com.mercadolibre.kisc.viewbuilder.template;

/**
 * Created by abertolo on 29/12/17.
 */
public class ViewTemplate<VC, Model> extends ContainerTemplate<VC, Model> {

    String layout;

    public static void test(){
        final ViewTemplate template = new ViewTemplate()
                .add(new Template());

    }

    @Override
    public ViewTemplate add(Template template) {
        return add(template, ViewTemplate.class);
    }


}
