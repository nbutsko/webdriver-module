package hurt_me_plenty.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class GoogleCloudSearchResultPage extends AbstractPage {

    private String searchTerm;

    @FindBy(xpath = "//*[contains(text(), 'Google Cloud Pricing Calculator')]/parent::a")
    private WebElement calculatorPageLink;

    public GoogleCloudSearchResultPage(WebDriver driver, String searchTerm) {
        super(driver);
        this.searchTerm = searchTerm;
    }

    @Override
    protected AbstractPage openPage() {
        throw new RuntimeException("You couldn't open this page!");
    }

    public GoogleCloudCalculatorPage openCalculator(){
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(calculatorPageLink));
        calculatorPageLink.click();
        return new GoogleCloudCalculatorPage(driver);
    }

}
