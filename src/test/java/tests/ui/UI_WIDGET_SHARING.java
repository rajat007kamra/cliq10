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

public class UI_WIDGET_SHARING extends Base {
  @Description("CHECK SHARING WIDGET")
  @Test(groups = { "default"})
  public void ui_widget_sharing() {
    Login loginjv27t = new Login(Driver.getDriver(), "{\"user\":\"BOTZ.001\"}");
    loginjv27t.setup();
    loginjv27t.execute();
    loginjv27t.validate();
    loginjv27t.tearDown();
    NavigateScreen navigatescreenZh5bV = new NavigateScreen(Driver.getDriver(), "{\"process\":\"REPORTS\",\"selectRow\":\"1\",\"column\":[{\"text\":\"BOTZ.SHARING RIGHTS\",\"title\":\"REPORT\"}],\"realm\":\"REPORTS\"}");
    navigatescreenZh5bV.setup();
    navigatescreenZh5bV.execute();
    navigatescreenZh5bV.validate();
    navigatescreenZh5bV.tearDown();
    Accordion modifyaccordionX7rY1 = new Accordion(Driver.getDriver(), "{\"accordion\":\"RIGHT\",\"state\":\"EXPAND\"}");
    modifyaccordionX7rY1.setup();
    modifyaccordionX7rY1.execute();
    modifyaccordionX7rY1.validate();
    modifyaccordionX7rY1.tearDown();
    OpenFlyouts openflyoutUGjgA = new OpenFlyouts(Driver.getDriver(), "{\"flexname\":\"WORKFLOW\",\"flyoutname\":\"SHARING\"}");
    openflyoutUGjgA.setup();
    openflyoutUGjgA.execute();
    openflyoutUGjgA.validate();
    openflyoutUGjgA.tearDown();
    CloseWidget closewidgetMddKx = new CloseWidget(Driver.getDriver(), "{}");
    closewidgetMddKx.setup();
    closewidgetMddKx.execute();
    closewidgetMddKx.validate();
    closewidgetMddKx.tearDown();
    Logout logout4SNjk = new Logout(Driver.getDriver(), "{}");
    logout4SNjk.setup();
    logout4SNjk.execute();
    logout4SNjk.validate();
    logout4SNjk.tearDown();
  }
}
