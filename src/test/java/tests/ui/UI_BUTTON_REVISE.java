package tests.ui;

import actions.confirmation.confirmationPopUp;
import actions.discard.Discard;
import actions.filterrow.FilterRow;
import actions.login.Login;
import actions.logout.Logout;
import actions.navigatescreen.NavigateScreen;
import actions.revise.Revise;
import io.qameta.allure.Description;
import org.testng.annotations.Test;
import selenium.context.Base;
import selenium.driver.Driver;

public class UI_BUTTON_REVISE extends Base {
  @Description("CHECK REVISE BUTTON")
  @Test(groups = { "default"})
  public void ui_button_revise() {
    Login loginfHjxy = new Login(Driver.getDriver(), "{\"user\":\"BOTZ.001\"}");
    loginfHjxy.setup();
    loginfHjxy.execute();
    loginfHjxy.validate();
    loginfHjxy.tearDown();
    NavigateScreen navigatescreenPGIyw = new NavigateScreen(Driver.getDriver(), "{\"process\":\"REPORT TEMPLATES\",\"realm\":\"SETTINGS\"}");
    navigatescreenPGIyw.setup();
    navigatescreenPGIyw.execute();
    navigatescreenPGIyw.validate();
    navigatescreenPGIyw.tearDown();
    FilterRow performvzfiltergydqw = new FilterRow(Driver.getDriver(), "{\"selectRow\":\"1\",\"column\":[{\"text\":\"ACTIVE\",\"title\":\"STATE\"},{\"text\":\"BOTZ.REVISING.RECORD\",\"title\":\"REPORT TEMPLATE\"}]}");
    performvzfiltergydqw.setup();
    performvzfiltergydqw.execute();
    performvzfiltergydqw.validate();
    performvzfiltergydqw.tearDown();
    Revise reviserecordVJJ7f = new Revise(Driver.getDriver(), "{}");
    reviserecordVJJ7f.setup();
    reviserecordVJJ7f.execute();
    reviserecordVJJ7f.validate();
    reviserecordVJJ7f.tearDown();
    Discard discardrecordyGJUN = new Discard(Driver.getDriver(), "{}");
    discardrecordyGJUN.setup();
    discardrecordyGJUN.execute();
    discardrecordyGJUN.validate();
    discardrecordyGJUN.tearDown();
    confirmationPopUp checkconfirmationFkMgr = new confirmationPopUp(Driver.getDriver(), "{\"option\":\"YES\"}");
    checkconfirmationFkMgr.setup();
    checkconfirmationFkMgr.execute();
    checkconfirmationFkMgr.validate();
    checkconfirmationFkMgr.tearDown();
    Logout logoutADhMy = new Logout(Driver.getDriver(), "{}");
    logoutADhMy.setup();
    logoutADhMy.execute();
    logoutADhMy.validate();
    logoutADhMy.tearDown();
  }
}
