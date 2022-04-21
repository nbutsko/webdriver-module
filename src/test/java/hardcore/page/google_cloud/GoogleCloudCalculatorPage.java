package hardcore.page.google_cloud;

import hardcore.page.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class GoogleCloudCalculatorPage extends AbstractPage {

    private static final String HOMEPAGE_URL = "https://cloud.google.com/products/calculator";

    @FindBy(xpath = "//devsite-iframe/iframe")
    private WebElement frameOuterCalculator;

    @FindBy(xpath = "//iframe[@id='myFrame']")
    private WebElement frameInnerCalculator;

    @FindBy(xpath = "//*[contains(text(), 'Compute Engine')]//ancestor::md-tab-item")
    private WebElement sectionComputeEngine;

    @FindBy(xpath = "//input[contains(@ng-model,'computeServer.quantity')]")
    private WebElement inputNumberOfInstances;

    @FindBy(xpath = "//*[contains(@ng-model,'computeServer.os')]")
    private WebElement selectorOperatingSystem;

    @FindBy(xpath = "//*[contains(@ng-model,'computeServer.class')]")
    private WebElement selectorMachineClass;

    @FindBy(xpath = "//*[contains(@ng-model,'computeServer.series')]")
    private WebElement selectorSeries;

    @FindBy(xpath = "//*[contains(@ng-model,'computeServer.instance')]")
    private WebElement selectorMachineType;

    @FindBy(xpath = "//*[contains(@ng-model,'computeServer.addGPUs')]")
    private WebElement checkboxAddGPUs;

    @FindBy(xpath = "//*[contains(@ng-model,'computeServer.gpuType')]")
    private WebElement selectorGpuType;

    @FindBy(xpath = "//*[contains(@ng-model,'computeServer.gpuCount')]")
    private WebElement selectorGpuNumber;

    @FindBy(xpath = "//*[contains(@ng-model,'computeServer.ssd')]")
    private WebElement selectorLocalSSD;

    @FindBy(xpath = "//*[contains(@ng-model,'computeServer.location')]")
    private WebElement selectorDatacenterLocation;

    @FindBy(xpath = "//*[contains(@ng-model,'computeServer.cud')]")
    private WebElement selectorCommittedUsage;

    @FindBy(xpath = "//button[contains(@ng-click, 'addComputeServer')]")
    private WebElement buttonAddToEstimate;

    public GoogleCloudCalculatorPage(WebDriver driver) {
        super(driver);
    }

    public GoogleCloudCalculatorPage openPage() {
        driver.get(HOMEPAGE_URL);
        return this;
    }

    public GoogleCloudCalculatorPage selectComputeEngineSection() {
        driver.switchTo().frame(frameOuterCalculator);
        driver.switchTo().frame(frameInnerCalculator);
        sectionComputeEngine.click();
        return this;
    }

    public GoogleCloudCalculatorPage fillFormInstances(String numberOfInstances, String operatingSystem, String machineClass, String series, String machineType){
        inputNumberOfInstances(numberOfInstances);
        selectOperatingSystem(operatingSystem);
        selectMachineClass(machineClass);
        selectSeries(series);
        selectMachineType(machineType);
        return this;
    }

    private GoogleCloudCalculatorPage inputNumberOfInstances(String number) {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(inputNumberOfInstances));
        inputNumberOfInstances.click();
        inputNumberOfInstances.sendKeys(number);
        return this;
    }

    private GoogleCloudCalculatorPage selectOperatingSystem(String operatingSystem) {
        selectOption(selectorOperatingSystem, operatingSystem);
        return this;
    }

    private GoogleCloudCalculatorPage selectMachineClass(String machineClass) {
        selectOption(selectorMachineClass, machineClass);
        return this;
    }

    private GoogleCloudCalculatorPage selectSeries(String series) {
        selectOption(selectorSeries, series);
        return this;
    }

    private GoogleCloudCalculatorPage selectMachineType(String machineType) {
        selectOption(selectorMachineType, machineType);
        return this;
    }

    public GoogleCloudCalculatorPage checkAddGPU() {
        checkboxAddGPUs.click();
        return this;
    }

    public GoogleCloudCalculatorPage fillFormGPU(String type, String number){
        selectTypeGPU(type);
        selectNumberGPU(number);
        return this;
    }

    private GoogleCloudCalculatorPage selectTypeGPU(String type) {
        selectOption(selectorGpuType, type);
        return this;
    }

    private GoogleCloudCalculatorPage selectNumberGPU(String number) {
        selectOption(selectorGpuNumber, number);
        return this;
    }

    public GoogleCloudCalculatorPage selectLocalSSD(String localSSD) {
        selectOption(selectorLocalSSD, localSSD);
        return this;
    }

    public GoogleCloudCalculatorPage selectDatacenterLocation(String location) {
        selectOption(selectorDatacenterLocation, location);
        return this;
    }

    public GoogleCloudCalculatorPage selectCommittedUsage(String committedUsage) {
        selectOption(selectorCommittedUsage, committedUsage);
        return this;
    }

    public GoogleCloudEstimatePage clickAddToEstimateButton() {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(buttonAddToEstimate)).click();
        return new GoogleCloudEstimatePage(driver);
    }

    private void selectOption(WebElement selector, String option) {
        String selectMenuLocator = "//div[@class='md-scroll-mask']/following-sibling::div//*[contains(text(),'%s')]/parent::md-option";
        By selectedOption = By.xpath(String.format(selectMenuLocator, option));
        selector.click();
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(selectedOption));
        driver.findElement(selectedOption).click();
    }
}
