package com.mercadolibre.conceptTest.pulmon;

import com.google.common.collect.Iterables;
import com.mercadolibre.conceptTest.data.InputComponent;
import com.mercadolibre.kisc.viewbuilder.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mforte on 1/11/18.
 */
public class Pulmon {

    public Component filterSteps(Component stepView, List<String> componentNavigation) {
        final List<Component> tasks = stepView.getComponents();
        final Boolean navigateAny = navigateAny(stepView, componentNavigation);
        List<Component> newTasks = null;
        if (!navigateAny) { // Devuelvo el primero sin errores de validacion
            newTasks = showFirstTask(tasks);
        } else {
            newTasks = showTasks(tasks, componentNavigation);
        }

        addNewComponents(stepView, newTasks);
        return stepView;
    }

    /**
     * Devuelve la primer tarea unicamente, sin errores de validacion.
     */
    protected List<Component> showFirstTask(List<Component> tasks) {
        final Component firstTask = tasks.get(0);
        final List<Component> cmps = new ArrayList<>();
        cmps.add(firstTask);
        return cmps;
    }

    /**
     * CS = Primero con error; // CS = First Error || Last
     * VALIDAR EDITAR EN PASO 1 AFECTA PASO 2.
     *
     * Steps = [0 y Confirmar] // LA CURADA
     * if(!steps.containsErrors())
     */

    /**
     * Devuelve las tareas mostrando de a una mas a la vez, si es que
     * no hay errores de validación. Si aparece una card nueva entre medio
     * se muestra hasta la nueva y en la siguiente vez, se muestra todas las que vio alguna vez.
     */
    protected List<Component> showTasks(List<Component> tasks, List<String> componentNavigation) {
        List<Component> newTasks = getTaskUntilLastCurrentStep(tasks, componentNavigation);
        if (allStepsInNavigation(newTasks, componentNavigation) && !containsValidationErrors((Component) newTasks)) {
            addNewTask(tasks, newTasks, componentNavigation);
        }

        return newTasks;
    }

    protected Boolean allStepsInNavigation(List<Component> newTasks, List<String> componentNavigation) {
        return newTasks.stream()
                .map(Component::getId)
                .allMatch(id -> componentNavigation.contains(id));
    }

    /**
     * Por cada Tarea, continuando desde la que confirmo
     * - Esta en Nav y no tiene error => Lo agrego y sigo
     * - Esta en Nav y tiene error => Lo agrego y no sigo
     * - No esta en Nav => Le saco los errores de validacion y corto.
     */
    private void addNewTask(List<Component> tasks, List<Component> newTasks, List<String> componentNavigation) {
        final Component last = Iterables.getLast(newTasks);
        final List<Component> tasksToAdd = tasks.subList(tasks.indexOf(last), tasks.size()); //TODO: Ver si no es hasta size -1
        for (int i = 0; i < tasksToAdd.size(); i++) {
            final Component task = tasksToAdd.get(i);
            newTasks.add(task);
            if (!componentNavigation.contains(task.getId())) {
                // Muestra la nueva y corto
                clearValidationErrors(task); // Si es nueva no da mostrarle errores de validacion
                break;
            } else if (containsValidationErrors(task)) {
                break; // No avanzo mas porque lo que vio en algun momento tiene errores de validacion
            }
        }
    }

    /*
     * Si estas en el paso 2 y viste A,C y luego por modificar algo del paso 1 tasks son A,B,C,D,E
     * Tenemos que mostrarle A,B porque B no esta en el navigation.
     */
    private List<Component> getTaskUntilLastCurrentStep(List<Component> tasks, List<String> componentNavigation) {
        String lastCurrentStep = Iterables.getLast(componentNavigation);
        //TODO: Si hay alguno que no está en el navigation hay que devolver de 0 a ese
        return tasks.subList(0, getIndexOf(tasks, lastCurrentStep));
    }

    protected Boolean navigateAny(Component stepView, List<String> componentNavigation) {
        if (componentNavigation == null) return false;

        for (int i = componentNavigation.size() - 1; i >= 0; i--) {
            final String taskId = componentNavigation.get(i);
            if (getSubComponentById(taskId, stepView) != null) {
                return true;
            }
        }
        return false;
    }

    protected Integer getIndexOf(List<Component> components, String id) {
        return components.indexOf(findComponent(id, components));
    }

    /**
     * Find the sibling by id
     *
     * @param id  Sibling id.
     * @param cmp Parent.
     */
    protected Component getSubComponentById(String id, Component cmp) {
        final List<Component> components = cmp.getComponents();
        return findComponent(id, components);
    }

    protected Component findComponent(String id, List<Component> components) {
        return components
                .stream()
                .filter(c -> id.equals(c.getId()))
                .findFirst()
                .orElse(null);
    }

    public void clearValidationErrors(Component taskView) {
        final List<Component> components = taskView.getComponents();
        components.stream()
                .filter(InputComponent.class::isInstance)
                .map(InputComponent.class::cast)
                .forEach(InputComponent::clearValidationErrors);
    }

    protected Boolean containsValidationErrors(Component taskView) {
        final List<Component> components = taskView.getComponents();
        return containsValidationErrors(components);
    }

    protected Boolean containsValidationErrors(List<Component> components) {
        return components.stream()
                .filter(InputComponent.class::isInstance)
                .map(InputComponent.class::cast)
                .anyMatch(inputComponent ->
                        inputComponent.getValidationErrors() != null &&
                                !inputComponent.getValidationErrors().isEmpty()
                );
    }


    private void addNewComponents(Component stepView, List<Component> newComponents) {
        //stepView.setComponents(newComponents);
        final List components = stepView.getComponents();
        components.clear();
        components.addAll(newComponents);
    }
}
