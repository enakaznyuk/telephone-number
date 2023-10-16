package org.example;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class HTMLParser {

    public static String parse(String url){

        String HTMLString = "<body>";
        Document html = Jsoup.parse(HTMLString);
        String title = html.title();

        Document doc;
        try {
            doc = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (HTML, like Gecko) Chrome/107.0.0.0 Safari/537.36")
                    .get();
            title = doc.outerHtml();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Pattern pattern = Pattern.compile("(\\+)*[8]{1}( )?(\\()*\\d{3}(\\))* ?\\d{3}[- ]?\\d{2}[- ]?\\d{2}");
        Matcher matcher = pattern.matcher(title);

        Map<String, String> map = new HashMap<>();

        while(matcher.find())
            map.put(matcher.group(), url);

        return String.valueOf(map);
    }
}

