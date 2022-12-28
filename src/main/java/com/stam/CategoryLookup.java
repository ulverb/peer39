package com.stam;

import java.util.*;

public class CategoryLookup {
    final Node root = new Node();

    public CategoryLookup(Map<String, List<String>> data) {
        for(Map.Entry<String, List<String>> e : data.entrySet()) {
            String category = e.getKey();
            for (String phrase : e.getValue()) {
                Node n = root;
                final String[] words = phrase.split("\\s");
                for (int i=0; i<words.length; i++) {
                    boolean theLastStep = i == words.length - 1;
                    n = n.add(words[i], theLastStep ? category : null);
                }
            }
        }
    }

    @Override
    public String toString() {
        return root.toString(0);
    }

    public Set<String> findCategories(String content) {
        String[] words = content.split("[\\s\\n]");
        Set<String> found = new HashSet<>();
        List<Node> nodes = new ArrayList<>();
        nodes.add(root);
        for (String s : words) {
            String word = s.toLowerCase();
            List<Node> additional = new ArrayList<>();
            for (Node nd : nodes) {
                if (nd.hasNext(word)) {
                    additional.add(nd.next(word));
                } else {
                    found.addAll(nd.getCategories());
                }
            }
            nodes.addAll(additional);
        }
        return found;
    }
}
