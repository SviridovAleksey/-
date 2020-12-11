package Control;


import Network.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

import java.io.IOException;
import java.util.List;

public class Controller {
    @FXML
    private TextField inputField;
    @FXML
    private ListView listViewMesseg;
    @FXML
    private ListView listViewFriends;
    private Client network;
    private String selectedRecipient;




    @FXML
    public void initialize() {
        customCell(listViewMesseg, Pos.BASELINE_LEFT);
        friendsRebuild();
        this.network = new Client();

    }

    public void friendsRebuild (){
        listViewFriends.setCellFactory(lv -> {
            MultipleSelectionModel<String> selectionModel = listViewFriends.getSelectionModel();
            ListCell<String> cell = new ListCell<>();
            cell.textProperty().bind(cell.itemProperty());
            cell.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> {
                listViewFriends.requestFocus();
                if (! cell.isEmpty()) {
                    int index = cell.getIndex();
                    if (selectionModel.getSelectedIndices().contains(index)) {
                        selectionModel.clearSelection(index);
                        selectedRecipient = null;
                    } else {
                        selectionModel.select(index);
                        selectedRecipient = cell.getItem();
                    }
                    event.consume();
                }
            });
            return cell ;
        });
    }


    @FXML
    public void addWordToListIn() throws IOException {
        String word = inputField.getText();
        if (!word.isEmpty()) {
            if(selectedRecipient == null) {
            network.sendMessage(word);
            addWordOut(listViewMesseg, word); }
            else  {
                network.sendPrivateMessage(word, selectedRecipient);
                addWordOut(listViewMesseg, word + " - личное для " + selectedRecipient);
            }
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
    public void openChangeNick() throws IOException {
        new OpenChangeNick().startNewWindow(network);
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

    public void updateUsers(List<String> users) {
        listViewFriends.setItems(FXCollections.observableList(users));
    }
}
