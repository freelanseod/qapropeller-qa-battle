import com.codeborne.selenide.Selenide;
import modals.ArticlesModal;
import org.openqa.selenium.Keys;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import owner.TestData;
import pages.AuthenticationPage;
import pages.ClientInformationPage;

import java.io.File;
import java.io.FileNotFoundException;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class AdvertisersTests extends BaseTest {
    private AuthenticationPage authenticationPage;
    private ClientInformationPage clientInformationPage;
    private ArticlesModal articlesModal;

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
        articlesModal = clientInformationPage.clickOnOneOfAdvertisers(0);
        articlesModal.articleHeader.shouldHave(text("Test Advertiser"));
        articlesModal.articleDescription.shouldHave(text("This is our test advertiser that we have ever met in our cool company. Please look at his photo - he is perfect.."));
        articlesModal.articleInput.shouldNotBe(empty);
        articlesModal.downloadButton.shouldBe(visible);
        articlesModal.cardText.shouldHave(text("Take a look on our hero. You can change image size by slider"));
        articlesModal.heroImage.shouldBe(visible);
        articlesModal.sliderImage.shouldBe(visible);
        articlesModal.saveArticleButton.shouldHave(attribute("disabled"));
        articlesModal.removeFromSavedArticlesButton.shouldBe(visible);
    }

    @Test
    public void enlargeAdvertiserPhoto() { //need fix, style image doesn't changes when changes slider's style in tests
        articlesModal = clientInformationPage.clickOnOneOfAdvertisers(0);
        articlesModal.changePhotoSize("#dataCard > div > div > div:nth-child(7) > div:nth-child(3) > div > span", "left: 40%;");
        articlesModal.heroImage.shouldHave(attribute("style", "width: 380px; height: 380px;"));
    }

    @Test
    public void moveAdvertiserToSavedList() {
        articlesModal = clientInformationPage.clickOnOneOfAdvertisers(1);
        Selenide.sleep(500);
        articlesModal.articleInput.sendKeys(Keys.ENTER);
        articlesModal.articleInput.sendKeys(Keys.PAGE_DOWN);
        Selenide.sleep(500);
        articlesModal.clickOnSaveImageButton();

        articlesModal.savedArticleHeader.shouldHave(text(testData.getSaveAdvertiserHeader()));
        articlesModal.clickOnSavedArticleButton();
        articlesModal.savedArticleName.shouldHave(text("Adidas"));
    }

    @Test
    public void removeAdvertiserFromSavedList() {
        articlesModal = clientInformationPage.clickOnOneOfAdvertisers(0);
        Selenide.sleep(500);
        articlesModal.articleInput.sendKeys(Keys.ENTER);
        articlesModal.articleInput.sendKeys(Keys.PAGE_DOWN);
        Selenide.sleep(500);
        articlesModal.clickOnSaveImageButton();

        articlesModal.clickOnSavedArticleButton();
        articlesModal.clickOnRemoveFromSavedImageButton();
        articlesModal.savedArticleName.shouldNotHave(text("Test Advertiser"));
    }

    @Test
    public void reduceAdvertiserPhoto() { //need fix, style image doesn't changes when changes slider's style in tests
        articlesModal = clientInformationPage.clickOnOneOfAdvertisers(0);
        articlesModal.changePhotoSize("#dataCard > div > div > div:nth-child(7) > div:nth-child(3) > div > span", "left: 50%;");

        articlesModal.changePhotoSize("#dataCard > div > div > div:nth-child(7) > div:nth-child(3) > div > span", "left: 10%;");
        articlesModal.heroImage.shouldHave(attribute("style", "width: 320px; height: 320px;"));
    }

}
