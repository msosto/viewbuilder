package com.mercadolibre.kisc.viewbuilder.template;

/**
 * Created by abertolo on 16/01/18.
 */
public class Group {

    final String id;

    private Group(String id) {
        this.id = id;
    }

    /**
     * Return a Group with a given Id.
     *
     * @param id
     * @return
     */
    public static Group withId(String id) {
        return new Group(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Group group = (Group) o;

        return id != null ? id.equals(group.id) : group.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
