package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.confirm;

public class AuthenticationPage {
    public SelenideElement
            loginField = $("#loginInput"),
            preliminaryLoginField = $("[onclick='startInputLogin()']"),
            passwordField = $("#passwordInput"),
            preliminaryPasswordField = $("[onclick='startInputPassword()']"),
            loginButton = $(".bg-transparent").$$(".btn-primary").last(),
            loginHeader = $(".card-body"),
            loginHelpRow = $("#loginHelp"),
            passwordHelpRow = $("#passwordHelp"),
            signInButton = $("[src='sign.png']");

    public void openLogin() {
        open("");
    }

    public void fillLoginField(String userName) {
        preliminaryLoginField.click();
        clearField(loginField);
        loginField.waitUntil(visible, 900);
        loginField.val(userName);
    }

    public void fillPasswordField(String userPassword) {
        preliminaryPasswordField.click();
        clearField(passwordField);
        passwordField.waitUntil(visible, 900);
        passwordField.val(userPassword);
    }

    public ClientInformationPage clickOnLoginButton() {
        loginButton.click();
        signInButton.waitUntil(visible, 9999).click();
        confirm("Are you sure you want to login?");
        confirm("Really sure?");
        return new ClientInformationPage();
    }

    public void login(String email, String password) {
        openLogin();
        fillLoginField(email);
        fillPasswordField(password);
        clickOnLoginButton();
    }

    public void clearField(SelenideElement element) {
        int valueLength = element.val().length();

        for (int i = 0; i < valueLength; i++) {
            element.sendKeys(Keys.BACK_SPACE);
        }

    }

}
