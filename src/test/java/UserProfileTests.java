import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import owner.TestData;
import pages.AuthenticationPage;
import pages.ClientInformationPage;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.clearBrowserCookies;

public class UserProfileTests extends BaseTest {
    private AuthenticationPage authenticationPage;
    private ClientInformationPage clientInformationPage;
    private pages.ClientProfilePage clientProfilePage;

    private final TestData testData = super.getTestData();

    @BeforeClass
    public void beforeClass() {
        authenticationPage = new AuthenticationPage();
        authenticationPage.login(testData.getUserValidEmail(), testData.getUserValidPassword());
        clientInformationPage = new ClientInformationPage();
        clientProfilePage = clientInformationPage.clickOnUserAvatar();
    }

    @AfterClass
    public void afterClass() {
        clearBrowserCookies();
    }

    @BeforeMethod
    public void beforeMethod() {
        clientProfilePage.userProfileButton.shouldBe(visible);
    }

    @Test
    public void displayUserProfileElements() { //need fix paymentSlider if value = 20, it thinks that value 16
        clientProfilePage.clickOnUserProfileButton();
        clientProfilePage.profileHeader.shouldHave(text(testData.getUserProfileHeader()));
        clientProfilePage.firstNameHeader.shouldHave(text(testData.getUserProfileFirstNameHeader()));
        clientProfilePage.firstNameInput.shouldBe(visible);
        clientProfilePage.lastNameHeader.shouldHave(text(testData.getUserProfileLastNameHeader()));
        clientProfilePage.lastNameInput.shouldBe(visible);

        clientProfilePage.clickOnPaymentInfoButton();
        clientProfilePage.cardNumberHeader.shouldHave(text(testData.getUserProfileCardNumberHeader()));
        clientProfilePage.cardNumberInput.shouldBe(visible);
        clientProfilePage.paymentSystemHeader.shouldHave(text(testData.getUserProfileCardSystemHeader()));
        clientProfilePage.paymentSystemSelect.shouldBe(visible);
        clientProfilePage.paymentRangeHeader.shouldHave(text(testData.getUserProfilePaymentRangeHeader()));
        clientProfilePage.paymentSlider.shouldHave(value("20"));
    }

    @Test
    public void addUserInformation() {
        clientProfilePage.clickOnUserProfileButton();
        clientProfilePage.fillFirstNameField("test name");
        clientProfilePage.fillLastNameField("test surname");
        clientProfilePage.clickOnSaveUserButton();
        clientProfilePage.successSaveInformationRow.shouldHave(text(testData.getUserProfileSavedChanges()));

        clientProfilePage.firstNameInput.shouldHave(value("test name"));
        clientProfilePage.lastNameInput.shouldHave(value("test surname"));
    }

    @Test
    public void editUserInformation() {
        clientProfilePage.clickOnUserProfileButton();
        clientProfilePage.fillFirstNameField("edit name");
        clientProfilePage.fillLastNameField("edit surname");
        clientProfilePage.clickOnSaveUserButton();
        clientProfilePage.successSaveInformationRow.shouldHave(text(testData.getUserProfileSavedChanges()));

        clientProfilePage.firstNameInput.shouldHave(value("edit name"));
        clientProfilePage.lastNameInput.shouldHave(value("edit surname"));
    }

    @Test
    public void addCreditCard() { //need fix paymentSlider, UI doesn't show text if move slider
        clientProfilePage.clickOnPaymentInfoButton();
        clientProfilePage.fillCardNumberField("1234 0000 9999 8888");
        clientProfilePage.choosePaymentSystem("MasterCard");
        clientProfilePage.choosePaymentDay("20");

        clientProfilePage.paymentDay.shouldHave(text("Current value: 20"));
        clientProfilePage.clickOnSaveCreditCardInfo();
        clientProfilePage.successSaveCreditCard.shouldHave(text(testData.getUserProfileSavedCreditCard()));

        clientProfilePage.cardNumberInput.shouldHave(value("1234 0000 9999 8888"));
        clientProfilePage.paymentSystemSelect.shouldHave(text("MasterCard"));
        clientProfilePage.paymentSlider.shouldHave(value("20"));
    }

    @Test
    public void editCreditCard() {//need fix paymentSlider, UI doesn't show text if move slider
        clientProfilePage.clickOnPaymentInfoButton();
        clientProfilePage.fillCardNumberField("1234 0000 9999 7777");
        clientProfilePage.choosePaymentSystem("Visa");
        clientProfilePage.choosePaymentDay("25");

        clientProfilePage.paymentDay.shouldHave(text("Current value: 25"));
        clientProfilePage.clickOnSaveCreditCardInfo();
        clientProfilePage.successSaveCreditCard.shouldHave(text(testData.getUserProfileSavedCreditCard()));

        clientProfilePage.cardNumberInput.shouldHave(value("1234 0000 9999 7777"));
        clientProfilePage.paymentSystemSelect.shouldHave(text("Visa"));
        clientProfilePage.paymentSlider.shouldHave(value("25"));
    }

    @Test
    public void addUserWithEmptyFirstName() {
        clientProfilePage.clickOnUserProfileButton();
        clientProfilePage.fillFirstNameField("");
        clientProfilePage.fillLastNameField("test surname");
        clientProfilePage.clickOnSaveUserButton();

        clientProfilePage.firstNameErrorRow.shouldHave(text(testData.errorEmptyFirstName()));
    }

    @Test
    public void addUserWithEmptyLastName() {
        clientProfilePage.clickOnUserProfileButton();
        clientProfilePage.fillFirstNameField("test name");
        clientProfilePage.fillLastNameField("");
        clientProfilePage.clickOnSaveUserButton();

        clientProfilePage.lastNameErrorRow.shouldHave(text(testData.errorEmptyLastName()));
    }

    @Test
    public void addCreditCardWithEmptyCardNumber() {
        clientProfilePage.clickOnPaymentInfoButton();
        clientProfilePage.fillCardNumberField("");
        clientProfilePage.choosePaymentSystem("Visa");
        clientProfilePage.choosePaymentDay("25");
        clientProfilePage.clickOnSaveCreditCardInfo();

        clientProfilePage.cardNumberErrorRow.shouldHave(text(testData.errorEmptyCardNumber()));
    }

    @Test
    public void addCreditCardWithEmptyPaymentSystem() {
        clientProfilePage.clickOnPaymentInfoButton();
        clientProfilePage.fillCardNumberField("1234 0000 9999 2345");
        clientProfilePage.choosePaymentSystem("");
        clientProfilePage.choosePaymentDay("4");
        clientProfilePage.clickOnSaveCreditCardInfo();

        clientProfilePage.paymentSystemErrorRow.shouldHave(text(testData.errorEmptyCardSystem()));
    }

}
