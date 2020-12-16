package Network;


import Control.Controller;
import clientserver.Command;
import commands.*;
import javafx.application.Platform;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    public String getUsername() {
        return username;
    }

    private String username;

    private static final String SERVER_ADRESS = "localhost";
    private static final int SERVER_PORT = 8189;
    private final String host;
    private final int port;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private Socket socket;
    private Controller controller;

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public Client() {
        this.host = SERVER_ADRESS;
        this.port = SERVER_PORT;

    }

    public boolean connect() {
        try {
            socket = new Socket(host, port);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            return true;
        } catch (IOException e) {
            System.out.println("Соединения не было установлено");
            e.printStackTrace();
            return false;
        }
    }

    public void sendMessage(String message) throws IOException {
        sendMessage(Command.publicMessageCommand(username, message));
    }

    public void sendMessage(Command command) throws IOException {
        out.writeObject(command);
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    private Command readCommand() throws IOException {
        try {
            return (Command) in.readObject();
        } catch (ClassNotFoundException e) {
            String errorMessage = "Получен неизвестный объект";
            System.err.println(errorMessage);
            e.printStackTrace();
            sendMessage(Command.errorCommand(errorMessage));
            return null;
        }
    }

    public void waitMessage() {

        Thread thread = new Thread(() -> {
            try {
                while (true) {

                    Command command = readCommand();
                    if (command == null) {
                        System.out.println("Ошибка серверва получена неверная команда");
                        continue;
                    }

                    switch (command.getType()) {
                        case INFO_MESSAGE: {
                            MessageInfoCommandData data = (MessageInfoCommandData) command.getData();
                            String message = data.getMessage();
                            String sender = data.getSender();
                            String formattedMessage = sender != null ? String.format("%s: %s", sender, message) : message;
                            Platform.runLater(() -> {
                                controller.addWordOut(controller.getListViewMesseg(), formattedMessage, true);
                            });
                            break;
                        }
                        case ERROR: {
                            ErrorCommandData data = (ErrorCommandData) command.getData();
                            String errorMessage = data.getErrorMessage();
                            Platform.runLater(() -> {
                                controller.addWordOut(controller.getListViewMesseg(), errorMessage, true);
                            });
                            break;
                        }
                        case UPDATE_USERS_LIST: {
                            UpdateUsersListCommandData data = (UpdateUsersListCommandData) command.getData();
                            Platform.runLater(() -> controller.updateUsers(data.getUsers()));
                            break;
                        }
                        case END: {
                            EndConnectionMessage data = (EndConnectionMessage) command.getData();
                            String sender = data.getSender();
                            Platform.runLater(() -> {
                                controller.addWordOut(controller.getListViewMesseg(), sender + " - вышел из чата", true);
                            });
                            break;
                        }
                        default:
                            Platform.runLater(() -> {
                                System.out.println("Unknown command from server!");

                            });
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Соединение потеряно!");
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    public void close() {
        try {
            sendMessage(Command.endCommand(username));
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            System.out.println("Соединение прервано со стороны клиента");
            e.printStackTrace();
        }
    }

    public void sendPrivateMessage(String message, String recipient) throws IOException {
        Command command = Command.privateMessageCommand(recipient, message);
        sendMessage(command);
    }

    public String sendAuthCommand(String login, String password) {
        try {
            Command authCommand = Command.authCommand(login, password);
            out.writeObject(authCommand);
            Command command = readCommand();
            if (command == null) {
                return "Ошибка чтения команды с сервера";
            }

            switch (command.getType()) {
                case AUTH_OK: {
                    AuthOkCommandData data = (AuthOkCommandData) command.getData();
                    this.username = data.getUsername();
                    controller.setLogin(login);
                    controller.setHistory();
                    return null;
                }

                case AUTH_ERROR:
                case ERROR: {
                    AuthErrorCommandData data = (AuthErrorCommandData) command.getData();
                    return data.getErrorMessage();
                }
                default:
                    return "Unknown type of command: " + command.getType();

            }
        } catch (IOException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}

