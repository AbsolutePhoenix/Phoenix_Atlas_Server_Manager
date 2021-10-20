package games.absolutephoenix.atlasservermanager.userinterface;

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

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class SplashScreen extends JFrame {

    private final JLabel Task = new JLabel();
    public SplashScreen()
    {
        setMaximumSize(new Dimension(960, 504));
        setPreferredSize(new Dimension(960, 504));
        setMinimumSize(new Dimension(960, 504));
        setUndecorated(true);
        setLayout(null);
        Image icon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("assets/banner/banner.png"))).getImage();
        icon = icon.getScaledInstance(960, 504,Image.SCALE_SMOOTH);
        JLabel backgroundImage = new JLabel(new ImageIcon(icon));
        add(Task);
        add(backgroundImage);

        backgroundImage.setBounds(0,0,960,504);
        Task.setOpaque(true);
        Task.setBackground(Color.gray.darker().darker().darker());
        Task.setForeground(Color.white);
        Task.setBounds(0, 504 - 25, 600, 25);
        setVisible(true);
        setLocationRelativeTo(null);
        setName("Phoenix Atlas Server Manager");
    }
    public void Destroy()
    {
        setVisible(false);
        dispose();
    }

    public void setTask(String task) {
        this.Task.setText("    " + task);
    }
}
