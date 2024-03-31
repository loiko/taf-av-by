package by.av.taf.singleton;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Singleton {
    private static WebDriver driver;
    private static WebDriverManager manager;

    private Singleton() {
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            manager.chromedriver().clearDriverCache().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static void quiteDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
