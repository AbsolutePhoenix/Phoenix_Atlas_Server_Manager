package games.absolutephoenix.atlasservermanager;

import games.absolutephoenix.atlasservermanager.functions.AtlasServerFunctions;

import java.io.IOException;

public class AtlasServerManager {

    public static void main(String[] args)
    {
        try {
            AtlasServerFunctions.StopRedisServer();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}