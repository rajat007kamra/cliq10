package tests.ui;

import actions.filterrow.FilterRow;
import actions.login.Login;
import actions.logout.Logout;
import actions.navigatescreen.NavigateScreen;
import io.qameta.allure.Description;
import org.testng.annotations.Test;
import selenium.context.Base;
import selenium.driver.Driver;

public class UI_FIND_FILTER extends Base {
  @Description("CHECK VIEW ZONE SEARCH")
  @Test(groups = { "default"})
  public void ui_find_filter() {
    Login loginI7dkk = new Login(Driver.getDriver(), "{\"user\":\"BOTZ.001\"}");
    loginI7dkk.setup();
    loginI7dkk.execute();
    loginI7dkk.validate();
    loginI7dkk.tearDown();
    NavigateScreen navigatescreenzgkg4 = new NavigateScreen(Driver.getDriver(), "{\"process\":\"REPORT TEMPLATES\",\"realm\":\"SETTINGS\"}");
    navigatescreenzgkg4.setup();
    navigatescreenzgkg4.execute();
    navigatescreenzgkg4.validate();
    navigatescreenzgkg4.tearDown();
    FilterRow performvzfilterFyUBo = new FilterRow(Driver.getDriver(), "{\"selectRow\":\"1\",\"column\":[{\"text\":\"ACTIVE\",\"title\":\"STATE\"},{\"text\":\"TEMP AGENT\",\"title\":\"REPORT TEMPLATE\"}]}");
    performvzfilterFyUBo.setup();
    performvzfilterFyUBo.execute();
    performvzfilterFyUBo.validate();
    performvzfilterFyUBo.tearDown();
    Logout logout0MZs7 = new Logout(Driver.getDriver(), "{}");
    logout0MZs7.setup();
    logout0MZs7.execute();
    logout0MZs7.validate();
    logout0MZs7.tearDown();
  }
}
