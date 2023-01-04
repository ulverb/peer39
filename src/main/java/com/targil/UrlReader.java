package com.targil;

import com.targil.readers.ContentReader;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Utility class that receives list of URLs and Content reader,
 * brings HTML from corresponding URL and removes tags (as possible)
 * return map of URLs and related text (cleaned from HTML tags)
 *
 * Exercise: Part 1
 */
public class UrlReader {

    public static Map<String, String> read(List<String> urls, ContentReader reader) {
        return Optional.ofNullable(urls).orElseGet(ArrayList::new)
            .stream().map(url -> Pair.of(url, reader.read(url)))
            .filter(p -> p.getRight() != null)
            .collect(Collectors.toMap(Pair::getLeft, Pair::getRight, (k1,k2) -> k1));

    }
}
