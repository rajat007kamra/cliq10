package tests.ui;

import actions.accordion.Accordion;
import actions.closewidget.CloseWidget;
import actions.login.Login;
import actions.logout.Logout;
import actions.navigatescreen.NavigateScreen;
import actions.openflyouts.OpenFlyouts;
import io.qameta.allure.Description;
import org.testng.annotations.Test;
import selenium.context.Base;
import selenium.driver.Driver;

public class UI_WIDGET_SCHEDULER extends Base {
  @Description("CHECK SCHEDULER WIDGET")
  @Test(groups = { "default"})
  public void ui_widget_scheduler() {
    Login loginYviw3 = new Login(Driver.getDriver(), "{\"user\":\"BOTZ.001\"}");
    loginYviw3.setup();
    loginYviw3.execute();
    loginYviw3.validate();
    loginYviw3.tearDown();
    NavigateScreen navigatescreen2lP88 = new NavigateScreen(Driver.getDriver(), "{\"process\":\"REPORT TEMPLATES\",\"selectRow\":\"1\",\"column\":[{\"text\":\"BOTZ.ONE-TIME REPORT SCHEDULE\",\"title\":\"REPORT TEMPLATE\"}],\"realm\":\"SETTINGS\"}");
    navigatescreen2lP88.setup();
    navigatescreen2lP88.execute();
    navigatescreen2lP88.validate();
    navigatescreen2lP88.tearDown();
    Accordion modifyaccordionYCKEt = new Accordion(Driver.getDriver(), "{\"accordion\":\"RIGHT\",\"state\":\"EXPAND\"}");
    modifyaccordionYCKEt.setup();
    modifyaccordionYCKEt.execute();
    modifyaccordionYCKEt.validate();
    modifyaccordionYCKEt.tearDown();
    OpenFlyouts openflyoutbDIR1 = new OpenFlyouts(Driver.getDriver(), "{\"flexname\":\"QUICKLIST\",\"flyoutname\":\"REPORT SCHEDULER\"}");
    openflyoutbDIR1.setup();
    openflyoutbDIR1.execute();
    openflyoutbDIR1.validate();
    openflyoutbDIR1.tearDown();
    CloseWidget closewidgetodxun = new CloseWidget(Driver.getDriver(), "{}");
    closewidgetodxun.setup();
    closewidgetodxun.execute();
    closewidgetodxun.validate();
    closewidgetodxun.tearDown();
    Logout logouttJTnv = new Logout(Driver.getDriver(), "{}");
    logouttJTnv.setup();
    logouttJTnv.execute();
    logouttJTnv.validate();
    logouttJTnv.tearDown();
  }
}
