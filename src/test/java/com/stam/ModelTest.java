package com.stam;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ModelTest {

    @Test
    void sdgsdgs() {
        Map<String, List<String>> map = new HashMap<>();
        map.put("star", Arrays.asList("star", "star sky"));
        map.put("star wars", Arrays.asList("star", "star wars", "hello"));

        final Model model = new Model(map);

        System.out.println(model);

    }
}