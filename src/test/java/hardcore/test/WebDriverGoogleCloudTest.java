package hardcore.test;

import hardcore.page.google_cloud.GoogleCloudEstimatePage;
import hardcore.page.google_cloud.GoogleCloudHomePage;
import hardcore.page.yopmail.YopmailGeneratedPage;
import hardcore.page.yopmail.YopmailHomePage;
import hardcore.page.yopmail.YopmailInboxPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class WebDriverGoogleCloudTest {
    private WebDriver driver;
    private GoogleCloudEstimatePage calculatorPage;
    private YopmailGeneratedPage yopmailGeneratedPage;

    private double amountOfRentFromGoogleCloudPage;
    private double amountOfRentFromYopMailPage;

    private String searchTerm = "Google Cloud Platform Pricing Calculator";

    private String numberOfInstances = "4";
    private String operatingSystem = "Free: Debian, CentOS, CoreOS, Ubuntu or BYOL (Bring Your Own License)";
    private String machineClass = "Regular";
    private String series = "N1";
    private String machineType = "n1-standard-8 (vCPUs: 8, RAM: 30GB)";
    private String gpuType = "NVIDIA Tesla P100";
    private String gpuNumber = "1";
    private String localSSD = "2x375 GB";
    private String datacenterLocation = "Frankfurt (europe-west3)";
    private String committedUsage = "1 Year";

    @BeforeTest(alwaysRun = true)
    public void browserSetup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @BeforeClass
    public void calculateCostAndSendEmailToTheGeneratedAddress(){
        calculatorPage = new GoogleCloudHomePage(driver)
                .openPage()
                .clickButtonAcceptCookies()
                .searchForTerms(searchTerm)
                .openCalculator()
                .selectComputeEngineSection()
                .inputNumberOfInstances(numberOfInstances)
                .selectOperatingSystem(operatingSystem)
                .selectMachineClass(machineClass)
                .selectSeries(series)
                .selectMachineType(machineType)
                .checkAddGPU()
                .selectTypeGPU(gpuType)
                .selectNumberGPU(gpuNumber)
                .selectLocalSSD(localSSD)
                .selectDatacenterLocation(datacenterLocation)
                .selectCommittedUsage(committedUsage)
                .clickAddToEstimateButton();

        amountOfRentFromGoogleCloudPage = calculatorPage.getAmountOfRentPerMonthFromGoogleCloudPage();
        String googleCloudWindow = driver.getWindowHandle();
        driver.switchTo().defaultContent();

        driver.switchTo().newWindow(WindowType.TAB);
        yopmailGeneratedPage = new YopmailHomePage(driver)
                .openPage()
                .clickButtonAcceptCookies()
                .clickButtonGenerateRandomAddress();
        String emailToSend = yopmailGeneratedPage.getGeneratedAddress();
        String yopMailWindow = driver.getWindowHandle();

        driver.switchTo().window(googleCloudWindow);
        calculatorPage.clickButtonEmailEstimate()
                .clickButtonSendEmailWithEstimate(emailToSend);

        driver.switchTo().window(yopMailWindow);
        YopmailInboxPage yopmailInboxPage = yopmailGeneratedPage.logInToGeneratedMail()
                .refreshPage();
        amountOfRentFromYopMailPage = yopmailInboxPage.getAmountOfRentPerMonthFromMailPage();
    }

    @Test
    public void amountOfRentPerMonthIsSame() {
        Assert.assertEquals(amountOfRentFromYopMailPage, amountOfRentFromGoogleCloudPage, 0.01);
    }

    @AfterTest(alwaysRun = true)
    public void browserTearDown() {
        driver.quit();
        driver = null;
    }
}
