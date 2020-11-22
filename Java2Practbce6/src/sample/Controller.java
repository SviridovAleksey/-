package sample;


import Network.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;
import javafx.util.Callback;

public class Controller {
    @FXML
    private TextField inputField;
    @FXML
    private ListView listViewMesseg;
    @FXML
    private ListView listViewMessegOut;
    @FXML
    private ListView listViewFriends;
    private ObservableList<String> friendList = FXCollections.observableArrayList("Петя", "Ваня", "Диана");
    private Client network;
    private Main main;




    @FXML
    public void initialize() {
        customCell(listViewFriends, Pos.BASELINE_LEFT);
        customCell(listViewMesseg, Pos.BASELINE_RIGHT);
        customCell(listViewMessegOut, Pos.BASELINE_LEFT);
        listViewFriends.setItems(friendList);
        this.network = new Client();

    }

    @FXML
    public void addWordToListIn() {
        String word = inputField.getText();
        if (!word.isBlank()) {
            network.sendMessage(word);
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
            System.out.println("Соединение установлено"); network.waitMessage(); }
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
            listView.getItems().add("");
    }

    public ListView getListViewMessegOut (){
        return listViewMessegOut;
    }

    public Client getNetwork() {
        return network;
    }
}
