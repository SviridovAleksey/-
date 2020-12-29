import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestClass {

    private static int[] arrTest1 = {1, 2, 3, 4, 5, 6, 0, 8, 9, 10};
    private static int[]  arrTest2 = {1, 1, 1, 1, 1, 1, 1, 1, 1, 4};


    public void start (String nameClass) {


        Constructor<Object> constr = null;

        try {
            Class mt = Class.forName(nameClass);
            constr = mt.getConstructor();
            Object myTest = constr.newInstance();
            Method[] methods = mt.getDeclaredMethods();

            if(!isContainBeforeAndAfter(methods)) {
                throw new IllegalAccessException("в классе MyTest больше двух Before и After");
            }


            testingProcess(methods, myTest);

        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            System.out.println("Тест не пройден");
            e.printStackTrace();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void testingProcess (Method[] methods, Object myTest) throws InvocationTargetException,
            IllegalAccessException {

        for (Method met : methods) {
            met.setAccessible(true);
            if (met.isAnnotationPresent(BeforeSuite.class)) {
                met.invoke(myTest);
            }
        }
        for (int i = 0; i < methods.length; i++) {
            for (Method whoFirst : methods) {
                if (whoFirst.isAnnotationPresent(Test.class)) {
                    whoFirst.setAccessible(true);
                    if (whoFirst.getAnnotation(Test.class).description() == i) {
                        if (whoFirst.getName().equals("test1")) {
                            whoFirst.invoke(myTest, arrTest1);
                        } else if (whoFirst.getName().equals("test2")) {
                            whoFirst.invoke(myTest, arrTest2);
                        } else {
                            whoFirst.invoke(myTest);
                            break;
                        }
                    }
                }
            }
        }

        for (Method met : methods) {
            met.setAccessible(true);
            if (met.isAnnotationPresent(AfterSuite.class)) {
                met.invoke(myTest);
            }
        }

    }

    private boolean isContainBeforeAndAfter (Method[] methods){
        int count=0;
        for (Method met : methods) {
            if (met.isAnnotationPresent(BeforeSuite.class) || met.isAnnotationPresent(AfterSuite.class)) {
                count++;
            }
        }
        if(count < 3) {
            return true;
        }
        else {
            return false;
        }
    }


}
