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
        waitForUrlContains("atlassian.com/login");
        AtlassianLoginPage atlassianLoginPage = page(AtlassianLoginPage.class);
        BoardsPage boardsPage = atlassianLoginPage.login(email, password);
        return boardsPage;
    }
}
