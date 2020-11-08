import java.util.Random;

class Main {

    protected static String[] name = {"Андрэ", "Чувачелло", "Косматый", "Страшила", "Соня", "Васька"};
    protected static Participant[] participants;
    protected static Let[] theTask;

    public static void main(String[] arg){
        int number = 5;
        int task = 3;
        participants = new Participant[number];
        theTask = new Let[task];
        who();
        fillingTask();
        competition();
    }

    protected static void who () {
        Random random = new Random();
        for (int i = 0; i < participants.length; i++) {
            int who = random.nextInt(3);
            switch (who) {
                case (0) -> participants[i] = new Human();
                case (1) -> participants[i] = new Cat();
                case (2) -> participants[i] = new Robot();
            }
            participants[i].setName(name [random.nextInt(name.length - 1)]);
        }
    }

    protected static void fillingTask(){
        Random random = new Random();
        int task;
     for (int i = 0; i < theTask.length; i++) {
         task = random.nextInt(3);
         switch (task) {
             case (0) -> {
                 theTask[i] = new Track();
                 theTask[i].setSize(random.nextInt(200));
             }
             case (1) -> {
                 theTask[i] = new Wall();
                 theTask[i].setSize(random.nextInt(10));
             }
             case (2) -> {
                 theTask[i] = new Lake();
                 theTask[i].setSize(random.nextInt(30));
             }
         }
     }

    }

    protected static void competition(){
        boolean check;
        for (Participant participant : participants) {
            int j = 0;
            check = true;
            System.out.println("Учстник " + participant.getKind() + " по имени " + participant.getName());
            while (j < theTask.length && check) {
                if (theTask[j] instanceof Track) {
                    participant.runnig();

                }
                if (theTask[j] instanceof Wall) {
                    participant.jumping();

                }
                if (theTask[j] instanceof Lake) {
                    participant.swiming();

                }
                check = theTask[j].overcoming(participant.getLimit());
                System.out.println("Предел для такого препятсвия был " + participant.getLimit());
                j++;
            if (j == theTask.length && check) {System.out.println("И тем самым успешно заканчивает гонку, поздравляем ");}
            }
            System.out.println();
        }
    }
}
