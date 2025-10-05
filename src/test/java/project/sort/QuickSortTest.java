package project.sort;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import project.collection.MyArrayList;
import project.models.CustomEntity;
import project.models.comparators.AgeComparator;
import project.models.comparators.SurnameComparator;

import static org.junit.jupiter.api.Assertions.*;

class QuickSortTest {

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
    void testSortByAge() {
        QuickSort<CustomEntity> quickSort = new QuickSort<>(new AgeComparator());
        quickSort.sort(list);

        assertEquals(14, list.get(0).getAge());
        assertEquals(18, list.get(1).getAge());
        assertEquals(19, list.get(2).getAge());
        assertEquals(21, list.get(3).getAge());
        assertEquals(21, list.get(4).getAge());
        assertEquals(23, list.get(5).getAge());
        assertEquals(40, list.get(6).getAge());
    }

    @Test
    void testSortEvenByAge() {
        QuickSort<CustomEntity> quickSort = new QuickSort<>(new AgeComparator());
        quickSort.sortEven(list, CustomEntity::getAge);

        assertEquals(21, list.get(0).getAge());
        assertEquals(14, list.get(1).getAge());
        assertEquals(23, list.get(2).getAge());
        assertEquals(19, list.get(3).getAge());
        assertEquals(18, list.get(4).getAge());
        assertEquals(40, list.get(5).getAge());
        assertEquals(21, list.get(6).getAge());
    }

    @Test
    void testSortBySurname() {
        QuickSort<CustomEntity> quickSort = new QuickSort<>(new SurnameComparator());
        quickSort.sort(list);

        assertEquals("Dia Vanille", list.get(0).getSurname());
        assertEquals("Estheim", list.get(1).getSurname());
        assertEquals("Farron", list.get(2).getSurname());
        assertEquals("Farron", list.get(3).getSurname());
        assertEquals("Katzroy", list.get(4).getSurname());
        assertEquals("Villiers", list.get(5).getSurname());
        assertEquals("Yun Fang", list.get(6).getSurname());
    }

    @Test
    void testSortEmptyArray() {
        CustomEntity[] emptyArray = new CustomEntity[]{};
        QuickSort<CustomEntity> quickSort = new QuickSort<>(new AgeComparator());

        quickSort.sort(emptyArray);

        assertEquals(0, emptyArray.length);
    }

    @Test
    void testSortSingleElement() {
        CustomEntity Lebreau = new CustomEntity.Builder().name("Lebreau").surname("Nora").age(19).build();
        CustomEntity[] array = new CustomEntity[]{Lebreau};

        QuickSort<CustomEntity> quickSort = new QuickSort<>(new AgeComparator());
        quickSort.sort(array);

        assertEquals(Lebreau, array[0]);
    }

    @Test
    void testSortPrimitiveWrapperArray() {
        Integer[] numbers = {21, 19, 40, 14, 23, 18, 21};
        QuickSort<Integer> quickSort = new QuickSort<>(Integer::compareTo);

        quickSort.sort(numbers);

        assertArrayEquals(new Integer[]{14, 18, 19, 21, 21, 23, 40}, numbers);
    }
}