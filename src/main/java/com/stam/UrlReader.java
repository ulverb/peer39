package com.stam;

import com.stam.readers.ContentReader;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UrlReader {

    private static Set<String> redundantWords = new HashSet<>(
        Arrays.asList(
            "in", "the", "a", "by", "and", "at", "from", "to", "or", "an", "about", "for", "of", "with", "this", "that",
                "these", "those", "however", "certainly", "on", "be", "is", "are", "been", "were", "was", "would", "should",
                "will", "shall", "could", "can", "despite"
        )
    );

    public static Map<String, String> read(List<String> urls, ContentReader reader) {
        return Optional.ofNullable(urls).orElseGet(ArrayList::new)
            .stream().map(url -> Pair.of(url, reader.read(url)))
            .filter(p -> p.getRight() != null)
            .map(p -> {
                String newContent = Stream.of(p.getRight().split(" "))
                        .filter(word -> !redundantWords.contains(word))
                        .collect(Collectors.joining(""));
                return p.newLeft(newContent);
            })
        .collect(Collectors.toMap(Pair::getLeft, Pair::getRight, (k1,k2) -> k1));
    }
}
