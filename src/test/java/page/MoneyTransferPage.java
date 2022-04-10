package page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.selector.ByText;
import data.DataHelper;
import org.w3c.dom.Text;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;


public class MoneyTransferPage {
    private SelenideElement heading = $(withText("Пополнение карты"));
    private SelenideElement amountToTransfer = $("[data-test-id=amount] input");
    private SelenideElement cardFrom = $("[data-test-id=from] input");
    private SelenideElement buttonToTransfer = $("[data-test-id=action-transfer]");

    public MoneyTransferPage() {
        heading.shouldBe(visible);
    }

    public DashboardPage transferBetweenCards(int sum, DataHelper.CardNumber cardNumber) {
        amountToTransfer.setValue(String.valueOf(sum));
        cardFrom.setValue(String.valueOf(cardNumber));
        buttonToTransfer.click();
        return new DashboardPage();
    }

}

