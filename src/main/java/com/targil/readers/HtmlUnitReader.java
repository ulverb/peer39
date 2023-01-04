package com.targil.readers;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import lombok.extern.slf4j.Slf4j;

/**
 * HtmlUnit - A headless browser intended for use in testing web-based applications.
 */
@Slf4j
public class HtmlUnitReader implements ContentReader {
    /**
     *  Method retrieves HTML from given URL and return
     */
    @Override
    public String read(String url) {
        try (WebClient client = new WebClient(BrowserVersion.CHROME)){
            client.getOptions().setCssEnabled(false);
            client.getOptions().setJavaScriptEnabled(false);
            client.getOptions().setThrowExceptionOnScriptError(false);
            client.getOptions().setFetchPolyfillEnabled(true);
            client.getOptions().setRedirectEnabled(true);
            final HtmlPage page = client.getPage(url);
            return page.getBody().asNormalizedText();
        } catch (Exception e) {
            log.error("HtmlUnitReader: : Failed to get data for url - " + url, e);
            return null;
        }
    }

}
