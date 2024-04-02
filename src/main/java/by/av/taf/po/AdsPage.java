package by.av.taf.po;

import by.av.taf.Utils.Utils;
import by.av.taf.singleton.Singleton;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.*;

public class AdsPage extends BasePage {
    private WebDriver driver;
    WebDriverWait wait;
    private static final Logger logger = LogManager.getLogger();
    private By parametersButtonLocator = By.xpath("//button[@class = 'button button--link'][1]");
    private By showResultButtonLocator = By.xpath("//div[@class = 'filter__show-result']");
    private By brandDropdownLocator = By.xpath("//button[@name = 'p-6-0-2-brand']");
    private By brandOptionLocator = By.xpath("//button[@data-property-label = 'Марка']");
    private By modelDropdownLocator = By.xpath("//button[@name = 'p-6-0-3-model']");
    private By modelOptionLocator = By.xpath("//button[@data-property-label = 'Модель']");
    private By generationDropdownLocator = By.xpath("//button[@name = 'p-6-0-4-generation']");
    private By generationOptionLocator = By.xpath("//span[@class = 'dropdown__card-text']");
    private By yearMinDropdownLocator = By.xpath("//button[@title = 'Год от']");
    private By yearMinOptionLocator = By.xpath("//div[@class = 'dropdown__box'][1]//button[@data-property-label = 'Год']");
    private By yearMaxDropdownLocator = By.xpath("//button[@name = 'p-7-year' and @title = 'до']");
    private By yearMaxOptionLocator = By.xpath("//div[@class = 'dropdown__box'][2]//button[@data-property-label = 'Год']");
    private By priceMinInputLocator = By.xpath("(//input[@id = 'p-9-price_usd'])[1]");
    private By priceMaxInputLocator = By.xpath("(//input[@id = 'p-9-price_usd'])[2]");
    private By engineMinDropdownLocator = By.xpath("//span[@class = 'dropdown-floatlabel__value' and text() = 'Объём от']");
    private By engineMinOptionLocator = By.xpath("//div[@class = 'dropdown__box'][1]//button[@data-property-label = 'Объём']");
    private By engineMaxDropdownLocator = By.xpath("(//span[@class = 'dropdown-floatlabel__value' and text() = 'до'])[2]");
    private By engineMaxOptionLocator = By.xpath("//div[@class = 'dropdown__box'][2]//button[@data-property-label = 'Объём']");
    private By regionDropdownLocator = By.xpath("//button[@name = 'p-22-place_region']");
    private By regionOptionLocator = By.xpath("//div[@id = 'p-22-place_region']//span[@class = 'checkbox__description']");
    private By cityDropdownLocator = By.xpath("//button[@name = 'p-23-place_city']");
    private By cityOptionLocator = By.xpath("//div[@id = 'p-23-place_city']//span[@class = 'checkbox__description']");
    private By registrationCountyDropdownLocator = By.xpath("(//div[@id='p-37-registration_country']//button)[1]");
    private By mileageDropdownLocator = By.xpath("//div[@id = 'p-26-mileage_km']");
    private By mileageOptionLocator = By.xpath("//button[@data-property-name = 'mileage_km']");
    private By vinCheckboxLocator = By.xpath("//div[@class= 'filter__option']/label[@for = 'p-36-vin_indicated']");
    private By adLocator = By.xpath("//div[@class = 'listing-item']");
    private By adTitleLocator = By.xpath("//h3//span[@class = 'link-text']");
    private By adCarParametersLocator = By.xpath("//div[@class = 'listing-item__params']/child::*[2]");
    private By adVinBadgeLocator = By.xpath("//div[@class = 'badge badge--vin']");
    private By adUsdPriceLocator = By.xpath("//div[@class = 'listing-item__priceusd']");
    private By adCarYearLocator = By.xpath("//div[@class = 'listing-item__params']/child::*[1]");
    private By adCarMileageLocator = By.xpath("//div[@class = 'listing-item__params']/child::*[3]/span");
    private By adCityLocator = By.xpath("//div[@class = 'listing-item__location']");

    public AdsPage() {
        this.driver = Singleton.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        logger.info("AdsPage instance created");
    }

    private int getTotalNumberOfAds() {
        try {
            List<WebElement> adElements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy((adLocator)));
            logger.info("Total number of ads retrieved: {}", adElements.size());
            return adElements.size();
        } catch (Exception e) {
            logger.error("Failed to retrieve total number of ads: {}", e.getMessage());
            throw e;
        }
    }

    public AdsPage clickParametersButton() {
        try {
            WebElement parametersButton = wait.until(ExpectedConditions.elementToBeClickable(parametersButtonLocator));
            parametersButton.click();
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0, 400);");
            logger.info("Parameters button clicked");
            return this;
        } catch (Exception e) {
            logger.error("Failed to click parameters button: {}", e.getMessage());
            throw e;
        }
    }

    public AdsPage clickShowResultButton() {
        try {
            WebElement showResultButton = wait.until(ExpectedConditions.elementToBeClickable(showResultButtonLocator));
            showResultButton.click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(adTitleLocator));
            logger.info("Show result button clicked");
            return this;
        } catch (Exception e) {
            logger.error("Failed to click show result button: {}", e.getMessage());
            throw e;
        }
    }

    public AdsPage clickVinCheckbox() {
        try {
            WebElement vinCheckbox = wait.until(ExpectedConditions.elementToBeClickable(vinCheckboxLocator));
            vinCheckbox.click();
            logger.info("VIN checkbox clicked");
            return this;
        } catch (Exception e) {
            logger.error("Failed to click VIN checkbox: {}", e.getMessage());
            throw e;
        }
    }

    public AdsPage enterPriceMin(String minPrice) {
        try {
            WebElement priceMinInput = wait.until(ExpectedConditions.visibilityOfElementLocated(priceMinInputLocator));
            priceMinInput.sendKeys(minPrice, Keys.ENTER);
            logger.info("Min price entered: {}", minPrice);
            return this;
        } catch (Exception e) {
            logger.error("Failed to enter min price: {}", e.getMessage());
            throw e;
        }
    }

    public AdsPage enterPriceMax(String maxPrice) {
        try {
            WebElement priceMaxInput = wait.until(ExpectedConditions.visibilityOfElementLocated(priceMaxInputLocator));
            priceMaxInput.sendKeys(maxPrice, Keys.ENTER);
            logger.info("Max price entered: {}", maxPrice);
            return this;
        } catch (Exception e) {
            logger.error("Failed to enter max price: {}", e.getMessage());
            throw e;
        }
    }

    public AdsPage selectBrand(String brandName) {
        try {
            WebElement brandDropdown = wait.until(ExpectedConditions.elementToBeClickable(brandDropdownLocator));
            brandDropdown.click();
            logger.info("Car brand dropdown clicked");
            List<WebElement> brandOptions = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(brandOptionLocator));
            for (WebElement brandOption : brandOptions) {
                if (brandOption.getText().equals(brandName)) {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", brandOption);
                    brandOption.click();
                    logger.info("Car brand '{}' selected", brandName);
                    break;
                }
            }
            return this;
        } catch (Exception e) {
            logger.error("Failed to select car brand '{}': {}", brandName, e.getMessage());
            throw e;
        }
    }

    public AdsPage selectModel(String modelName) {
        try {
            WebElement modelDropdown = wait.until(ExpectedConditions.elementToBeClickable(modelDropdownLocator));
            modelDropdown.click();
            logger.info("Car model dropdown clicked");
            List<WebElement> modelOptions = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(modelOptionLocator));
            for (WebElement modelOption : modelOptions) {
                if (modelOption.getText().equals(modelName)) {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", modelOption);
                    modelOption.click();
                    logger.info("Car model'{}' selected", modelName);
                    break;
                }
            }
            return this;
        } catch (Exception e) {
            logger.error("Failed to select car model '{}': {}", modelName, e.getMessage());
            throw e;
        }
    }

    public AdsPage selectGeneration(String generationName) {
        try {
            WebElement generationDropdown = wait.until(ExpectedConditions.elementToBeClickable(generationDropdownLocator));
            generationDropdown.click();
            logger.info("Car generation dropdown clicked");
            List<WebElement> generationOptions = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(generationOptionLocator));
            for (WebElement generationOption : generationOptions) {
                if (generationOption.getText().contains(generationName)) {
                    generationOption.click();
                    logger.info("Car generation'{}' selected", generationName);
                    break;
                }
            }
            return this;
        } catch (Exception e) {
            logger.error("Failed to select car generation' '{}': {}", generationName, e.getMessage());
            throw e;
        }
    }

    public AdsPage selectCarYearMin(String minCarYear) {
        try {
            WebElement yearMinDropdown = wait.until(ExpectedConditions.elementToBeClickable(yearMinDropdownLocator));
            yearMinDropdown.click();
            logger.info("Min car year dropdown clicked");
            List<WebElement> yearFromOptions = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(yearMinOptionLocator));
            for (WebElement yearFromOption : yearFromOptions) {
                if (yearFromOption.getText().equals(minCarYear)) {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", yearFromOption);
                    yearFromOption.click();
                    logger.info("Min car year '{}' selected", minCarYear);
                    break;
                }
            }
            return this;
        } catch (Exception e) {
            logger.error("Failed to select min car year '{}': {}", minCarYear, e.getMessage());
            throw e;
        }
    }

    public AdsPage selectCarYearMax(String maxCarYear) {
        try {
            WebElement yearMaxDropdown = wait.until(ExpectedConditions.elementToBeClickable(yearMaxDropdownLocator));
            yearMaxDropdown.click();
            logger.info("Max car year dropdown clicked");
            List<WebElement> yearToOptions = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(yearMaxOptionLocator));
            for (WebElement yearToOption : yearToOptions) {
                if (yearToOption.getText().equals(maxCarYear)) {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", yearToOption);
                    yearToOption.click();
                    logger.info("Max car year '{}' selected", maxCarYear);
                    break;
                }
            }
            return this;
        } catch (Exception e) {
            logger.error("Failed to select max car year '{}': {}", maxCarYear, e.getMessage());
            throw e;
        }
    }

    public AdsPage selectEngineMin(String minEngine) {
        try {
            WebElement engineMinDropdown = wait.until(ExpectedConditions.elementToBeClickable(engineMinDropdownLocator));
            engineMinDropdown.click();
            logger.info("Min engine dropdown clicked");
            List<WebElement> engineMinOptions = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(engineMinOptionLocator));
            for (WebElement engineMinOption : engineMinOptions) {
                if (engineMinOption.getText().contains(minEngine)) {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", engineMinOption);
                    engineMinOption.click();
                    logger.info("Min engine '{}' selected", minEngine);
                    break;
                }
            }
            return this;
        } catch (Exception e) {
            logger.error("Failed to select min engine '{}': {}", minEngine, e.getMessage());
            throw e;
        }
    }

    public AdsPage selectEngineMax(String maxEngine) {
        try {
            WebElement engineMaxDropdown = wait.until(ExpectedConditions.elementToBeClickable(engineMaxDropdownLocator));
            engineMaxDropdown.click();
            logger.info("Max engine dropdown clicked");
            List<WebElement> engineMaxOptions = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(engineMaxOptionLocator));
            for (WebElement engineMaxOption : engineMaxOptions) {
                if (engineMaxOption.getText().contains(maxEngine)) {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", engineMaxOption);
                    engineMaxOption.click();
                    logger.info("Max engine '{}' selected", maxEngine);
                    break;
                }
            }
            return this;
        } catch (Exception e) {
            logger.error("Failed to select max engine '{}': {}", maxEngine, e.getMessage());
            throw e;
        }
    }

    public AdsPage selectRegion(String region) {
        try {
            WebElement regionDropdown = wait.until(ExpectedConditions.elementToBeClickable(regionDropdownLocator));
            regionDropdown.click();
            logger.info("Region dropdown clicked");
            List<WebElement> regionOptions = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(regionOptionLocator));
            for (WebElement regionOption : regionOptions) {
                if (regionOption.getText().equals(region)) {
                    regionOption.click();
                    logger.info("Region '{}' selected", region);
                    break;
                }
            }
            wait.until(ExpectedConditions.visibilityOfElementLocated(cityDropdownLocator));
            return this;
        } catch (Exception e) {
            logger.error("Failed to select region '{}': {}", region, e.getMessage());
            throw e;
        }
    }

    public AdsPage selectRegistrationCountry(String country) {
        try {
            WebElement registrationCountryDropdown = wait.until(ExpectedConditions.elementToBeClickable(registrationCountyDropdownLocator));
            registrationCountryDropdown.click();
            logger.info("Registration country dropdown clicked");
            List<WebElement> registrationCountries = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(generationOptionLocator));
            for (WebElement registrationCountry : registrationCountries) {
                if (registrationCountry.getText().equals(country)) {
                    registrationCountry.click();
                    logger.info("Registration country  '{}' selected", country);
                    break;
                }
            }
            return this;
        } catch (Exception e) {
            logger.error("Failed to select registration country  '{}': {}", country, e.getMessage());
            throw e;
        }
    }

    public AdsPage selectCity(String city) {
        try {
            WebElement cityDropdown = wait.until(ExpectedConditions.elementToBeClickable(cityDropdownLocator));
            cityDropdown.click();
            logger.info("City dropdown clicked");
            List<WebElement> cityOptions = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(cityOptionLocator));
            for (WebElement cityOption : cityOptions) {
                if (cityOption.getText().equals(city)) {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", cityOption);
                    cityOption.click();
                    logger.info("City  '{}' selected", city);
                    break;
                }
            }
            return this;
        } catch (Exception e) {
            logger.error("Failed to select city '{}': {}", city, e.getMessage());
            throw e;
        }
    }

    public AdsPage selectMaxMileage(String maxMileage) {
        try {
            WebElement maxMileageDropdown = wait.until(ExpectedConditions.elementToBeClickable(mileageDropdownLocator));
            maxMileageDropdown.click();
            logger.info("Max mileage dropdown clicked");
            List<WebElement> cityOptions = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(mileageOptionLocator));
            for (WebElement mileageOption : cityOptions) {
                if (mileageOption.getText().equals(maxMileage)) {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", mileageOption);
                    mileageOption.click();
                    logger.info("Max mileage '{}' selected", maxMileage);
                    break;
                }
            }
            return this;
        } catch (Exception e) {
            logger.error("Failed to select max mileage'{}': {}", maxMileage, e.getMessage());
            throw e;
        }
    }

    public String getAdsBrand() {
        Set<String> adBrands = new HashSet<>();
        try {
            List<WebElement> adBrandElements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(adTitleLocator));
            for (WebElement adBrand : adBrandElements) {
                String text = adBrand.getText();
                String[] parts = text.split(" ");
                String brand = parts[0];
                adBrands.add(brand);
            }
            String result = adBrands.toString();
            result = result.substring(1, result.length() - 1);
            logger.info("Retrieved ads brands: {}", result);
            return result;
        } catch (Exception e) {
            logger.error("Failed to retrieve ads brands: {}", e.getMessage());
            return null;
        }
    }

    public String getAdsModel() {
        Set<String> adModels = new HashSet<>();
        try {
            List<WebElement> adModelElements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(adTitleLocator));
            for (WebElement adModel : adModelElements) {
                String text = adModel.getText();
                String[] parts = text.split(" ");
                String model = parts[1];
                adModels.add(model);
            }
            String result = adModels.toString();
            result = result.substring(1, result.length() - 1);
            logger.info("Retrieved ads models: {}", result);
            return result;
        } catch (Exception e) {
            logger.error("Failed to retrieve ads models: {}", e.getMessage());
            return null;
        }
    }

    public String getAdsGeneration() {
        Set<String> adGenerations = new HashSet<>();
        try {
            List<WebElement> adGenerationElements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(adTitleLocator));
            for (WebElement adGeneration : adGenerationElements) {
                String text = adGeneration.getText();
                String[] parts = text.split(" ");
                String generation = parts[2];
                adGenerations.add(generation);
            }
            String result = adGenerations.toString();
            result = result.substring(1, result.length() - 1);
            logger.info("Retrieved ads generations: {}", result);
            return result;
        } catch (Exception e) {
            logger.error("Failed to retrieve ads generations: {}", e.getMessage());
            return null;
        }
    }

    public List<String> getAdsUsdPrice() {
        List<String> adUsdPrices = new ArrayList<>();
        try {
            List<WebElement> adUsdPriceElements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(adUsdPriceLocator));
            for (WebElement adUsdPrice : adUsdPriceElements) {
                String text = adUsdPrice.getText();
                String usdPrice = text.replaceAll("[\\p{Z}\\s$≈]", "");
                adUsdPrices.add(usdPrice);
            }
            logger.info("Retrieved ads USD prices: {}", adUsdPrices);
            return adUsdPrices;
        } catch (Exception e) {
            logger.error("Failed to retrieve ads USD prices: {}", e.getMessage());
            return null;
        }
    }

    public String getAdCarUsdPriceMax(String maxPrice) {
        List<String> adUsdPrices = getAdsUsdPrice();
        try {
            for (String adUsdPrice : adUsdPrices) {
                int usdPrice = Integer.parseInt(adUsdPrice);
                if (Utils.isItMax(usdPrice, Integer.parseInt(maxPrice))) {
                    logger.info("Max USD price found: {}", maxPrice);
                    return maxPrice;
                }
            }
        } catch (NumberFormatException e) {
            logger.error("Failed to parse USD price: {}", e.getMessage());
        }
        logger.warn("No ad with max USD price found");
        return null;
    }

    public List<String> getAdsCarYear() {
        List<String> adCarYears = new ArrayList<>();
        try {
            List<WebElement> adCarYearElements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(adCarYearLocator));
            for (WebElement adCarYear : adCarYearElements) {
                String text = adCarYear.getText();
                String carYear = text.replaceAll("[\\sг.]", "");
                adCarYears.add(carYear);
            }
            logger.info("Retrieved ads car years: {}", adCarYears);
            return adCarYears;
        } catch (Exception e) {
            logger.error("Failed to retrieve ads car years: {}", e.getMessage());
            throw e;
        }
    }

    public String getAdCarYearMin(String minCarYear) {
        List<String> adCarYears = getAdsCarYear();
        try {
            for (String adCarYear : adCarYears) {
                int carYear = Integer.parseInt(adCarYear);
                if (Utils.isItMin(carYear, Integer.parseInt(minCarYear))) {
                    logger.info("Min car year found: {}", minCarYear);
                    return minCarYear;
                }
            }
            return null;
        } catch (Exception e) {
            logger.error("Failed to find min car year: {}", e.getMessage());
            throw e;
        }
    }

    public List<String> getAdsCarMileage() {
        List<String> adCarMileages = new ArrayList<>();
        try {
            List<WebElement> adCarMileageElements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(adCarMileageLocator));
            for (WebElement adCarMileage : adCarMileageElements) {
                String text = adCarMileage.getText();
                String carMileage = text.replaceAll("[\\p{Z}\\sкм]", "");
                adCarMileages.add(carMileage);
            }
            logger.info("Retrieved ads car mileages: {}", adCarMileages);
            return adCarMileages;
        } catch (Exception e) {
            logger.error("Failed to retrieve ads car mileages: {}", e.getMessage());
            throw e;
        }
    }

    public String getAdCarMileageMax(String maxMileage) {
        List<String> adCarMileages = getAdsCarMileage();
        try {
            for (String adCarMileage : adCarMileages) {
                int carMileages = Integer.parseInt(adCarMileage);
                if (Utils.isItMax(carMileages, Integer.parseInt(maxMileage))) {
                    logger.info("Max car mileage found: {}", maxMileage);
                    return maxMileage;
                }
            }
            return null;
        } catch (Exception e) {
            logger.error("Failed to find max car mileage: {}", e.getMessage());
            throw e;
        }
    }

    public String getAdsCity() {
        Set<String> adCities = new HashSet<>();
        try {
            List<WebElement> adCityElements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(adCityLocator));
            for (WebElement adCity : adCityElements) {
                adCities.add(adCity.getText());
            }
            String result = adCities.toString();
            result = result.substring(1, result.length() - 1);
            logger.info("Retrieved ads car cities: {}", adCities);
            return result;
        } catch (Exception e) {
            logger.error("Failed to retrieve advertisement car cities: {}", e.getMessage());
            throw e;
        }
    }

    public String isAdsContainVin() {
        try {
            List<WebElement> vinBadges = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(adVinBadgeLocator));
            int totalAds = getTotalNumberOfAds();
            int adsWithVinCount = vinBadges.size();
            boolean adsContainVin = totalAds == adsWithVinCount;
            String result = adsContainVin ? "True" : "False";
            logger.info("Ads contain VIN: {}", result);
            return result;
        } catch (Exception e) {
            logger.error("Failed to check if ads contain VIN: {}", e.getMessage());
            throw e;
        }
    }

    public List<String> getAdsEngineСapacity() {
        List<String> adEngineCapacities = new ArrayList<>();
        try {
            List<WebElement> adEngineCapacitiesElements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(adCarParametersLocator));
            for (WebElement adEngineCapacityElement : adEngineCapacitiesElements) {
                String text = adEngineCapacityElement.getText();
                String[] parts = text.split(",\\s*");
                String engineCapacity = parts[1].replace(" л", "");
                adEngineCapacities.add(engineCapacity);
            }
            logger.info("Retrieved ads engine capacities: {}", adEngineCapacities);
            return adEngineCapacities;
        } catch (Exception e) {
            logger.error("Failed to retrieve ads engine capacities: {}", e.getMessage());
            throw e;
        }
    }

    public String getAdEngineСapacityMin(String minEngine) {
        List<String> adEgineСapacities = getAdsEngineСapacity();
        try {
            for (String adEgineСapacity : adEgineСapacities) {
                double egineСapacity = Double.parseDouble(adEgineСapacity);
                if (Utils.isItMin(egineСapacity, Double.parseDouble(minEngine))) {
                    logger.info("Min engine found: {}", minEngine);
                    return minEngine;
                }
            }
            return null;
        } catch (Exception e) {
            logger.error("Failed to find min engine capacity: {}", e.getMessage());
            throw e;
        }
    }
}
