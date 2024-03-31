package ui;

import by.av.taf.po.AdsPage;
import by.av.taf.po.HomePage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdsPageTest extends BaseTest {
    private HomePage homePage;
    private AdsPage adsPage;
    private final String BRAND = "Toyota";
    private final String MODEL = "RAV4";
    private final String GENERATION = "V";
    private final String MIN_CAR_YEAR = "2020";
    private final String MAX_PRICE = "35000";
    private final String MIN_ENGINE = "2,5";
    private final String MAX_MILEAGE = "До 200 тыс.";
    private final String REGISTRATION_COUNTRY = "Беларусь";
    private final String REGION = "Минская обл.";
    private final String CITY = "Минск";

    @BeforeEach
    public void setUp() {
        homePage = new HomePage();
        adsPage = new AdsPage();
        homePage.closeCookies();
        homePage.goAdsPage();
    }

    @Test
    public void testSearchCarsByFilter() throws InterruptedException {
        adsPage
                .enterPriceMax(MAX_PRICE)
                .selectBrand(BRAND)
                .selectModel(MODEL)
                .selectGeneration(GENERATION)
                .selectCarYearMin(MIN_CAR_YEAR)
                .selectEngineMin(MIN_ENGINE)
                .clickParametersButton()
                .selectRegion(REGION)
                .selectMaxMileage(MAX_MILEAGE)
                .selectRegistrationCountry(REGISTRATION_COUNTRY)
                .clickVinCheckbox()
                .selectCity(CITY)
                .clickShowResultButton();

        Map<String, String> actualAdCarParameters = getActualAdCarParameters();
        Map<String, String> expectedAdCarParameters = getExpectedAdCarParameters();
        assertEquals(expectedAdCarParameters, actualAdCarParameters);
    }

    private Map<String, String> getActualAdCarParameters() {
        Map<String, String> actualAdCarParameters = new HashMap<>();
        actualAdCarParameters.put("Brand", adsPage.getAdsBrand());
        actualAdCarParameters.put("Model", adsPage.getAdsModel());
        actualAdCarParameters.put("Generation", adsPage.getAdsGeneration());
        actualAdCarParameters.put("Car year from", adsPage.getAdCarYearMin(MIN_CAR_YEAR));
        actualAdCarParameters.put("Car price to", adsPage.getAdCarUsdPriceMax(MAX_PRICE));
        actualAdCarParameters.put("Engine capacity from", adsPage.getAdEngineСapacityMin(formatEngineCapacity(MIN_ENGINE)));
        actualAdCarParameters.put("Mileage from", adsPage.getAdCarMileageMax(formatMileage(MAX_MILEAGE)));
        actualAdCarParameters.put("City", adsPage.getAdsCity());
        actualAdCarParameters.put("VIN", adsPage.isAdsContainVin());
        return actualAdCarParameters;
    }

    private Map<String, String> getExpectedAdCarParameters() {
        Map<String, String> expectedAdCarParameters = new HashMap<>();
        expectedAdCarParameters.put("Brand", BRAND);
        expectedAdCarParameters.put("Model", MODEL);
        expectedAdCarParameters.put("Generation", GENERATION);
        expectedAdCarParameters.put("Car year from", MIN_CAR_YEAR);
        expectedAdCarParameters.put("Car price to", MAX_PRICE);
        expectedAdCarParameters.put("Engine capacity from", formatEngineCapacity(MIN_ENGINE));
        expectedAdCarParameters.put("Mileage from", formatMileage(MAX_MILEAGE));
        expectedAdCarParameters.put("City", CITY);
        expectedAdCarParameters.put("VIN", "True");
        return expectedAdCarParameters;
    }

    private String formatEngineCapacity(String engineCapacity) {
        return engineCapacity.replace(',', '.');
    }

    private String formatMileage(String mileage) {
        String formatterMileage = mileage.replaceAll("[^0-9]", "");
        return formatterMileage + "000";
    }
}




