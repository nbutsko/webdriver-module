package bring_it_on.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;

public class PastebinHomePage extends AbstractPage{

    @FindBy(xpath = "//textarea[@id='postform-text']")
    private WebElement newPasteInputField;

    @FindBy(xpath = "//div[contains(@class, 'postform-format')]//*[@role='combobox']")
    private WebElement syntaxHighlightingSelect;

    @FindBy(xpath = "//div[contains(@class, 'postform-expiration')]//*[@role='combobox']")
    private WebElement pasteExpirationSelect;

    @FindBy(id = "postform-name")
    private WebElement pasteNameInputFiled;

    @FindBy(xpath = "//button[contains(text(),'Create New Paste')]")
    private WebElement createNewPasteButton;

    public PastebinHomePage(WebDriver driver) {
        super(driver);
    }

    public PastebinHomePage openPage() {
        String homepageUrl = "https://pastebin.com/";
        driver.get(homepageUrl);
        return this;
    }

    public PastebinHomePage inputNewPasteToTextarea(String text) {
        newPasteInputField.sendKeys(text);
        return this;
    }

    public PastebinHomePage chooseSyntaxHighlighting(String syntax) {
        syntaxHighlightingSelect.click();
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
        //        .until(ExpectedConditions.attributeContains(syntaxHighlightingSelect, "aria-owns", "select2-postform-format-results"));
                .until(ExpectedConditions.visibilityOf(syntaxHighlightingSelect));
        By syntaxLocator = By.xpath(String.format("//*[@role='group']//*[contains(text(),'%s')]", syntax));
        driver.findElement(syntaxLocator).click();

        //WebElement syntaxSelector = driver.findElement(By.xpath(String.format("//*[@role='group']//*[contains(text(),'%s')]", syntax)));
        //syntaxSelector.click();
        return this;
    }

    public PastebinHomePage choosePasteExpiration(String expiration) {
        pasteExpirationSelect.click();
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
        //        .until(ExpectedConditions.attributeContains(pasteExpirationSelect, "aria-owns", "select2-postform-expiration-results"));
                .until(ExpectedConditions.visibilityOf(pasteExpirationSelect));
        By syntaxLocator = By.xpath(String.format("//*[@role='listbox']/*[contains(text(),'%s')]", expiration));
        driver.findElement(syntaxLocator).click();
        //WebElement pasteExpirationSelector = driver.findElement(By.xpath(String.format("//*[@role='listbox']/*[contains(text(),'%s')]", expiration)));
        //pasteExpirationSelector.click();
        return this;
    }

    public PastebinHomePage inputPasteName(String name) {
        pasteNameInputFiled.sendKeys(name);
        return this;
    }

    public PastebinCreatedPastePage submitNewPasteCreation() {
        createNewPasteButton.click();
//        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
//                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//img[@alt='Guest User']")));

        return new PastebinCreatedPastePage(driver);
    }

}
