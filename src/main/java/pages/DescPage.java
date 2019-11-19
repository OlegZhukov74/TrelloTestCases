package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;

import static system.DriverHolder.getWebDriver;
import static utils.PageUtils.isElementPresent;

public class DescPage extends BasePage {
    @FindBy(xpath = "//*[@id='board']//*[@class='placeholder']")
    private WebElement addListBtn;
    @FindBy(xpath = "//input[@class='list-name-input']")
    private WebElement listNameInput;
    @FindBy(xpath = "//form//*[@type='submit']")
    private WebElement submitAddListBtn;
    @FindBy(xpath = "//textarea[contains(@class, 'list-header-name')]")
    private List<WebElement> listNames;
    @FindBy(xpath = "//a[contains(@class, 'close-list')]")
    private WebElement archiveListBtn;
    @FindBy(xpath = "//a[contains(@class, 'open-list-menu')]")
    private WebElement listMenu;

    public void addList(String name) {
        addListBtn.click();
        listNameInput.sendKeys(name);
        submitAddListBtn.click();
    }

    public void archiveList(String name) {
        getWebDriver().findElement(By.xpath(String.format("//div[contains(@class, 'list-header')][.//textarea[text() = '%s']]//a[contains(@class, 'list-header-extras-menu')]", name))).click();
        archiveListBtn.click();
    }

    public void archiveAllLists() {
        while (isElementPresent(listMenu)) {
            listMenu.click();
            archiveListBtn.click();
        }
    }

    public void checkListExistByName(String name) {
        Assert.assertTrue(getWebDriver().findElements(By.xpath(String.format("//textarea[contains(@class, 'list-header-name') and contains(text(), '%s')]", name))).size() > 0);
    }

    public void checkListCount(int count) {
        Assert.assertTrue(listNames.size() == count);
    }
}
