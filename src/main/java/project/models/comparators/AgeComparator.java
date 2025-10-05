package project.models.comparators;

import project.models.CustomEntity;

import java.util.Comparator;

// Comparator for field age
public class AgeComparator implements Comparator<CustomEntity> {

    @Override
    public int compare(CustomEntity o1, CustomEntity o2) {
        if (o1.getAge() < o2.getAge()) {
            return -1;
        } else if (o1.getAge() > o2.getAge()) {
            return 1;
        } else {
            return 0;
        }
    }
}