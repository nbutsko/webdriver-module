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
    private WebElement outerCalculatorFrame;

    @FindBy(xpath = "//iframe[@id='myFrame']")
    private WebElement innerCalculatorFrame;

    @FindBy(xpath = "//*[contains(text(), 'Compute Engine')]//ancestor::md-tab-item")
    private WebElement computeEngineSection;

    @FindBy(xpath = "//input[contains(@ng-model,'computeServer.quantity')]")
    private WebElement numberOfInstancesInput;

    @FindBy(xpath = "//*[contains(@ng-model,'computeServer.os')]")
    private WebElement operatingSystemSelector;

    @FindBy(xpath = "//*[contains(@ng-model,'computeServer.class')]")
    private WebElement machineClassSelector;

    @FindBy(xpath = "//*[contains(@ng-model,'computeServer.series')]")
    private WebElement seriesSelector;

    @FindBy(xpath = "//*[contains(@ng-model,'computeServer.instance')]")
    private WebElement machineTypeSelector;

    @FindBy(xpath = "//*[contains(@ng-model,'computeServer.addGPUs')]")
    private WebElement checkboxAddGPUs;

    @FindBy(xpath = "//*[contains(@ng-model,'computeServer.gpuType')]")
    private WebElement gpuTypeSelector;

    @FindBy(xpath = "//*[contains(@ng-model,'computeServer.gpuCount')]")
    private WebElement gpuNumberSelector;

    @FindBy(xpath = "//*[contains(@ng-model,'computeServer.ssd')]")
    private WebElement localSSDSelector;

    @FindBy(xpath = "//*[contains(@ng-model,'computeServer.location')]")
    private WebElement datacenterLocationSelector;

    @FindBy(xpath = "//*[contains(@ng-model,'computeServer.cud')]")
    private WebElement committedUsageSelector;

    @FindBy(xpath = "//button[contains(@ng-click, 'addComputeServer')]")
    private WebElement buttonAddToEstimate;

    public GoogleCloudCalculatorPage(WebDriver driver) {
        super(driver);
    }

    public GoogleCloudCalculatorPage openPage() {
        driver.get(HOMEPAGE_URL);
        return this;
    }

    /*@Override
    protected AbstractPage openPage() {
        return null;
    }*/

    public GoogleCloudCalculatorPage selectComputeEngineSection() {
        driver.switchTo().frame(outerCalculatorFrame);
        driver.switchTo().frame(innerCalculatorFrame);
        computeEngineSection.click();
        return this;
    }

    public GoogleCloudCalculatorPage inputNumberOfInstances(String number) {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(numberOfInstancesInput));
        numberOfInstancesInput.click();
        numberOfInstancesInput.sendKeys(number);
        return this;
    }

    public GoogleCloudCalculatorPage selectOperatingSystem(String operatingSystem) {
        selectOption(operatingSystemSelector, operatingSystem);
        return this;
    }

    public GoogleCloudCalculatorPage selectMachineClass(String machineClass) {
        selectOption(machineClassSelector, machineClass);
        return this;
    }

    public GoogleCloudCalculatorPage selectSeries(String series) {
        selectOption(seriesSelector, series);
        return this;
    }

    public GoogleCloudCalculatorPage selectMachineType(String machineType) {
        selectOption(machineTypeSelector, machineType);
        return this;
    }

    public GoogleCloudCalculatorPage checkAddGPU() {
        checkboxAddGPUs.click();
        return this;
    }

    public GoogleCloudCalculatorPage selectTypeGPU(String type) {
        selectOption(gpuTypeSelector, type);
        return this;
    }

    public GoogleCloudCalculatorPage selectNumberGPU(String number) {
        selectOption(gpuNumberSelector, number);
        return this;
    }

    public GoogleCloudCalculatorPage selectLocalSSD(String localSSD) {
        selectOption(localSSDSelector, localSSD);
        return this;
    }

    public GoogleCloudCalculatorPage selectDatacenterLocation(String location) {
        selectOption(datacenterLocationSelector, location);
        return this;
    }

    public GoogleCloudCalculatorPage selectCommittedUsage(String committedUsage) {
        selectOption(committedUsageSelector, committedUsage);
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
