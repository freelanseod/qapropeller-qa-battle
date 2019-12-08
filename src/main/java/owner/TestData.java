package owner;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:TestData.properties")
public interface TestData extends Config {

    @Key("server.${env}.url")
    String url();

    @Key("user.valid.email")
    String getUserValidEmail();

    @Key("user.valid.password")
    String getUserValidPassword();

    @Key("user.login.header")
    String getUserLoginHeader();

    @Key("user.email.help.row")
    String getUserEmailHelpInformation();

    @Key("user.password.help.row")
    String getUserPasswordHelpInformation();

    @Key("user.login.button")
    String getUserLoginButtonText();

    @Key("user.login.placeholder")
    String getUserLoginPlaceholder();

    @Key("user.password.placeholder")
    String getUserPasswordPlaceholder();

    @Key("article.save.header")
    String getSaveAdvertiserHeader();

    @Key("user.profile.header")
    String getUserProfileHeader();

    @Key("user.profile.first.name.header")
    String getUserProfileFirstNameHeader();

    @Key("user.profile.last.name.header")
    String getUserProfileLastNameHeader();

    @Key("user.profile.card.number.header")
    String getUserProfileCardNumberHeader();

    @Key("user.profile.card.system.header")
    String getUserProfileCardSystemHeader();

    @Key("user.profile.payment.range.header")
    String getUserProfilePaymentRangeHeader();

    @Key("user.profile.saved.changes")
    String getUserProfileSavedChanges();

    @Key("user.profile.saved.credit.card")
    String getUserProfileSavedCreditCard();

    @Key("error.empty.first.name")
    String errorEmptyFirstName();

    @Key("error.empty.last.name")
    String errorEmptyLastName();

    @Key("error.empty.card.number")
    String errorEmptyCardNumber();

    @Key("error.empty.card.system")
    String errorEmptyCardSystem();

}
