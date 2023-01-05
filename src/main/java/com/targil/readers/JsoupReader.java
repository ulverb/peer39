package com.targil.readers;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.stream.Collectors;

/**
 * Jsoup Java HTML Parser
 * jsoup is a Java library for working with real-world HTML.
 * It provides a very convenient API for fetching URLs and extracting and manipulating data.
 */
@Slf4j
public class JsoupReader implements ContentReader {

    @Override
    public String read(String url) {
        try {
            Document doc = Jsoup.connect(url).get();
            final Elements body = doc.select("body");
            return body.stream().filter(el -> el.hasText()).map(el -> el.text()).collect(Collectors.joining("\t"));
        } catch (IOException e) {

            log.error("JsoupReader: Failed to get data for url - " + url, e);
            return null;
        }
    }
}
