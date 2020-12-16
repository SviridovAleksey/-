package Control;


import Network.Client;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Controller {
    @FXML
    private TextField inputField;
    @FXML
    private ListView listViewMesseg;
    @FXML
    private ListView listViewFriends;
    private Client network;
    private String selectedRecipient;
    private static String fileName;




    @FXML
    public void initialize() {
        customCell(listViewMesseg, Pos.BASELINE_LEFT);
        friendsRebuild();
        this.network = new Client();


    }
    // устанавливает логин для имени файла истории и путь к файлу
    public void setLogin (String login){
        fileName = "NetworkClient\\src\\history\\history_"+login+".txt";

    }
    // востанавливает последнии 100 сообщений переписки
    public void setHistory () throws IOException {
        if(Files.exists(Paths.get(fileName))) {
        List<String> lines = Files.readAllLines(Paths.get(fileName), UTF_8);
        System.out.println(lines.size());
        if (lines.size() <= 100){
        for (int i = 0; i < lines.size(); i++) {
            addWordOut(listViewMesseg, lines.get(i) ,false);
        }} else {
            for (int i = lines.size() - 100; i < lines.size(); i++) {
                addWordOut(listViewMesseg, lines.get(i) ,false);}
        }
        } else {Files.createFile(Paths.get(fileName));}
        Date date = new Date();
        addWordOut(listViewMesseg, "вход - " + date.toString(),true);
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
            addWordOut(listViewMesseg, word, true); }
            else  {
                network.sendPrivateMessage(word, selectedRecipient);
                addWordOut(listViewMesseg, word + " - личное для " + selectedRecipient, true);
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
    //метод одновременно добавляет сообщения и на ListView, и в файл истории
    public synchronized void addWordOut (ListView listView,String word, Boolean isSave) {
        listView.getItems().add(word);
        if(isSave){
        try {
            Writer writer = new BufferedWriter(new OutputStreamWriter(
             new FileOutputStream(fileName, true), "UTF-8"));
            writer.write(word+'\n');
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        } }
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
