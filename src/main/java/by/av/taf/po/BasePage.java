package by.av.taf.po;

import by.av.taf.singleton.Singleton;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    private static WebDriver driver;
    private WebDriverWait wait;
    private static final Logger logger = LogManager.getLogger();
    private By cookiesCloseButtonLocator = By.xpath("//div[@class = 'cookie-banner__action']//button[1]");
    private By copyrightLocator = By.xpath("//p[@class = 'footer__copy']");
    private By adsPageButtonLocator = By.xpath("//span[@class = 'nav__link-text' and text() = 'Объявления']");

    public BasePage() {
        this.driver = Singleton.getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        logger.info("BasePage instance created");
    }

    public void closeCookies() {
        try {
            WebElement cookiesCloseButton = driver.findElement((cookiesCloseButtonLocator));
            cookiesCloseButton.click();
            logger.info("Cookies closed");
        } catch (NoSuchElementException e) {
            logger.error("Failed to close cookies : {}", e.getMessage());
        }
    }

    public String getCopyrightText() {
        try {
            WebElement copyrightElement = wait.until(ExpectedConditions.visibilityOfElementLocated(copyrightLocator));
            logger.info("Copyright text retrieved");
            return copyrightElement.getText();
        } catch (Exception e) {
            logger.error("Failed to retrieve copyright text: {}", e.getMessage());
            return null;
        }
    }

    public void goAdsPage() {
        try {
            WebElement adsPageButtonElement = wait.until(ExpectedConditions.elementToBeClickable(adsPageButtonLocator));
            adsPageButtonElement.click();
            logger.info("Navigated to ads page");
        } catch (Exception e) {
            logger.error("Failed to open ads page: {}", e.getMessage());
        }
    }
}

