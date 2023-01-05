package com.targil;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;


class CategoryLookupTest {

    @Test
    void CategoryLookup_buildModelTest() {
        Map<String, List<String>> map = new HashMap<>();
        map.put("Star", Arrays.asList("star", "star sky"));
        map.put("Star Wars", Arrays.asList("star", "star wars", "may the force be with you"));
        map.put("Islands sky", Arrays.asList("sky", "islands"));

        final CategoryLookup categoryLookup = new CategoryLookup(map);

        System.out.println(categoryLookup);

    }

    @Test
    void CategoryLookup_findCategoriesTest() {
        Map<String, List<String>> map = new HashMap<>();
        map.put("Star", Arrays.asList("star", "star sky"));
        map.put("Star Wars", Arrays.asList("star wars", "starwars", "may the force be with you"));
        map.put("Islands Sky", Arrays.asList("Sky were so far"));

        final CategoryLookup categoryLookup = new CategoryLookup(map);

        Set<String> categories = categoryLookup.findCategories("Set about five years after the fall of the Empire, before the rise of the First " +
                "Order, The Mandalorian is an exploration of a new era in Star Wars storytelling onscreen. Sky were so far");

        System.out.println(categories);

        assertThat(categories).isNotEmpty().containsExactlyInAnyOrder("Star", "Star Wars", "Islands Sky");
    }
}