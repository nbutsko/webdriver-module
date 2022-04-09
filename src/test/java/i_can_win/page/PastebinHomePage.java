package i_can_win.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PastebinHomePage extends AbstractPage {

    @FindBy(xpath = "//textarea[@id='postform-text']")
    private WebElement newPasteInput;

    @FindBy(xpath = "//div[contains(@class, 'postform-expiration')]//*[@role='combobox']")
    private WebElement pasteExpiration;

    @FindBy(id = "postform-name")
    private WebElement pasteNameInput;

    @FindBy(xpath = "//button[contains(text(),'Create New Paste')]")
    private WebElement createNewPasteButton;

    public PastebinHomePage(WebDriver driver) {
        super(driver);
    }

    public PastebinHomePage openPage() {
        driver.manage().window().maximize();
        String homepageUrl = "https://pastebin.com/";
        driver.get(homepageUrl);
        return this;
    }

    public PastebinHomePage inputNewPasteToTextarea(String text) {
        newPasteInput.sendKeys(text);
        return this;
    }

    public PastebinHomePage choosePasteExpiration(String expiration) {
        pasteExpiration.click();
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOf(pasteExpiration));
        By pasteExpirationLocator = By.xpath(String.format("//*[@role='listbox']/*[contains(text(),'%s')]", expiration));
        driver.findElement(pasteExpirationLocator).click();
        return this;
    }

    public PastebinHomePage inputPasteName(String name) {
        pasteNameInput.sendKeys(name);
        return this;
    }

    public PastebinCreatedPastePage submitNewPasteCreation() {
        createNewPasteButton.click();
        return new PastebinCreatedPastePage(driver);
    }

}
