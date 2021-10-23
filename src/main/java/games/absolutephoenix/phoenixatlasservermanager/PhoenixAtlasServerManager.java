package games.absolutephoenix.phoenixatlasservermanager;

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

import com.bulenkov.darcula.DarculaLaf;
import games.absolutephoenix.phoenixatlasservermanager.configuration.ConfigManager;
import games.absolutephoenix.phoenixatlasservermanager.configuration.settings.ManagerSettings;
import games.absolutephoenix.phoenixatlasservermanager.functions.AtlasServerFunctions;
import games.absolutephoenix.phoenixatlasservermanager.userinterface.MainFrame;
import games.absolutephoenix.phoenixatlasservermanager.userinterface.SplashScreen;
import games.absolutephoenix.phoenixatlasservermanager.utils.HelperMethods;

import javax.swing.*;
import javax.swing.plaf.basic.BasicLookAndFeel;
import java.io.File;
import java.io.IOException;

public class PhoenixAtlasServerManager {

    public static void main(String[] args)
    {
        try {
            if(!new File("ManagerConfig/Manager.ini").exists())
                ConfigManager.FirstTimeCreateConfig();
            ConfigManager.LoadLauncherSettings();
            ConfigManager.SaveLauncherSettings();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(ManagerSettings.DarkMode) {
            BasicLookAndFeel darcula = new DarculaLaf();
            try {
                UIManager.setLookAndFeel(darcula);
            } catch (UnsupportedLookAndFeelException e) {
                e.printStackTrace();
            }
        }

        SplashScreen splashScreen = new SplashScreen();
        //InstallRequiredPrograms(splashScreen);
        splashScreen.setTask("Startup Tasks complete!");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        splashScreen.Destroy();

        MainFrame mainFrame = new MainFrame();
    }

    private static void InstallRequiredPrograms(SplashScreen splashScreen) {
        splashScreen.setTask("Checking for SteamCMD.");
        try {
            Thread.sleep(400);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(HelperMethods.CheckForSteamCMD()) {
            splashScreen.setTask("SteamCMD is installed.");
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else {
            splashScreen.setTask("SteamCMD not found.");
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            splashScreen.setTask("Downloading and installing SteamCMD. (Please wait, depending on your internet this could take a bit.)");
            AtlasServerFunctions.InstallSteamCMD();
            splashScreen.setTask("SteamCMD is now installed.");
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        splashScreen.setTask("Checking for Atlas.");
        try {
            Thread.sleep(400);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(HelperMethods.CheckForAtlas()) {
            splashScreen.setTask("Atlas is installed.");
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else {
            splashScreen.setTask("Atlas not found.");
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            splashScreen.setTask("Downloading and installing Atlas. (Please wait, depending on your internet this could take a bit.)");
            AtlasServerFunctions.InstallAtlas();
            splashScreen.setTask("Atlas is now installed");
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        splashScreen.setTask("Checking for Server Grid Editor.");
        try {
            Thread.sleep(400);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(HelperMethods.CheckForServerGridEditor()) {
            splashScreen.setTask("Server Grid Editor is installed.");
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else {
            splashScreen.setTask("Server Grid Editor not found.");
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            splashScreen.setTask("Downloading Server Grid Editor.");
            AtlasServerFunctions.InstallAtlasServerGridEditor();
            splashScreen.setTask("Server Grid Editor is now installed");
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}