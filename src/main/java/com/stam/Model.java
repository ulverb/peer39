package com.stam;

import java.util.List;
import java.util.Map;

public class Model {
    Node root;

    public Model(Map<String, List<String>> data) {
        root = new Node();
        for(Map.Entry<String, List<String>> e : data.entrySet()) {
            String category = e.getKey();
            for (String phrase : e.getValue()) {
                Node n = root;
                final String[] words = phrase.split(" ");
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
}
