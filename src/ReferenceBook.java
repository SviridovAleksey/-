import java.util.HashMap;


public class ReferenceBook {
    HashMap<String, String> referenceBook;

    ReferenceBook(String[] phoneNumber, String[] lastName){
        this.referenceBook = new HashMap<>();
        for (int i = 0; i < lastName.length && i < phoneNumber.length; i++) {
            referenceBook.put(phoneNumber[i], lastName[i]);
        }
    }

    protected void allOutput(){
        System.out.println(referenceBook);
    }

    protected void add(String phoneNumber, String lastName){
        referenceBook.put(phoneNumber, lastName);
    }

    protected void get(String lastName) {
        StringBuilder number = new StringBuilder();
        for(String key: referenceBook.keySet()){
            if  (referenceBook.get(key).equals(lastName))
                number.append("  ").append(key);
        }
        System.out.println(lastName + " - " + number);

    }

}
