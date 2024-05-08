package ui;

import by.av.taf.po.HomePage;
import by.av.taf.singleton.Singleton;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

public class BaseTest {
    WebDriver driver;
    private static final Logger logger = LogManager.getLogger();

    @BeforeEach
    public void driversSetUp() {
        HomePage homePage = new HomePage();
        homePage.openHomePage();
        homePage.closeCookies();
        logger.info("Home page opened");
    }

    @AfterEach
    public void driverShutDown() {
        Singleton.quiteDriver();
        logger.info("WebDriver closed");
    }
}
