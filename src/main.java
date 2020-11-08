import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/*
1. Создать массив с набором слов (10-20 слов, должны встречаться повторяющиеся). Найти и вывести список уникальных
слов, из которых состоит массив (дубликаты не считаем). Посчитать сколько раз встречается каждое слово.

2. Написать простой класс ТелефонныйСправочник, который хранит в себе список фамилий и телефонных номеров.
В этот телефонный справочник с помощью метода add() можно добавлять записи. С помощью метода get() искать номер
телефона по фамилии. Следует учесть, что под одной фамилией может быть несколько телефонов (в случае однофамильцев),
тогда при запросе такой фамилии должны выводиться все телефоны.

Желательно как можно меньше добавлять своего, чего нет в задании (т.е. не надо в телефонную запись добавлять
еще дополнительные поля (имя, отчество, адрес), делать взаимодействие с пользователем через консоль и т.д.).
Консоль желательно не использовать (в том числе Scanner), тестировать просто из метода main() прописывая add() и get().
 */

public class Main {

    protected static String[] lastName = {"Иванов", "Смирнов", "Кузнецов", "Попов", "Васильев", "Петрова", "Соколова",
            "Махайлов", "Новикова", "Фёдорова", "Иванов", "Морозова", "Попов", "Лебедев", "Козлов", "Смирнов",
            "Егорова", "Соколова", "Иванов", "Фёдорова"};
    protected static String[] phoneNumber = {"375256612939", "375292308770", "380504180697", "380632829292",
            "380672176010", "380672176020", "380672176030", "380673557777", "380931777772", "380937027555",
            "380937028555", "380963447777", "380968777777", "380984957269", "447624803018", "447624806152",
            "447624806153", "77017333190", "77019381234", "77027773690"};

    public static void main(String[] args){
        repeatingWords(lastName);
        ReferenceBook referenceBook = new ReferenceBook(phoneNumber, lastName);
        referenceBook.add("11111", "Щеглов");
        referenceBook.get("Иванов");
    }

    protected static void repeatingWords (String[] lastName) {
        ArrayList<String> list = new ArrayList<>(Arrays.asList(lastName));
        Set<String> set = new HashSet<>(list);
        HashMap<String, Integer> frequencyMap = new HashMap<>(lastName.length/2);
        for (String text : set){
            if (Collections.frequency(list, text) > 1)
                frequencyMap.put(text, Collections.frequency(list, text));
        }
        System.out.println("список уникальных фамилий - " + set);
        System.out.println("повторяющиеся фамилий - " + frequencyMap);
    }
}