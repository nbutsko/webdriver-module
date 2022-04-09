package bring_it_on.test;

import bring_it_on.page.PastebinCreatedPastePage;
import bring_it_on.page.PastebinHomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class WebDriverPastebinTest {
    private WebDriver driver;
    private String inputPasteCode = "git config --global user.name  \"New Sheriff in Town\"\n" +
            "git reset $(git commit-tree HEAD^{tree} -m \"Legacy code\")\n" +
            "git push origin master --force";
    private String inputPasteName = "how to gain dominance among developers";
    private PastebinCreatedPastePage createdPastePage;

    @BeforeTest(alwaysRun = true)
    public void browserSetup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @BeforeClass
    public void createNewPaste() {
        createdPastePage = new PastebinHomePage(driver)
                .openPage()
                .inputNewPasteToTextarea(inputPasteCode)
                .chooseSyntaxHighlighting("Bash")
                .choosePasteExpiration("10 Minutes")
                .inputPasteName(inputPasteName)
                .submitNewPasteCreation();
    }

    @Test
    public void browserPageTitleCorrespondsPasteName() {
        String browserPageTitle = createdPastePage.findPageTitle();
        Assert.assertTrue(browserPageTitle.contains(inputPasteName));
    }

    @Test
    public void checkHighlightOfBashSyntax() {
        String actualBashColour = createdPastePage.findColourOfBashSyntax();
        String expectedBashColour = "rgba(194, 12, 185, 1)";
        Assert.assertEquals(expectedBashColour, actualBashColour);
    }

    @Test
    public void checkEnterAndOutputCode() {
        String actualCode = createdPastePage.getCodeFromPaste();
        Assert.assertEquals(inputPasteCode, actualCode);
    }

    @AfterTest(alwaysRun = true)
    public void browserTearDown() {
        driver.quit();
        driver = null;
    }
}
