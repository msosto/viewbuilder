package com.mercadolibre.conceptTest.graphs.builder.view;

import com.google.common.collect.Lists;
import com.mercadolibre.conceptTest.pulmon.Pulmon;
import com.mercadolibre.flux.util.TestUtils;
import com.mercadolibre.kisc.viewbuilder.Component;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

/**
 * Created by mlizarraga on 19/1/18
 */
public class PulmonTest {

    private Pulmon pulmon;
    private Component view1;
    private Component a;
    private Component b;
    private Component c;
    private Component header;
    private Component withErrors;

    @Before
    public void setUp() {
        pulmon = new Pulmon();
        header = new Component().withId("header");
        a = new Component().withId("A");
        a.add(header);
        b = new Component().withId("B");
        b.add(header);
        c = new Component().withId("C");
        c.add(header);
        view1 = new Component();
        view1.add(a);
        view1.add(b);
        view1.add(c);

        withErrors = new Component().withId("cmp_with_error");
        InputComponent inputComponent = new InputComponent();
        inputComponent.withValidationErrors(Lists.newArrayList(TestUtils.newMap("required", true)));
        withErrors.withData(inputComponent);
    }

    @Test
    public void withoutComponentNavigation() {
        Component filteredResult = pulmon.filterSteps(view1, null);
        assertFalse(filteredResult.getComponents().isEmpty());
        List<Component> tasks = filteredResult.getComponents();
        assertEquals(1, tasks.size());
        assertEquals("A", tasks.get(0).getId());
    }

    @Test
    public void emptyComponentNavigation() {
        Component filteredResult = pulmon.filterSteps(view1, Lists.newArrayList());
        assertFalse(filteredResult.getComponents().isEmpty());
        List<Component> tasks = filteredResult.getComponents();
        assertEquals(1, tasks.size());
        assertEquals("A", tasks.get(0).getId());
    }

    @Test
    public void navigatedOneTask() {
        Component filteredResult = pulmon.filterSteps(view1, Lists.newArrayList("A"));
        assertFalse(filteredResult.getComponents().isEmpty());
        List<Component> tasks = filteredResult.getComponents();
        assertEquals(2, tasks.size());
        assertEquals("A", tasks.get(0).getId());
        assertEquals("B", tasks.get(1).getId());
    }

    @Test
    public void navigatedAllTasks() {
        Component filteredResult = pulmon.filterSteps(view1, Lists.newArrayList("A", "B", "C"));
        assertFalse(filteredResult.getComponents().isEmpty());
        List<Component> tasks = filteredResult.getComponents();
        assertEquals(3, tasks.size());
        assertEquals("A", tasks.get(0).getId());
        assertEquals("B", tasks.get(1).getId());
        assertEquals("C", tasks.get(2).getId());
    }

    @Test
    public void navigateWithErrors() {
        b.add(withErrors);
        Component filteredResult = pulmon.filterSteps(view1, Lists.newArrayList("A", "B"));
        assertFalse(filteredResult.getComponents().isEmpty());
        List<Component> tasks = filteredResult.getComponents();
        assertEquals(2, tasks.size());
        assertEquals("A", tasks.get(0).getId());
        assertEquals("B", tasks.get(1).getId());
    }

    @Test
    public void navigateWithErrors_moreTasks() {
        Component d = new Component().withId("D");
        d.add(withErrors);
        Component e = new Component().withId("E");
        d.add(header);
        Component f = new Component().withId("F");
        d.add(header);
        view1.getComponents().clear();
        view1.add(a);
        view1.add(d);
        view1.add(e);
        view1.add(f);
        Component filteredResult = pulmon.filterSteps(view1, Lists.newArrayList("A"));
        List<Component> tasks = filteredResult.getComponents();
        assertEquals(2, tasks.size());
        assertEquals("A", tasks.get(0).getId());
        assertEquals("D", tasks.get(1).getId());
    }

    @Test
    public void cleanValidationErrors() {
        Component d = new Component().withId("D");
        d.add(withErrors);
        view1.getComponents().clear();
        view1.add(a);
        view1.add(d);
        Component filteredResult = pulmon.filterSteps(view1, Lists.newArrayList("A"));
        assertFalse(filteredResult.getComponents().isEmpty());
        List<Component> tasks = filteredResult.getComponents();
        List<Component> cmps = tasks.get(1).getComponents();
        InputComponent inputComponent = (InputComponent) cmps.get(0).getData();
        assertEquals("A", tasks.get(0).getId());
        assertEquals("D", tasks.get(1).getId());
        assertNull(inputComponent.getValidationErrors());
    }

}
