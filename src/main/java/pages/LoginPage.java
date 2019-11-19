package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import static utils.PageUtils.*;

public class LoginPage extends BasePage {
    public static final String URL = "/login";

    @FindBy(xpath = "//*[@id='user']")
    private WebElement emailInput;
    @FindBy(xpath = "//*[@id='password']")
    private WebElement passwordInput;
    @FindBy(xpath = "//*[@id='login']")
    private WebElement loginSubmitBtn;

    public BoardsPage loginWithAtlassian(String email, String password) {
        emailInput.sendKeys(email);
        if (passwordInput.isDisplayed()) {
            passwordInput.sendKeys(password);
        } else {
            Assert.assertTrue(loginSubmitBtn.getAttribute("value").contains("Atlassian"));
        }
        loginSubmitBtn.click();
        waitForUrlContains("atlassian.com/login"); // TODO сделать цикл кликов на логин, после того как запустищь в дженкинсе каждые 5 минут и убедишься что проблема реально есть
        AtlassianLoginPage atlassianLoginPage = page(AtlassianLoginPage.class);
        BoardsPage boardsPage = atlassianLoginPage.login(email, password);
        return boardsPage;
    }
}
