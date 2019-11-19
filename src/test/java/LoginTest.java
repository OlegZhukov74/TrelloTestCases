import org.testng.annotations.Test;
import pages.BoardsPage;

import static utils.UserUtils.loginSimpleUser;

public class LoginTest extends BaseTest {

    @Test
    public void loginTest() {
        BoardsPage boardsPage = loginSimpleUser();
        boardsPage.checkBoardsPage();
    }

}
