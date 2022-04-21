package hardcore.page.google_cloud;

import hardcore.page.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class GoogleCloudEstimatePage extends AbstractPage {

    @FindBy(xpath = "//devsite-iframe/iframe")
    private WebElement frameOuterCalculator;

    @FindBy(xpath = "//iframe[@id='myFrame']")
    private WebElement frameInnerCalculator;

    @FindBy(xpath = "//*[contains(text(),' Total Estimated Cost')]")
    private WebElement totalEstimatedCost;

    @FindBy(xpath = "//button[@aria-label='Email Estimate']")
    private WebElement buttonEmailEstimate;

    @FindBy(xpath = "//md-input-container//input[contains(@ng-model,'user.email')]")
    private WebElement inputEmail;

    @FindBy(xpath = "//button[@aria-label='Send Email']")
    private WebElement buttonSendEmail;

    public GoogleCloudEstimatePage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected AbstractPage openPage() {
        return null;
    }

    public double getAmountOfRentPerMonthFromGoogleCloudPage() {
        String totalCostLine = totalEstimatedCost.getText();
        int startIndex = totalCostLine.indexOf("USD");
        int endIndex = totalCostLine.indexOf("per");
        String amountOfRent = totalCostLine.substring(startIndex + 3, endIndex).trim().replaceAll(",", "");
        return Double.parseDouble(amountOfRent);
    }

    public GoogleCloudEstimatePage clickButtonEmailEstimate() {
        driver.switchTo().frame(frameOuterCalculator);
        driver.switchTo().frame(frameInnerCalculator);
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(buttonEmailEstimate));
        buttonEmailEstimate.click();
        return this;
    }

    public GoogleCloudEstimatePage clickButtonSendEmailWithEstimate(String emailAddress) {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(inputEmail));
        inputEmail.sendKeys(emailAddress);
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(buttonSendEmail));
        buttonSendEmail.click();
        return this;
    }
}
