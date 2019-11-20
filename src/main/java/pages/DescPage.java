package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;

import static system.DriverHolder.getWebDriver;
import static utils.PageUtils.isElementPresent;
import static utils.TestUtils.addTextToClipboard;

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

    public void checkListNotExistByName(String name) {
        Assert.assertTrue(getWebDriver().findElements(By.xpath(String.format("//textarea[contains(@class, 'list-header-name') and contains(text(), '%s')]", name))).size() == 0);
        // TODO можно переработать метод чтобы не ждал по 10 секунд
    }

    public void checkListCount(int count) {
        Assert.assertTrue(listNames.size() == count);
    }

    public void addCardToList(String listName, String cardName) {
        getWebDriver().findElement(By.xpath(String.format("//*[@class='list js-list-content'][.//*[contains(@class, 'list-header') and text()='%s']]//a[contains(@class, 'open-card-composer')]", listName))).click();
        if (cardName.length() > 100) {
            addTextToClipboard(cardName);
            WebElement textarea = getWebDriver().findElement(By.xpath(String.format("//*[@class='list js-list-content'][.//*[contains(@class, 'list-header') and text()='%s']]//textarea[contains(@class, 'list-card-composer-textarea')]", listName)));
            textarea.click();
            ((JavascriptExecutor)getWebDriver()).executeScript("arguments[0].value = arguments[1];", textarea,
                    cardName);
        } else {
            getWebDriver().findElement(By.xpath(String.format("//*[@class='list js-list-content'][.//*[contains(@class, 'list-header') and text()='%s']]//textarea[contains(@class, 'list-card-composer-textarea')]", listName))).sendKeys(cardName);
        }
        getWebDriver().findElement(By.xpath(String.format("//*[@class='list js-list-content'][.//*[contains(@class, 'list-header') and text()='%s']]//input[contains(@class, 'confirm')]", listName))).click();
    }

    public void checkCardText(String listName, String cardName) {
        getWebDriver().findElement(By.xpath(String.format("//*[@class='list js-list-content'][.//*[contains(@class, 'list-header') and text()='%s']]//*[contains(@class, 'list-card-title') and contains(text(), '%s')]", listName, cardName))).isDisplayed();
    }

    public void checkCardsCountInList(String listName, int count) {
        List<WebElement> cards = getWebDriver().findElements(By.xpath(String.format("//*[@class='list js-list-content'][.//*[contains(@class, 'list-header') and text()='%s']]//a[contains(@class, 'list-card')]", listName)));
        Assert.assertTrue(cards.size() == count);
    }
}
