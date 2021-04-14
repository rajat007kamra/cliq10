package tests.ui;

import actions.closewidget.CloseWidget;
import actions.formview.FormView;
import actions.login.Login;
import actions.logout.Logout;
import actions.navigatescreen.NavigateScreen;
import io.qameta.allure.Description;
import org.testng.annotations.Test;
import selenium.context.Base;
import selenium.driver.Driver;

public class UI_WIDGET_FORM extends Base {
  @Description("CHECK FORM WIDGET")
  @Test(groups = { "default"})
  public void ui_widget_form() {
    Login loginRzXp2 = new Login(Driver.getDriver(), "{\"user\":\"BOTZ.001\"}");
    loginRzXp2.setup();
    loginRzXp2.execute();
    loginRzXp2.validate();
    loginRzXp2.tearDown();
    NavigateScreen navigatescreenJIZZd = new NavigateScreen(Driver.getDriver(), "{\"process\":\"REPORTS\",\"selectRow\":\"1\",\"column\":[{\"text\":\"BOTZ.TEMP-EXL-001\",\"title\":\"REPORT\"}],\"realm\":\"REPORTS\"}");
    navigatescreenJIZZd.setup();
    navigatescreenJIZZd.execute();
    navigatescreenJIZZd.validate();
    navigatescreenJIZZd.tearDown();
    FormView openformvmX7g = new FormView(Driver.getDriver(), "{}");
    openformvmX7g.setup();
    openformvmX7g.execute();
    openformvmX7g.validate();
    openformvmX7g.tearDown();
    CloseWidget closewidget65iGy = new CloseWidget(Driver.getDriver(), "{}");
    closewidget65iGy.setup();
    closewidget65iGy.execute();
    closewidget65iGy.validate();
    closewidget65iGy.tearDown();
    Logout logoutlNPWI = new Logout(Driver.getDriver(), "{}");
    logoutlNPWI.setup();
    logoutlNPWI.execute();
    logoutlNPWI.validate();
    logoutlNPWI.tearDown();
  }
}
