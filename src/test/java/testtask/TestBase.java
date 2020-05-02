package testtask;

import org.testng.annotations.AfterClass;
import ui.BrowserWrapper;

public class TestBase {

    @AfterClass
    public void browserTearDown() {
        BrowserWrapper.getInstance().stopBrowser();
    }
}
