package ui;

import by.av.taf.po.HomePage;
import by.av.taf.singleton.Singleton;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

public class BaseTest {
    WebDriver driver;

    @BeforeEach
    public void driversSetUp() {
        HomePage homePage = new HomePage();
        homePage.openHomePage();
    }

    @AfterEach
    public void driverShutDown() {
        Singleton.quiteDriver();
    }
}
