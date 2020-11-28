package auth;

import chat.User;

import java.util.List;

public class BaseAuthService {

    private static final List<User> clients = List.of(
            new User("user1", "1111", "Петя"),
            new User("user2", "2222", "Ваня"),
            new User("user3", "3333", "Диана")
    );


    public String getUsernameByLoginAndPassword(String login, String password) {

        for (User client : clients) {
            if (client.getLogin().equals(login) && client.getPassword().equals(password)) {
                return client.getUsername();
            }
        }
        return null;
    }
}
