package hardcore.page.yopmail;

import hardcore.page.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class YopmailHomePage extends AbstractPage {

    private static final String HOMEPAGE_URL = "https://yopmail.com/en/";

    @FindBy(xpath = "//h2[text()='Privacy Settings']/parent::div//button[@id='accept']")
    private WebElement buttonAcceptCookies;

    @FindBy(xpath = "//a[@href='email-generator']")
    private WebElement buttonGenerateRandomAddress;

    public YopmailHomePage(WebDriver driver) {
        super(driver);
    }

    public YopmailHomePage openPage() {
        driver.get(HOMEPAGE_URL);
        return this;
    }

    public YopmailHomePage clickButtonAcceptCookies() {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(buttonAcceptCookies));
        buttonAcceptCookies.click();
        return this;
    }

    public YopmailGeneratedPage clickButtonGenerateRandomAddress() {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(buttonGenerateRandomAddress));
        buttonGenerateRandomAddress.click();
        return new YopmailGeneratedPage(driver);
    }
}
