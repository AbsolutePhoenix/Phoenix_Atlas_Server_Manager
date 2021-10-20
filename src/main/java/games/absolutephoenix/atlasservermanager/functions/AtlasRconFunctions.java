package games.absolutephoenix.atlasservermanager.functions;

import games.absolutephoenix.atlasservermanager.rcon.Rcon;
import games.absolutephoenix.atlasservermanager.rcon.ex.AuthenticationException;

import java.io.IOException;

public class AtlasRconFunctions {


    public static void StopGrid(String GridID)
    {
        try {
            Rcon rcon = new Rcon("127.0.0.1", 2000, "p03301991".getBytes());
            rcon.command("DoExit");
            rcon.disconnect();
        } catch (IOException | AuthenticationException e) {
            e.printStackTrace();
        }
    }

    public static String[][] GetPlayers(String GridID)
    {
        String[] FirstSplit;
        String[][] result;
        String response;
        try {
            Rcon rcon = new Rcon("127.0.0.1", 28186, "p03301991".getBytes());
            response = rcon.command("listplayers");
            rcon.disconnect();
        } catch (IOException | AuthenticationException e) {
            response = "No Players Connected";
        }
        if(response.equals("No Players Connected")) {
            result = new String[][]{{"No Players Connected", "No Players Connected"}};
        }
        else {
            FirstSplit = response.replaceFirst("\n", "").split("\n");
            for (int i = 0; i < FirstSplit.length; i++) {
                FirstSplit[i] = FirstSplit[i].substring(FirstSplit[i].indexOf(". ") + 2);

            }
            result = new String[FirstSplit.length -1][];
            for (int i = 0; i < FirstSplit.length -1; i++)
            {
                String[] tmp = FirstSplit[i].split(", ");
                result[i] = tmp;
            }

        }

        return result;
    }
}
