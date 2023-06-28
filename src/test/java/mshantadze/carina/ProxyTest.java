package mshantadze.carina;

import com.browserup.bup.BrowserUpProxy;
import com.browserup.bup.proxy.CaptureType;
import com.zebrunner.carina.core.AbstractTest;
import com.zebrunner.carina.proxy.browserup.ProxyPool;
import mshantadze.carina.base.AbstractProxyTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

public class ProxyTest extends AbstractProxyTest {
    private static final Logger LOGGER = LogManager.getLogger(ProxyTest.class);

    @Test
    public void proxyTest() {
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        List<String> requestUrls = proxy.getHar().getLog().getEntries()
                .stream()
                .map((entry) -> {
                    String url = entry.getRequest().getUrl();
                    LOGGER.info(url);
                    return url;
                })
                .collect(Collectors.toList());

        Assert.assertTrue(
                requestUrls.stream().anyMatch((url) -> url.contains("https://rest.happierleads.com")),
                "Request is not sent to https://rest.happierleads.com"
        );
    }
}
