package mshantadze.carina.base;

import com.browserup.bup.BrowserUpProxy;
import com.browserup.bup.proxy.CaptureType;
import com.zebrunner.carina.core.AbstractTest;
import com.zebrunner.carina.proxy.browserup.ProxyPool;
import com.zebrunner.carina.utils.R;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class AbstractProxyTest extends AbstractTest {
    protected BrowserUpProxy proxy;

    @BeforeMethod
    public void initProxy() {
        R.CONFIG.put("browserup_proxy","true");
        getDriver();
        proxy = ProxyPool.getProxy();
        proxy.enableHarCaptureTypes(CaptureType.REQUEST_CONTENT,CaptureType.RESPONSE_CONTENT);
        proxy.getHar();
    }
}
