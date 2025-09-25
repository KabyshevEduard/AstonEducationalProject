import org.example.collection.MyArrayList;
import org.example.collection.MyList;
import org.junit.Assert;
import org.junit.Test;

public class MyArrayListTests {

    @Test
    public void countElementTest() {
        MyArrayList<Integer> arr =  new MyArrayList<>(1, 2, 3, 3, 4, 5, 5, 9);
        long firstCase = arr.countElement(5, 2);
        long secondCase = arr.countElement(9, 3);
        Assert.assertEquals(2L, firstCase);
        Assert.assertEquals(1L, secondCase);
    }

    @Test
    public void addTest() {
        MyList<Integer> arr = new MyArrayList<>(1, 2, 3);
        arr.add(4);
        int el = arr.get(3);
        Assert.assertEquals(4, el);
    }

    @Test
    public void getTest() {
        MyList<Integer> arr = new MyArrayList<>(1, 2, 3);
        int el = arr.get(2);
        Assert.assertEquals(3, el);
    }
}
