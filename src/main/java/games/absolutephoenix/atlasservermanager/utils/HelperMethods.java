package games.absolutephoenix.atlasservermanager.utils;

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

import java.io.*;
import java.net.URL;

public class HelperMethods {

    public static String getExternalIp() throws IOException {
        URL whatismyip = new URL("http://checkip.amazonaws.com");
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(
                    whatismyip.openStream()));
            return in.readLine();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static boolean CheckForSteamCMD() {
        return new File("SteamCMD/steamcmd.exe").exists();
    }
    public static boolean CheckForAtlas() {
        return new File("ShooterGame").exists();
    }
    public static boolean CheckForServerGridEditor() {
        return new File("AtlasTools\\ServerGridEditor").exists();
    }
}
