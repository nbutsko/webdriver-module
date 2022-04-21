package hardcore.page.google_cloud;

import hardcore.page.AbstractPage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class GoogleCloudHomePage extends AbstractPage {

    private static final String HOMEPAGE_URL = "https://cloud.google.com/";

    @FindBy(xpath = "//devsite-snackbar[@type='cookie-notification']//button")
    private WebElement buttonAcceptCookies;

    @FindBy(xpath = "//input[@aria-label='Search']")
    private WebElement inputSearch;

    public GoogleCloudHomePage(WebDriver driver) {
        super(driver);
    }

    public GoogleCloudHomePage openPage() {
        driver.get(HOMEPAGE_URL);
        return this;
    }

    public GoogleCloudHomePage clickButtonAcceptCookies(){
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(buttonAcceptCookies));
        buttonAcceptCookies.click();
        return this;
    }

    public GoogleCloudSearchResultPage searchForTerms(String term) {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(inputSearch));
        inputSearch.click();
        inputSearch.sendKeys(term + Keys.ENTER);
        return new GoogleCloudSearchResultPage(driver, term);
    }

}
