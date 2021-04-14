package tests.ui;

import actions.checkmessage.CheckMessage;
import actions.login.Login;
import actions.logout.Logout;
import actions.navigatescreen.NavigateScreen;
import actions.revise.Revise;
import actions.submit.Submit;
import io.qameta.allure.Description;
import org.testng.annotations.Test;
import selenium.context.Base;
import selenium.driver.Driver;

public class UI_BUTTON_SUBMIT extends Base {
  @Description("CHECK SUBMIT BUTTON")
  @Test(groups = { "default"})
  public void ui_button_submit() {
    Login login9SUY8 = new Login(Driver.getDriver(), "{\"user\":\"BOTZ.001\"}");
    login9SUY8.setup();
    login9SUY8.execute();
    login9SUY8.validate();
    login9SUY8.tearDown();
    NavigateScreen navigatescreenZ6Wbw = new NavigateScreen(Driver.getDriver(), "{\"process\":\"REPORT TEMPLATES\",\"selectRow\":\"1\",\"column\":[{\"text\":\"ACTIVE\",\"title\":\"STATE\"},{\"text\":\"BOTZ.SUBMIT RECORD\",\"title\":\"REPORT TEMPLATE\"}],\"realm\":\"SETTINGS\"}");
    navigatescreenZ6Wbw.setup();
    navigatescreenZ6Wbw.execute();
    navigatescreenZ6Wbw.validate();
    navigatescreenZ6Wbw.tearDown();
    Revise reviserecordrwCJ7 = new Revise(Driver.getDriver(), "{}");
    reviserecordrwCJ7.setup();
    reviserecordrwCJ7.execute();
    reviserecordrwCJ7.validate();
    reviserecordrwCJ7.tearDown();
    Submit submitrecordLkJ29 = new Submit(Driver.getDriver(), "{}");
    submitrecordLkJ29.setup();
    submitrecordLkJ29.execute();
    submitrecordLkJ29.validate();
    submitrecordLkJ29.tearDown();
    CheckMessage checkmessageSWBri = new CheckMessage(Driver.getDriver(), "{\"action\":\"SUBMIT\",\"message\":\"SUBMITTED!\"}");
    checkmessageSWBri.setup();
    checkmessageSWBri.execute();
    checkmessageSWBri.validate();
    checkmessageSWBri.tearDown();
    Logout logoutZsHle = new Logout(Driver.getDriver(), "{}");
    logoutZsHle.setup();
    logoutZsHle.execute();
    logoutZsHle.validate();
    logoutZsHle.tearDown();
  }
}
