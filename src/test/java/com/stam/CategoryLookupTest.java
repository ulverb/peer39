package com.stam;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;


class CategoryLookupTest {

    @Test
    void buildModelTest() {
        Map<String, List<String>> map = new HashMap<>();
        map.put("star", Arrays.asList("star", "star sky"));
        map.put("star wars", Arrays.asList("star", "star wars", "hello"));
        map.put("Islands sky", Arrays.asList("sky", "islands"));

        final CategoryLookup categoryLookup = new CategoryLookup(map);

        System.out.println(categoryLookup);

    }

    @Test
    void findCategoriesTest() {
        Map<String, List<String>> map = new HashMap<>();
        map.put("star", Arrays.asList("star", "star sky"));
        map.put("star wars", Arrays.asList("star", "star wars", "hello"));
        map.put("Islands sky", Arrays.asList("sky", "islands"));

        final CategoryLookup categoryLookup = new CategoryLookup(map);

        Set<String> categories = categoryLookup.findCategories("Set about five years after the fall of the Empire, before the rise of the First " +
                "Order, The Mandalorian is an exploration of a new era in Star Wars storytelling " +
                "onscreen. Sky were so far");

        assertThat(categories).isNotEmpty().containsExactlyInAnyOrder("star", "star wars", "Islands sky");
    }
}