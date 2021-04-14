package tests.ui;

import actions.accordion.Accordion;
import actions.closewidget.CloseWidget;
import actions.login.Login;
import actions.logout.Logout;
import actions.navigatescreen.NavigateScreen;
import actions.timelineactions.VariableHistory;
import io.qameta.allure.Description;
import org.testng.annotations.Test;
import selenium.context.Base;
import selenium.driver.Driver;

public class UI_SCREEN_HISTORY extends Base {
  @Description("CHECK RECORD HISTORY")
  @Test(groups = { "default"})
  public void ui_screen_history() {
    Login login1AdM4 = new Login(Driver.getDriver(), "{\"user\":\"BOTZ.001\"}");
    login1AdM4.setup();
    login1AdM4.execute();
    login1AdM4.validate();
    login1AdM4.tearDown();
    NavigateScreen navigatescreenmKPfE = new NavigateScreen(Driver.getDriver(), "{\"process\":\"EMPLOYEES\",\"selectRow\":\"1\",\"column\":[{\"text\":\"MONITORING TEAM\",\"title\":\"EMPLOYEE\"}],\"realm\":\"PROCESSES\"}");
    navigatescreenmKPfE.setup();
    navigatescreenmKPfE.execute();
    navigatescreenmKPfE.validate();
    navigatescreenmKPfE.tearDown();
    Accordion modifyaccordion8D6dM = new Accordion(Driver.getDriver(), "{\"accordion\":\"RIGHT\",\"state\":\"EXPAND\"}");
    modifyaccordion8D6dM.setup();
    modifyaccordion8D6dM.execute();
    modifyaccordion8D6dM.validate();
    modifyaccordion8D6dM.tearDown();
    VariableHistory openvariablehistoryEDowW = new VariableHistory(Driver.getDriver(), "{}");
    openvariablehistoryEDowW.setup();
    openvariablehistoryEDowW.execute();
    openvariablehistoryEDowW.validate();
    openvariablehistoryEDowW.tearDown();
    CloseWidget closewidgetUsFTA = new CloseWidget(Driver.getDriver(), "{}");
    closewidgetUsFTA.setup();
    closewidgetUsFTA.execute();
    closewidgetUsFTA.validate();
    closewidgetUsFTA.tearDown();
    Logout logoutcNjQf = new Logout(Driver.getDriver(), "{}");
    logoutcNjQf.setup();
    logoutcNjQf.execute();
    logoutcNjQf.validate();
    logoutcNjQf.tearDown();
  }
}
