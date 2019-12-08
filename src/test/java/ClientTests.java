import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import owner.TestData;
import pages.AuthenticationPage;
import pages.ClientInformationPage;

import static org.assertj.core.api.Assertions.assertThat;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.clearBrowserCookies;

public class ClientTests extends BaseTest {
    private AuthenticationPage authenticationPage;
    private ClientInformationPage clientInformationPage;

    private final TestData testData = super.getTestData();

    @BeforeClass
    public void beforeClass() {
        authenticationPage = new AuthenticationPage();
        authenticationPage.login(testData.getUserValidEmail(), testData.getUserValidPassword());
    }

    @AfterClass
    public void afterClass() {
        clearBrowserCookies();
    }

    @BeforeMethod
    public void beforeMethod() {
        clientInformationPage = new ClientInformationPage();
        clientInformationPage.advertisersButton.shouldBe(visible);
    }

    @Test
    public void displayClientPageElement() {
        clientInformationPage.advertisersButton.shouldBe(visible);
        clientInformationPage.publishersButton.shouldBe(visible);
        clientInformationPage.topLevelClientsButton.shouldBe(visible);

        clientInformationPage.clickOnAdvertisersButton();
        clientInformationPage.findAdvertiser("Test Advertiser");
        clientInformationPage.findAdvertiser("Adidas");
        assertThat(clientInformationPage.advertisers.size()).isEqualTo(2);

        clientInformationPage.clickOnPublishersButton();
        clientInformationPage.findPublishers("Youtube");
        clientInformationPage.findPublishers("Instagram");
        assertThat(clientInformationPage.publishers.size()).isEqualTo(2);

        clientInformationPage.clickOnClientButton();
        clientInformationPage.findTopClients("Jon Snow");
        clientInformationPage.findTopClients("Artur Fleck");
        assertThat(clientInformationPage.topLevelClients.size()).isEqualTo(10);
    }

}
