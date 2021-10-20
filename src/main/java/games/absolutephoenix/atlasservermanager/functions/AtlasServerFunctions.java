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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AtlasServerFunctions {

    public static boolean CheckForRedis() throws IOException {
        boolean redisRunning = false;
        String line;
        Process p = Runtime.getRuntime().exec(System.getenv("windir") + "\\system32\\"+"tasklist.exe");
        BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
        while ((line = input.readLine()) != null) {
            if(line.contains("redis-server.exe")) {
                redisRunning = true;
            }
        }
        input.close();
        return redisRunning;
    }

    public static void StartRedisServer() throws IOException {
        if (CheckForRedis())
        {
            System.out.println("The Redis Server is already running.");
        }else
        {
            Process process = new ProcessBuilder("cmd.exe", "/c start /min AtlasTools\\RedisDatabase\\redis-server.exe AtlasTools/RedisDatabase/redis.conf").start();
            try {
                process.waitFor();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void StopRedisServer() throws IOException {
        Process p = Runtime.getRuntime().exec("taskkill /F /IM redis-server.exe");
    }
    public static void StartAtlasServer(int gridX, int gridY, String saveDirectoryName, String adminPassword, int maxPlayers, int queryPort, int gamePort, String ip, boolean rconEnabled, int rconPort, boolean battleEye) throws IOException {
        String ProcessArguments = "Ocean" + "?" + "ServerX=" + gridX + "?" + "ServerY=" + gridY + "?" + "AltSaveDirectoryName=" + saveDirectoryName + "?" + "ServerAdminPassword=" + adminPassword + "?" + "MaxPlayers=" + maxPlayers + "?" + "ReservedPlayerSlots=" + (maxPlayers / 2) + "?" + "QueryPort=" + queryPort + "?" + "Port=" + gamePort + "?" + "SeamlessIP=" + ip + "?" + "RCONEnabled=" + rconEnabled + "?" + "RCONPort=" + rconPort + " -log -server";
        if(!battleEye)
            ProcessArguments = ProcessArguments + " -NoBattlEye";
        Process process = new ProcessBuilder("cmd.exe", "/c start /high /min ShooterGame\\Binaries\\Win64\\ShooterGameServer.exe " + ProcessArguments).start();
        try {
            process.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}