package bring_it_on.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class PastebinCreatedPastePage extends AbstractPage {

    @FindBy(xpath = "//*[@class='bash']//*[@class='kw2']")
    private WebElement bashSyntax;

    @FindBy(xpath = "//*[@class='bash']//*[@class='de1']")
    private List<WebElement> code;

    public PastebinCreatedPastePage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected AbstractPage openPage() {
        throw new RuntimeException("You couldn't open this page without creation a new paste!");
    }

    public String findPageTitle() {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.titleContains(" - Pastebin.com"));
        return driver.getTitle();
    }

    public String findColourOfBashSyntax() {
        //new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
        //        .until(ExpectedConditions.titleContains(" - Pastebin.com"));
        //WebElement bashSyntax = driver.findElement(By.xpath("//*[@class='bash']//*[@class='kw2']"));
        return bashSyntax.getCssValue("color");
    }

    public String getCodeFromPaste() {
        //new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
        //        .until(ExpectedConditions.titleContains(" - Pastebin.com"));
        //List<WebElement> code = driver.findElements(By.xpath("//*[@class='bash']//*[@class='de1']"));
        StringBuilder result = new StringBuilder();
        for (WebElement lines : code) {
            result.append(lines.getText()).append("\n");
        }
        return result.substring(0, result.length()-1);
    }
}
