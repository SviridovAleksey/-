import chat.MyServer;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.*;

public class ServerApp {
    private  static final int DEFULT_PORT = 8189;
    private static final Logger logger = Logger.getLogger(ServerApp.class.getName());
    private static Handler fileHandler;

    public static void main(String[] args) {
        int port = DEFULT_PORT;

        if(args.length != 0) {
            port = Integer.parseInt(args[0]);
        }

        try {
            fileHandler = new FileHandler("log_%g.txt", 10*1024, 20, true);
            fileHandler.setFormatter(new SimpleFormatter());
            new MyServer(port, fileHandler).start();
        } catch (IOException e) {
            System.out.println("Ошибка");
            logger.log(Level.SEVERE, e.getMessage());
            e.printStackTrace();
            System.exit(1);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Не подключился к базе");
            logger.log(Level.SEVERE, e.getMessage());
        }
    }


}
