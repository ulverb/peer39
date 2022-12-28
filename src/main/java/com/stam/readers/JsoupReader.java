package com.stam.readers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.stream.Collectors;

public class JsoupReader implements ContentReader {

    @Override
    public String read(String url) {
        try {
            Document doc = Jsoup.connect(url).get();
            final Elements body = doc.select("body");
            return body.stream().filter(el -> el.hasText()).map(el -> el.text()).collect(Collectors.joining("\t"));
        } catch (IOException e) {
            System.out.println("Failed to get data for url " + url);
            return null;
            //throw new RuntimeException(url, e);
        }
    }
}
