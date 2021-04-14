package tests.ui;

import actions.login.Login;
import actions.logout.Logout;
import actions.navigatescreen.NavigateScreen;
import actions.preferences.Preferences;
import io.qameta.allure.Description;
import org.testng.annotations.Test;
import selenium.context.Base;
import selenium.driver.Driver;

public class UI_SCREEN_PREFERENCES extends Base {
  @Description("CHECK PREFERENCES")
  @Test(groups = { "default"})
  public void ui_screen_preferences() {
    Login login5LDSw = new Login(Driver.getDriver(), "{\"user\":\"BOTZ.001\"}");
    login5LDSw.setup();
    login5LDSw.execute();
    login5LDSw.validate();
    login5LDSw.tearDown();
    NavigateScreen navigatescreenwX53E = new NavigateScreen(Driver.getDriver(), "{\"process\":\"PRIVILEGES\",\"realm\":\"SETTINGS\"}");
    navigatescreenwX53E.setup();
    navigatescreenwX53E.execute();
    navigatescreenwX53E.validate();
    navigatescreenwX53E.tearDown();
    Preferences openpreferencesHfoBX = new Preferences(Driver.getDriver(), "{\"preference\":\"reset settings\"}");
    openpreferencesHfoBX.setup();
    openpreferencesHfoBX.execute();
    openpreferencesHfoBX.validate();
    openpreferencesHfoBX.tearDown();
    Logout logoutYOCiK = new Logout(Driver.getDriver(), "{}");
    logoutYOCiK.setup();
    logoutYOCiK.execute();
    logoutYOCiK.validate();
    logoutYOCiK.tearDown();
  }
}
