package games.absolutephoenix.phoenixatlasservermanager.userinterface;

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

import games.absolutephoenix.phoenixatlasservermanager.functions.AtlasServerFunctions;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Objects;

public class MainFrame extends JFrame {
    JMenuBar MenuBar = new JMenuBar();

    JMenu FileMenu = new JMenu("File");
    JMenuItem ExitMenuItem;


    JMenu SetupMenu = new JMenu("Setup");
    JMenuItem InstallSteamCMDMenuItem;
    JMenuItem InstallAtlasMenuItem;
    JMenuItem InstallServerGridEditorMenuItem;

    JMenu ConfigurationMenu = new JMenu("Configuration");
    JMenuItem ManagerSettingsMenu;
    JMenuItem AtlasGlobalSettings;
    JMenuItem AtlasIndividualSettings;

    public MainFrame()
    {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Phoenix Atlas Server Manager");
        try {
            setIconImage(new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/assets/logo/logo.png")))).getImage());
        } catch (IOException e) {
            e.printStackTrace();
        }
        setMinimumSize(new Dimension(1280, 720));
        setPreferredSize(new Dimension(1280, 720));
        setLayout(null);
        setLocationRelativeTo(null);
        CreateMenuBar();

        ActionListeners();
        setVisible(true);
    }

    public void CreateMenuBar(){
        try {

            ExitMenuItem = new JMenuItem("Quit", new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/assets/icons/ExitMenuIcon.png"))).getScaledInstance(35, 35, Image.SCALE_SMOOTH)));
            InstallSteamCMDMenuItem = new JMenuItem("Update/Install SteamCMD", new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/assets/icons/SteamMenuIcon.png"))).getScaledInstance(35, 35, Image.SCALE_SMOOTH)));
            InstallAtlasMenuItem = new JMenuItem("Update/Install Atlas", new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/assets/icons/AtlasMenuItem.png"))).getScaledInstance(35, 35, Image.SCALE_SMOOTH)));
            InstallServerGridEditorMenuItem = new JMenuItem("Update/Install Server Grid Editor", new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/assets/icons/ServerGridEditorMenuIcon.png"))).getScaledInstance(35, 35, Image.SCALE_SMOOTH)));
            ManagerSettingsMenu = new JMenuItem("Manager Settings", new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/assets/icons/ManagerSettingsMenuIcon.png"))).getScaledInstance(35, 35, Image.SCALE_SMOOTH)));
            AtlasGlobalSettings = new JMenuItem("Atlas Global Settings", new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/assets/icons/globalConfigMenuIcon.png"))).getScaledInstance(35, 35, Image.SCALE_SMOOTH)));
            AtlasIndividualSettings = new JMenuItem("Atlas Individual Settings", new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/assets/icons/IndividualConfigMenuIcon.png"))).getScaledInstance(35, 35, Image.SCALE_SMOOTH)));


        } catch (IOException e) {
            e.printStackTrace();
        }

        FileMenu.add(ExitMenuItem);
        SetupMenu.add(InstallSteamCMDMenuItem);
        SetupMenu.add(InstallAtlasMenuItem);
        SetupMenu.add(InstallServerGridEditorMenuItem);
        ConfigurationMenu.add(ManagerSettingsMenu);
        ConfigurationMenu.add(new JSeparator());
        ConfigurationMenu.add(AtlasGlobalSettings);
        ConfigurationMenu.add(AtlasIndividualSettings);
        MenuBar.add(FileMenu);
        MenuBar.add(SetupMenu);
        MenuBar.add(ConfigurationMenu);
        setJMenuBar(MenuBar);
    }

    public void ActionListeners()
    {
        ExitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        InstallSteamCMDMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Thread thread = new Thread(){
                    public void run() {
                        AtlasServerFunctions.InstallSteamCMD();
                    }
                };
                thread.start();
            }
        });
        InstallServerGridEditorMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Thread thread = new Thread(){
                    public void run() {
                        AtlasServerFunctions.InstallAtlasServerGridEditor();
                    }
                };
                thread.start();
            }
        });
        InstallAtlasMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Thread thread = new Thread(){
                    public void run() {
                        AtlasServerFunctions.InstallAtlas();
                    }
                };
                thread.start();
            }
        });
    }
}
