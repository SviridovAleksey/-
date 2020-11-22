package Network;


import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import sample.Controller;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
    private static final String SERVER_ADRESS = "localhost";
    private static final int SERVER_PORT = 8189;
    private final String host;
    private final int port;
    private DataInputStream in;
    private DataOutputStream out;
    private Socket socket;
    private static String message;
    private Controller controller;

public Client(String host, int port){
    this.host = host;
    this.port = port;
}
public Client() {
    this.host = SERVER_ADRESS;
    this.port = SERVER_PORT;

}
public boolean connect (){
    try {
        socket = new Socket(host, port);
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
        return true;
    } catch (IOException e) {
        System.out.println("Соединения не было установлено");
        e.printStackTrace();
        return false;
    }
}

public void sendMessage (String message){
    try {
        out.writeUTF(message);
    } catch (IOException e) {
        e.printStackTrace();
        System.out.println("Ошибка при отправке");
    }
}

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void waitMessage (){
    controller.getListViewMessegOut().getItems().add("");
Thread thread = new Thread( () -> {
    try {
        while (true) {
            message = in.readUTF();
            Platform.runLater(() -> controller.addWordOut( controller.getListViewMessegOut(), message));
            System.out.println("Сообщение от сервера: " + message);
        }
    } catch (IOException e) {
        e.printStackTrace();
        Platform.runLater(() -> controller.addWordOut( controller.getListViewMessegOut(),"Соединение" +
                " потеряно!"));
        System.out.println("Соединение потеряно");
    }
});
thread.setDaemon(true);
thread.start();
}


public void close (){
    try {
        in.close();
        out.close();
        socket.close();
    } catch (IOException e) {
        System.out.println("Соединение прервано со стороны клиента");
        e.printStackTrace();
    }
}



}
