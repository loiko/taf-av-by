package ui;

import by.av.taf.po.HomePage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class HomePageTest extends BaseTest {
    private HomePage homePage;

    @BeforeEach
    public void setUp() {
        homePage = new HomePage();
        homePage.closeCookies();
    }

    @Test
    public void testHomePageOpened() {
        final String MAIN_TITLE_TEXT = ".*объявлени[яйе] о продаже авто в Беларуси";
        String actualMainTitle = homePage.getMainTitleText();
        assertTrue(actualMainTitle.matches(MAIN_TITLE_TEXT));
    }

    @Test
    public void testCopyrightPresence() {
        final String COPYRIGHT_TEXT = "© 2001, ООО «Автоклассифайд», УНП 192787977, Минск, ул. Кутузова, 15";
        String actualCopyrightTitle = homePage.getCopyrightText();
        assertTrue(actualCopyrightTitle.equals(COPYRIGHT_TEXT));
    }
}
