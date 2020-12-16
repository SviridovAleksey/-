import chat.MyServer;

import java.io.IOException;
import java.sql.SQLException;

public class ServerApp {
    private  static final int DEFULT_PORT = 8189;


    public static void main(String[] args) {
        int port = DEFULT_PORT;

        if(args.length != 0) {
            port = Integer.parseInt(args[0]);
        }

        try {
            new MyServer(port).start();
        } catch (IOException e) {
            System.out.println("Ошибка");
            e.printStackTrace();
            System.exit(1);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Не подключился к базе");
        }
    }


}
