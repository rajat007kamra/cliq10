package tests.ui;

import actions.filterrow.FilterRow;
import actions.login.Login;
import actions.logout.Logout;
import actions.navigatescreen.NavigateScreen;
import actions.reject.Reject;
import io.qameta.allure.Description;
import io.qameta.allure.Link;
import org.testng.annotations.Test;
import selenium.context.Base;
import selenium.driver.Driver;

public class APPROVERS_CHECKRECORDDENYENABLED extends Base {
  @Description("DEMOTEST")
  @Link("Approvers")
  @Test(groups = { "default"})
  public void approvers_checkrecorddenyenabled() {
    Login loginnM8qE = new Login(Driver.getDriver(), "{\"password\":\"Indiaamerica@1992\",\"subtenant\":\"MEZOCLIQ\",\"user\":\"GURKIRAT.SINGH\"}");
    loginnM8qE.setup();
    loginnM8qE.execute();
    loginnM8qE.validate();
    loginnM8qE.tearDown();
    NavigateScreen navigatescreenLtNvs = new NavigateScreen(Driver.getDriver(), "{\"process\":\"LOCATIONS\",\"realm\":\"PROCESSES\"}");
    navigatescreenLtNvs.setup();
    navigatescreenLtNvs.execute();
    navigatescreenLtNvs.validate();
    navigatescreenLtNvs.tearDown();
    FilterRow performvzfiltersWu32 = new FilterRow(Driver.getDriver(), "{\"selectRow\":\"1\",\"column\":[{\"text\":\"ONBOARDING\",\"title\":\"STATE\"},{\"text\":\"MODEL TOWN\",\"title\":\"LOCATION\"}]}");
    performvzfiltersWu32.setup();
    performvzfiltersWu32.execute();
    performvzfiltersWu32.validate();
    performvzfiltersWu32.tearDown();
    Reject denyrecordthwV9 = new Reject(Driver.getDriver(), "{\"access\":\"YES\"}");
    denyrecordthwV9.setup();
    denyrecordthwV9.execute();
    denyrecordthwV9.validate();
    denyrecordthwV9.tearDown();
    Logout logoutR2AJ8 = new Logout(Driver.getDriver(), "{}");
    logoutR2AJ8.setup();
    logoutR2AJ8.execute();
    logoutR2AJ8.validate();
    logoutR2AJ8.tearDown();
  }
}
