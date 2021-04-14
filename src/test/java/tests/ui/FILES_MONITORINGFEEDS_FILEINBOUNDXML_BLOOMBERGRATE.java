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

public class FILES_MONITORINGFEEDS_FILEINBOUNDXML_BLOOMBERGRATE extends Base {
  @Description("FILES_MONITORINGFEEDS_FILEINBOUNDXML_BLOOMBERGRATE")
  @Link("UPLOAD A NEW FILE AND VERIFY ATTACHMENT FOR BLOOMBERG RATE")
  @Test(groups = { "default"})
  public void files_monitoringfeeds_fileinboundxml_bloombergrate() {
    Login loginldLLd = new Login(Driver.getDriver(), "{\"user\":\"BOTZ.001\"}");
    loginldLLd.execute();
    loginldLLd.validate();
    NavigateScreen navigatescreenviGSk = new NavigateScreen(Driver.getDriver(), "{\"process\":\"FEEDS\",\"selectRow\":\"1\",\"column\":[{\"text\":\"COMPLETED\",\"title\":\"STATUS\"},{\"text\":\"ODS TEAM*\",\"title\":\"FEED\"},{\"text\":\"TEAM\",\"title\":\"PROCESS\"}],\"realm\":\"SETTINGS\"}");
    navigatescreenviGSk.execute();
    navigatescreenviGSk.validate();
    Record performrightclickmwkg8 = new Record(Driver.getDriver(), "{\"RowNumber\":\"1\",\"Option\":\"CLONE\"}");
    performrightclickmwkg8.execute();
    performrightclickmwkg8.validate();
    UploadDocument uploaddocumentMClZq = new UploadDocument(Driver.getDriver(), "{\"path\":\"testdata/TCCLIQINTEG038.csv\",\"name\":\"TCCLIQINTEG038.csv\"}");
    uploaddocumentMClZq.execute();
    uploaddocumentMClZq.validate();
    Attachments downloadattachmentshP406 = new Attachments(Driver.getDriver(), "{\"isDownload\":\"no\",\"fileToVerify\":[\"TCCLIQINTEG038.csv\"],\"fileToDownload\":[\"\"]}");
    downloadattachmentshP406.execute();
    downloadattachmentshP406.validate();
  }
}
