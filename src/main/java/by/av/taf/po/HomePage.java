package by.av.taf.po;

import by.av.taf.singleton.Singleton;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage extends BasePage {
    private WebDriver driver;
    private WebDriverWait wait;
    private static final Logger logger = LogManager.getLogger();
    private final String HOMEPAGE_URL = "https://av.by/";
    private By mainTitleLocator = By.xpath("//h1[@class = 'general-title']");

    public HomePage() {
        this.driver = Singleton.getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        logger.info("HomePage instance created");
    }

    public void openHomePage() {
        try {
            driver.get(HOMEPAGE_URL);
            logger.info("Opened the home page: {}", HOMEPAGE_URL);
        } catch (Exception e) {
            logger.error("Failed to open the home page: {}", e.getMessage());
        }
    }

    public String getMainTitleText() {
        try {
            WebElement mainTitleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(mainTitleLocator));
            logger.info("Main title text retrieved");
            return mainTitleElement.getText();
        } catch (NoSuchElementException | TimeoutException e) {
            logger.error("Failed to retrieve main title text: {}", e.getMessage());
            return null;
        }
    }
}
