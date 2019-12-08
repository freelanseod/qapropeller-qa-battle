package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.$;

public class ClientProfilePage {
    private static final String FOR = "for";

    public SelenideElement
            profileHeader = $(".card-header"),
            userProfileButton = $("#v-pills-home-tab"),
            paymentInfoButton = $("#v-pills-messages-tab"),
            firstNameHeader = $(byForAttribute("firstNameInput")),
            firstNameInput = $("#firstNameInput"),
            firstNameErrorRow = $("#v-pills-home > div > div:nth-child(2) > div:nth-child(1)"),
            saveUserInfoButton = $("[onclick='saveUserInfo()']"),
            lastNameHeader = $(byForAttribute("lastNameInput")),
            lastNameInput = $("#lastNameInput"),
            lastNameErrorRow = $("#v-pills-home > div > div:nth-child(2) > div:nth-child(2)"),
            cardNumberHeader = $(byForAttribute("cardNumberInput")),
            cardNumberInput = $("#cardNumberInput"),
            cardNumberErrorRow = $("#v-pills-messages > div > div:nth-child(2) > div"),
            paymentSystemHeader = $(byForAttribute("paymentSystemSelect")),
            paymentSystemSelect = $("#paymentSystemSelect"),
            paymentSystemErrorRow = $("#v-pills-messages > div > div:nth-child(3) > div"),
            paymentRangeHeader = $(byForAttribute("paymentRange")),
            paymentSlider = $(".slider"),
            successSaveInformationRow = $("#successUserInfoSaveInfo"),
            successSaveCreditCard = $("#successPaymentInfoSaveInfo"),
            savePaymentButton = $("[onclick='savePaymentInfo()']"),
            paymentDay = $("#v-pills-messages > div > div:nth-child(4) > div > div:nth-child(3) > h6");

    public void clickOnUserProfileButton() {
        userProfileButton.click();
    }

    public void clickOnPaymentInfoButton() {
        paymentInfoButton.click();
    }

    public void clickOnSaveUserButton() {
        saveUserInfoButton.click();
    }

    public void fillFirstNameField(String firstName) {
        clearField(firstNameInput);
        firstNameInput.waitUntil(visible, 900);
        firstNameInput.val(firstName);
    }

    public void fillLastNameField(String lastName) {
        clearField(lastNameInput);
        lastNameInput.waitUntil(visible, 900);
        lastNameInput.val(lastName);
    }

    public void fillCardNumberField(String cardNumber) {
        clearField(cardNumberInput);
        cardNumberInput.waitUntil(visible, 900);
        cardNumberInput.val(cardNumber);
    }

    public void choosePaymentSystem(String systemName) {
        paymentSystemSelect.selectOptionContainingText(systemName);
    }

    public void choosePaymentDay(String day) {
        paymentSlider.setValue(day);
    }

    public static By byForAttribute(String attributeValue) {
        return byAttribute(FOR, attributeValue);
    }

    public void clickOnSaveCreditCardInfo() {
        savePaymentButton.click();
    }

    public void clearField(SelenideElement element) {
        int valueLength = element.val().length();

        for (int i = 0; i < valueLength; i++) {
            element.sendKeys(Keys.BACK_SPACE);
        }

    }

}
