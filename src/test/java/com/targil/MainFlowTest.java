package com.targil;

import org.junit.jupiter.api.Test;

import java.util.*;

public class MainFlowTest {

    static List<String> urls = Arrays.asList(
//            "http://www.msn.com/en-nz/travel/tripideas/70-of-the-planets-most-breathtaking-sights/ss-AAIUpDp",
//            "https://www.radiosport.co.nz/sport-news/rugby/accident-or-one-last-dig-eddie-jones-reveals-hansens-next-job/",
//            "https://www.glamour.de/frisuren/frisurenberatung/haarschnitte",
//            "https://www3.forbes.com/business/2020-upcoming-hottest-new-vehicles/13/?nowelcome",
//            "https://www.tvblog.it/post/1681999/valerio-fabrizio-salvatori-gli-inseparabili-chi-sono-pechino-express-2020",
//            "http://edition.cnn.com/",
//            "https://www.bbc.com",
           // "https://www.starwars.com/news/everything-we-know-about-the-mandalorian",
            "http://www.starwars.com",
            "https://www.imdb.com/find?q=star+wars&ref_=nv_sr_sm",
            "https://edition.cnn.com/sport",
            "https://en.wikipedia.org/wiki/Basketball"
    );

    @Test
    void mainFlowTest(){
        List<String> relevantCategories = new ArrayList<>();

        relevantCategories.add("Star Wars");
        relevantCategories.add("Basketball");

        Map<String, Set<String>> result;
        Runner.loadAllCategories();
        Runner runner = new Runner();
        result  = runner.classify(relevantCategories, urls);

        result.entrySet().forEach(entry -> {
            System.out.println(entry.getKey() + " " + entry.getValue());
        });

    }
}
