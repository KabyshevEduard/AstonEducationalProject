package project.models.comparators;

import project.models.CustomEntity;

import java.util.Comparator;

// Comparator for field Surname
public class SurnameComparator implements Comparator<CustomEntity> {

    @Override
    public int compare(CustomEntity o1, CustomEntity o2) {
        return o1.getSurname().compareTo(o2.getSurname());
    }
}