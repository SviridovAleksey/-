import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.Collection;


@RunWith(Parameterized.class)
public class MassTest {

    private Integer[]  arr;
    private Integer[]  arrTask2;


    public MassTest(Integer[] arr, Integer[] arrTask2) {
        this.arr = arr;
        this.arrTask2 = arrTask2;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10},new Integer[] {1, 1, 1, 4, 1, 1, 1, 1, 4, 1}},
                {new Integer[]{1, 2, 3, 4, 5, 6, 4, 8, 9, 10},new Integer[] {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}},
                {new Integer[]{1, 2, 3, 0, 5, 6, 0, 8, 9, 10},new Integer[] {1, 1, 1, 4, 1, 3, 1, 1, 4, 1}},
                {new Integer[]{1, 2, 3, 0, 5, 6, 0, 8, 9, 4},new Integer[] {4, 4, 4, 4, 4, 4, 4, 4, 4, 4}},
                {new Integer[]{},new Integer[] {}},
        });
    }

    @Test
    public void testTask11() {
        Assert.assertNotEquals(null, MainPractice6TEST.getNewMassTask1(arr));
    }
    @Test
    public void testTask12() {
        Assert.assertNotNull(MainPractice6TEST.getNewMassTask1(arr));
    }
    @Test
    public void testTask13() {
        if (MainPractice6TEST.getNewMassTask1(arr) == null) {
            Assert.fail("4-ка в конце метод, вернул null");
        }
    }

    @Test
    public void testTask21() {
        Assert.assertEquals(true, MainPractice6TEST.isOneFourTask2(arrTask2));
    }
    @Test
    public void testTask22() {
        Assert.assertTrue(MainPractice6TEST.isOneFourTask2(arrTask2));
    }

    @Test
    public void testTask23() {
        if (MainPractice6TEST.isOneFourTask2(arrTask2) == false) {
            Assert.fail("От метода вернулся false");
        }
    }
}
