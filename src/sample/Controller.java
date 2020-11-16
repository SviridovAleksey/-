package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

public class Controller {

    protected Stage primaryStage;

    @FXML
    private TextField inputField;
    @FXML
    private ListView listViewMesseg;
    @FXML
    private ListView listViewMessegOut;
    @FXML
    private ListView listViewFriends;
    private ObservableList<String> friendList = FXCollections.observableArrayList("Петя", "Ваня", "Диана");

    @FXML
    public void initialize() {
        customCell(listViewFriends, Pos.BASELINE_LEFT);
        customCell(listViewMesseg, Pos.BASELINE_RIGHT);
        customCell(listViewMessegOut, Pos.BASELINE_LEFT);
        listViewFriends.setItems(friendList);
    }

    @FXML
    public void addWordToList() {
        String word = inputField.getText();
        if (!word.isBlank()) {
            listViewMesseg.getItems().add(word);
            listViewMessegOut.getItems().add("");
            System.out.println(listViewMessegOut.getItems());
            listViewMessegOut.getItems().add(word);
            listViewMesseg.getItems().add("");
            ObservableList aabannba= listViewMesseg.getItems();
            }
        inputField.setText("");
        }

    @FXML
    public void exit(){
        System.exit(0);
    }

    @FXML
    public void openAbout(){
    new openAbout().startNewWindow();
    }


    private void customCell (ListView listView, Pos position){
        listView.setCellFactory(new Callback<ListView, ListCell>() {
            @Override
            public ListCell call(ListView listView) {
                return new LayoutListViewCell(position);
            }
        });
    }


}
