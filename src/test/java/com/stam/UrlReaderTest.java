package com.stam;

import com.stam.readers.HtmlUnitReader;
import com.stam.readers.JsoupReader;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class UrlReaderTest {

    static List<String> url = Arrays.asList(
//            "http://www.msn.com/en-nz/travel/tripideas/70-of-the-planets-most-breathtaking-sights/ss-AAIUpDp",
//            "https://www.radiosport.co.nz/sport-news/rugby/accident-or-one-last-dig-eddie-jones-reveals-hansens-next-job/",
//            "https://www.glamour.de/frisuren/frisurenberatung/haarschnitte",
            "https://www.bbc.com"
//            ,
//            "https://www3.forbes.com/business/2020-upcoming-hottest-new-vehicles/13/?nowelcome",
//            "https://www.tvblog.it/post/1681999/valerio-fabrizio-salvatori-gli-inseparabili-chi-sono-pechino-express-2020",
//            "http://edition.cnn.com/"
    );

    static final UrlReader urlReader = new UrlReader();

    @Test
    void testJsoup() {
        final Map<String, String> read = urlReader.read(url, new JsoupReader());
        read.forEach((key, value) -> {
            assertThat(key).isIn(url);
            assertThat(value).isNotBlank();
            assertThat(value.length()).isGreaterThan(0);
            System.out.println("--------------------------");
            System.out.println(key);
            System.out.println(value.substring(0, Math.min(1000, value.length())));
        });
    }

    @Test
    void testHtmlUnit() {
        final Map<String, String> read = urlReader.read(url, new HtmlUnitReader());
        read.forEach((key, value) -> {
            assertThat(key).isIn(url);
            assertThat(value).isNotBlank();
            assertThat(value.length()).isGreaterThan(0);
            System.out.println("--------------------------");
            System.out.println(key);
            System.out.println(value.substring(0, Math.min(1000, value.length())));
        });
    }
}