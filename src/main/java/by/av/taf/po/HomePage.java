package by.av.taf.po;

import by.av.taf.singleton.Singleton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage extends BasePage {
    private WebDriver driver;
    private WebDriverWait wait;
    private final String HOMEPAGE_URL = "https://av.by/";
    private By mainTitleLocator = By.xpath("//h1[@class = 'general-title']");

    public HomePage() {
        this.driver = Singleton.getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void openHomePage() {
        driver.get(HOMEPAGE_URL);
    }

    public String getMainTitleText() {
        WebElement mainTitleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(mainTitleLocator));
        return mainTitleElement.getText();
    }
}
