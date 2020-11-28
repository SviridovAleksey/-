package Network;


import javafx.application.Platform;
import Control.Controller;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
    private static final String AUTH_CMD_PREFIX = "/auth";
    private static final String AUTHOK_CMD_PREFIX = "/authok";
    private static final String AUTHERR_CMD_PREFIX = "/autherr";

    public String getUsername() {
        return username;
    }

    private String username;

    private static final String SERVER_ADRESS = "localhost";
    private static final int SERVER_PORT = 8189;
    private final String host;
    private final int port;
    private DataInputStream in;
    private DataOutputStream out;
    private Socket socket;
    private static String message;
    private static String messageLogin;
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

public void sendMessage (String message, boolean login){
    try {
        if (!login) {
        out.writeUTF(username + ": " + message); }
        else {out.writeUTF(message);}
    } catch (IOException e) {
        e.printStackTrace();
        System.out.println("Ошибка при отправке");
    }
}

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void waitMessage (){
Thread thread = new Thread( () -> {
    try {
        while (true) {
            message = in.readUTF();
            Platform.runLater(() -> controller.addWordOut( controller.getListViewMesseg(), message));
            System.out.println("Сообщение от сервера: " + message); }
    } catch (IOException e) {
        e.printStackTrace();
        Platform.runLater(() -> controller.addWordOut( controller.getListViewMesseg(),"Соединение" +
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



    public String sendAuthCommand(String login, String password) {
        sendMessage(String.format("%s %s %s", AUTH_CMD_PREFIX, login, password), true);
 //       System.out.println(String.format("%s %s %s", AUTH_CMD_PREFIX, login, password));
        waitMessageLogin();
        while (true){
            if(messageLogin != null) {
        if(messageLogin.startsWith(AUTHOK_CMD_PREFIX)) {
            System.out.println(messageLogin);
           this.username = messageLogin.split("\\s+", 2)[1];
           return null;
        }
        if(messageLogin.startsWith(AUTHERR_CMD_PREFIX)) {
            return AUTHERR_CMD_PREFIX; }
        } }
    }

    public void waitMessageLogin(){
        Thread threadLogin = new Thread( () -> {
            try {
                while (true) {
                    messageLogin = in.readUTF();
                    System.out.println("Сообщение от сервера: " + messageLogin);
                return;}
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Соединение потеряно");
            }
        });
        threadLogin.start(); }
}

