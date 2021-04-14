package tests.ui;

import actions.login.Login;
import actions.logout.Logout;
import actions.navigatescreen.NavigateScreen;
import actions.record.Record;
import io.qameta.allure.Description;
import io.qameta.allure.Link;
import org.testng.annotations.Test;
import selenium.context.Base;
import selenium.driver.Driver;

public class UI_BUTTON_CLONE extends Base {
  @Description("UI_BUTTON_CLONE")
  @Link("RUN BUTTON ACTIONS")
  @Test(groups = { "default"})
  public void ui_button_clone() {
    Login loginUIxge = new Login(Driver.getDriver(), "{\"user\":\"BOTZ.001\"}");
    loginUIxge.execute();
    loginUIxge.validate();
    NavigateScreen navigatescreen2mXeM = new NavigateScreen(Driver.getDriver(), "{\"process\":\"REPORT TEMPLATES\",\"selectRow\":\"1\",\"column\":[{\"text\":\"ACTIVE\",\"title\":\"STATE\"},{\"text\":\"TEMP AGENT\",\"title\":\"REPORT TEMPLATE\"}],\"realm\":\"SETTINGS\"}");
    navigatescreen2mXeM.execute();
    navigatescreen2mXeM.validate();
    Record performrightclickbXVQp = new Record(Driver.getDriver(), "{\"RowNumber\":\"1\",\"Option\":\"CLONE\"}");
    performrightclickbXVQp.execute();
    performrightclickbXVQp.validate();
    Logout logoutlNnml = new Logout(Driver.getDriver(), "{}");
    logoutlNnml.execute();
    logoutlNnml.validate();
  }
}
