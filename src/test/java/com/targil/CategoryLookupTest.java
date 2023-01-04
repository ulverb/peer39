package com.targil;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;


class CategoryLookupTest {

    @Test
    void buildModelTest() {
        Map<String, List<String>> map = new HashMap<>();
        map.put("Star", Arrays.asList("star", "star sky"));
        map.put("Star Wars", Arrays.asList("star", "star wars", "may the force be with you"));
        map.put("Islands sky", Arrays.asList("sky", "islands"));

        final CategoryLookup categoryLookup = new CategoryLookup(map);

        System.out.println(categoryLookup);

    }

    @Test
    void findCategoriesTest() {
        Map<String, List<String>> map = new HashMap<>();
        map.put("Star", Arrays.asList("new"));
        map.put("Star Wars", Arrays.asList("of a new"));
        map.put("Islands Sky", Arrays.asList("exploration of a new era in Star"));

        final CategoryLookup categoryLookup = new CategoryLookup(map);

        Set<String> categories = categoryLookup.findCategories("Set about five years after the fall of the Empire, before the rise of the First " +
                "Order, The Mandalorian is an exploration of a new era in Star Wars storytelling onscreen. Sky were so far");

        System.out.println(categories);

        assertThat(categories).isNotEmpty().containsExactlyInAnyOrder("Star", "Star Wars", "Islands Sky");
    }

    @Test
    void complecityModel() {
        Map<String, List<String>> map = new HashMap<>();
        map.put("Star", Arrays.asList("is an exploration of a new era in", "of a new era in Star"));
        map.put("Star Wars", Arrays.asList("new era in Star Wars storytelling", "new era in Star", "The Mandalorian is an exploration of a", "may the force be with you"));
        map.put("Islands sky", Arrays.asList("new era in Star Wars storytelling", "era in Star", "Mandalorian is an exploration of a new"));

        final CategoryLookup categoryLookup = new CategoryLookup(map);

        System.out.println(categoryLookup);

    }

    @Test
    void aaacomplecityModel() {
        Map<String, List<String>> map = new HashMap<>();
        map.put("Star", Arrays.asList("aaa1 aaa1", "aaa1 aaa2 aaa3 aaa4", "aaa3 aaa1","aaa5 aaa1"));
        map.put("Wars", Arrays.asList("aaa2 aaa1", "aaa2 aaa3 aaa4", "aaa4", "aaa3 aaa4","aaa5 aaa2"));
        map.put("Islands Sky", Arrays.asList("aaa3 aaa1", "aaa3 aaa4 aaa5", "aaa4 aaa1", "aaa5 aaa3"));

        final CategoryLookup categoryLookup = new CategoryLookup(map);

        System.out.println(categoryLookup);

    }

    @Test
    void findComplecity() {
        Map<String, List<String>> map = new HashMap<>();
        map.put("Star", Arrays.asList("aaa1 aaa1", "aaa1 aaa2 aaa3 aaa4", "aaa1 aaa3"," aaa1 aaa5"));
        map.put("Wars", Arrays.asList("aaa2 aaa1", "aaa2 aaa3 aaa4", "aaa4", "aaa2 aaa4","aaa2 aaa2"));
        map.put("Islands Sky", Arrays.asList("aaa2 aaa1", "aaa3 aaa4 aaa5", "aaa2 aaa5", "aaa5 aaa3"));

        final CategoryLookup categoryLookup = new CategoryLookup(map);

        Set<String> categories = categoryLookup.findCategories("aaa1 aaa2 aaa3 aaa4 aaa5 aaa6");
        System.out.println(categories);
        assertThat(categories).isNotEmpty().containsExactlyInAnyOrder("Star", "Wars", "Islands Sky");
    }
}