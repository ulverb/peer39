package com.stam;

import com.stam.readers.ContentReader;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UrlReader {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder(builderClassName = "Builder", toBuilder = true)
    private static class Pair {
        private String url;
        private String content;
    }

    private static Set<String> redundantWords = new HashSet<>(
        Arrays.asList(
            "in", "the", "a", "by", "and", "at", "from", "to", "or", "an", "about", "for", "of", "with", "this", "that",
                "these", "those", "however", "certainly", "on", "be", "is", "are", "been", "were", "was", "would", "should",
                "will", "shall", "could", "can", "despite"
        )
    );

    public Map<String, String> read(List<String> urls, ContentReader reader) {
        return Optional.ofNullable(urls).orElseGet(ArrayList::new)
            .stream().map(url -> Pair.builder()
                .url(url)
                .content(reader.read(url)).build()
            )
            .filter(p -> p.getContent() != null)
            .map(p -> {
                String newContent = Stream.of(p.getContent().split(" "))
                        .filter(word -> !redundantWords.contains(word))
                        .collect(Collectors.joining(""));
                return p.toBuilder().content(newContent).build();
            })
        .collect(Collectors.toMap(Pair::getUrl, Pair::getContent, (k1,k2) -> k1));
    }
}
