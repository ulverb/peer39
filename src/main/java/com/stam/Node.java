package com.stam;

import lombok.Data;
import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
@Getter
public class Node {
    private Set<String> categories = new HashSet<>();
    private Map<String, Node> inner = new HashMap<>();


    public Node add(String word, String category) {
        Node node = inner.get(word);
        if (node == null) {
            node = new Node();
            inner.put(word, node);
        }
        if (category != null && !category.isBlank()) {
            node.categories.add(category);
        }
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
