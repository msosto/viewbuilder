package com.mercadolibre.kisc.viewbuilder;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mercadolibre.example.ExampleModel;
import com.mercadolibre.example.ExampleModelItem;
import com.mercadolibre.example.OtherModel;
import com.mercadolibre.example.contract.AutocompleteInput;
import com.mercadolibre.example.contract.Label;
import com.mercadolibre.example.contract.Picture;
import com.mercadolibre.kisc.viewbuilder.template.Group;
import com.mercadolibre.kisc.viewbuilder.template.Template;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;

/**
 * Created by abertolo on 03/01/18.
 */
public class ViewBuilderTest {

    final Gson gson = new GsonBuilder().create();
    public static final Group LALA = Group.withId("LALA");

    @Test
    public void build() throws Exception {

        final Template<OtherModel> comm = new Template<OtherModel>(OtherModel.class) {

            @Override
            protected void createTemplate() {
                addChild().id("comunicacion")
                    .addSibling().id("hno de comu");

            }
        };

        final Template<ExampleModel> template = new Template<ExampleModel>(ExampleModel.class) {
            @Override
            protected void createTemplate() {

                addChild(comm, ExampleModel::getOther).groups(LALA);
                addChild().id("page").uiType("desktop_page")
                        .addChild().id("search")
                            .dataBuilder(m -> new AutocompleteInput()
                                .withPlaceholder(m.getSearchPlaceholder())
                                .withIcon(m.getSearchIcon())
                                .withFormName("q"))
                        .addSibling().id("grid").uiType("desktop_grid")
                        .addChildren(ExampleModelItem.class, ExampleModel::getItems)
                        .addChild().id("picture").dataBuilder(m -> m.getPictures().stream().findFirst().orElse(null))
                        .addSibling().id("title").dataBuilder(m -> new Label().withText(m.getTitle()))
                        .parent()
                        .parent(ExampleModel.class)
                        .addChild().id("footer");
                addChild(OtherModel.class, ExampleModel::getOther).id("page_footer").groups(LALA);

                apply(LALA, exampleModel -> false);
            }
        };

        final ExampleModel model = getModel();

        final Component component = template.build(model);

        assertNotNull(component);

        System.out.println(gson.toJson(component));

        /*
{
  "components": [
    {
      "id": "search",
      "uiType": "autocomplete",
      "data": {
        "placeholder": "soy un placeholder",
        "formName": "q",
        "icon": "lupita"
      }
    },
    {
      "id": "grid",
      "uiType": "desktop_grid",
      "components": [
        {
          "id": "rows",
          "uiType": "grid_body",
          "components": [
            {
              "uiType": "row",
              "components": [
                {
                  "uiType": "title",
                  "data": {
                    "text": "item 1"
                  }
                }
              ]
            },
            {
              "uiType": "row",
              "components": [
                {
                  "uiType": "title",
                  "data": {
                    "text": "item 2"
                  }
                }
              ]
            }
          ]
        },
        {
          "id": "pages",
          "uiType": "grid_footer"
        }
      ]
    }
  ]
}
        * */
    }

    private ExampleModel getModel() {
        List<ExampleModelItem> items = new ArrayList<>();
        items.add(new ExampleModelItem().withTitle("item 1").withPictures(Lists.newArrayList(new Picture().withUrl("picture1_url"))));
        items.add(new ExampleModelItem().withTitle("item 2").withPictures(Lists.newArrayList(new Picture().withUrl("picture2_url"))));
        return new ExampleModel()
                .withSearchPlaceholder("soy un placeholder")
                .withSearchIcon("lupita")
                .withItems(items)
                .withOther(new OtherModel().withTermsAndCondition("terminos y condiciones"));
    }

}