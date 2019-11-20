package utils;

import pages.BoardsPage;
import pages.LoginPage;

import static utils.PageUtils.page;

public class UserUtils {

    public static BoardsPage loginSimpleUser() {
        LoginPage loginPage = page("https://trello.com" + LoginPage.URL, LoginPage.class);
        BoardsPage boardsPage = loginPage.loginWithAtlassian("umacte556@gmail.com", "qweasd74");
        return boardsPage;
    }
}
