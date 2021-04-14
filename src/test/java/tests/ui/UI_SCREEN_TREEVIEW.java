package tests.ui;

import actions.closewidget.CloseWidget;
import actions.login.Login;
import actions.logout.Logout;
import actions.navigatescreen.NavigateScreen;
import actions.treeview.TreeView;
import io.qameta.allure.Description;
import org.testng.annotations.Test;
import selenium.context.Base;
import selenium.driver.Driver;

public class UI_SCREEN_TREEVIEW extends Base {
  @Description("CHECK TREE VIEW")
  @Test(groups = { "default"})
  public void ui_screen_treeview() {
    Login logintJmtr = new Login(Driver.getDriver(), "{\"user\":\"BOTZ.001\"}");
    logintJmtr.setup();
    logintJmtr.execute();
    logintJmtr.validate();
    logintJmtr.tearDown();
    NavigateScreen navigatescreennF3Wf = new NavigateScreen(Driver.getDriver(), "{\"process\":\"SUBTENANTS\",\"selectRow\":\"1\",\"column\":[{\"text\":\"ACTIVE\",\"title\":\"STATE\"},{\"text\":\"BOTZ.MEZOCLIQ\",\"title\":\"SUBTENANT\"}],\"realm\":\"SETTINGS\"}");
    navigatescreennF3Wf.setup();
    navigatescreennF3Wf.execute();
    navigatescreennF3Wf.validate();
    navigatescreennF3Wf.tearDown();
    TreeView opentreeviewvnFOw = new TreeView(Driver.getDriver(), "{}");
    opentreeviewvnFOw.setup();
    opentreeviewvnFOw.execute();
    opentreeviewvnFOw.validate();
    opentreeviewvnFOw.tearDown();
    CloseWidget closewidgetr0frW = new CloseWidget(Driver.getDriver(), "{}");
    closewidgetr0frW.setup();
    closewidgetr0frW.execute();
    closewidgetr0frW.validate();
    closewidgetr0frW.tearDown();
    Logout logoutem4r6 = new Logout(Driver.getDriver(), "{}");
    logoutem4r6.setup();
    logoutem4r6.execute();
    logoutem4r6.validate();
    logoutem4r6.tearDown();
  }
}
