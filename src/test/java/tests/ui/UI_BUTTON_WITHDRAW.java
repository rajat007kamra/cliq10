package tests.ui;

import actions.confirmation.confirmationPopUp;
import actions.discard.Discard;
import actions.login.Login;
import actions.logout.Logout;
import actions.navigatescreen.NavigateScreen;
import actions.withdraw.Withdraw;
import io.qameta.allure.Description;
import org.testng.annotations.Test;
import selenium.context.Base;
import selenium.driver.Driver;

public class UI_BUTTON_WITHDRAW extends Base {
  @Description("CHECK WITHDRAW BUTTON")
  @Test(groups = { "default"})
  public void ui_button_withdraw() {
    Login loginlyp9L = new Login(Driver.getDriver(), "{\"user\":\"BOTZ.001\"}");
    loginlyp9L.setup();
    loginlyp9L.execute();
    loginlyp9L.validate();
    loginlyp9L.tearDown();
    NavigateScreen navigatescreenhVbnw = new NavigateScreen(Driver.getDriver(), "{\"process\":\"REPORT TEMPLATES\",\"selectRow\":\"1\",\"column\":[{\"text\":\"ACTIVE\",\"title\":\"STATE\"},{\"text\":\"BOTZ.WITHDRAW RECORD\",\"title\":\"REPORT TEMPLATE\"}],\"realm\":\"SETTINGS\"}");
    navigatescreenhVbnw.setup();
    navigatescreenhVbnw.execute();
    navigatescreenhVbnw.validate();
    navigatescreenhVbnw.tearDown();
    Withdraw withdrawrecordRFoCm = new Withdraw(Driver.getDriver(), "{}");
    withdrawrecordRFoCm.setup();
    withdrawrecordRFoCm.execute();
    withdrawrecordRFoCm.validate();
    withdrawrecordRFoCm.tearDown();
    Discard discardrecordlDXAy = new Discard(Driver.getDriver(), "{}");
    discardrecordlDXAy.setup();
    discardrecordlDXAy.execute();
    discardrecordlDXAy.validate();
    discardrecordlDXAy.tearDown();
    confirmationPopUp checkconfirmationAIwEx = new confirmationPopUp(Driver.getDriver(), "{\"option\":\"YES\"}");
    checkconfirmationAIwEx.setup();
    checkconfirmationAIwEx.execute();
    checkconfirmationAIwEx.validate();
    checkconfirmationAIwEx.tearDown();
    Logout logoutu71oz = new Logout(Driver.getDriver(), "{}");
    logoutu71oz.setup();
    logoutu71oz.execute();
    logoutu71oz.validate();
    logoutu71oz.tearDown();
  }
}
