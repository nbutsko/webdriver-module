package hardcore.page.google_cloud;

import hardcore.page.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class GoogleCloudEstimatePage extends AbstractPage {

    @FindBy(xpath = "//md-list-item/*[contains(text(),'Region')]")
    private WebElement region;

    @FindBy(xpath = "//md-list-item/*[contains(text(),'Commitment term')]")
    private WebElement commitmentTerm;

    @FindBy(xpath = "//md-list-item/*[contains(text(),'VM class')]")
    private WebElement vmClass;

    @FindBy(xpath = "//md-list-item/*[contains(text(),' Instance type')]")
    private  WebElement instanceType;

    @FindBy(xpath = "//md-list-item/*[contains(text(),' Local SSD')]")
    private WebElement localSSD;

    @FindBy(xpath = "//*[contains(text(),' Total Estimated Cost')]")
    private WebElement totalEstimatedCost;

    @FindBy(xpath = "//input[contains(@ng-model,'user.email')]")
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

    public String getRegionOnEstimate(){
        return region.getText();
    }

    public String getCommitmentTermOnEstimate(){
        return commitmentTerm.getText();
    }

    public String getVMClassOnEstimate(){
        return vmClass.getText();
    }

    public String getInstanceTypeOnEstimate(){
        return instanceType.getText();
    }

    public String getLocalSSDOnEstimate(){
        return localSSD.getText();
    }

    public double getAmountOfRentPerMonthFromGoogleCloudPage(){
        String totalCostLine = totalEstimatedCost.getText();
        int startIndex = totalCostLine.indexOf("USD");
        int endIndex = totalCostLine.indexOf("per");
        String amountOfRent = totalCostLine.substring(startIndex+3,endIndex).trim().replaceAll(",","");
        return Double.parseDouble(amountOfRent);
    }

    public GoogleCloudEstimatePage sendEmailWithEstimate (String emailAddress){
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(inputEmail));
        inputEmail.sendKeys(emailAddress);
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(buttonSendEmail));
        buttonSendEmail.click();
        return this;
    }


}
