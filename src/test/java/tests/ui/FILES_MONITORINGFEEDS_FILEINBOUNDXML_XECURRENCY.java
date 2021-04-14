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

public class FILES_MONITORINGFEEDS_FILEINBOUNDXML_XECURRENCY extends Base {
  @Description("FILES_MONITORINGFEEDS_FILEINBOUNDXML_XECURRENCY")
  @Link("UPLOAD A NEW FILE AND VERIFY ATTACHMENT FOR XE CURRENCY")
  @Test(groups = { "default"})
  public void files_monitoringfeeds_fileinboundxml_xecurrency() {
    Login loginsASwg = new Login(Driver.getDriver(), "{\"user\":\"BOTZ.001\"}");
    loginsASwg.execute();
    loginsASwg.validate();
    NavigateScreen navigatescreenWover = new NavigateScreen(Driver.getDriver(), "{\"process\":\"FEEDS\",\"selectRow\":\"1\",\"column\":[{\"text\":\"COMPLETED\",\"title\":\"STATUS\"},{\"text\":\"ODS TEAM*\",\"title\":\"FEED\"},{\"text\":\"TEAM\",\"title\":\"PROCESS\"}],\"realm\":\"SETTINGS\"}");
    navigatescreenWover.execute();
    navigatescreenWover.validate();
    Record performrightclickS5cTm = new Record(Driver.getDriver(), "{\"RowNumber\":\"1\",\"Option\":\"CLONE\"}");
    performrightclickS5cTm.execute();
    performrightclickS5cTm.validate();
    UploadDocument uploaddocumentBhZxA = new UploadDocument(Driver.getDriver(), "{\"path\":\"testdata/TCCLIQINTEG038.csv\",\"name\":\"TCCLIQINTEG038.csv\"}");
    uploaddocumentBhZxA.execute();
    uploaddocumentBhZxA.validate();
    Attachments downloadattachmentskLhRT = new Attachments(Driver.getDriver(), "{\"isDownload\":\"no\",\"fileToVerify\":[\"TCCLIQINTEG038.csv\"],\"fileToDownload\":[\"\"]}");
    downloadattachmentskLhRT.execute();
    downloadattachmentskLhRT.validate();
  }
}
