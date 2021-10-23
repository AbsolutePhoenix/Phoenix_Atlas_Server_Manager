package games.absolutephoenix.phoenixatlasservermanager.configuration;

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

import games.absolutephoenix.phoenixatlasservermanager.configuration.settings.DiscordWebhookSettings;
import games.absolutephoenix.phoenixatlasservermanager.configuration.settings.ManagerSettings;
import org.ini4j.Config;
import org.ini4j.Ini;
import org.ini4j.Wini;

import java.io.File;
import java.io.IOException;

public class ConfigManager {
    public static void FirstTimeCreateConfig() throws IOException {
        if(!new File("ManagerConfig/Manager.ini").exists()) {
            //noinspection ResultOfMethodCallIgnored
            new File("ManagerConfig/").mkdir();
            //noinspection ResultOfMethodCallIgnored
            new File("ManagerConfig/Manager.ini").createNewFile();
        }
        //build ini configuration
        Wini ini = new Wini();
        Config config = new Config();
        config.setMultiOption(true);
        config.setMultiSection(true);
        ini.setConfig(config);

        ini.add("Phoenix Manager");
        ini.add("Discord Webhook");

        Ini.Section PhoenixManager = ini.get("Phoenix Manager");
        Ini.Section DiscordWebhook = ini.get("Discord Webhook");
        //manager settings
        PhoenixManager.add("DarkMode", "true");
        PhoenixManager.add("AllowTracking", "true");
        PhoenixManager.add( "IndividualTracking", "false");
        PhoenixManager.add("PlayersToTrack", "");
        //discord settings
        DiscordWebhook.add("WebhookURL", "");
        DiscordWebhook.add("SenderUsername", "Phoenix Atlas Server Manager");
        DiscordWebhook.add("SenderAvatar", "https://b.thumbs.redditmedia.com/8in3lDndYdhG6GA3w0GdbiUZAOd0KY2L9_xD1_e-D0M.png");


        ini.store(new File("ManagerConfig/Manager.ini"));
    }

    public  static void LoadLauncherSettings() throws IOException {

        Wini ini = new Wini();
        Config config = new Config();
        config.setMultiOption(true);
        config.setMultiSection(true);
        ini.setConfig(config);
        ini.load(new File("ManagerConfig/Manager.ini"));

        Ini.Section PhoenixManager = ini.get("Phoenix Manager");
        Ini.Section DiscordWebhook  = ini.get("Discord Webhook");

        System.out.println(PhoenixManager);
        System.out.println(DiscordWebhook);
        ManagerSettings.DarkMode = PhoenixManager.get("DarkMode", boolean.class);
        ManagerSettings.AllowTracking = PhoenixManager.get("AllowTracking", boolean.class);
        ManagerSettings.IndividualTracking = PhoenixManager.get("IndividualTracking", boolean.class);
        ManagerSettings.PlayersToTrack = PhoenixManager.getAll("PlayersToTrack");
        for(int x = 0; x < ManagerSettings.PlayersToTrack.size(); x++)
            System.out.println(ManagerSettings.PlayersToTrack.get(x));

        DiscordWebhookSettings.WebhookURL = DiscordWebhook.get("WebhookURL");
        DiscordWebhookSettings.SenderUsername = DiscordWebhook.get("SenderUsername");
        DiscordWebhookSettings.SenderAvatar = DiscordWebhook.get("SenderAvatar");
    }
    public static void SaveLauncherSettings() throws IOException {
        if(!new File("ManagerConfig/Manager.ini").exists()) {
            //noinspection ResultOfMethodCallIgnored
            new File("ManagerConfig/").mkdir();
            //noinspection ResultOfMethodCallIgnored
            new File("ManagerConfig/Manager.ini").createNewFile();
        }
        //build ini configuration
        Wini ini = new Wini();
        Config config = new Config();
        config.setMultiOption(true);
        config.setMultiSection(true);
        ini.setConfig(config);

        ini.add("Phoenix Manager");
        ini.add("Discord Webhook");

        Ini.Section PhoenixManager = ini.get("Phoenix Manager");
        Ini.Section DiscordWebhook = ini.get("Discord Webhook");
        //manager settings
        PhoenixManager.add("DarkMode", ManagerSettings.DarkMode);
        PhoenixManager.add("AllowTracking", ManagerSettings.AllowTracking);
        PhoenixManager.add( "IndividualTracking", ManagerSettings.IndividualTracking);
        for(int size = 0; size < ManagerSettings.PlayersToTrack.size(); size++)
            PhoenixManager.add("PlayersToTrack", ManagerSettings.PlayersToTrack.get(size));
        //discord settings
        DiscordWebhook.add("WebhookURL", "");
        DiscordWebhook.add("SenderUsername", "Phoenix Atlas Server Manager");
        DiscordWebhook.add("SenderAvatar", "https://b.thumbs.redditmedia.com/8in3lDndYdhG6GA3w0GdbiUZAOd0KY2L9_xD1_e-D0M.png");


        ini.store(new File("ManagerConfig/Manager.ini"));
    }
}
