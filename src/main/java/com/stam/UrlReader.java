package com.stam;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class UrlReader {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder(builderClassName = "Builder", toBuilder = true)
    private static class Pair {
        private String url;
        private String content;
    }

    public Map<String, String> read(List<String> urls) {
        return Optional.ofNullable(urls).orElseGet(ArrayList::new)
            .stream().map(url -> Pair.builder()
                .url(url)
                .content(readContent(url)).build()
            )
            .filter(p -> p.getContent() != null)
        .collect(Collectors.toMap(Pair::getUrl, Pair::getContent, (k1,k2) -> k1));
    }

    private static String readContent(String url) {
        try {
            Document doc = Jsoup.connect(url).get();
            final Elements body = doc.select("body");
            return String.join("\n", body.eachText());
        } catch (IOException e) {
            System.out.println("Failed to get data for url " + url);
            return null;
            //throw new RuntimeException(url, e);
        }
    }
}
