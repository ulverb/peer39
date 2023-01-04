package com.targil;

import com.targil.readers.HtmlUnitReader;
import com.targil.readers.JsoupReader;

import java.util.*;
import java.util.stream.Collectors;

/**
 * The Runner class parses the URLs and matches them with the appropriate categories.
 */
public class Runner {

    static Map<String, List<String>> allCategories = new HashMap<>();

    /**
     * The main method receivers two parameters: list of categories and list of URLs
     * and returns the map with URLs and sets of the associated categories
     *
     * Exercise: Part 2.4
     */
    public Map<String, Set<String>> classify(List<String> categories, List<String> urls) {

        Map<String, List<String>> relevantCategories = categories.stream().map(categoryName -> Pair.of(categoryName, allCategories.get(categoryName)))
                .filter(p -> p.getRight() != null).collect(Collectors.toMap(Pair::getLeft, Pair::getRight));

        CategoryLookup categoryLookup = new CategoryLookup(relevantCategories);

        Map<String, Set<String>> result =
                UrlReader.read(urls, new JsoupReader()) /** Exercise: Part 1 */
                .entrySet()
                .stream()
                .map(entry ->
                    Pair.of(
                        entry.getKey(),
                        categoryLookup.findCategories(entry.getValue())  /** Exercise: Part 2.3 */
                    )
                )
                .collect(Collectors.toMap(Pair::getLeft, Pair::getRight));
        return result;

    }

    /**
     * Load All categories.
     * Method could be changed to load categories from any source.
     */
    public static void loadAllCategories(){
        allCategories.put("Star Wars", Arrays.asList("star war", "star wars", "starwar", "starwars", "r2d2", "may the force be with you"));
        allCategories.put("Basketball", Arrays.asList("basketball", "nba", "ncaa", "lebron james", "john stokton", "anthony davis"));
    }
}
