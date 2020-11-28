package Control;


import Network.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.util.Callback;

public class Controller {
    @FXML
    private TextField inputField;
    @FXML
    private ListView listViewMesseg;
    @FXML
    private ListView listViewFriends;
    private ObservableList<String> friendList = FXCollections.observableArrayList("Петя", "Ваня", "Диана");
    private Client network;




    @FXML
    public void initialize() {
        customCell(listViewFriends, Pos.BASELINE_LEFT);
        customCell(listViewMesseg, Pos.BASELINE_RIGHT);
        customCell(listViewMesseg, Pos.BASELINE_LEFT);
        listViewFriends.setItems(friendList);
        this.network = new Client();

    }

    @FXML
    public void addWordToListIn() {
        String word = inputField.getText();
        if (!word.isBlank()) {
            network.sendMessage(word, false);
            addWordOut(listViewMesseg, word);
        }
        inputField.setText("");
        }

    @FXML
    public void exit(){
        System.exit(0);
        network.close();
    }

    @FXML
    public void openAbout(){
    new openAbout().startNewWindow();
    }

    @FXML
    public void connect () {
        if (!network.connect()) {
            System.out.println("Ошибка подключения");
        }
        else {
            System.out.println("Соединение установлено"); }
    }


    private void customCell (ListView listView, Pos position) {
        listView.setCellFactory(new Callback<ListView, ListCell>() {
            @Override
            public ListCell call(ListView listView)
            {
                return new LayoutListViewCell(position);
            }
        });
    }

    public synchronized void addWordOut (ListView listView,String word){

            listView.getItems().add(word);
    }

    public ListView getListViewMesseg (){
        return listViewMesseg;
    }

    public Client getNetwork() {
        return network;
    }
}
