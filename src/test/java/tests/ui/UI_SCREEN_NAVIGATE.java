package tests.ui;

import actions.login.Login;
import actions.logout.Logout;
import actions.navigatescreen.NavigateScreen;
import io.qameta.allure.Description;
import org.testng.annotations.Test;
import selenium.context.Base;
import selenium.driver.Driver;

public class UI_SCREEN_NAVIGATE extends Base {
  @Description("CHECK NAVIGATE SCREEN")
  @Test(groups = { "default"})
  public void ui_screen_navigate() {
    Login logint0XIp = new Login(Driver.getDriver(), "{\"user\":\"BOTZ.001\"}");
    logint0XIp.setup();
    logint0XIp.execute();
    logint0XIp.validate();
    logint0XIp.tearDown();
    NavigateScreen navigatescreenv1mHE = new NavigateScreen(Driver.getDriver(), "{\"process\":\"REPORT TEMPLATES\",\"realm\":\"SETTINGS\"}");
    navigatescreenv1mHE.setup();
    navigatescreenv1mHE.execute();
    navigatescreenv1mHE.validate();
    navigatescreenv1mHE.tearDown();
    Logout logoutnHHXU = new Logout(Driver.getDriver(), "{}");
    logoutnHHXU.setup();
    logoutnHHXU.execute();
    logoutnHHXU.validate();
    logoutnHHXU.tearDown();
  }
}
