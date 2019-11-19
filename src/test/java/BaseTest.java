import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import system.DriverHolder;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    private WebDriver driver;
    private DriverHolder driverHolder;

    @BeforeSuite
    public void beforeSuite() {
        WebDriverManager.chromedriver().setup();
        driverHolder = DriverHolder.getInstance();
    }

    @BeforeMethod
    public void setupTest() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driverHolder.setDriver(driver);
    }

    @AfterMethod
    public void afterMethod() {
        if (driver != null) {
            driver.quit();
        }
    }

    @AfterSuite
    public void afterSuite() {
        if (driver != null) {
            driver.quit();
        }
    }

}
