package chat;

import auth.BaseAuthService;
import clientserver.*;
import handler.ClientHandler;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.*;

public class MyServer {
    private final ServerSocket serverSoket;
    private final BaseAuthService authService;
    private final List<ClientHandler> clients = new ArrayList<>();
    private static final Logger logger = Logger.getLogger(MyServer.class.getName());
    private Socket clientSocket;

    public MyServer(int port, Handler fileHandler) throws IOException, SQLException, ClassNotFoundException {
        this.serverSoket = new ServerSocket(port);
        this.authService = new BaseAuthService();
        logger.addHandler(fileHandler);


    }

    public void start() throws IOException {
        try {
            logger.setUseParentHandlers(false);
            logger.log(Level.INFO, "Сервер запущен");
            while (true) {
                waitAndProcessNewClientConnection();
            }
        }catch (IOException e) {
            System.out.println("Ошибка создания нового подключения");
            logger.log(Level.SEVERE, e.getMessage());
            e.printStackTrace();
        } finally {
            serverSoket.close();
        }
    }

    private void waitAndProcessNewClientConnection() throws IOException {
        logger.log(Level.INFO, "Ожидание пользователя");
        clientSocket = serverSoket.accept();
        logger.log(Level.INFO, "Клиент подключен");
        ExecutorService service = Executors.newFixedThreadPool(10);
        service.execute(()->{
            try {
                processNewClientConnection(clientSocket);
            } catch (IOException e) {
                e.printStackTrace();
                logger.log(Level.SEVERE, e.getMessage());
            }
        });

    }

    public void disconnect (Socket socket) throws IOException {
        socket.close();
    }

    private void processNewClientConnection(Socket clientSocket) throws IOException {
        ClientHandler clientHandler = new ClientHandler(this, clientSocket);
        clientHandler.handle();
    }

      public BaseAuthService getAuthService() {
      return authService;
      }
    
    public synchronized void subscribe(ClientHandler clientHandler) throws IOException {
        logLevelInfo(clientHandler, " ---------вошел в чат");
        clients.add(clientHandler);
        List<String> userNames = getAllUsernames();
        broadCastMessage(null, Command.updateUsersListCommand(userNames));
    }

    public synchronized void changeNickName(ClientHandler clientHandler) throws IOException {
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
        logLevelInfo(clientHandler, " ---------вышел из чата");
        String message = String.format(">>> %s вышел из чата", clientHandler.getClientUserName());
        clients.remove(clientHandler);
        List<String> usernames = getAllUsernames();
        broadCastMessage(null, Command.updateUsersListCommand(usernames));
        broadCastMessage(null, Command.messageInfoCommand(message, null));

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
        logLevelInfo(sender, " написал сообщение в общий чат");
        for (ClientHandler client : clients) {
            if (client == sender) {
                continue;
            }
            client.sendMessage(command);
        }
    }
    public synchronized void sendPrivateMessage(String recipient, Command command, ClientHandler sender) throws IOException {
        logLevelInfo(sender, " написал сообщение в линый чат");
        for (ClientHandler client : clients) {
            if (client.getClientUserName().equals(recipient)) {
                client.sendMessage(command);
                break;
            }
        }
    }

    private void logLevelInfo(ClientHandler sender, String message) {
        if (sender != null) {
            logger.log(Level.INFO, sender.getClientUserName() + message);
        }
    }
}
