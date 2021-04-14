package tests.ui;

import actions.columnchooser.ColumnChooser;
import actions.login.Login;
import actions.logout.Logout;
import actions.navigatescreen.NavigateScreen;
import io.qameta.allure.Description;
import org.testng.annotations.Test;
import selenium.context.Base;
import selenium.driver.Driver;

public class UI_FIND_CHOOSER extends Base {
  @Description("CHECK ADD COLUMN")
  @Test(groups = { "default"})
  public void ui_find_chooser() {
    Login loginiurC1 = new Login(Driver.getDriver(), "{\"user\":\"BOTZ.001\"}");
    loginiurC1.setup();
    loginiurC1.execute();
    loginiurC1.validate();
    loginiurC1.tearDown();
    NavigateScreen navigatescreen2bBOQ = new NavigateScreen(Driver.getDriver(), "{\"process\":\"DASHBOARDS\",\"realm\":\"REPORTS\"}");
    navigatescreen2bBOQ.setup();
    navigatescreen2bBOQ.execute();
    navigatescreen2bBOQ.validate();
    navigatescreen2bBOQ.tearDown();
    ColumnChooser openchooserBbu1S = new ColumnChooser(Driver.getDriver(), "{}");
    openchooserBbu1S.setup();
    openchooserBbu1S.execute();
    openchooserBbu1S.validate();
    openchooserBbu1S.tearDown();
    Logout logoutQP1Jd = new Logout(Driver.getDriver(), "{}");
    logoutQP1Jd.setup();
    logoutQP1Jd.execute();
    logoutQP1Jd.validate();
    logoutQP1Jd.tearDown();
  }
}
