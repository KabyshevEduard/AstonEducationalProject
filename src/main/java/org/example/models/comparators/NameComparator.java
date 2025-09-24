package org.example.models.comparators;

import org.example.models.CustomEntity;

import java.util.Comparator;

public class NameComparator implements Comparator<CustomEntity> {

    @Override
    public int compare(CustomEntity o1, CustomEntity o2) {
        return o1.getSurname().compareTo(o2.getSurname());
    }
}
