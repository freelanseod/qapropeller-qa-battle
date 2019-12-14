import com.codeborne.selenide.*;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;
import owner.TestData;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class BaseTest {
    private Map<String, String> props;
    private static TestData testData;

    private final static int rootSectionId = 945057;
    private final static int suiteId = 1897;

    @Parameters({"localization"})
    @BeforeClass
    public void setUp(@Optional("en") String loc) throws MalformedURLException {
        String env = System.getProperty("env", "local");
        String serviceUrl = System.getenv("SERVICES_HOST");

        String[] app = {"qabattle:qabattle"};

        props = new HashMap<>();
        props.put("env", env);
        props.put("localization", loc);

        //SelenideLogger.addListener("reportPortalLogListener", new ReportPortalListener());
        Configuration.baseUrl = this.getLocalization().url();
        Configuration.timeout = 4000;

        if (!env.equals("local")) {
            DesiredCapabilities browser = new DesiredCapabilities();
            browser.setBrowserName("chrome");
            browser.setVersion("67.0");
            browser.setCapability("screenResolution", "1366x768x24");
            browser.setCapability("enableVNC", true);
            browser.setCapability("applicationContainers", app);
            browser.setCapability(
                    "name",
                    "[" + props.get("env") + "]" + "[" + props.get("localization") + "]" + this.getClass().getSimpleName()
            );

            RemoteWebDriver driver = new RemoteWebDriver(new URL(serviceUrl + ":4444/wd/hub"),
                    browser);
            driver.setFileDetector(new LocalFileDetector());
            WebDriverRunner.setWebDriver(driver);
        } else {
            Configuration.browser = "chrome";
        }

    }

    public TestData getLocalization() {
        return ConfigFactory.create(TestData.class, props);
    }

    @AfterClass
    public void tearDown() {
        WebDriverRunner.closeWebDriver();
    }

    public TestData getTestData() {
        return testData == null ? testData = ConfigFactory.create(TestData.class) : testData;
    }

}
