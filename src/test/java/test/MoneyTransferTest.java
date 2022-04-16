package test;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import data.DataHelper;
import page.DashboardPage;
import page.LoginPageV1;
import page.MoneyTransferPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoneyTransferTest {

    @BeforeEach
    void Preparation() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        var loginPage = new LoginPageV1();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
    }

    @Test
    void shouldTransferMoneyBetweenOwnCardsV1() {
        var dashboardPage = new DashboardPage();
        var showFirstCardBalance = dashboardPage.getFirstCardBalance();
        var showSecondCardBalance = dashboardPage.getSecondCardBalance();
        var moneyTransferPage = dashboardPage.firstCardButton();
        var cardToTransferFrom = DataHelper.getSecondCardInfo();
        int sum = 100;
        moneyTransferPage.transferBetweenCards(Integer.parseInt(String.valueOf(sum)), cardToTransferFrom);

        var expectedFirstCard = showFirstCardBalance + sum;
        var expectedSecondCard = showSecondCardBalance - sum;
        var actualFirstCard = dashboardPage.getFirstCardBalance();
        var actualSecondCard = dashboardPage.getSecondCardBalance();

        assertEquals(expectedFirstCard, actualFirstCard);
        assertEquals(expectedSecondCard, actualSecondCard);

    }

    @Test
    void shouldTransferMoneyBetweenOwnCardsV2() {
        var dashboardPage = new DashboardPage();
        var showFirstCardBalance = dashboardPage.getFirstCardBalance();
        var showSecondCardBalance = dashboardPage.getSecondCardBalance();
        var moneyTransferPage = dashboardPage.secondCardButton();
        var cardToTransferFrom = DataHelper.getFirstCardInfo();
        int sum = 100;
        moneyTransferPage.transferBetweenCards(Integer.parseInt(String.valueOf(sum)), cardToTransferFrom);

        var expectedFirstCard = showFirstCardBalance - sum;
        var expectedSecondCard = showSecondCardBalance + sum;
        var actualFirstCard = dashboardPage.getFirstCardBalance();
        var actualSecondCard = dashboardPage.getSecondCardBalance();

        assertEquals(expectedFirstCard, actualFirstCard);
        assertEquals(expectedSecondCard, actualSecondCard);
    }

    @Test
    void shouldTransferMoneyBetweenOwnCardsV3() {
        var dashboardPage = new DashboardPage();
        var showFirstCardBalance = dashboardPage.getFirstCardBalance();
        var showSecondCardBalance = dashboardPage.getSecondCardBalance();
        var moneyTransferPage = dashboardPage.secondCardButton();
        var cardToTransferFrom = DataHelper.getFirstCardInfo();
        int sum = 20000;
        moneyTransferPage.transferBetweenCards(Integer.parseInt(String.valueOf(sum)), cardToTransferFrom);

        var expectedFirstCard = showFirstCardBalance - sum;
        var expectedSecondCard = showSecondCardBalance + sum;
        var actualFirstCard = dashboardPage.getFirstCardBalance();
        var actualSecondCard = dashboardPage.getSecondCardBalance();

        assertEquals(expectedFirstCard, actualFirstCard);
        assertEquals(expectedSecondCard, actualSecondCard);
    }


}
