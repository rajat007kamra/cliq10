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

public class FILES_MONITORINGFEEDS_FILEOUTBOUNDXML_DEMOEMPLOYEE extends Base {
  @Description("FILES_MONITORINGFEEDS_FILEOUTBOUNDXML_DEMOEMPLOYEE")
  @Link("UPLOAD A NEW FILE AND VERIFY ATTACHMENT FOR DEMO EMPLOYEE")
  @Test(groups = { "default"})
  public void files_monitoringfeeds_fileoutboundxml_demoemployee() {
    Login logingvLJq = new Login(Driver.getDriver(), "{\"user\":\"BOTZ.001\"}");
    logingvLJq.execute();
    logingvLJq.validate();
    NavigateScreen navigatescreentL71C = new NavigateScreen(Driver.getDriver(), "{\"process\":\"FEEDS\",\"selectRow\":\"1\",\"column\":[{\"text\":\"COMPLETED\",\"title\":\"STATUS\"},{\"text\":\"ODS TEAM*\",\"title\":\"FEED\"},{\"text\":\"TEAM\",\"title\":\"PROCESS\"}],\"realm\":\"SETTINGS\"}");
    navigatescreentL71C.execute();
    navigatescreentL71C.validate();
    Record performrightclickQIwgR = new Record(Driver.getDriver(), "{\"RowNumber\":\"1\",\"Option\":\"CLONE\"}");
    performrightclickQIwgR.execute();
    performrightclickQIwgR.validate();
    UploadDocument uploaddocumentBMAxy = new UploadDocument(Driver.getDriver(), "{\"path\":\"testdata/TCCLIQINTEG038.csv\",\"name\":\"TCCLIQINTEG038.csv\"}");
    uploaddocumentBMAxy.execute();
    uploaddocumentBMAxy.validate();
    Attachments downloadattachments7ol8o = new Attachments(Driver.getDriver(), "{\"isDownload\":\"no\",\"fileToVerify\":[\"TCCLIQINTEG038.csv\"],\"fileToDownload\":[\"\"]}");
    downloadattachments7ol8o.execute();
    downloadattachments7ol8o.validate();
  }
}
