package hardcore.page.yopmail;

import hardcore.page.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class YopmailGeneratedPage extends AbstractPage {

    @FindBy(id = "egen")
    WebElement generatedAddress;

    @FindBy(xpath = "//button[@onclick='egengo();']")
    WebElement buttonCheckInbox;

    public YopmailGeneratedPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected AbstractPage openPage() {
        return null;
    }

    public String getGeneratedAddress(){
        //System.out.println(generatedAddress.getText());
        return generatedAddress.getText();
    }

    public YopmailInboxPage logInToGeneratedMail(){
        buttonCheckInbox.click();
        return new YopmailInboxPage(driver);
    }

}
