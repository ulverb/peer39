package com.stam;

import com.stam.readers.HtmlUnitReader;

import java.util.*;
import java.util.stream.Collectors;

public class Runner {

    Map<String, List<String>> allCategories = new HashMap<>();

    public Map<String, Set<String>> classify(List<String> categories, List<String> urls) { // todo: part 2.4

        Map<String, List<String>> relevantCategories = categories.stream().map(categoryName -> Pair.of(categoryName, allCategories.get(categoryName)))
                .filter(p -> p.getRight() != null).collect(Collectors.toMap(Pair::getLeft, Pair::getRight));

        CategoryLookup categoryLookup = new CategoryLookup(relevantCategories);

        Map<String, Set<String>> result =
                UrlReader.read(urls, new HtmlUnitReader())// todo: part 1
                .entrySet()
                .stream()
                .map(entry ->
                    Pair.of(
                        entry.getKey(),
                        categoryLookup.findCategories(entry.getValue()) // todo: part 2.3
                    )
                )
                .collect(Collectors.toMap(Pair::getLeft, Pair::getRight));
        return result;

    }
}
