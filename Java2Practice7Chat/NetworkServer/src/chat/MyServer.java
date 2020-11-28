package chat;

import auth.BaseAuthService;
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
        Socket clientSocket = serverSoket.accept();
        System.out.println("Клиент подключился");
        processNewClientConnection(clientSocket);
    }

    private void processNewClientConnection(Socket clientSocket) throws IOException {
        ClientHandler clientHandler = new ClientHandler(this, clientSocket);
        clientHandler.handle();
    }

    public BaseAuthService getAuthService() {
        return authService;
    }
    
    public void subscribe(ClientHandler clientHandler) {
        clients.add(clientHandler);
    }
    
    public void unSubScribe(ClientHandler clientHandler) throws IOException {
        for (ClientHandler client : clients) {
            if (client == clientHandler) {
                System.out.println(clientHandler.getClientUserName() + " Отписался");
                broadCastMessage(clientHandler.getClientUserName() + " вышел из чата", clientHandler);
                clients.remove(clientHandler);
            }

        }
    }

    public boolean isUserNameBusy(String clientUserName) {

        for (ClientHandler client : clients) {
            if(client.getClientUserName().equals(clientUserName)) {
                return  true;
            }
        }
        return false;
    }

    public void broadCastMessage(String s, ClientHandler sender) throws IOException {
        for (ClientHandler client : clients) {
            if (client == sender) {
                continue;
            }
            client.sendMessage(s);

        }
    }
}
