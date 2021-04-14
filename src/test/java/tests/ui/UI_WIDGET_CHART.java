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

public class UI_WIDGET_CHART extends Base {
  @Description("CHECK CHART WIDGET")
  @Test(groups = { "default"})
  public void ui_widget_chart() {
    Login loginnxK5w = new Login(Driver.getDriver(), "{\"user\":\"BOTZ.001\"}");
    loginnxK5w.setup();
    loginnxK5w.execute();
    loginnxK5w.validate();
    loginnxK5w.tearDown();
    NavigateScreen navigatescreen5LCj8 = new NavigateScreen(Driver.getDriver(), "{\"process\":\"CHART\",\"realm\":\"PROCESSES\"}");
    navigatescreen5LCj8.setup();
    navigatescreen5LCj8.execute();
    navigatescreen5LCj8.validate();
    navigatescreen5LCj8.tearDown();
    Accordion modifyaccordionhBBLa = new Accordion(Driver.getDriver(), "{\"accordion\":\"RIGHT\",\"state\":\"EXPAND\"}");
    modifyaccordionhBBLa.setup();
    modifyaccordionhBBLa.execute();
    modifyaccordionhBBLa.validate();
    modifyaccordionhBBLa.tearDown();
    OpenFlyouts openflyoutmXWCd = new OpenFlyouts(Driver.getDriver(), "{\"flexname\":\"QUICKLIST\",\"flyoutname\":\"CHART\"}");
    openflyoutmXWCd.setup();
    openflyoutmXWCd.execute();
    openflyoutmXWCd.validate();
    openflyoutmXWCd.tearDown();
    CloseWidget closewidgetYW7RT = new CloseWidget(Driver.getDriver(), "{}");
    closewidgetYW7RT.setup();
    closewidgetYW7RT.execute();
    closewidgetYW7RT.validate();
    closewidgetYW7RT.tearDown();
    Logout logoutxkcJl = new Logout(Driver.getDriver(), "{}");
    logoutxkcJl.setup();
    logoutxkcJl.execute();
    logoutxkcJl.validate();
    logoutxkcJl.tearDown();
  }
}
