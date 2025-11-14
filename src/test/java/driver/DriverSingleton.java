package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DriverSingleton {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static final Logger log = LoggerFactory.getLogger(DriverSingleton.class);

    private DriverSingleton() {}

    public static WebDriver getDriver(BrowserType browser) {
        if (driver.get() == null) {
            switch (browser) {
                case FIREFOX:
                    driver.set(new FirefoxDriver());
                    break;

                case EDGE:
                    driver.set(new EdgeDriver());
                    break;

                default:
                    driver.set(new ChromeDriver());
            }
            log.info("Driver initialized: {}", browser);
        }
        return driver.get();
    }

    public static void closeDriver() {
        if (driver.get() != null) {
            log.info("Closing browser");
            driver.get().quit();
            driver.remove();
        }
    }
}
