package tests.ui;

import actions.login.Login;
import actions.logout.Logout;
import actions.navigatescreen.NavigateScreen;
import actions.sortcolumns.SortColumn;
import io.qameta.allure.Description;
import org.testng.annotations.Test;
import selenium.context.Base;
import selenium.driver.Driver;

public class UI_FIND_SORT extends Base {
  @Description("CHECK VIEW ZONE COLUMN SORT")
  @Test(groups = { "default"})
  public void ui_find_sort() {
    Login loginCs7sP = new Login(Driver.getDriver(), "{\"user\":\"BOTZ.001\"}");
    loginCs7sP.setup();
    loginCs7sP.execute();
    loginCs7sP.validate();
    loginCs7sP.tearDown();
    NavigateScreen navigatescreenYg6Gn = new NavigateScreen(Driver.getDriver(), "{\"process\":\"REPORT TEMPLATES\",\"realm\":\"SETTINGS\"}");
    navigatescreenYg6Gn.setup();
    navigatescreenYg6Gn.execute();
    navigatescreenYg6Gn.validate();
    navigatescreenYg6Gn.tearDown();
    SortColumn performsort6utXP = new SortColumn(Driver.getDriver(), "{\"column\":\"REPORT TEMPLATE\",\"order\":\"ASCENDING\"}");
    performsort6utXP.setup();
    performsort6utXP.execute();
    performsort6utXP.validate();
    performsort6utXP.tearDown();
    Logout logoutA75C0 = new Logout(Driver.getDriver(), "{}");
    logoutA75C0.setup();
    logoutA75C0.execute();
    logoutA75C0.validate();
    logoutA75C0.tearDown();
  }
}
