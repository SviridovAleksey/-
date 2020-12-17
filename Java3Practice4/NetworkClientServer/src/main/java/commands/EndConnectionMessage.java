package commands;

import java.io.Serializable;

public class EndConnectionMessage implements Serializable {

    private final String sender;

    public EndConnectionMessage(String sender) {
        this.sender = sender;
    }

    public String getSender() {
        return sender;
    }
}
