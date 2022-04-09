package hurt_me_plenty.test;

import hurt_me_plenty.page.GoogleCloudCalculatorPage;
import hurt_me_plenty.page.GoogleCloudEstimatePage;
import hurt_me_plenty.page.GoogleCloudHomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Locale;


public class WebDriverGoogleCloudTest {
    private WebDriver driver;
    private GoogleCloudEstimatePage calculatorPage;
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
    public void createCalculatorPage(){
        calculatorPage = new GoogleCloudHomePage(driver)
                .openPage()
                .searchForTerms(searchTerm)
                .openCalculator()
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
    }

    @Test
    public void checkRegion(){
        String[] selectedRegion = datacenterLocation.split(" ");
        String actualRegion = calculatorPage.getRegionOnEstimate();
        Assert.assertTrue(actualRegion.contains(selectedRegion[0]));
    }

    @Test
    public void checkCommitmentTerm(){
        String actualCommitmentTerm = calculatorPage.getCommitmentTermOnEstimate();
        Assert.assertTrue(actualCommitmentTerm.contains(committedUsage));
    }

    @Test
    public void checkVMClass(){
        String actualVMClass = calculatorPage.getVMClassOnEstimate();
        Assert.assertTrue(actualVMClass.contains(machineClass.toLowerCase()));
    }

    @Test
    public void checkInstanceType(){
        String[] selectedInstanceType = machineType.split(" ");
        String actualInstanceType = calculatorPage.getInstanceTypeOnEstimate();
        Assert.assertTrue(actualInstanceType.contains(selectedInstanceType[0]));
    }

    @Test
    public void checkLocalSSd(){
        String actualLocalSSD = calculatorPage.getLocalSSDOnEstimate();
        String[] selectedSSD = localSSD.split(" ");
        Assert.assertTrue(actualLocalSSD.contains(selectedSSD[0]));
    }

    @Test
    public void amountOfRentPerMonthIsSame(){
        Assert.assertEquals(calculatorPage.getAmountOfRentPerMonth(),4026.13, 0.01);
    }

    @AfterTest(alwaysRun = true)
    public void browserTearDown() {
        driver.quit();
        driver = null;
    }
}
