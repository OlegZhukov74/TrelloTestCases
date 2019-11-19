import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.BoardsPage;
import pages.DescPage;

import static utils.PageUtils.refresh;
import static utils.TestUtils.generateRandomString;
import static utils.TestUtils.generateRandomStringWithPrefix;
import static utils.UserUtils.loginSimpleUser;

public class DescTest extends BaseTest {

    @DataProvider(name = "listsCount", parallel = true)
    public Object[][] listsCount() {
        return new Object[][]{
                {3}
        };
    }
    @DataProvider(name = "listNamesLength", parallel = true)
    public Object[][] listNamesLength() {
        return new Object[][]{
                {5},
                {512}
        };
    }


    @Test(dataProvider = "listNamesLength")
    public void testListNamesLength(int nameLength) {
        BoardsPage boardsPage = loginSimpleUser();
        DescPage descPage = boardsPage.openDescByName("testDesc1"); // TODO выташи название доски в конфиг
        descPage.archiveAllLists();
        String name = generateRandomString(nameLength);
        descPage.addList(name);
        refresh();
        descPage.checkListExistByName(name);
    }

    @Test(dataProvider = "listsCount")
    public void testCanAddFewLists(int countOfLists) {
        BoardsPage boardsPage = loginSimpleUser();
        DescPage descPage = boardsPage.openDescByName("testDesc1"); // TODO выташи название доски в конфиг
        descPage.archiveAllLists();
        for (int i = 0; i < countOfLists; i++) {
            String listName = generateRandomStringWithPrefix("list_", 5);
            descPage.addList(listName);
            refresh();
        }
        descPage.checkListCount(countOfLists);
    }

}
