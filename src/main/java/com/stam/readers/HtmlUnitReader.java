package com.stam.readers;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HtmlUnitReader implements ContentReader {
    @Override
    public String read(String url) {
        try {
            final WebClient client = new WebClient(BrowserVersion.CHROME);
            client.getOptions().setCssEnabled(true);
            client.getOptions().setJavaScriptEnabled(true);
            client.getOptions().setThrowExceptionOnScriptError(false);
            client.getOptions().setFetchPolyfillEnabled(true);
            client.getOptions().setRedirectEnabled(true);
            final HtmlPage page = client.getPage(url);
            return page.asNormalizedText();
        } catch (Exception e) {
            log.error("", e);
            // throw new RuntimeException(e);
            return null;
        }
    }
}
