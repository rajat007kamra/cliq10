package tests.ui;

import actions.exportviewzone.ExportViewZone;
import actions.login.Login;
import actions.logout.Logout;
import actions.navigatescreen.NavigateScreen;
import io.qameta.allure.Description;
import org.testng.annotations.Test;
import selenium.context.Base;
import selenium.driver.Driver;

public class UI_SCREEN_EXPORTVIEWZONE extends Base {
  @Description("CHECK EXPORT LIST")
  @Test(groups = { "default"})
  public void ui_screen_exportviewzone() {
    Login login95UKX = new Login(Driver.getDriver(), "{\"user\":\"BOTZ.001\"}");
    login95UKX.setup();
    login95UKX.execute();
    login95UKX.validate();
    login95UKX.tearDown();
    NavigateScreen navigatescreenkgN2z = new NavigateScreen(Driver.getDriver(), "{\"process\":\"REPORT TEMPLATES\",\"realm\":\"SETTINGS\"}");
    navigatescreenkgN2z.setup();
    navigatescreenkgN2z.execute();
    navigatescreenkgN2z.validate();
    navigatescreenkgN2z.tearDown();
    ExportViewZone exportviewzoneWwEH4 = new ExportViewZone(Driver.getDriver(), "{\"expected\":\"EXPORTED!\"}");
    exportviewzoneWwEH4.setup();
    exportviewzoneWwEH4.execute();
    exportviewzoneWwEH4.validate();
    exportviewzoneWwEH4.tearDown();
    Logout logoutb0a42 = new Logout(Driver.getDriver(), "{}");
    logoutb0a42.setup();
    logoutb0a42.execute();
    logoutb0a42.validate();
    logoutb0a42.tearDown();
  }
}
