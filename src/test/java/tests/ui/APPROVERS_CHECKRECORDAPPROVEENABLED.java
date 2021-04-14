package tests.ui;

import actions.accordion.Accordion;
import actions.approve.Approve;
import actions.filterrow.FilterRow;
import actions.login.Login;
import actions.logout.Logout;
import actions.navigatescreen.NavigateScreen;
import io.qameta.allure.Description;
import org.testng.annotations.Test;
import selenium.context.Base;
import selenium.driver.Driver;

public class APPROVERS_CHECKRECORDAPPROVEENABLED extends Base {
  @Description("APPROVERS_CheckRecordApproveEnabled")
  @Test(groups = { "default"})
  public void approvers_checkrecordapproveenabled() throws InterruptedException {
    Login loginPeVaq = new Login(Driver.getDriver(), "{\"password\":\"Indiaamerica@1992\",\"subtenant\":\"MEZOCLIQ\",\"user\":\"GURKIRAT.SINGH\"}");
    loginPeVaq.setup();
    loginPeVaq.execute();
    loginPeVaq.validate();
    loginPeVaq.tearDown();
    NavigateScreen navigatescreenGZHbC = new NavigateScreen(Driver.getDriver(), "{\"process\":\"LOCATIONS\",\"realm\":\"PROCESSES\"}");
    navigatescreenGZHbC.setup();
    navigatescreenGZHbC.execute();
    navigatescreenGZHbC.validate();
    navigatescreenGZHbC.tearDown();
    FilterRow performvzfilterMhNSm = new FilterRow(Driver.getDriver(), "{\"selectRow\":\"1\",\"column\":[{\"text\":\"ONBOARDING\",\"title\":\"STATE\"},{\"text\":\"MODEL TOWN\",\"title\":\"LOCATION\"}]}");
    performvzfilterMhNSm.setup();
    performvzfilterMhNSm.execute();
    performvzfilterMhNSm.validate();
    performvzfilterMhNSm.tearDown();
//    Accordion accord = new Accordion(Driver.getDriver(), "{\"accordion\":\"right\", \"state\":\"expand\"}");
//    accord.execute();
//    accord.validate();
 
    Approve approverecordKJe2d = new Approve(Driver.getDriver(), "{\"access\":\"YES\"}");
    approverecordKJe2d.setup();
    approverecordKJe2d.execute();
    approverecordKJe2d.validate();
    approverecordKJe2d.tearDown();
    Thread.sleep(1000000);
//    Logout logoutq5Wtw = new Logout(Driver.getDriver(), "{}");
//    logoutq5Wtw.setup();
//    logoutq5Wtw.execute();
//    logoutq5Wtw.validate();
//    logoutq5Wtw.tearDown();
  }
}
