package games.absolutephoenix.atlasservermanager.rcon.ex;

import java.io.IOException;

public class MalformedPacketException extends IOException {

    public MalformedPacketException(String message) {
        super(message);
    }
}