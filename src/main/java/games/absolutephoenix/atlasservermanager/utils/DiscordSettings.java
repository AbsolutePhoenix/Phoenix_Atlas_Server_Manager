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

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class DiscordSettings {
    public static String DiscordWebhookURL= "https://discord.com/api/webhooks/894387120712605737/XCEOF1ZCAIKVRVOpEr8S5Fext1VlIx8Ln8phExzUNt6InwuFQuRC4UFrhpWq3C5SV349";
    public static String DiscordSenderUsername = "Complete Atlas Server Manager";
    public static String DiscordSenderAvatar = "https://b.thumbs.redditmedia.com/8in3lDndYdhG6GA3w0GdbiUZAOd0KY2L9_xD1_e-D0M.png";

    public static class AtlasServerGridEditor {
        public static void download() throws IOException {
            FileUtils.copyURLToFile(new URL("https://github.com/GrapeshotGames/ServerGridEditor/archive/refs/heads/master.zip"), new File("ServerGridEditor.zip"));
        }
    }
}
