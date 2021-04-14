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

public class UI_WIDGET_TRANSLATION extends Base {
  @Description("CHECK TRANSLATION WIDGET")
  @Test(groups = { "default"})
  public void ui_widget_translation() {
    Login loginI67Dp = new Login(Driver.getDriver(), "{\"user\":\"BOTZ.001\"}");
    loginI67Dp.setup();
    loginI67Dp.execute();
    loginI67Dp.validate();
    loginI67Dp.tearDown();
    NavigateScreen navigatescreenwFhTO = new NavigateScreen(Driver.getDriver(), "{\"process\":\"TRANSLATIONS\",\"realm\":\"SETTINGS\"}");
    navigatescreenwFhTO.setup();
    navigatescreenwFhTO.execute();
    navigatescreenwFhTO.validate();
    navigatescreenwFhTO.tearDown();
    Accordion modifyaccordionJMIQC = new Accordion(Driver.getDriver(), "{\"accordion\":\"RIGHT\",\"state\":\"EXPAND\"}");
    modifyaccordionJMIQC.setup();
    modifyaccordionJMIQC.execute();
    modifyaccordionJMIQC.validate();
    modifyaccordionJMIQC.tearDown();
    OpenFlyouts openflyouttuQW8 = new OpenFlyouts(Driver.getDriver(), "{\"flexname\":\"QUICKLIST\",\"flyoutname\":\"TRANSLATION ASSIGNMENTS\"}");
    openflyouttuQW8.setup();
    openflyouttuQW8.execute();
    openflyouttuQW8.validate();
    openflyouttuQW8.tearDown();
    CloseWidget closewidget7H2fl = new CloseWidget(Driver.getDriver(), "{}");
    closewidget7H2fl.setup();
    closewidget7H2fl.execute();
    closewidget7H2fl.validate();
    closewidget7H2fl.tearDown();
    Logout logoutUoNEI = new Logout(Driver.getDriver(), "{}");
    logoutUoNEI.setup();
    logoutUoNEI.execute();
    logoutUoNEI.validate();
    logoutUoNEI.tearDown();
  }
}
