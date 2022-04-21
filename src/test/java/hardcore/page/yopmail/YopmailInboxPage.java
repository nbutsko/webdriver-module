package hardcore.page.yopmail;

import hardcore.page.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class YopmailInboxPage extends AbstractPage {

    @FindBy(xpath = "//iframe[@id='ifmail']")
    private WebElement frameMail;

    @FindBy(xpath = "//h3[contains(text(), 'USD')]")
    private WebElement totalEstimatedCost;

    public YopmailInboxPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected AbstractPage openPage() {
        return null;
    }

    public YopmailInboxPage refreshPage() {
        driver.navigate().refresh();
        return this;
    }

    public double getAmountOfRentPerMonthFromMailPage() {
        driver.switchTo().frame(frameMail);
        String totalCostFromEmail = totalEstimatedCost.getText();
        int startIndex = totalCostFromEmail.indexOf("USD");
        String amountOfRent = totalCostFromEmail.substring(startIndex + 3).trim().replaceAll(",", "");
        return Double.parseDouble(amountOfRent);
    }
}
