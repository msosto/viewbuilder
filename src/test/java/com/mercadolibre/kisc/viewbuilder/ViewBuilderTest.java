package com.mercadolibre.kisc.viewbuilder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mercadolibre.example.ExampleModel;
import com.mercadolibre.example.ExampleModelItem;
import com.mercadolibre.example.OtherModel;
import com.mercadolibre.example.contract.AutocompleteInput;
import com.mercadolibre.example.contract.Label;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;

/**
 * Created by abertolo on 03/01/18.
 */
public class ViewBuilderTest {

    final Gson gson = new GsonBuilder().create();

    @Test
    public void build() throws Exception {


        ViewBuilder<ExampleModel> viewBuilder = new ViewBuilder<>(ExampleModel.class)
                .add("page").uiType("desktop_page").done()
                .add("search", "page").mapper(m -> new AutocompleteInput()
                        .withPlaceholder(m.getSearchPlaceholder())
                        .withIcon(m.getSearchIcon())
                        .withFormName("q")).id("search").done()
                .add("grid", "page").uiType("desktop_grid").done()
                .add("row", "grid", ExampleModelItem.class).spread(ExampleModel::getItems).newModel()
                .add("title", "row").mapper(m -> new Label().withText(m.getTitle())).done()
                .add("footer", "grid").id("pages").done()
                .add("footer_page", "page", OtherModel.class).transform(ExampleModel::getOther).done();

        final ExampleModel model = getModel();

        final Component view = viewBuilder.build(model);

        assertNotNull(view);

        System.out.println(gson.toJson(view));

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
        items.add(new ExampleModelItem().withTitle("item 1"));
        items.add(new ExampleModelItem().withTitle("item 2"));
        return new ExampleModel()
                .withSearchPlaceholder("soy un placeholder")
                .withSearchIcon("lupita")
                .withItems(items);
    }

}