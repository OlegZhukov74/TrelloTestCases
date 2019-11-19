package system;

import org.openqa.selenium.WebDriver;

public class DriverHolder {
    private ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();

    private static DriverHolder instance = null;

    private DriverHolder() {
    }

    public static DriverHolder getInstance() {
        if (instance == null) {
            instance = new DriverHolder();
        }
        return instance;
    }

    public final void setDriver(WebDriver driver) {
        webDriver.set(driver);
    }

    public static WebDriver getWebDriver() {
        return DriverHolder.getInstance().webDriver.get();
    }

}
