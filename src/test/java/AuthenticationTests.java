import com.codeborne.selenide.Condition;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import owner.TestData;
import pages.AuthenticationPage;
import pages.ClientInformationPage;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.text;

public class AuthenticationTests extends BaseTest {
    private AuthenticationPage authenticationPage;
    private ClientInformationPage clientInformationPage;

    private final TestData testData = super.getTestData();

    @BeforeMethod
    public void beforeMethod() {
        authenticationPage = new AuthenticationPage();
        authenticationPage.openLogin();
    }

    @Test
    public void displayAuthenticationPage() {
        authenticationPage.loginHeader.shouldHave(text(testData.getUserLoginHeader()));
        authenticationPage.loginField.shouldHave(attribute("placeholder", testData.getUserLoginPlaceholder()));
        authenticationPage.loginHelpRow.shouldHave(text(testData.getUserEmailHelpInformation()));
        authenticationPage.passwordField.shouldHave(attribute("placeholder", testData.getUserPasswordPlaceholder()));
        authenticationPage.passwordHelpRow.shouldHave(text(testData.getUserPasswordHelpInformation()));
        authenticationPage.loginButton.shouldHave(text(testData.getUserLoginButtonText()));
    }

    @Test
    public void loginWithValidParams() {
        authenticationPage.fillLoginField(testData.getUserValidEmail());
        authenticationPage.fillPasswordField(testData.getUserValidPassword());
        clientInformationPage =  authenticationPage.clickOnLoginButton();
        clientInformationPage.articlesHeader.shouldBe(Condition.visible);
    }

}
