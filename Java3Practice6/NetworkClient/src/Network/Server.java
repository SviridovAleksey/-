package Network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {
    private static DataInputStream in;
    private static DataOutputStream out;
    public static void main(String[] args) {
        Socket socket;
        try {
            ServerSocket serverSocket = new ServerSocket(8189);
            System.out.println("Ожидание подключения...");
            socket = serverSocket.accept();
            System.out.println("Клиент подключен");
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            userOutMessage();
            while (true) {
                String str = in.readUTF();
                System.out.println("Пользователь: " + str);
                if (str.equals("/end")) {break;}
                out.writeUTF(str);
            }
            System.out.println("Сервер остановлен");
        } catch (IOException e) {
            System.out.println("Соединение не возможно или потеряно");
            e.printStackTrace();
        }
    }

    private static void userOutMessage () {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("Введите сообщение клиенту:");
                    Scanner input = new Scanner(System.in);
                    String inputMessage = input.next();
                    if (!inputMessage.equals(null)) {
                        try {
                            out.writeUTF(inputMessage);
                        } catch (IOException e) {
                            System.out.println("Не удалось отправить сообщение");
                            e.printStackTrace();
                        }
                    }
                }
            }
        }); thread.setDaemon(true);
        thread.start();

    }
}
