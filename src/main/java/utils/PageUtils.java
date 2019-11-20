package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.BasePage;
import system.DriverHolder;

import static system.DriverHolder.getWebDriver;

public class PageUtils {

    public static <TPage extends BasePage> TPage page(Class<TPage> pageClass) {
        try {
            WebDriver driver = getWebDriver();
            return PageFactory.initElements(driver, pageClass);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static <TPage extends BasePage> TPage page(String url, Class<TPage> pageClass) {
        try {
            WebDriver driver = getWebDriver();
            driver.get(url);
            waitForUrlContains(url); // TODO поидее надо убрать отсюда проверку юрла, тк может быть редирект на другой похожий юрл и это норм, чекать юрлы в самих тестах гже это требуется
            return PageFactory.initElements(driver, pageClass);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static void getUrl(String url) {
        getWebDriver().get(url);
    }

    public static void waitForUrlContains(String expectedString, int timeout) {
        WebDriver driver = getWebDriver();
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        ExpectedCondition<Boolean> urlIsCorrect = arg0 -> driver.getCurrentUrl().contains(expectedString);
        wait.until(urlIsCorrect);
        waitForPageLoadComplete(timeout);
    }

    public static void waitForUrlContains(String expectedString) {
        waitForUrlContains(expectedString, 40);
    }

    public static void waitForPageLoadComplete(int specifiedTimeout) {
        Wait<WebDriver> wait = new WebDriverWait(getWebDriver(), specifiedTimeout);
        wait.until(driver1 -> String
                .valueOf(((JavascriptExecutor) driver1).executeScript("return document.readyState"))
                .equals("complete"));
    }

    public static void refresh() {
        getWebDriver().navigate().refresh();
        waitForPageLoadComplete(10);
    }

    public static void sleep(int millisec) {
        try {
            Thread.sleep(millisec);
        } catch (InterruptedException ie) {
        }
    }

    public static boolean isElementPresent(WebElement element) { //TODO можно улучшить метод чтобы не ждал implicitlyWait(10, TimeUnit.SECONDS); 10 секунд
        try {
            return element.isDisplayed();
        }
        catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }
}
