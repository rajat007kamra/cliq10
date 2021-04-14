package tests.ui;

import actions.accordion.Accordion;
import actions.clarifier.Clarifier;
import actions.closewidget.CloseWidget;
import actions.login.Login;
import actions.logout.Logout;
import actions.navigatescreen.NavigateScreen;
import io.qameta.allure.Description;
import org.testng.annotations.Test;
import selenium.context.Base;
import selenium.driver.Driver;

public class UI_WIDGET_CLARIFIER extends Base {
  @Description("CHECK CLARIFIER WIDGET")
  @Test(groups = { "default"})
  public void ui_widget_clarifier() {
    Login login5k1Wj = new Login(Driver.getDriver(), "{\"user\":\"BOTZ.001\"}");
    login5k1Wj.setup();
    login5k1Wj.execute();
    login5k1Wj.validate();
    login5k1Wj.tearDown();
    NavigateScreen navigatescreenJ7A6a = new NavigateScreen(Driver.getDriver(), "{\"process\":\"APPROVERS\",\"realm\":\"SETTINGS\"}");
    navigatescreenJ7A6a.setup();
    navigatescreenJ7A6a.execute();
    navigatescreenJ7A6a.validate();
    navigatescreenJ7A6a.tearDown();
    Accordion modifyaccordiondVPiJ = new Accordion(Driver.getDriver(), "{\"accordion\":\"RIGHT\",\"state\":\"EXPAND\"}");
    modifyaccordiondVPiJ.setup();
    modifyaccordiondVPiJ.execute();
    modifyaccordiondVPiJ.validate();
    modifyaccordiondVPiJ.tearDown();
    Clarifier openclarifierayBol = new Clarifier(Driver.getDriver(), "{}");
    openclarifierayBol.setup();
    openclarifierayBol.execute();
    openclarifierayBol.validate();
    openclarifierayBol.tearDown();
    CloseWidget closewidgetqvfBp = new CloseWidget(Driver.getDriver(), "{}");
    closewidgetqvfBp.setup();
    closewidgetqvfBp.execute();
    closewidgetqvfBp.validate();
    closewidgetqvfBp.tearDown();
    Logout logoutOg5CS = new Logout(Driver.getDriver(), "{}");
    logoutOg5CS.setup();
    logoutOg5CS.execute();
    logoutOg5CS.validate();
    logoutOg5CS.tearDown();
  }
}
