package hurt_me_plenty.page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class GoogleCloudHomePage extends AbstractPage {

    private static final String HOMEPAGE_URL = "https://cloud.google.com/";

    @FindBy(xpath = "//input[@aria-label='Search']")
    private WebElement searchInput;

    public GoogleCloudHomePage(WebDriver driver) {
        super(driver);
    }

    public GoogleCloudHomePage openPage() {
        driver.get(HOMEPAGE_URL);
        return this;
    }

    public GoogleCloudSearchResultPage searchForTerms(String term) {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(searchInput));
        searchInput.click();
        searchInput.sendKeys(term + Keys.ENTER);
        return new GoogleCloudSearchResultPage(driver, term);
    }

}
