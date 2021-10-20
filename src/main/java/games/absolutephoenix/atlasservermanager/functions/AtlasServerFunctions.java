package games.absolutephoenix.atlasservermanager.functions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
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