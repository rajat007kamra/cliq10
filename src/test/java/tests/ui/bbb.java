package tests.ui;

import actions.addnew.AddNew;
import actions.checkmessage.CheckMessage;
import actions.login.Login;
import actions.logout.Logout;
import actions.navigatescreen.NavigateScreen;
import actions.populateform.PopulateForm;
import actions.save.Save;
import actions.submit.Submit;
import io.qameta.allure.Description;
import io.qameta.allure.Link;
import org.testng.annotations.Test;
import selenium.context.Base;
import selenium.driver.Driver;

public class bbb extends Base {
  @Description("APPROVERS_CREATEAPPROVALRULE_WithSpecificityCompanyWideWithProcessClarifier")
  @Link("Approvers")
  @Test(groups = { "default"})
  public void approvers_createapprovalrule_withspecificitycompanywidewithprocessclarifier() {
    Login login2YB4v = new Login(Driver.getDriver(), "{\"password\":\"Indiaamerica@1992\",\"subtenant\":\"MEZOCLIQ\",\"user\":\"GURKIRAT.SINGH\"}");
    login2YB4v.setup();
    login2YB4v.execute();
    login2YB4v.validate();
    login2YB4v.tearDown();
    NavigateScreen navigatescreen1iQYi = new NavigateScreen(Driver.getDriver(), "{\"process\":\"APPROVERS\",\"realm\":\"SETTINGS\"}");
    navigatescreen1iQYi.setup();
    navigatescreen1iQYi.execute();
    navigatescreen1iQYi.validate();
    navigatescreen1iQYi.tearDown();
    AddNew createrecord5ErFd = new AddNew(Driver.getDriver(), "{}");
    createrecord5ErFd.setup();
    createrecord5ErFd.execute();
    createrecord5ErFd.validate();
    createrecord5ErFd.tearDown();
    PopulateForm populateformJ2yG0 = new PopulateForm(Driver.getDriver(), "{\"column\":[{\"variable\":\"SPECIFICITY\",\"value\":\"COMPANY-WIDE\"},{\"variable\":\"PROCESS\",\"value\":\"TEAM\"},{\"variable\":\"DEADLINE\",\"value\":\"+2\"},{\"variable\":\"PENDING APPROVAL\",\"value\":\"-2\"}]}");
    populateformJ2yG0.setup();
    populateformJ2yG0.execute();
    populateformJ2yG0.validate();
    populateformJ2yG0.tearDown();
//    Save saverecordFkSSE = new Save(Driver.getDriver(), "{}");
//    saverecordFkSSE.setup();
//    saverecordFkSSE.execute();
//    saverecordFkSSE.validate();
//    saverecordFkSSE.tearDown();
//    CheckMessage checkmessagedsFS3 = new CheckMessage(Driver.getDriver(), "{\"action\":\"SAVE\",\"message\":\"SAVED!\"}");
//    checkmessagedsFS3.setup();
//    checkmessagedsFS3.execute();
//    checkmessagedsFS3.validate();
//    checkmessagedsFS3.tearDown();
    Submit submitrecordWCvQU = new Submit(Driver.getDriver(), "{}");
    submitrecordWCvQU.setup();
    submitrecordWCvQU.execute();
    submitrecordWCvQU.validate();
    submitrecordWCvQU.tearDown();
    Logout logoutPeo88 = new Logout(Driver.getDriver(), "{}");
    logoutPeo88.setup();
    logoutPeo88.execute();
    logoutPeo88.validate();
    logoutPeo88.tearDown();
  }
}
