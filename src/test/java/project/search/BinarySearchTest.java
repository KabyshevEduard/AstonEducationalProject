package project.search;

import org.example.collection.*;
import org.example.models.*;
import org.example.models.comparators.*;
import org.example.sort.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import project.collection.MyArrayList;
import project.models.CustomEntity;
import project.models.comparators.AgeComparator;
import project.models.comparators.NameComparator;
import project.models.comparators.SurnameComparator;
import project.sort.MergeSort;
import project.sort.QuickSort;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTest {

    private MyArrayList<CustomEntity> list;

    @BeforeEach
    void setUp() {
        list = new MyArrayList<>(
                new CustomEntity.Builder().name("Claire").surname("Farron").age(21).build(),
                new CustomEntity.Builder().name("Serah").surname("Farron").age(18).build(),
                new CustomEntity.Builder().name("Snow").surname("Villiers").age(23).build(),
                new CustomEntity.Builder().name("Oerba").surname("Dia Vanille").age(19).build(),
                new CustomEntity.Builder().name("Sazh").surname("Katzroy").age(40).build(),
                new CustomEntity.Builder().name("Hope").surname("Estheim").age(14).build(),
                new CustomEntity.Builder().name("Oerba").surname("Yun Fang").age(21).build()
        );
    }

    @Test
    void testSearchByName() {
        QuickSort<CustomEntity> sortByName = new QuickSort<>(new NameComparator());
        sortByName.sort(list);
        CustomEntity needle = new CustomEntity.Builder().name("Hope").surname("Estheim").age(14).build();
        BinarySearch<CustomEntity> result = new BinarySearch<>(new NameComparator());

        int index = result.search(list, needle);
        assertEquals(1, index);

        needle = new CustomEntity.Builder().name("Sazh").surname("Katzroy").age(40).build();

        index = result.search(list, needle);
        assertEquals(4, index);
    }

    @Test
    void testSearchBySurname() {
        MergeSort<CustomEntity> sortBySurname = new MergeSort<>(new SurnameComparator());
        sortBySurname.sort(list);
        CustomEntity needle = new CustomEntity.Builder().name("Oerba").surname("Dia Vanille").age(19).build();
        BinarySearch<CustomEntity> result = new BinarySearch<>(new SurnameComparator());

        int index = result.search(list, needle);
        assertEquals(0, index);

        needle = new CustomEntity.Builder().name("Sazh").surname("Katzroy").age(40).build();

        index = result.search(list, needle);
        assertEquals(4, index);
    }

    @Test
    void testSearchByAge() {
        MergeSort<CustomEntity> sortByAge = new MergeSort<>(new AgeComparator());
        sortByAge.sort(list);
        CustomEntity needle = new CustomEntity.Builder().name("Oerba").surname("Yun Fang").age(21).build();
        BinarySearch<CustomEntity> result = new BinarySearch<>(new AgeComparator());

        int index = result.search(list, needle);
        assertEquals(3, index);

        needle = new CustomEntity.Builder().name("Sazh").surname("Katzroy").age(40).build();

        index = result.search(list, needle);
        assertEquals(6, index);
    }

    @Test
    void testSearchEmptyArray() {
        CustomEntity needle = new CustomEntity.Builder().name("Lightning").surname("None").age(21).build();
        MyArrayList<CustomEntity> emptyList = new MyArrayList<>();
        BinarySearch<CustomEntity> result = new BinarySearch<>(new AgeComparator());

        int index = result.search(emptyList, needle);
        assertEquals(-1, index);
    }

    @Test
    void testSearchSingleElement() {
        CustomEntity needle = new CustomEntity.Builder().name("Lebreau").surname("Nora").age(19).build();
        MyArrayList<CustomEntity> singleList = new MyArrayList<>(needle);
        BinarySearch<CustomEntity> result = new BinarySearch<>(new AgeComparator());

        int index = result.search(singleList, needle);
        assertEquals(0, index);
    }
}