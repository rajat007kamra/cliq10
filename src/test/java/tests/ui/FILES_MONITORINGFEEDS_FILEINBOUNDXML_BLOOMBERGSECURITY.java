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

public class FILES_MONITORINGFEEDS_FILEINBOUNDXML_BLOOMBERGSECURITY extends Base {
  @Description("FILES_MONITORINGFEEDS_FILEINBOUNDXML_BLOOMBERGSECURITY")
  @Link("UPLOAD A NEW FILE AND VERIFY ATTACHMENT FOR BLOOMBERG SECURITY")
  @Test(groups = { "default"})
  public void files_monitoringfeeds_fileinboundxml_bloombergsecurity() {
    Login login9uDUV = new Login(Driver.getDriver(), "{\"user\":\"BOTZ.001\"}");
    login9uDUV.execute();
    login9uDUV.validate();
    NavigateScreen navigatescreencjCuE = new NavigateScreen(Driver.getDriver(), "{\"process\":\"FEEDS\",\"selectRow\":\"1\",\"column\":[{\"text\":\"COMPLETED\",\"title\":\"STATUS\"},{\"text\":\"ODS TEAM*\",\"title\":\"FEED\"},{\"text\":\"TEAM\",\"title\":\"PROCESS\"}],\"realm\":\"SETTINGS\"}");
    navigatescreencjCuE.execute();
    navigatescreencjCuE.validate();
    Record performrightclick9u5Vo = new Record(Driver.getDriver(), "{\"RowNumber\":\"1\",\"Option\":\"CLONE\"}");
    performrightclick9u5Vo.execute();
    performrightclick9u5Vo.validate();
    UploadDocument uploaddocumentU29Rr = new UploadDocument(Driver.getDriver(), "{\"path\":\"testdata/TCCLIQINTEG038.csv\",\"name\":\"TCCLIQINTEG038.csv\"}");
    uploaddocumentU29Rr.execute();
    uploaddocumentU29Rr.validate();
    Attachments downloadattachmentsp2ke6 = new Attachments(Driver.getDriver(), "{\"isDownload\":\"no\",\"fileToVerify\":[\"TCCLIQINTEG038.csv\"],\"fileToDownload\":[\"\"]}");
    downloadattachmentsp2ke6.execute();
    downloadattachmentsp2ke6.validate();
  }
}
