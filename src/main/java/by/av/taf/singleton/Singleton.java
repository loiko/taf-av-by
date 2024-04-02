package by.av.taf.singleton;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Singleton {
    private static WebDriver driver;
    private static WebDriverManager manager;
    private static final Logger logger = LogManager.getLogger();

    private Singleton() {
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            logger.info("Creating new WebDriver instance");
            manager.chromedriver().clearDriverCache().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static void quiteDriver() {
        if (driver != null) {
            logger.info("Quitting WebDriver");
            driver.quit();
            driver = null;
        }
    }
}
