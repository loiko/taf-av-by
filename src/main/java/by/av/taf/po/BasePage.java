package by.av.taf.po;

import by.av.taf.singleton.Singleton;
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
    private By cookiesCloseButtonLocator = By.xpath("//div[@class = 'cookie-banner__action']//button[1]");
    private By copyrightLocator = By.xpath("//p[@class = 'footer__copy']");
    private By adsPageButtonLocator = By.xpath("//span[@class = 'nav__link-text' and text() = 'Объявления']");

    public BasePage() {
        this.driver = Singleton.getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void closeCookies() {
        try {
            WebElement cookiesCloseButton = driver.findElement((cookiesCloseButtonLocator));
            cookiesCloseButton.click();
        } catch (NoSuchElementException e) {

        }
    }

    public String getCopyrightText() {
        WebElement copyrightElement = wait.until(ExpectedConditions.visibilityOfElementLocated(copyrightLocator));
        return copyrightElement.getText();
    }

    public void goAdsPage() {
        WebElement adsPageButtonElement = wait.until(ExpectedConditions.elementToBeClickable(adsPageButtonLocator));
        adsPageButtonElement.click();
    }
}

