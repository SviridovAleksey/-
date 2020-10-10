import java.util.Scanner;

public class employee {
    private String firstName;
    private String lastName;
    private String position;
    private String email;
    private String phoneNumber;
    private String salary;
    private String age;

    employee() {
        firstName = "Неизвестно";
        lastName = "Неизвестно";
        position = "Неизвестно";
        email = "Неизвестно";
        phoneNumber = "Неизвестно";
        salary = "Неизвестно";
        age = "Неизвестно";
    }

    void filling () {
        String[] mapEployee = new String[7];
        Scanner input = new Scanner(System.in);
        for (int i = 0;i < mapEployee.length; i++) {
            mapEployee[i] = input.next();
        }
        firstName = mapEployee[0];
        lastName = mapEployee[1];
        position = mapEployee[2];
        email = mapEployee[3];
        phoneNumber = mapEployee[4];
        salary = mapEployee[5];
        age = mapEployee[6];
    }

    void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    void setLastName(String lastName) {
        this.lastName = lastName;
    }

    void setPosition(String position) { this.position = position;  }

    void setEmail(String email) { this.email = email; }

    void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    void setSalary(String salary) {
        this.salary = salary;
    }

    void setAge(String age) {
        this.age = age;
    }

    String getFirstName() {
        return firstName;
    }

    String getLastName() {
        return lastName;
    }

    String getPosition() {
        return position;
    }

    String getEmail() {
        return email;
    }

    String getPhoneNumber() {
        return phoneNumber;
    }

    String getSalary() {
        return salary;
    }

    String getAge() {
        return age;
    }

}