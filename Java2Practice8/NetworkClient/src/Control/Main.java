package Control;

import Network.Client;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    private Client network;
    private Controller controller;
    private String PATH_SAMPLE_XML = "sample.fxml";


    @Override
    public void start(Stage primaryStage) throws Exception {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(Main.class.getResource(PATH_SAMPLE_XML));
                Parent root = loader.load();
                primaryStage.setScene(new Scene(root));
                primaryStage.setResizable(false);
//               primaryStage.show();
                controller = loader.getController();
                network = controller.getNetwork();
                network.setController(controller);
                controller.connect();
                new OpenAuthorization().startNewWindow(primaryStage, network);
                primaryStage.setOnCloseRequest(windowEvent -> network.close());

        }


    public static void main(String[] args) {
        launch(args);
    }


}
