package project.models.comparators;

import project.models.CustomEntity;

import java.util.Comparator;

// Comparator for field name
public class NameComparator implements Comparator<CustomEntity> {

    @Override
    public int compare(CustomEntity o1, CustomEntity o2) {
        return o1.getName().compareTo(o2.getName());
    }
}