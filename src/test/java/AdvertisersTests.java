import com.codeborne.selenide.Selenide;
import modals.AdvertisersModal;
import org.openqa.selenium.Keys;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import owner.TestData;
import pages.AuthenticationPage;
import pages.ClientInformationPage;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class AdvertisersTests extends BaseTest {
    private AuthenticationPage authenticationPage;
    private ClientInformationPage clientInformationPage;
    private AdvertisersModal advertisersModal;

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
        clientInformationPage.clickOnAdvertisersButton();
    }

    @Test
    public void displayAdvertisersElements() {
        advertisersModal = clientInformationPage.clickOnOneOfAdvertisers(0);
        advertisersModal.advertiserHeader.shouldHave(text("Test Advertiser"));
        advertisersModal.advertiserDescription.shouldHave(text("This is our test advertiser that we have ever met in our cool company. Please look at his photo - he is perfect.."));
        advertisersModal.advertiserInput.shouldNotBe(empty);
        advertisersModal.downloadButton.shouldBe(visible);
        advertisersModal.cardText.shouldHave(text("Take a look on our hero. You can change image size by slider"));
        advertisersModal.heroImage.shouldBe(visible);
        advertisersModal.sliderImage.shouldBe(visible);
        advertisersModal.saveAdvertiserButton.shouldHave(attribute("disabled"));
        advertisersModal.removeFromSavedAdvertisersButton.shouldBe(visible);
    }

    @Test
    public void enlargeAdvertiserPhoto() {
        advertisersModal = clientInformationPage.clickOnOneOfAdvertisers(0);
        advertisersModal.enlargePhoto(".ui-slider-handle", "left: 30.5%;");
        advertisersModal.heroImage.shouldHave(attribute("style", "width: 300px; height: 300px;"));
    }

    @Test
    public void moveAdvertiserToSavedList() {
        advertisersModal = clientInformationPage.clickOnOneOfAdvertisers(1);
        Selenide.sleep(500);
        advertisersModal.advertiserInput.sendKeys(Keys.ENTER);
        advertisersModal.advertiserInput.sendKeys(Keys.PAGE_DOWN);
        Selenide.sleep(500);
        advertisersModal.clickOnSaveImageButton();

        advertisersModal.savedAdvertiserHeader.shouldHave(text(testData.getSaveAdvertiserHeader()));
        advertisersModal.clickOnSavedAdvertiserButton();
        advertisersModal.savedAdvertiserName.shouldHave(text("Adidas"));
    }

    @Test
    public void removeAdvertiserFromSavedList() {
        advertisersModal = clientInformationPage.clickOnOneOfAdvertisers(0);
        Selenide.sleep(500);
        advertisersModal.advertiserInput.sendKeys(Keys.ENTER);
        advertisersModal.advertiserInput.sendKeys(Keys.PAGE_DOWN);
        Selenide.sleep(500);
        advertisersModal.clickOnSaveImageButton();

        advertisersModal.clickOnSavedAdvertiserButton();
        advertisersModal.clickOnRemoveFromSavedImageButton();
        advertisersModal.savedAdvertiserName.shouldNotHave(text("Test Advertiser"));
    }

}
