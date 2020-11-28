package handler;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import chat.MyServer;

public class ClientHandler {
    private static final String AUTH_CMD_PREFIX = "/auth";
    private static final String AUTHOK_CMD_PREFIX = "/authok";
    private static final String AUTHERR_CMD_PREFIX = "/autherr";
    private final MyServer myServer;
    private final Socket clientSoket;
    private DataInputStream io;
    private DataOutputStream out;
    private String clientUserName;
    private String message;

    public ClientHandler(MyServer myServer, Socket clientSocket) {
        this.myServer = myServer;
        this.clientSoket = clientSocket;
    }
    public void handle() throws IOException {
        io = new DataInputStream(clientSoket.getInputStream());
        out = new DataOutputStream(clientSoket.getOutputStream());
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    authentication();
                    readMessage();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    unSubScribe();
                }
            }
        }).start();

    }

    private void readMessage() throws IOException {
        while (true) {
            message = io.readUTF();
            System.out.println("message | " + clientUserName + ": " + message);
            if (message.startsWith("/end")) {
                 return;
            }
            if (!message.startsWith(AUTH_CMD_PREFIX) && !message.startsWith(AUTHOK_CMD_PREFIX) && !message.startsWith(AUTHERR_CMD_PREFIX))
            myServer.broadCastMessage(message, this);
        }
    }

    private void authentication() throws IOException {
        while (true) {
            message = io.readUTF();
            if (message.startsWith(AUTH_CMD_PREFIX)) {
                String[] parts = message.split("\\s+", 3);
                String login = parts[1];
                String password = parts[2];
                this.clientUserName = myServer.getAuthService().getUsernameByLoginAndPassword(login, password);
                if (clientUserName != null) {

                    if (myServer.isUserNameBusy(clientUserName)) {
                        out.writeUTF(AUTHERR_CMD_PREFIX + "Логин уже занят");
                       }
                    else {
                    out.writeUTF(AUTHOK_CMD_PREFIX + " " + clientUserName);
                    myServer.broadCastMessage(clientUserName + " вошел в чат", this);
                    myServer.subscribe(this);
                    break; }
                } else {
                    out.writeUTF(AUTHERR_CMD_PREFIX + " Логин или пароль не корректны");
                   }

            }
            else {
                out.writeUTF(AUTHERR_CMD_PREFIX + " ошибка авторизации");
                }
        }
    }

    public String getClientUserName() {
        return clientUserName;
    }

    public void sendMessage(String s) throws IOException {
        out.writeUTF(s);
    }

    public void unSubScribe () {
        try {
            myServer.unSubScribe(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
