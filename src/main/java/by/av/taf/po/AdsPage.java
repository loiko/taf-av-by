package by.av.taf.po;

import by.av.taf.Utils.Utils;
import by.av.taf.singleton.Singleton;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.*;

public class AdsPage extends BasePage {
    private WebDriver driver;
    WebDriverWait wait;
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
    }

    private int getTotalNumberOfAds() {
        List<WebElement> adElements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy((adLocator)));
        return adElements.size();
    }

    public AdsPage clickParametersButton() {
        WebElement parametersButton = wait.until(ExpectedConditions.elementToBeClickable(parametersButtonLocator));
        parametersButton.click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 400);");
        return this;
    }

    public AdsPage clickShowResultButton() {
        WebElement showResultButton = wait.until(ExpectedConditions.elementToBeClickable(showResultButtonLocator));
        showResultButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(adTitleLocator));
        return this;
    }

    public AdsPage clickVinCheckbox() {
        WebElement vinCheckbox = wait.until(ExpectedConditions.elementToBeClickable(vinCheckboxLocator));
        vinCheckbox.click();
        return this;
    }

    public AdsPage enterPriceMin(String minPrice) {
        WebElement priceMinInput = wait.until(ExpectedConditions.visibilityOfElementLocated(priceMinInputLocator));
        priceMinInput.sendKeys(minPrice, Keys.ENTER);
        return this;
    }

    public AdsPage enterPriceMax(String maxPrice) {
        WebElement priceMaxInput = wait.until(ExpectedConditions.visibilityOfElementLocated(priceMaxInputLocator));
        priceMaxInput.sendKeys(maxPrice, Keys.ENTER);
        return this;
    }

    public AdsPage selectBrand(String brandName) {
        WebElement brandDropdown = wait.until(ExpectedConditions.elementToBeClickable(brandDropdownLocator));
        brandDropdown.click();
        List<WebElement> brandOptions = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(brandOptionLocator));
        for (WebElement brandOption : brandOptions) {
            if (brandOption.getText().equals(brandName)) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", brandOption);
                brandOption.click();
                break;
            }
        }
        return this;
    }

    public AdsPage selectModel(String modelName) {
        WebElement modelDropdown = wait.until(ExpectedConditions.elementToBeClickable(modelDropdownLocator));
        modelDropdown.click();
        List<WebElement> modelOptions = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(modelOptionLocator));
        for (WebElement modelOption : modelOptions) {
            if (modelOption.getText().equals(modelName)) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", modelOption);
                modelOption.click();
                break;
            }
        }
        return this;
    }

    public AdsPage selectGeneration(String generationName) {
        WebElement generationDropdown = wait.until(ExpectedConditions.elementToBeClickable(generationDropdownLocator));
        generationDropdown.click();
        List<WebElement> generationOptions = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(generationOptionLocator));
        for (WebElement generationOption : generationOptions) {
            if (generationOption.getText().contains(generationName)) {
                generationOption.click();
                break;
            }
        }
        return this;
    }

    public AdsPage selectCarYearMin(String minCarYear) {
        WebElement yearMinDropdown = wait.until(ExpectedConditions.elementToBeClickable(yearMinDropdownLocator));
        yearMinDropdown.click();
        List<WebElement> yearFromOptions = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(yearMinOptionLocator));
        for (WebElement yearFromOption : yearFromOptions) {
            if (yearFromOption.getText().equals(minCarYear)) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", yearFromOption);
                yearFromOption.click();
                break;
            }
        }
        return this;
    }

    public AdsPage selectCarYearMax(String yearTo) {
        WebElement yearMaxDropdown = wait.until(ExpectedConditions.elementToBeClickable(yearMaxDropdownLocator));
        yearMaxDropdown.click();
        List<WebElement> yearToOptions = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(yearMaxOptionLocator));
        for (WebElement yearToOption : yearToOptions) {
            if (yearToOption.getText().equals(yearTo)) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", yearToOption);
                yearToOption.click();
                break;
            }
        }
        return this;
    }

    public AdsPage selectEngineMin(String minEngine) {
        WebElement engineMinDropdown = wait.until(ExpectedConditions.elementToBeClickable(engineMinDropdownLocator));
        engineMinDropdown.click();
        List<WebElement> engineMinOptions = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(engineMinOptionLocator));
        for (WebElement engineMinOption : engineMinOptions) {
            if (engineMinOption.getText().contains(minEngine)) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", engineMinOption);
                engineMinOption.click();
                break;
            }
        }
        return this;
    }

    public AdsPage selectEngineMax(String maxEngine) {
        WebElement engineMaxDropdown = wait.until(ExpectedConditions.elementToBeClickable(engineMaxDropdownLocator));
        engineMaxDropdown.click();
        List<WebElement> engineMaxOptions = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(engineMaxOptionLocator));
        for (WebElement engineMaxOption : engineMaxOptions) {
            if (engineMaxOption.getText().contains(maxEngine)) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", engineMaxOption);
                engineMaxOption.click();
                break;
            }
        }
        return this;
    }

    public AdsPage selectRegion(String region) throws InterruptedException {
        WebElement regionDropdown = wait.until(ExpectedConditions.elementToBeClickable(regionDropdownLocator));
        regionDropdown.click();
        List<WebElement> regionOptions = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(regionOptionLocator));
        for (WebElement regionOption : regionOptions) {
            if (regionOption.getText().equals(region)) {
                regionOption.click();
                break;
            }
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(cityDropdownLocator));
        return this;
    }

    public AdsPage selectRegistrationCountry(String country) {
        WebElement registrationCountryDropdown = wait.until(ExpectedConditions.elementToBeClickable(registrationCountyDropdownLocator));
        registrationCountryDropdown.click();
        List<WebElement> registrationCountries = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(generationOptionLocator));
        for (WebElement registrationCountry : registrationCountries) {
            if (registrationCountry.getText().equals(country)) {
                registrationCountry.click();
                break;
            }
        }
        return this;
    }

    public AdsPage selectCity(String city) {
        WebElement cityDropdown = wait.until(ExpectedConditions.elementToBeClickable(cityDropdownLocator));
        cityDropdown.click();
        List<WebElement> cityOptions = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(cityOptionLocator));
        for (WebElement cityOption : cityOptions) {
            if (cityOption.getText().equals(city)) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", cityOption);
                cityOption.click();
                break;
            }
        }
        return this;
    }

    public AdsPage selectMaxMileage(String maxMileage) {
        WebElement maxMileageDropdown = wait.until(ExpectedConditions.elementToBeClickable(mileageDropdownLocator));
        maxMileageDropdown.click();
        List<WebElement> cityOptions = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(mileageOptionLocator));
        for (WebElement mileageOption : cityOptions) {
            if (mileageOption.getText().equals(maxMileage)) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", mileageOption);
                mileageOption.click();
                break;
            }
        }
        return this;
    }

    public String getAdsBrand() {
        Set<String> adBrands = new HashSet<>();
        List<WebElement> adBrandElements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(adTitleLocator));
        for (WebElement adBrand : adBrandElements) {
            String text = adBrand.getText();
            String[] parts = text.split(" ");
            String brand = parts[0];
            adBrands.add(brand);
        }
        String result = adBrands.toString();
        result = result.substring(1, result.length() - 1);
        return result;
    }

    public String getAdsModel() {
        Set<String> adModels = new HashSet<>();
        List<WebElement> adModelElements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(adTitleLocator));
        for (WebElement adModel : adModelElements) {
            String text = adModel.getText();
            String[] parts = text.split(" ");
            String model = parts[1];
            adModels.add(model);
        }
        String result = adModels.toString();
        result = result.substring(1, result.length() - 1);
        return result;
    }

    public String getAdsGeneration() {
        Set<String> adGenerations = new HashSet<>();
        List<WebElement> adGenerationElements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(adTitleLocator));
        for (WebElement adGeneration : adGenerationElements) {
            String text = adGeneration.getText();
            String[] parts = text.split(" ");
            String generation = parts[2];
            adGenerations.add(generation);
        }
        String result = adGenerations.toString();
        result = result.substring(1, result.length() - 1);
        return result;
    }

    public List<String> getAdsUsdPrice() {
        List<String> adUsdPrices = new ArrayList<>();
        List<WebElement> adUsdPriceElements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(adUsdPriceLocator));
        for (WebElement adUsdPrice : adUsdPriceElements) {
            String text = adUsdPrice.getText();
            String usdPrice = text.replaceAll("[\\p{Z}\\s$≈]", "");
            adUsdPrices.add(usdPrice);
        }
        return adUsdPrices;
    }

    public String getAdCarUsdPriceMax(String maxPrice) {
        List<String> adUsdPrices = getAdsUsdPrice();
        for (String adUsdPrice : adUsdPrices) {
            int usdPrice = Integer.parseInt(adUsdPrice);
            if (Utils.isItMax(usdPrice, Integer.parseInt(maxPrice))) {
                return maxPrice;
            }
        }
        return null;
    }

    public List<String> getAdsCarYear() {
        List<String> adCarYears = new ArrayList<>();
        List<WebElement> adCarYearElements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(adCarYearLocator));
        for (WebElement adCarYear : adCarYearElements) {
            String text = adCarYear.getText();
            String carYear = text.replaceAll("[\\sг.]", "");
            adCarYears.add(carYear);
        }
        return adCarYears;
    }

    public String getAdCarYearMin(String minCarYear) {
        List<String> adCarYears = getAdsCarYear();
        for (String adCarYear : adCarYears) {
            int carYear = Integer.parseInt(adCarYear);
            if (Utils.isItMin(carYear, Integer.parseInt(minCarYear))) {
                return minCarYear;
            }
        }
        return null;
    }

    public List<String> getAdsCarMileage() {
        List<String> adCarMileages = new ArrayList<>();
        List<WebElement> adCarMileageElements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(adCarMileageLocator));
        for (WebElement adCarMileage : adCarMileageElements) {
            String text = adCarMileage.getText();
            String carMileage = text.replaceAll("[\\p{Z}\\sкм]", "");
            adCarMileages.add(carMileage);
        }
        System.out.println(adCarMileages);
        return adCarMileages;
    }

    public String getAdCarMileageMax(String maxMileage) {
        List<String> adCarMileages = getAdsCarMileage();
        for (String adCarMileage : adCarMileages) {
            int carMileages = Integer.parseInt(adCarMileage);
            if (Utils.isItMax(carMileages, Integer.parseInt(maxMileage))) {
                return maxMileage;
            }
        }
        return null;
    }

    public String getAdsCity() {
        Set<String> adCities = new HashSet<>();
        List<WebElement> adCityElements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(adCityLocator));
        for (WebElement adCity : adCityElements) {
            adCities.add(adCity.getText());
        }
        String result = adCities.toString();
        result = result.substring(1, result.length() - 1);
        return result;
    }

    public String isAdsContainVin() {
        List<WebElement> vinBadges = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(adVinBadgeLocator));
        int totalAds = getTotalNumberOfAds();
        int adsWithVinCount = vinBadges.size();
        return totalAds == adsWithVinCount ? "True" : "False";
    }

    public List<String> getAdsEngineСapacity() {
        List<String> adEngineCapacities = new ArrayList<>();
        List<WebElement> adEngineCapacitiesElements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(adCarParametersLocator));
        for (WebElement adEngineCapacityElement : adEngineCapacitiesElements) {
            String text = adEngineCapacityElement.getText();
            String[] parts = text.split(",\\s*");
            String engineCapacity = parts[1].replace(" л", "");
            adEngineCapacities.add(engineCapacity);
        }
        return adEngineCapacities;
    }

    public String getAdEngineСapacityMin(String minEngine) {
        List<String> adEgineСapacities = getAdsEngineСapacity();
        for (String adEgineСapacity : adEgineСapacities) {
            double egineСapacity = Double.parseDouble(adEgineСapacity);
            if (Utils.isItMin(egineСapacity, Double.parseDouble(minEngine))) {
                return minEngine;
            }
        }
        return null;
    }

    public Set<String> getAdsEngineType() {
        Set<String> adEngineTypes = new HashSet<>();
        List<WebElement> adEngineTypeElements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(adCarParametersLocator));
        for (WebElement adEngineTypeElement : adEngineTypeElements) {
            String text = adEngineTypeElement.getText();
            String[] parts = text.split(",\\s*");
            String engineType = parts[2];
            adEngineTypes.add(engineType);
        }
        System.out.println(adEngineTypes);
        return adEngineTypes;
    }
}
