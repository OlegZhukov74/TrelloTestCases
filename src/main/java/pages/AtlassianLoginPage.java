package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static utils.PageUtils.page;
import static utils.PageUtils.waitForUrlContains;

public class AtlassianLoginPage extends BasePage {

    @FindBy(xpath = "//*[@id='login-submit']")
    private WebElement continueLoginBtn;
    @FindBy(xpath = "//*[@id='username']")
    private WebElement emailInput;
    @FindBy(xpath = "//*[@id='password']")
    private WebElement passwordInput;
    @FindBy(xpath = "//*[@id='login-submit']")
    private WebElement loginSubmitBtn;

    public BoardsPage login(String email, String password) {
        emailInput.clear();
        emailInput.sendKeys(email);
        continueLoginBtn.click();
        passwordInput.sendKeys(password);
        loginSubmitBtn.click();
        waitForUrlContains("/boards");
        return page(BoardsPage.class);
    }




}
