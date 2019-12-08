package modals;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class AdvertisersModal {
    public SelenideElement
            advertiserHeader = $(".card-title"),
            advertiserDescription = $(".card-text"),
            advertiserInput = $("#dataCard > div > div > div:nth-child(5) > textarea"),
            downloadButton = $("#dataCard > div > div > div:nth-child(5) > button"),
            cardText = $("#dataCard > div > div"),
            heroImage = $("#heroImage"),
            sliderImage = $(".ui-state-default"),
            saveAdvertiserButton = $("#dataCard > div > div > div:nth-child(9) > button:nth-child(1)"),
            removeFromSavedAdvertisersButton = $("#dataCard > div > div > div:nth-child(9) > button:nth-child(2)"),
            savedAdvertiserHeader = $("#mainContainer > div:nth-child(2) > div:nth-child(3) > div > div.card-header.text-center"),
            savedAdvertiserButton = $("#mainContainer > div:nth-child(2) > div:nth-child(3) > div > div.card-body.text-right > div > button"),
            savedAdvertiserName = $(".right-sub-tree");

    public void clickOnDownloadButton() {
        downloadButton.click();
    }

    public void clickOnSaveImageButton() {
        saveAdvertiserButton.click();
    }

    public void clickOnRemoveFromSavedImageButton() {
        removeFromSavedAdvertisersButton.click();
    }

    public void clickOnSavedAdvertiserButton() {
        savedAdvertiserButton.click();
    }

    public void enlargePhoto(String locator, String value) {
        String js = String.format("document.querySelector('%s').setAttribute('style', '%s')", locator, value);
        executeJavaScript(js);
    }

}
