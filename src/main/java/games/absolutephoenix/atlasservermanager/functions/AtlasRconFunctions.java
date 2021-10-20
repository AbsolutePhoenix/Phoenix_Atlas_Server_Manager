package games.absolutephoenix.atlasservermanager.functions;

/*
The MIT License (MIT)

Copyright (c) 2021 Absolute_Phoenix

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
documentation files (the "Software"), to deal in the Software without restriction, including without limitation the
rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit
persons to whom the Software is furnished to do so, subject to the following conditions:
1: All products using this code may not make revenue.
2: Any project using this project or significant portions of this project must give credit to the original copyright holder.
*/

import games.absolutephoenix.atlasservermanager.rcon.Rcon;
import games.absolutephoenix.atlasservermanager.rcon.ex.AuthenticationException;

import java.io.IOException;

public class AtlasRconFunctions {


    public static void StopGrid(String GridID)
    {
        try {
            Rcon rcon = new Rcon("127.0.0.1", 2000, "password".getBytes());
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
            Rcon rcon = new Rcon("127.0.0.1", 28186, "password".getBytes());
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
