import java.util.HashMap;

public class School {
    private static final String[] nameClass = {"А", "Б", "В", "Г", "Д", "Е", "Ё", "Ж", "З"};
    private static final int letterMax = 1; // количество буквенный классов в уровне изначально
    private static final int classes = 11;  // количество уровней
    private static final int capacityInClassMin = 12;  //минимальное кол-во человек в классе при созданияя
    private static final int capacityInClassMax = 25;  //максимальное количество человек в классе
    private static HashMap<Integer, String> nameClasses = new HashMap<>(nameClass.length);
    private static HashMap<Integer, HashMap> levelClass = new HashMap<>(classes);

    School(){
        HashMap<Integer, Schoolboy[]> classesMap = new HashMap<>(letterMax-1);
        for(int i = 0; i < classes; i++) {
            for (int j = 0; j < letterMax; j++) {
                nameClasses.put(j, nameClass[j]);
                Schoolboy[] schoolboys = new Schoolboy[capacityInClassMax+1];
                for (int k = 0; k < capacityInClassMin; k++) {
                    schoolboys[k] = new Schoolboy();
                    schoolboys[k].setAge(schoolboys[k].getAge()+i);
                }
                classesMap.put(j, schoolboys);
            }
            levelClass.put(i, classesMap);
            classesMap = new HashMap<>(nameClass.length);
        }
    }


    protected void showClasses(int level, int liter){
    if (level +1 <= classes && liter +1 <= levelClass.get(level).size()) {
            Schoolboy[] output = (Schoolboy[]) levelClass.get(level).get(liter);
            System.out.println("Список учеников в классе " + (level + 1) + "'" + nameClasses.get(liter) + "':");
            int i = 0;
            while (output[i] != null) {
                System.out.println(output[i].getFirstName() + " " + output[i].getLastName() + " возраст - "
                        + output[i].getAge() + "; успеваемость - " + output[i].getProgressName()); i++;}
        }
    else {
        System.out.println("Чтото не так, классов - " + classes + " букв в запрошенном классе до - " + nameClass[(levelClass.get(level).size())-1]);
        System.out.println("Запрос классов - " + (level + 1) + " буква - " + nameClass[(liter)]);
    }

    }

    protected void addSchoolboy(int age, int count) {
        if (age < 6) {
            System.out.println("Возраст должен быть больше 6, ему еще рано в школу");
        } if (age > 17) {
            System.out.println("Возраст должен быть меньше 17, ему уже поздно в школу");
        } else {
            if (age == 6) { age++; }
            count --;
            int where = 0;
            for (int c = 0 ;count >= c; c++) {
                boolean stop = true;
                int level = age - 7;
                for (int j = 0 + where; stop; j++) {
                    Schoolboy[] newClass = (Schoolboy[]) levelClass.get(level).get(j);
                    int i = 0;
                    while (newClass[i] != null && stop) {
                        if (i == capacityInClassMax - 1) { j++; i=0; where++; nameClasses.put(j, nameClass[j]);
                            newClass = new Schoolboy[capacityInClassMax+1];
                            newClass[i] = new Schoolboy();
                            newClass[i].setFirstName("Новенький " + newClass[i].getFirstName());
                            newClass[i].setAge(age);
                            HashMap classesMap = levelClass.get(level);
                            classesMap.put(j, newClass);
                            levelClass.put(level, classesMap);
                            stop = false;
                            j--;
                        break;
                        }

                       if (newClass[i + 1] == null) {
                            newClass[i + 1] = new Schoolboy();
                            newClass[i + 1].setFirstName("Новенький " + newClass[i + 1].getFirstName());
                            newClass[i + 1].setAge(age);
                            HashMap classesMap = levelClass.get(level);
                            classesMap.put(j, newClass);
                            levelClass.put(level, classesMap);
                            stop = false;}
                        i++;
                    }

                }
            }
        }
    }
}