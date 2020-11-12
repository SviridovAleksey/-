import java.util.Random;

public class Schoolboy {
    private static String[] firstName= {"Алексей", "Елисей", "Иван", "Олег", "Иван", "Емельян", "Игнат", "Зенон", "Евгений", "Сани",
            "Моисей", "Наиль", "Аскер", "Павел", "Мартин", "Нестор", "Кирилл", "Нурлан", "Рафаэль", "Рэй"};
    private static String[] lastName = {"Смирнов", "Иванов", "Соколов", "Новиков", "Лебедев", "Николаев", "Козлов", "Захаров",
            "Павлов", "Семенов", "Волков", "Федоров", "Попов", "Петров", "Сафронов", "Шаров", "Лазарев", "Котов",
            "Юдин", "Носков"};
    private String first;
    private String last;
    private Integer age;
    private Integer progress;



    Schoolboy(){
        Random random = new Random();
        this.first = firstName[random.nextInt(firstName.length)];
        this.last = lastName[random.nextInt(lastName.length)];
        this.age = random.nextInt(3) + 6;
        this.progress = random.nextInt(5) + 1;
    }

    Schoolboy(String firstName, String lastName, int age, int progress){
        this.first = firstName;
        this.last = lastName;
        this.age = age;
        this.progress = progress;
    }
    protected String getFirstName(){
        return first;
    }

    protected String getLastName() {
        return last;
    }

    protected Integer getAge() {
        return age;
    }

    protected Integer getProgressName() {
        return progress;
    }

    protected void setFirstName(String firstName){
        this.first = firstName;
    }

    protected void setLastName(String lastName){
        this.last = lastName;
    }

    protected void setAge(int age){
        this.age = age;
    }

    protected void setProgressName(int progress){
        this.progress = progress;
    }

}