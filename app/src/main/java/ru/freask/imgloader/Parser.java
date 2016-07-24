package ru.freask.imgloader;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by FreaskHOME on 24.07.2016.
 */
public class Parser {

    public Parser() {}

    public static List<String> parse(String url, Document doc) {
        List<String> list = new ArrayList<>();
        Elements newsHeadlines = doc.select("img");
        for (Element element: newsHeadlines) {
            String src = element.attr("src");
            if (src != null && !src.equals("")) {
                src = urlFix(url, src);
                list.add(src);
            }
        }
        return list;
    }

    public static String urlFix(String url, String imgUrl) {
        if (imgUrl.substring(0,2).equals("//"))
            return imgUrl.substring(2);
        else if (imgUrl.substring(0,1).equals("/"))
            imgUrl = url + imgUrl;

        return imgUrl;
    }
}
