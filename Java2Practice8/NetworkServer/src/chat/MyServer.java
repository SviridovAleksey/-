package chat;

import auth.BaseAuthService;
import clientserver.Command;
import handler.ClientHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MyServer {
    private final ServerSocket serverSoket;
    private final BaseAuthService authService;
    private final List<ClientHandler> clients = new ArrayList<>();
    private Socket clientSocket;

    public MyServer(int port) throws IOException {
        this.serverSoket = new ServerSocket(port);
        this.authService = new BaseAuthService();

    }

    public void start() throws IOException {
        System.out.println("Сервер запущен");
        try {
            while (true) {
                waitAndProcessNewClientConnection();
            }
        }catch (IOException e) {
            System.out.println("Ошибка создания нового подключения");
            e.printStackTrace();
        } finally {
            serverSoket.close();
        }
    }

    private void waitAndProcessNewClientConnection() throws IOException {
        System.out.println("Ожидание пользователя");
        clientSocket = serverSoket.accept();
        System.out.println("Клиент подключился");
        processNewClientConnection(clientSocket);
    }

    public void disconnect () throws IOException {
        clientSocket.close();
    }

    private void processNewClientConnection(Socket clientSocket) throws IOException {
        ClientHandler clientHandler = new ClientHandler(this, clientSocket);
        clientHandler.handle();
    }

    public BaseAuthService getAuthService() {
        return authService;
    }
    
    public synchronized void subscribe(ClientHandler clientHandler) throws IOException {
        clients.add(clientHandler);
        List<String> userNames = getAllUsernames();
        broadCastMessage(null, Command.updateUsersListCommand(userNames));
    }

    private List<String> getAllUsernames() {
        List<String> usernames = new ArrayList<>();
        for (ClientHandler client : clients) {
            usernames.add(client.getClientUserName());
        }
        return usernames;
    }


    public synchronized void unSubScribe(ClientHandler clientHandler) throws IOException {
        clients.remove(clientHandler);
        List<String> usernames = getAllUsernames();
        broadCastMessage(null, Command.updateUsersListCommand(usernames));
    }

    public boolean isUserNameBusy(String clientUserName) {

        for (ClientHandler client : clients) {
            if(client.getClientUserName().equals(clientUserName)) {
                return  true;
            }
        }
        return false;
    }

    public synchronized void broadCastMessage(ClientHandler sender, Command command) throws IOException {
        for (ClientHandler client : clients) {
            if (client == sender) {
                continue;
            }
            client.sendMessage(command);

        }
    }
    public synchronized void sendPrivateMessage(String recipient, Command command) throws IOException {
        for (ClientHandler client : clients) {
            if (client.getClientUserName().equals(recipient)) {
                client.sendMessage(command);
                break;
            }
        }
    }
}
