package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import modals.ArticlesModal;
import org.openqa.selenium.By;
import java.util.List;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;

public class ClientInformationPage {
    public ElementsCollection
            userType = $(".card-body").$$(".tree-main-node");

    public List<SelenideElement> advertisers = $$(By.xpath("//*[@id=\"mainContainer\"]/div[2]/div[1]/div/div[2]/div[1]/div/div"));
    public List<SelenideElement> publishers = $$(By.xpath("//*[@id=\"mainContainer\"]/div[2]/div[1]/div/div[2]/div[2]/div/div"));
    public List<SelenideElement> topLevelClients = $$(By.xpath("//*[@id=\"mainContainer\"]/div[2]/div[1]/div/div[2]/div[3]/div/div"));

    public SelenideElement
            articlesHeader = $(".card-header"),
            advertisersButton = userType.get(0),
            publishersButton = userType.get(1),
            topLevelClientsButton = userType.last(),
            userAvatar = $("#avatar"),
            loader = $("#loader");

    public void clickOnAdvertisersButton() {
        advertisersButton.click();
    }

    public ArticlesModal clickOnPublishersButton() {
        publishersButton.click();
        return new ArticlesModal();
    }

    public void clickOnTopLevelClientsButton() {
        topLevelClientsButton.click();
    }

    public ArticlesModal clickOnOneOfAdvertisers(int number) {
        advertisersButton.$$(".sub-tree-element").get(number).click();
        return new ArticlesModal();
    }

    public void findAdvertiser(String name) {
        advertisersButton.$(byText(name)).shouldBe(visible);
    }

    public void findPublishers(String name) {
        publishersButton.$(byText(name)).shouldBe(visible);
    }

    public void findTopClients(String name) {
        topLevelClientsButton.$(byText(name)).shouldBe(visible);
    }

    public ArticlesModal clickOnClientButton() {
        clickOnTopLevelClientsButton();
        return new ArticlesModal();
    }

    public ClientProfilePage clickOnUserAvatar() {
        userAvatar.click();
        return new ClientProfilePage();
    }

    public ArticlesModal clickOnOneOfPublishers(int number) {
        publishersButton.$$(".sub-tree-element").get(number).click();
        return new ArticlesModal();
    }

    public ArticlesModal clickOnOneOfTopClients(int number) {
        topLevelClientsButton.$$(".sub-tree-element").get(number).click();
        return new ArticlesModal();
    }

}
