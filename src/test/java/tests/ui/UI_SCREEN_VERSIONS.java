package tests.ui;

import actions.accordion.Accordion;
import actions.closewidget.CloseWidget;
import actions.login.Login;
import actions.logout.Logout;
import actions.navigatescreen.NavigateScreen;
import actions.timelineactions.VersionHistory;
import io.qameta.allure.Description;
import org.testng.annotations.Test;
import selenium.context.Base;
import selenium.driver.Driver;

public class UI_SCREEN_VERSIONS extends Base {
  @Description("CHECK VERSION HISTORY")
  @Test(groups = { "default"})
  public void ui_screen_versions() {
    Login loginYvKnI = new Login(Driver.getDriver(), "{\"user\":\"BOTZ.001\"}");
    loginYvKnI.setup();
    loginYvKnI.execute();
    loginYvKnI.validate();
    loginYvKnI.tearDown();
    NavigateScreen navigatescreenENykp = new NavigateScreen(Driver.getDriver(), "{\"process\":\"EMPLOYEES\",\"selectRow\":\"1\",\"column\":[{\"text\":\"MONITORING TEAM\",\"title\":\"EMPLOYEE\"}],\"realm\":\"PROCESSES\"}");
    navigatescreenENykp.setup();
    navigatescreenENykp.execute();
    navigatescreenENykp.validate();
    navigatescreenENykp.tearDown();
    Accordion modifyaccordionQE7OM = new Accordion(Driver.getDriver(), "{\"accordion\":\"RIGHT\",\"state\":\"EXPAND\"}");
    modifyaccordionQE7OM.setup();
    modifyaccordionQE7OM.execute();
    modifyaccordionQE7OM.validate();
    modifyaccordionQE7OM.tearDown();
    VersionHistory openversionhistoryiLNLr = new VersionHistory(Driver.getDriver(), "{\"displayHistory\":\"NO\"}");
    openversionhistoryiLNLr.setup();
    openversionhistoryiLNLr.execute();
    openversionhistoryiLNLr.validate();
    openversionhistoryiLNLr.tearDown();
    CloseWidget closewidgetPLrIb = new CloseWidget(Driver.getDriver(), "{}");
    closewidgetPLrIb.setup();
    closewidgetPLrIb.execute();
    closewidgetPLrIb.validate();
    closewidgetPLrIb.tearDown();
    Logout logoutHR7Dt = new Logout(Driver.getDriver(), "{}");
    logoutHR7Dt.setup();
    logoutHR7Dt.execute();
    logoutHR7Dt.validate();
    logoutHR7Dt.tearDown();
  }
}
