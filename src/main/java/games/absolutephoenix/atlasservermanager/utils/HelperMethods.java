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

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
    public static void DownloadSteamCMD() {
        try {
            FileUtils.copyURLToFile(new URL("https://steamcdn-a.akamaihd.net/client/installer/steamcmd.zip"), new File("steamcmd.zip"));
            try {
                Path filePath = Paths.get("steamcmd.zip");
                InputStream inputStream = Files.newInputStream(filePath);
                ArchiveStreamFactory archiveStreamFactory = new ArchiveStreamFactory();
                ArchiveInputStream archiveInputStream = archiveStreamFactory.createArchiveInputStream(ArchiveStreamFactory.ZIP, inputStream);
                ArchiveEntry archiveEntry;
                while ((archiveEntry = archiveInputStream.getNextEntry()) != null) {
                    Path path = Paths.get("SteamCMD", archiveEntry.getName());
                    File file = path.toFile();
                    if (archiveEntry.isDirectory()) {
                        if (!file.isDirectory()) {
                            //noinspection ResultOfMethodCallIgnored
                            file.mkdirs();
                        }
                    } else {
                        File parent = file.getParentFile();
                        if (!parent.isDirectory()) {
                            //noinspection ResultOfMethodCallIgnored
                            parent.mkdirs();
                        }
                        try (OutputStream outputStream = Files.newOutputStream(path)) {
                            IOUtils.copy(archiveInputStream, outputStream);
                        }
                    }
                }
            } catch (IOException | ArchiveException e) {
                e.printStackTrace();
            }
            FileUtils.forceDelete(new File("steamcmd.zip"));

            Process process = Runtime.getRuntime().exec(new String[]{"cmd.exe", "/c", "start /wait /high steamcmd\\steamcmd.exe +exit"});
            process.waitFor();
        }catch (IOException | InterruptedException e)
        {
            e.printStackTrace();
        }
    }
    public static void RunSteamCMD() {
        try {
            Process process = Runtime.getRuntime().exec(new String[]{"cmd.exe", "/c", "start /wait /high steamcmd\\steamcmd.exe +login anonymous +force_install_dir ..\\ +app_update 1006030 validate +exit"});
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void DownloadAtlasServerGridEditor() {
        try {
            FileUtils.copyURLToFile(new URL("https://github.com/GrapeshotGames/ServerGridEditor/archive/refs/heads/master.zip"), new File("AtlasServerGridEditor.zip"));
            try {
                Path filePath = Paths.get("AtlasServerGridEditor.zip");
                InputStream inputStream = Files.newInputStream(filePath);
                ArchiveStreamFactory archiveStreamFactory = new ArchiveStreamFactory();
                ArchiveInputStream archiveInputStream = archiveStreamFactory.createArchiveInputStream(ArchiveStreamFactory.ZIP, inputStream);
                ArchiveEntry archiveEntry;
                while ((archiveEntry = archiveInputStream.getNextEntry()) != null) {
                    Path path = Paths.get("AtlasTools", archiveEntry.getName());
                    File file = path.toFile();
                    if (archiveEntry.isDirectory()) {
                        if (!file.isDirectory()) {
                            //noinspection ResultOfMethodCallIgnored
                            file.mkdirs();
                        }
                    } else {
                        File parent = file.getParentFile();
                        if (!parent.isDirectory()) {
                            //noinspection ResultOfMethodCallIgnored
                            parent.mkdirs();
                        }
                        try (OutputStream outputStream = Files.newOutputStream(path)) {
                            IOUtils.copy(archiveInputStream, outputStream);
                        }
                    }
                }
            } catch (IOException | ArchiveException e) {
                e.printStackTrace();
            }
            FileUtils.forceDelete(new File("AtlasServerGridEditor.zip"));
            //noinspection ResultOfMethodCallIgnored
            new File("AtlasTools/ServerGridEditor-master").renameTo(new File("AtlasTools/ServerGridEditor"));

        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
