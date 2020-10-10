import java.util.Random;
import java.util.Scanner;


public class Main {
    public static employee employeeOne = new employee();
    public static String[] arrEmploye;

    public static void main (String[] args) {
        int question;
        System.out.println("Наполнить массив сотрудников в ручную или вывести автоматическую генерацию? 1-да; 0 - нет");
        Scanner input = new Scanner(System.in);
        question = input.nextInt();
        if (question == 1) {
            arrEmploye = new String[2];
            filling();
        }
        else {
            arrEmploye = new String[5];
            auto();
        }
        output();

    }


    public static void filling(){
        System.out.println("Через enter, введите имя сотрудника, фамилию, должность, email, номер телефона, зарплату," +
                " возраст");
        for (int i = 0; i < arrEmploye.length; i++){
            employeeOne = new employee();
            employeeOne.filling();
            arrEmploye[i] = "Имя - " + employeeOne.getFirstName() + "; Фамилия - " + employeeOne.getLastName() + "; Должность - "
                    + employeeOne.getPosition() + "; emaail - " + employeeOne.getEmail() + "; Телефон - "
                    + employeeOne.getPhoneNumber() + "; Зарплата - " + employeeOne.getSalary() + "; возраст - "
                    + employeeOne.getAge() + ";";
            if (i < arrEmploye.length - 1) {
                System.out.println("***** Введите следующего сотрудника *****"); }
        }
    }

    public static void output(){

        for (int i = 1; i < arrEmploye.length + 1; i++) {
            System.out.println("Сотрудник " + i + ":");
            System.out.println(arrEmploye[i-1]); }

    }

    public static void auto(){
        Random random = new Random();
        String name = "Алексей";
        String lastName = "Венедиктов";
        String position = "Работник";
        for (int i = 0; i < arrEmploye.length; i++) {
            name = name + i;
            lastName= lastName + i;
            position = position + i;
            employeeOne = new employee();
            employeeOne.setFirstName(name);
            employeeOne.setLastName(lastName);
            employeeOne.setPosition(position);
            employeeOne.setEmail((random.nextInt(1000)) + "@gmail.com");
            employeeOne.setPhoneNumber((random.nextInt(1000000000)) + "");
            employeeOne.setSalary((random.nextInt(1000000000)) + "");
            employeeOne.setAge((random.nextInt(100)) + "");
            arrEmploye[i] = "Имя - " + employeeOne.getFirstName() + "; Фамилия - " + employeeOne.getLastName() + "; Должность - "
                    + employeeOne.getPosition() + "; emaail - " + employeeOne.getEmail() + "; Телефон - "
                    + employeeOne.getPhoneNumber() + "; Зарплата - " + employeeOne.getSalary() + "; возраст - "
                    + employeeOne.getAge() + ";";
        }
    }
}