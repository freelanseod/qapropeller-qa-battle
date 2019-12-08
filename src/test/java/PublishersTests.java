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

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.clearBrowserCookies;

public class PublishersTests extends BaseTest {
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
        clientInformationPage.clickOnPublishersButton();
    }

    @Test
    public void displayPublishersElements() {
        articlesModal = clientInformationPage.clickOnOneOfPublishers(0);
        articlesModal.articleHeader.shouldHave(text("Youtube"));
        articlesModal.articleDescription.shouldHave(text("YouTube is an American video-sharing platform headquartered in San Bruno, California."));
        articlesModal.articleInput.shouldNotBe(empty);
        articlesModal.downloadButton.shouldBe(visible);
        articlesModal.cardText.shouldHave(text("Take a look on our hero. You can change image size by slider"));
        articlesModal.heroImage.shouldBe(visible);
        articlesModal.sliderImage.shouldBe(visible);
        articlesModal.saveArticleButton.shouldHave(attribute("disabled"));
        articlesModal.removeFromSavedArticlesButton.shouldBe(visible);
    }

    @Test
    public void enlargePublisherPhoto() { //need fix, style image doesn't changes when changes slider's style
        articlesModal = clientInformationPage.clickOnOneOfPublishers(0);
        articlesModal.changePhotoSize("#dataCard > div > div > div:nth-child(7) > div:nth-child(3) > div > span", "left: 40%;");
        articlesModal.heroImage.shouldHave(attribute("style", "width: 380px; height: 380px;"));
    }

    @Test
    public void movePublisherToSavedList() {
        articlesModal = clientInformationPage.clickOnOneOfPublishers(1);
        Selenide.sleep(5000);
        articlesModal.articleInput.sendKeys(Keys.ENTER);
        articlesModal.articleInput.sendKeys(Keys.PAGE_DOWN);
        Selenide.sleep(500);
        articlesModal.clickOnSaveImageButton();

        articlesModal.savedArticleHeader.shouldHave(text(testData.getSaveAdvertiserHeader()));
        articlesModal.clickOnSavedArticleButton();
        articlesModal.savedArticleName.shouldHave(text("Instagram"));
    }

    @Test
    public void removePublisherFromSavedList() {
        articlesModal = clientInformationPage.clickOnOneOfPublishers(0);
        Selenide.sleep(500);
        articlesModal.articleInput.sendKeys(Keys.ENTER);
        articlesModal.articleInput.sendKeys(Keys.PAGE_DOWN);
        Selenide.sleep(500);
        articlesModal.clickOnSaveImageButton();

        articlesModal.clickOnSavedArticleButton();
        articlesModal.clickOnRemoveFromSavedImageButton();
        articlesModal.savedArticleName.shouldNotHave(text("Youtube"));
    }

    @Test
    public void reducePublisherPhoto() { //need fix, style image doesn't changes when changes slider's style in tests
        articlesModal = clientInformationPage.clickOnOneOfPublishers(0);
        articlesModal.changePhotoSize("#dataCard > div > div > div:nth-child(7) > div:nth-child(3) > div > span", "left: 50%;");

        articlesModal.changePhotoSize("#dataCard > div > div > div:nth-child(7) > div:nth-child(3) > div > span", "left: 10%;");
        articlesModal.heroImage.shouldHave(attribute("style", "width: 320px; height: 320px;"));
    }

}
