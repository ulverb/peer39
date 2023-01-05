package com.targil;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Node class represents the model for Categories and their Keywords
 *
 * Exercise: Part 2.1
 */
public class Node{
    private final Set<String> categories;
    private final Map<String, Node> inner;

    public Node() {
        this(new HashSet<>(), new ConcurrentHashMap<>());
    }

    private Node(Set<String> categories, Map<String, Node> inner) {
        this.categories = categories;
        this.inner = inner;
    }

    public Node add(String word, String category) {
        Objects.requireNonNull(word);
        String lowerCase = word.toLowerCase();
        Node node = inner.get(lowerCase);
        if (node == null) {
            node = new Node();
            inner.put(lowerCase, node);
        }
        if (category != null && !category.trim().isEmpty()) {
            node.categories.add(category);
        }
        return node;
    }

    public Set<String> getCategories() {
        return new HashSet<>(categories);
    }

    public boolean hasNext(String word) {
        return inner.containsKey(word);
    }

    public Node next(String word) {
        Objects.requireNonNull(word);
        String lowerCase = word.toLowerCase();
        Node node = inner.get(lowerCase);
        return node;
    }

    public String toString(int level) {
        StringBuilder sb = new StringBuilder();
        final String spaces = IntStream.range(0, level).mapToObj(i -> "\t").collect(Collectors.joining());
        sb.append(spaces).append("categories: ").append(categories).append("\n");
        inner.forEach((k, v) -> {
            sb.append(spaces).append(k).append(":\n").append(" ").append(spaces).append(v.toString(level+1)).append("\n");
        });
        return sb.toString();
    }
}
