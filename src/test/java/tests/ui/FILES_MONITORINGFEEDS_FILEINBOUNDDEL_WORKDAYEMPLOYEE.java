package tests.ui;

import actions.attachments.Attachments;
import actions.login.Login;
import actions.navigatescreen.NavigateScreen;
import actions.record.Record;
import actions.uploaddocument.UploadDocument;
import io.qameta.allure.Description;
import io.qameta.allure.Link;
import org.testng.annotations.Test;
import selenium.context.Base;
import selenium.driver.Driver;

public class FILES_MONITORINGFEEDS_FILEINBOUNDDEL_WORKDAYEMPLOYEE extends Base {
  @Description("FILES_MONITORINGFEEDS_FILEINBOUNDDEL_WORKDAYEMPLOYEE")
  @Link("UPLOAD A NEW FILE AND VERIFY ATTACHMENT FOR WORKDAY EMPLOYEE")
  @Test(groups = { "default"})
  public void files_monitoringfeeds_fileinbounddel_workdayemployee() {
    Login loginyO69Y = new Login(Driver.getDriver(), "{\"user\":\"BOTZ.001\"}");
    loginyO69Y.execute();
    loginyO69Y.validate();
    NavigateScreen navigatescreenkD4KP = new NavigateScreen(Driver.getDriver(), "{\"process\":\"FEEDS\",\"selectRow\":\"1\",\"column\":[{\"text\":\"COMPLETED\",\"title\":\"STATUS\"},{\"text\":\"ODS TEAM*\",\"title\":\"FEED\"},{\"text\":\"TEAM\",\"title\":\"PROCESS\"}],\"realm\":\"SETTINGS\"}");
    navigatescreenkD4KP.execute();
    navigatescreenkD4KP.validate();
    Record performrightclickKgJja = new Record(Driver.getDriver(), "{\"RowNumber\":\"1\",\"Option\":\"CLONE\"}");
    performrightclickKgJja.execute();
    performrightclickKgJja.validate();
    UploadDocument uploaddocumentde0nn = new UploadDocument(Driver.getDriver(), "{\"path\":\"testdata/TCCLIQINTEG038.csv\",\"name\":\"TCCLIQINTEG038.csv\"}");
    uploaddocumentde0nn.execute();
    uploaddocumentde0nn.validate();
    Attachments downloadattachmentsmaylG = new Attachments(Driver.getDriver(), "{\"isDownload\":\"no\",\"fileToVerify\":[\"TCCLIQINTEG038.csv\"],\"fileToDownload\":[\"\"]}");
    downloadattachmentsmaylG.execute();
    downloadattachmentsmaylG.validate();
  }
}
