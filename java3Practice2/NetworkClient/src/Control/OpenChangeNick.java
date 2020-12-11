package Control;
import Network.Client;
import clientserver.Command;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class OpenChangeNick {
    private String PATH_SAMPLE_XML = "changeNick.fxml";
    @FXML
    private TextField inputFieldChange;
    private static Client network;

    public void startNewWindow (Client net) throws IOException {
        network = net;
        FXMLLoader loader = new FXMLLoader();
        Stage primaryStage = new Stage();
        loader.setLocation(Main.class.getResource(PATH_SAMPLE_XML));
        Parent root = loader.load();
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.show();
    }

    @FXML
    public void changeNickStart() throws IOException {
        String word = inputFieldChange.getText();
        if (!word.isEmpty()) {
            System.out.println("work");
            network.sendMessage(Command.changeNickName(word));
        }
        inputFieldChange.setText("");
    }
}
