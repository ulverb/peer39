package com.stam.readers;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HtmlUnitReader implements ContentReader {
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
            log.error("", e);
            // throw new RuntimeException(e);
            return null;
        }
    }

}
