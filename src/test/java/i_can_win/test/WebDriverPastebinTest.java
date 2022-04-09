package i_can_win.test;

import i_can_win.page.PastebinHomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class WebDriverPastebinTest {
    private WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void browserSetup() {
        driver = new ChromeDriver();
    }

    @Test(description = "automation of new paste creation")
    public void testCreationNewPaste(){
        new PastebinHomePage(driver)
                .openPage()
                .inputNewPasteToTextarea("Hello from WebDriver")
                .choosePasteExpiration("10 Minutes")
                .inputPasteName("helloweb")
                .submitNewPasteCreation();
    }

    @AfterMethod(alwaysRun = true)
    public void browserTearDown() {
        driver.quit();
        driver = null;
    }
}
