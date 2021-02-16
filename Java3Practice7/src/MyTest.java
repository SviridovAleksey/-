
import java.util.List;

public class MyTest {

    @BeforeSuite
    private void beforeStart() {
        System.out.println("бефоре старт");
    }

    @AfterSuite
    private void afterStart() {
        System.out.println("афтер старт");
    }


    @Test(description = 6)
    private void test1(int[] arr){
        System.out.println("test1 start");
        Integer[] arrSend = getInteger(arr);
        List<Integer> comeBack= Main.getNewMassTask1(arrSend);
        if(comeBack == null) {
            System.out.println("тест не пройден вернулся null(четверка в конце)");
        }
        else {
            System.out.println("-------------------test1 пройден");
        }


    }

    @Test(description = 6)
    private void test2(int[] arr){
        System.out.println("test2 start");
        Integer[] arrSend = getInteger(arr);
        if (Main.isOneFourTask2(arrSend)) {
            System.out.println("-------------------test2 пройден");
        } else {
            System.out.println("тест провален");
        }

    }

    @Test(description = 3)
    private void test3(){
        System.out.println("test3 start");
    }

    @Test(description = 0)
    private void test4(){
        System.out.println("test4 start");
    }

    private Integer[] getInteger(int[] arr) {
        Integer[] arrSend = new Integer[arr.length];
        for (int i = 0; i < arr.length; i++) {
            arrSend[i] = arr[i];
        }
        return arrSend;
    }



}
