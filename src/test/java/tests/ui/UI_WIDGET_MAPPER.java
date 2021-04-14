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

public class UI_WIDGET_MAPPER extends Base {
  @Description("CHECK MAPPER WIDGET")
  @Test(groups = { "default"})
  public void ui_widget_mapper() {
    Login loginWVw3b = new Login(Driver.getDriver(), "{\"user\":\"BOTZ.001\"}");
    loginWVw3b.setup();
    loginWVw3b.execute();
    loginWVw3b.validate();
    loginWVw3b.tearDown();
    NavigateScreen navigatescreenrvLfx = new NavigateScreen(Driver.getDriver(), "{\"process\":\"MAPPINGS\",\"realm\":\"SETTINGS\"}");
    navigatescreenrvLfx.setup();
    navigatescreenrvLfx.execute();
    navigatescreenrvLfx.validate();
    navigatescreenrvLfx.tearDown();
    Accordion modifyaccordion7ynjY = new Accordion(Driver.getDriver(), "{\"accordion\":\"RIGHT\",\"state\":\"EXPAND\"}");
    modifyaccordion7ynjY.setup();
    modifyaccordion7ynjY.execute();
    modifyaccordion7ynjY.validate();
    modifyaccordion7ynjY.tearDown();
    OpenFlyouts openflyoutGwhke = new OpenFlyouts(Driver.getDriver(), "{\"flexname\":\"QUICKLIST\",\"flyoutname\":\"MAPPER\"}");
    openflyoutGwhke.setup();
    openflyoutGwhke.execute();
    openflyoutGwhke.validate();
    openflyoutGwhke.tearDown();
    CloseWidget closewidgetiko2g = new CloseWidget(Driver.getDriver(), "{}");
    closewidgetiko2g.setup();
    closewidgetiko2g.execute();
    closewidgetiko2g.validate();
    closewidgetiko2g.tearDown();
    Logout logoutk2mv3 = new Logout(Driver.getDriver(), "{}");
    logoutk2mv3.setup();
    logoutk2mv3.execute();
    logoutk2mv3.validate();
    logoutk2mv3.tearDown();
  }
}
