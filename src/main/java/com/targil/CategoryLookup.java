package com.targil;

import java.util.*;

/**
 * The class initializes the model for the Categories and their Keywords
 */
public class CategoryLookup {
    private final Node root = new Node();

    /**
     * Initialize the model for Categories and their Keywords
     *
     * Complexity: categories * keywords * 6(max length of phrase in keyword)  = O(M*K*6)
     *
     * Exercise: Part 2.2
     */
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

    /**
     * The method classifies the received text into appropriate categories.
     *
     * Complexity:
     *
     * Exercise: Part 2.3
     */
    public Set<String> findCategories(String text) {
        int count = 0;
        String[] words = text.split("[\\s\\n]");
        Set<String> found = new HashSet<>();
        List<Node> nodes = new ArrayList<>();
        nodes.add(root);
        List<Node> previous = new ArrayList<>();

        for (String s : words) {
            String word = s.toLowerCase();
            List<Node> additional = new ArrayList<>();
            for (Node nd : nodes) {
                if (nd.hasNext(word)) {
                    additional.add(nd.next(word));
                }
                found.addAll(nd.getCategories());

                count +=1;
            }
            nodes.addAll(additional);
            nodes.removeAll(previous);
            previous = additional;
        }
        System.out.println(count);
        return found;
    }

    @Override
    public String toString() {
        return root.toString(0);
    }
}
