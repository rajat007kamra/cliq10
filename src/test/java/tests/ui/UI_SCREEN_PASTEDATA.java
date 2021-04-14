package tests.ui;

import actions.login.Login;
import actions.logout.Logout;
import actions.navigatescreen.NavigateScreen;
import actions.pastedata.PasteData;
import io.qameta.allure.Description;
import org.testng.annotations.Test;
import selenium.context.Base;
import selenium.driver.Driver;

public class UI_SCREEN_PASTEDATA extends Base {
  @Description("CHECK PASTE DATA")
  @Test(groups = { "default"})
  public void ui_screen_pastedata() {
    Login login0ujHg = new Login(Driver.getDriver(), "{\"user\":\"BOTZ.001\"}");
    login0ujHg.setup();
    login0ujHg.execute();
    login0ujHg.validate();
    login0ujHg.tearDown();
    NavigateScreen navigatescreen2jmCa = new NavigateScreen(Driver.getDriver(), "{\"process\":\"REPORT TEMPLATES\",\"realm\":\"SETTINGS\"}");
    navigatescreen2jmCa.setup();
    navigatescreen2jmCa.execute();
    navigatescreen2jmCa.validate();
    navigatescreen2jmCa.tearDown();
    PasteData pastedatarksz6 = new PasteData(Driver.getDriver(), "{\"data\":\"ACTIVE\"}");
    pastedatarksz6.setup();
    pastedatarksz6.execute();
    pastedatarksz6.validate();
    pastedatarksz6.tearDown();
    Logout logoutN7ZNj = new Logout(Driver.getDriver(), "{}");
    logoutN7ZNj.setup();
    logoutN7ZNj.execute();
    logoutN7ZNj.validate();
    logoutN7ZNj.tearDown();
  }
}
