package tests.ui;

import actions.accordion.Accordion;
import actions.directory.Directory;
import actions.editzone.workbar.CloseFormView;
import actions.login.Login;
import actions.logout.Logout;
import actions.navigatescreen.NavigateScreen;
import io.qameta.allure.Description;
import io.qameta.allure.Link;
import org.testng.annotations.Test;
import selenium.context.Base;
import selenium.driver.Driver;

public class UI_WIDGET_DIRECTORY extends Base {
  @Description("UI_WIDGET_DIRECTORY")
  @Link("RUN WIDGET ACTIONS")
  @Test(groups = { "default"})
  public void ui_widget_directory() {
    Login loginupZYf = new Login(Driver.getDriver(), "{\"user\":\"BOTZ.001\"}");
    loginupZYf.execute();
    loginupZYf.validate();
    NavigateScreen navigatescreenk7J3i = new NavigateScreen(Driver.getDriver(), "{\"process\":\"VENDORS\",\"realm\":\"PROCESSES\"}");
    navigatescreenk7J3i.execute();
    navigatescreenk7J3i.validate();
    Accordion modifyaccordionxxmQj = new Accordion(Driver.getDriver(), "{\"accordion\":\"RIGHT\",\"state\":\"EXPAND\"}");
    modifyaccordionxxmQj.execute();
    modifyaccordionxxmQj.validate();
    Directory opendirectorytz4bR = new Directory(Driver.getDriver(), "{}");
    opendirectorytz4bR.execute();
    opendirectorytz4bR.validate();
    CloseFormView closewidgetDpLtj = new CloseFormView(Driver.getDriver(), "{}");
    closewidgetDpLtj.execute();
    closewidgetDpLtj.validate();
    Logout logoutakf8b = new Logout(Driver.getDriver(), "{}");
    logoutakf8b.execute();
    logoutakf8b.validate();
  }
}
