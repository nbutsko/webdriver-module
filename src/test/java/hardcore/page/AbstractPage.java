package hardcore.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class AbstractPage {

    protected WebDriver driver;

    protected abstract AbstractPage openPage() throws InterruptedException;
    protected final int WAIT_TIMEOUT_SECONDS = 10;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
