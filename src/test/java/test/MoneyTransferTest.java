package test;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import data.DataHelper;
import page.DashboardPage;
import page.LoginPageV1;

import static com.codeborne.selenide.Selenide.open;

public class MoneyTransferTest {

    @BeforeEach
    void Preparation() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");

    }

    @Test
    void shouldTransferMoneyBetweenOwnCardsV1() {
        var loginPage = new LoginPageV1();
        var authInfo = DataHelper.getAuthInfo();
        var CardInfo = DataHelper.CardInfo();

        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        dashboardPage.getCardBalance(CardInfo);

    }
}
