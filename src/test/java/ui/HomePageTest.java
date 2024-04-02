package ui;

import by.av.taf.po.HomePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class HomePageTest extends BaseTest {
    private HomePage homePage;
    private static final Logger logger = LogManager.getLogger();

    @BeforeEach
    public void setUp() {
        homePage = new HomePage();
        homePage.closeCookies();
    }

    @Test
    public void testHomePageOpened() {
        final String MAIN_TITLE_TEXT = ".*объявлени[яйе] о продаже авто в Беларуси";

        try {
            String actualMainTitle = homePage.getMainTitleText();
            assertTrue(actualMainTitle.matches(MAIN_TITLE_TEXT));
            logger.info("Main title text matches the expected pattern");
        } catch (AssertionError e) {
            logger.error("Main title text doesn't match the expected pattern");
            throw e;
        } catch (Exception e) {
            logger.error("An error occurred during the test: {}", e.getMessage());
            throw e;
        }
    }

    @Test
    public void testCopyrightPresence() {
        final String COPYRIGHT_TEXT = "© 2001, ООО «Автоклассифайд», УНП 192787977, Минск, ул. Кутузова, 15";

        try {
            String actualCopyrightTitle = homePage.getCopyrightText();
            assertTrue(actualCopyrightTitle.equals(COPYRIGHT_TEXT));
            logger.info("Copyright text matches the expected value");
        } catch (AssertionError e) {
            logger.error("Copyright text doesn't match the expected value");
            throw e;
        } catch (Exception e) {
            logger.error("An error occurred during the test: {}", e.getMessage());
            throw e;
        }
    }
}
