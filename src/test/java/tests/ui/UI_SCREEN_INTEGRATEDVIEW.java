package tests.ui;

import actions.integratedview.IntegratedView;
import actions.login.Login;
import actions.logout.Logout;
import actions.navigatescreen.NavigateScreen;
import io.qameta.allure.Description;
import org.testng.annotations.Test;
import selenium.context.Base;
import selenium.driver.Driver;

public class UI_SCREEN_INTEGRATEDVIEW extends Base {
  @Description("CHECK INTEGRATED VIEW")
  @Test(groups = { "default"})
  public void ui_screen_integratedview() {
    Login loginNpGHG = new Login(Driver.getDriver(), "{\"user\":\"BOTZ.001\"}");
    loginNpGHG.setup();
    loginNpGHG.execute();
    loginNpGHG.validate();
    loginNpGHG.tearDown();
    NavigateScreen navigatescreenbNZyB = new NavigateScreen(Driver.getDriver(), "{\"process\":\"TEAMS\",\"realm\":\"PROCESSES\"}");
    navigatescreenbNZyB.setup();
    navigatescreenbNZyB.execute();
    navigatescreenbNZyB.validate();
    navigatescreenbNZyB.tearDown();
    IntegratedView openintegratedviewNZbXp = new IntegratedView(Driver.getDriver(), "{}");
    openintegratedviewNZbXp.setup();
    openintegratedviewNZbXp.execute();
    openintegratedviewNZbXp.validate();
    openintegratedviewNZbXp.tearDown();
    Logout logout78oQx = new Logout(Driver.getDriver(), "{}");
    logout78oQx.setup();
    logout78oQx.execute();
    logout78oQx.validate();
    logout78oQx.tearDown();
  }
}
