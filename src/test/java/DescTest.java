import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.BoardsPage;
import pages.DescPage;

import static utils.PageUtils.refresh;
import static utils.TestUtils.generateRandomString;
import static utils.TestUtils.generateRandomStringWithPrefix;
import static utils.UserUtils.loginSimpleUser;

public class DescTest extends BaseTest {

    @DataProvider(name = "listNamesLength", parallel = false)
    public Object[][] listNamesLength() {
        return new Object[][]{
                {5},
                {512}
        };
    }
    @DataProvider(name = "cardNamesLength", parallel = false)
    public Object[][] cardNamesLength() {
        return new Object[][]{
                {5},
                {10000}
        };
    }


    @Test(dataProvider = "listNamesLength")
    public void testListNamesLength(int nameLength) {
        BoardsPage boardsPage = loginSimpleUser();
        DescPage descPage = boardsPage.openDescByName("testDesc1");
        descPage.archiveAllLists();
        String name = generateRandomString(nameLength);
        descPage.addList(name);
        refresh();
        descPage.checkListExistByName(name);
    }

    @Test
    public void testCanAddFewLists() {
        BoardsPage boardsPage = loginSimpleUser();
        DescPage descPage = boardsPage.openDescByName("testDesc1");
        descPage.archiveAllLists();
        int countOfLists = 3;
        for (int i = 0; i < countOfLists; i++) {
            String listName = generateRandomStringWithPrefix("list_", 5);
            descPage.addList(listName);
            refresh();
        }
        descPage.checkListCount(countOfLists);
    }

    @Test(dataProvider = "cardNamesLength")
    public void testCardNamesLength(int nameLength) {
        BoardsPage boardsPage = loginSimpleUser();
        DescPage descPage = boardsPage.openDescByName("testDesc1");
        descPage.archiveAllLists();
        String listName = generateRandomStringWithPrefix("list_", 5);
        descPage.addList(listName);
        String cardname = generateRandomString(nameLength);
        descPage.addCardToList(listName, cardname);
        refresh();
        descPage.checkCardText(listName, cardname);
    }

    @Test
    public void testCanAddFewCardsToList() {
        BoardsPage boardsPage = loginSimpleUser();
        DescPage descPage = boardsPage.openDescByName("testDesc1");
        descPage.archiveAllLists();
        String listName = generateRandomStringWithPrefix("list_", 5);
        descPage.addList(listName);

        int cardsCount = 3;
        for (int i = 0; i < cardsCount; i++) {
            String cardname = generateRandomStringWithPrefix("card_", 5);
            descPage.addCardToList(listName, cardname);
            refresh();
            descPage.checkCardText(listName, cardname);
        }
        descPage.checkCardsCountInList(listName, 3);


    }

    @Test
    public void testArchiveList() {
        BoardsPage boardsPage = loginSimpleUser();
        DescPage descPage = boardsPage.openDescByName("testDesc1");
        descPage.archiveAllLists();
        String listName = generateRandomStringWithPrefix("list_", 5);
        descPage.addList(listName);
        refresh();
        descPage.archiveList(listName);
        refresh();
        descPage.checkListNotExistByName(listName);
    }

}
