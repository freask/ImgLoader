package ru.freask.imgloader;

/**
 * Created by FreaskHOME on 24.07.2016.
 */
public class Tools {
    public static boolean checkUrlProtocol(String url) {
        return url.startsWith("http://") || url.startsWith("https://");
    }
}
