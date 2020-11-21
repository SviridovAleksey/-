import java.util.*;


public class Practice3 {
    public static void main(String args[]) {
        String[] wordArray = {"apple", "orange", "lemon", "banana", "apricot", "avocado",
                "broccoli", "carrot", "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango",
                "mushroom", "nut", "olive", "pea", "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};
        String intArrayString = Arrays.toString(wordArray);
        System.out.println(intArrayString);
        int randomWord = (int) (Math.random() * wordArray.length);
        String word = wordArray[randomWord];
        int count = 0; int letter = 1; String star = "***************";
        for (int i = 0; i < word.length(); i++ ) {
            count++;
        }
        for (int i = 0; i < wordArray.length; i++) {
            System.out.println("Введите фрукт");
            Scanner input = new Scanner(System.in);
            String fruitIn = input.next();
            if (fruitIn.equals(wordArray[randomWord])) {
                System.out.println("Вы угадали");
                i=wordArray.length ;  }
            else {
                System.out.println("Не верно попробуй снова");
                if (letter <= count) {
                    char[] str= new char[(15 - letter) - 0];
                    char[] dst= new char[letter - 0];
                    star.getChars(0, (15-letter), str, 0);
                    word.getChars(0, letter, dst, 0);
                    System.out.print(dst); System.out.println(str); letter ++;
                }
                else {System.out.println(wordArray[randomWord]);}
                i = 0; }
        }
    }
}