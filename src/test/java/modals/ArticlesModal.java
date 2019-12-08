package modals;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class ArticlesModal {
    public SelenideElement
            articleHeader = $(".card-title"),
            articleDescription = $(".card-text"),
            articleInput = $("#dataCard > div > div > div:nth-child(5) > textarea"),
            downloadButton = $("#dataCard > div > div > div:nth-child(5) > button"),
            cardText = $("#dataCard > div > div"),
            heroImage = $("#heroImage"),
            sliderImage = $(".ui-state-default"),
            saveArticleButton = $("#dataCard > div > div > div:nth-child(9) > button:nth-child(1)"),
            removeFromSavedArticlesButton = $("#dataCard > div > div > div:nth-child(9) > button:nth-child(2)"),
            savedArticleHeader = $("#mainContainer > div:nth-child(2) > div:nth-child(3) > div > div.card-header.text-center"),
            savedArticleButton = $("#mainContainer > div:nth-child(2) > div:nth-child(3) > div > div.card-body.text-right > div > button"),
            savedArticleName = $(".right-sub-tree");

    public void clickOnDownloadButton() {
        downloadButton.click();
    }

    public void clickOnSaveImageButton() {
        saveArticleButton.click();
    }

    public void clickOnRemoveFromSavedImageButton() {
        removeFromSavedArticlesButton.click();
    }

    public void clickOnSavedArticleButton() {
        savedArticleButton.click();
    }

    public void changePhotoSize(String locator, String value) {
        String js = String.format("document.querySelector('%s').setAttribute('style', '%s')", locator, value);
        executeJavaScript(js);
    }

}
