package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import static system.DriverHolder.getWebDriver;
import static utils.PageUtils.page;
import static utils.PageUtils.waitForUrlContains;

public class BoardsPage extends BasePage {
    @FindBy(xpath = "//*[@class='board-tile']")
    private WebElement boardTiles;
    @FindBy(xpath = "//*[@data-test-id='header-boards-menu-button']")
    private WebElement headerMenuBoardsBtn;
    @FindBy(xpath = "//*[@data-test-id='templates']")
    private WebElement templatesBtn;

    public DescPage openDescByName(String descName) {
        WebElement boardTile = getWebDriver().findElement(By.xpath(String.format("//*[@class='board-tile'][.//*[@title='%s']]", descName)));
        boardTile.click();
        waitForUrlContains(descName.toLowerCase());
        DescPage descPage = page(DescPage.class);
        return descPage;
    }

    public void checkBoardsPage() {
        Assert.assertTrue(headerMenuBoardsBtn.isDisplayed());
        Assert.assertTrue(templatesBtn.isDisplayed());
    }

}
