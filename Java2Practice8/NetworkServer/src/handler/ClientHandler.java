package handler;

import java.io.*;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

import auth.BaseAuthService;
import chat.MyServer;
import clientserver.Command;
import clientserver.CommandType;
import clientserver.commands.AuthCommandData;
import clientserver.commands.PrivateMessageCommandData;
import clientserver.commands.PublicMessageCommandData;

public class ClientHandler {
    private final MyServer myServer;
    private final Socket clientSoket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private String clientUserName;
    private Boolean isAuthentication = true;
    private Timer timer = new Timer("Timer");

    public ClientHandler(MyServer myServer, Socket clientSocket) {
        this.myServer = myServer;
        this.clientSoket = clientSocket;
    }
    public void handle() throws IOException {
        in = new ObjectInputStream(clientSoket.getInputStream());
        out = new ObjectOutputStream(clientSoket.getOutputStream());
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    timForDisconnect();
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

    private void timForDisconnect() {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                try {
                    myServer.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        long delay = 120000L;
        timer.schedule(timerTask, delay);
    }

    private void readMessage() throws IOException {
        while (true) {
            Command command = readCommand();
            if (command == null) {
                continue;
            }

            switch (command.getType()) {
                case END:
                    unSubScribe();
                    return;
                case PUBLIC_MESSAGE: {
                    PublicMessageCommandData data = (PublicMessageCommandData) command.getData();
                    String message = data.getMessage();
                    String sender = data.getSender();
                    myServer.broadCastMessage(this, Command.messageInfoCommand(message, sender));
                    break;
                }
                case PRIVATE_MESSAGE:
                    PrivateMessageCommandData data = (PrivateMessageCommandData) command.getData();
                    String recipient = data.getReceiver();
                    String message = data.getMessage();
                    String addition = "Личное от - " + clientUserName;
                    myServer.sendPrivateMessage(recipient, Command.messageInfoCommand(message, addition));
                    break;
                default:
                    String errorMessage = "Неизвестный тип команды" + command.getType();
                    System.err.println(errorMessage);
                    sendMessage(Command.errorCommand(errorMessage));
            }
        }
    }

    private void authentication() throws IOException {

        while (true) {
                Command command = readCommand();
                if (command == null) {
                    continue;
                }
                if (command.getType() == CommandType.AUTH) {

                    boolean isSuccessAuth = processAuthCommand(command);
                    if (isSuccessAuth) {
                        break;
                    }
                }
            }

        }

    private boolean processAuthCommand(Command command) throws IOException {
        AuthCommandData cmdData = (AuthCommandData) command.getData();
        String login = cmdData.getLogin();
        String password = cmdData.getPassword();
        BaseAuthService authService = myServer.getAuthService();
        this.clientUserName = authService.getUsernameByLoginAndPassword(login, password);
        if (clientUserName != null) {
            if (myServer.isUserNameBusy(clientUserName)) {
                sendMessage(Command.authErrorCommand("Логин уже используется"));
                timer.cancel(); timer = new Timer("Timer"); timForDisconnect();
                return false;
            }
            timer.cancel();
        sendMessage(Command.authOkCommand(clientUserName));
            String message = String.format(">>> %s присоединился к чату", clientUserName);
            myServer.broadCastMessage(this, Command.messageInfoCommand(message, null));
            myServer.subscribe(this);
            return true;
        } else {
            sendMessage(Command.authErrorCommand("Логин или пароль не корректны"));
            timer.cancel(); timer = new Timer("Timer"); timForDisconnect();
            return false;
        }
    }


    private Command readCommand() throws IOException {
        try {
            return  (Command) in.readObject();
        } catch (ClassNotFoundException e) {
            String errorMessage = "Неизвестный объект";
            System.err.println(errorMessage);
            e.printStackTrace();
            return null;
        }
    }

    public String getClientUserName() {
        return clientUserName;
    }

    public void sendMessage(Command s) throws IOException {
        out.writeObject(s);
    }

    public void unSubScribe () {
        try {
            myServer.unSubScribe(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
