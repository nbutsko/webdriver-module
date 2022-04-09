package hardcore.test;

import hardcore.page.google_cloud.GoogleCloudCalculatorPage;
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
    private YopmailGeneratedPage generatedPage;
    //private YopmailInboxPage yopmailInboxPage;

    double amountOfRentFromGoogleCloudPage;
    double amountOfRentFromYopMailPage;

    private String searchTerm = "Google Cloud Platform Pricing Calculator";

    private String operatingSystem = "Free: Debian, CentOS, CoreOS, Ubuntu or BYOL (Bring Your Own License)";
    private String machineClass = "Regular";
    private String machineType = "n1-standard-8 (vCPUs: 8, RAM: 30GB)";
    private String gpuType = "NVIDIA Tesla P100";
    private String localSSD = "2x375 GB";
    private String datacenterLocation = "Frankfurt (europe-west3)";
    private String committedUsage = "1 Year";

    @BeforeTest(alwaysRun = true)
    public void browserSetup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @BeforeClass
    public void createCalculatorPage() {
        //calculatorPage = new GoogleCloudHomePage(driver)
        //        .openPage()
        //        .searchForTerms(searchTerm)
        //        .openCalculator()
        calculatorPage = new GoogleCloudCalculatorPage(driver)
                .openPage()
                .selectComputeEngineSection()
                .inputNumberOfInstances("4")
                .selectOperatingSystem(operatingSystem)
                .selectMachineClass(machineClass)
                .selectSeries("N1")
                .selectMachineType(machineType)
                .checkAddGPU()
                .selectTypeGPU(gpuType)
                .selectNumberGPU("1")
                .selectLocalSSD(localSSD)
                .selectDatacenterLocation(datacenterLocation)
                .selectCommittedUsage(committedUsage)
                .clickAddToEstimateButton();

        amountOfRentFromGoogleCloudPage = calculatorPage.getAmountOfRentPerMonthFromGoogleCloudPage();
        System.out.println("amout from google "+amountOfRentFromGoogleCloudPage);

        String googleCloudWindow = driver.getWindowHandle();

        driver.switchTo().newWindow(WindowType.TAB);

        generatedPage = new YopmailHomePage(driver)
                .openPage()
                .generateRandomEmail();

        String emailToSend = generatedPage.getGeneratedAddress();

        System.out.println("email to send " + emailToSend);

        String yopMailWindow = driver.getWindowHandle();

        for (String windowHandle : driver.getWindowHandles()) {
            if(!yopMailWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        //driver.navigate().to(googleCloudWindow);
        calculatorPage.sendEmailWithEstimate(emailToSend);

        for (String windowHandle : driver.getWindowHandles()) {
            if(!googleCloudWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        //driver.navigate().to(yopMailWindow);
        YopmailInboxPage yopmailInboxPage = generatedPage.logInToGeneratedMail()
                .refreshPage();

        amountOfRentFromYopMailPage = yopmailInboxPage.getAmountOfRentPerMonthFromGoogleCloudPage();


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
