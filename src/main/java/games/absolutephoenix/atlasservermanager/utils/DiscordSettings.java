package games.absolutephoenix.atlasservermanager.utils;

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
