package tests.ui;

import actions.addnew.AddNew;
import actions.closewidget.CloseWidget;
import actions.login.Login;
import actions.logout.Logout;
import actions.navigatescreen.NavigateScreen;
import io.qameta.allure.Description;
import org.testng.annotations.Test;
import selenium.context.Base;
import selenium.driver.Driver;

public class UI_WIDGET_PLUS extends Base {
  @Description("CHECK PLUS")
  @Test(groups = { "default"})
  public void ui_widget_plus() {
    Login loginjP4mE = new Login(Driver.getDriver(), "{\"user\":\"BOTZ.001\"}");
    loginjP4mE.setup();
    loginjP4mE.execute();
    loginjP4mE.validate();
    loginjP4mE.tearDown();
    NavigateScreen navigatescreenATFCa = new NavigateScreen(Driver.getDriver(), "{\"process\":\"REPORTS\",\"realm\":\"REPORTS\"}");
    navigatescreenATFCa.setup();
    navigatescreenATFCa.execute();
    navigatescreenATFCa.validate();
    navigatescreenATFCa.tearDown();
    AddNew createrecordeOhXf = new AddNew(Driver.getDriver(), "{}");
    createrecordeOhXf.setup();
    createrecordeOhXf.execute();
    createrecordeOhXf.validate();
    createrecordeOhXf.tearDown();
    CloseWidget closewidgetEumf9 = new CloseWidget(Driver.getDriver(), "{}");
    closewidgetEumf9.setup();
    closewidgetEumf9.execute();
    closewidgetEumf9.validate();
    closewidgetEumf9.tearDown();
    Logout logouttu8Zj = new Logout(Driver.getDriver(), "{}");
    logouttu8Zj.setup();
    logouttu8Zj.execute();
    logouttu8Zj.validate();
    logouttu8Zj.tearDown();
  }
}
