package auth;

import java.sql.*;

public class BaseAuthService implements AuthService{

    private static Connection connection;
    private static Statement stnt;
    private static PreparedStatement psGetUser;
    private static PreparedStatement psChangeNick;
    private static int userId;


    public BaseAuthService() throws SQLException, ClassNotFoundException {
    connect();
    prepareStatements();
    }


    public String getUsernameByLoginAndPassword(String login, String password) {

        try {
            psGetUser.setString(1,login);
            ResultSet resultSet = psGetUser.executeQuery();
            boolean stop = true;
            while (resultSet.next()) {
                int idFromBase = resultSet.getInt(1);
                String userNameFromBAse = resultSet.getString(2);
                String passwordFromBase = resultSet.getString(3);
                String loginFromBase = resultSet.getString(4);
                if (login.equals(loginFromBase) && password.equals(passwordFromBase)) {
                    userId = idFromBase;
                    resultSet.close(); return userNameFromBAse;
                }
            }
            resultSet.close();
            return null;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }

    }

    public int getUserId () {
        return userId;
    }

    public void changeNickInBase (int id, String newNick) throws SQLException {
        String newNickForDataBase = "'" + newNick + "'";
        psChangeNick.setString(1,newNick);
        psChangeNick.setString(2,id+"");
        psChangeNick.executeUpdate();
        psChangeNick.close();
    }


    public static void connect() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:main.db"); //путь и подключение к базе
        stnt = connection.createStatement(); // защищенный запрос
    }


    private static void prepareStatements() throws SQLException {
        psGetUser = connection.prepareStatement("SELECT id,nickName,password,login FROM users WHERE login=?;");
        psChangeNick = connection.prepareStatement("UPDATE users SET nickName=? WHERE id = ?;");
    }

}
