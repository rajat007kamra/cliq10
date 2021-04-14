package tests.ui;

import actions.accordion.Accordion;
import actions.equation.Equation;
import actions.login.Login;
import actions.logout.Logout;
import actions.navigatescreen.NavigateScreen;
import io.qameta.allure.Description;
import io.qameta.allure.Link;
import org.testng.annotations.Test;
import selenium.context.Base;
import selenium.driver.Driver;

public class UI_WIDGET_EQUATION extends Base {
  @Description("UI_WIDGET_EQUATION")
  @Link("RUN WIDGET ACTIONS")
  @Test(groups = { "default"})
  public void ui_widget_equation() {
    Login loginGInFy = new Login(Driver.getDriver(), "{\"user\":\"BOTZ.001\"}");
    loginGInFy.execute();
    loginGInFy.validate();
    NavigateScreen navigatescreen6vebL = new NavigateScreen(Driver.getDriver(), "{\"process\":\"DICTIONARY\",\"realm\":\"SETTINGS\"}");
    navigatescreen6vebL.execute();
    navigatescreen6vebL.validate();
    Accordion modifyaccordion6F3wX = new Accordion(Driver.getDriver(), "{\"accordion\":\"RIGHT\",\"state\":\"EXPAND\"}");
    modifyaccordion6F3wX.execute();
    modifyaccordion6F3wX.validate();
    Equation openequation0r7Yv = new Equation(Driver.getDriver(), "{}");
    openequation0r7Yv.execute();
    openequation0r7Yv.validate();
    Logout logoutNyB1A = new Logout(Driver.getDriver(), "{}");
    logoutNyB1A.execute();
    logoutNyB1A.validate();
  }
}
