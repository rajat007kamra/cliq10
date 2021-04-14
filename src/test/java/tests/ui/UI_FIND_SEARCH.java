package tests.ui;

import actions.checkvariable.CheckVariable;
import actions.globalsearch.GlobalSearch;
import actions.login.Login;
import actions.logout.Logout;
import actions.navigatescreen.NavigateScreen;
import io.qameta.allure.Description;
import org.testng.annotations.Test;
import selenium.context.Base;
import selenium.driver.Driver;

public class UI_FIND_SEARCH extends Base {
  @Description("CHECK GLOBAL SEARCH")
  @Test(groups = { "default"})
  public void ui_find_search() {
    Login login3b52F = new Login(Driver.getDriver(), "{\"user\":\"BOTZ.001\"}");
    login3b52F.setup();
    login3b52F.execute();
    login3b52F.validate();
    login3b52F.tearDown();
    NavigateScreen navigatescreenihTV1 = new NavigateScreen(Driver.getDriver(), "{\"process\":\"REPORT TEMPLATES\",\"realm\":\"SETTINGS\"}");
    navigatescreenihTV1.setup();
    navigatescreenihTV1.execute();
    navigatescreenihTV1.validate();
    navigatescreenihTV1.tearDown();
    GlobalSearch performglobalsearchVt9fo = new GlobalSearch(Driver.getDriver(), "{\"text\":\"TEMP AGENT\"}");
    performglobalsearchVt9fo.setup();
    performglobalsearchVt9fo.execute();
    performglobalsearchVt9fo.validate();
    performglobalsearchVt9fo.tearDown();
    CheckVariable checkvariableDtyD3 = new CheckVariable(Driver.getDriver(), "{\"column\":[{\"expected\":\"TEMP AGENT\",\"variable\":\"REPORT TEMPLATE\"}],\"zone_name\":\"VIEW ZONE\"}");
    checkvariableDtyD3.setup();
    checkvariableDtyD3.execute();
    checkvariableDtyD3.validate();
    checkvariableDtyD3.tearDown();
    Logout logoutqz5Ch = new Logout(Driver.getDriver(), "{}");
    logoutqz5Ch.setup();
    logoutqz5Ch.execute();
    logoutqz5Ch.validate();
    logoutqz5Ch.tearDown();
  }
}
