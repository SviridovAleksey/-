package commands;

import java.io.Serializable;

public class ChangeNickNameCommand implements Serializable {

    private final String nick;

    public ChangeNickNameCommand(String nickNew) {
        this.nick = nickNew;
    }

    public String getChangeNick() {
        return nick;
    }
}
