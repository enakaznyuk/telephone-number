package org.example;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class HTMLParser {

    public static void parse(){

        String HTMLSTring = "<body>";

        Document html = Jsoup.parse(HTMLSTring);
        String title = html.title();
        System.out.println("title = " + title);
        Document doc;
        try {
            doc = Jsoup.connect("https://repetitors.info/")
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36")
                    .get();
            title = doc.outerHtml();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String string = title;
        System.out.println("string = " + string);
        boolean bool = true;
        System.out.println("Jsoup Can read HTML page from URL, title : " + title);

        String input = title;
        Pattern pattern = Pattern.compile("(\\+)*[8]{1}( )?(\\()*\\d{3}(\\))* ?\\d{3}[- ]?\\d{2}[- ]?\\d{2}");
        Matcher matcher = pattern.matcher(input);
        while(matcher.find())
            System.out.println(matcher.group());
    }
}

