package com.mercadolibre.kisc.viewbuilder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mercadolibre.example.ExampleModel;
import com.mercadolibre.example.ExampleModelItem;
import com.mercadolibre.example.contract.AutocompleteInput;
import com.mercadolibre.example.contract.Label;
import com.mercadolibre.example.contract.PriceLabel;
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

    @Test
    public void build() throws Exception {

        final Template template = new Template("layout")
                .add(
                        new Template<AutocompleteInput, ExampleModel>("autocomplete")
                                .withMapper(m -> new AutocompleteInput()
                                        .withPlaceholder(m.getSearchPlaceholder())
                                        .withIcon(m.getSearchIcon())
                                        .withFormName("q"))
                                .withId("search")

                )
                .add(
                        new Template("desktop_grid")
                                .add(
                                        new Template("grid_body")
                                                .add(
                                                        new Template<Object, ExampleModelItem>("row")
                                                                .withTransformToList(o -> {
                                                                    ExampleModel model = (ExampleModel) o;
                                                                    return model.getItems();
                                                                })
                                                                .add(
                                                                        new Template<Label, ExampleModelItem>("picture")
                                                                                .withMapper(m -> new Label()
                                                                                        .withText(m.getTitle()))
                                                                )
                                                )
                                                .withId("rows")
                                )
                                .add(
                                        new Template("grid_footer")
                                                .withId("pages")
                                )
                                .withId("grid")
                );


        ViewBuilder<ExampleModel> viewBuilder = new ViewBuilder<>(template);

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