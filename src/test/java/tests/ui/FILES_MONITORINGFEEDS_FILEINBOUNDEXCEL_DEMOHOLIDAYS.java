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

public class FILES_MONITORINGFEEDS_FILEINBOUNDEXCEL_DEMOHOLIDAYS extends Base {
  @Description("FILES_MONITORINGFEEDS_FILEINBOUNDEXCEL_DEMOHOLIDAYS")
  @Link("UPLOAD A NEW FILE AND VERIFY ATTACHMENT FOR DEMO HOLIDAYS")
  @Test(groups = { "default"})
  public void files_monitoringfeeds_fileinboundexcel_demoholidays() {
    Login loginFcnfY = new Login(Driver.getDriver(), "{\"user\":\"BOTZ.001\"}");
    loginFcnfY.execute();
    loginFcnfY.validate();
    NavigateScreen navigatescreen6IMIH = new NavigateScreen(Driver.getDriver(), "{\"process\":\"FEEDS\",\"selectRow\":\"1\",\"column\":[{\"text\":\"COMPLETED\",\"title\":\"STATUS\"},{\"text\":\"ODS TEAM*\",\"title\":\"FEED\"},{\"text\":\"TEAM\",\"title\":\"PROCESS\"}],\"realm\":\"SETTINGS\"}");
    navigatescreen6IMIH.execute();
    navigatescreen6IMIH.validate();
    Record performrightclickKwNyz = new Record(Driver.getDriver(), "{\"RowNumber\":\"1\",\"Option\":\"CLONE\"}");
    performrightclickKwNyz.execute();
    performrightclickKwNyz.validate();
    UploadDocument uploaddocumenttbwd8 = new UploadDocument(Driver.getDriver(), "{\"path\":\"testdata/TCCLIQINTEG038.csv\",\"name\":\"TCCLIQINTEG038.csv\"}");
    uploaddocumenttbwd8.execute();
    uploaddocumenttbwd8.validate();
    Attachments downloadattachmentsfNxLY = new Attachments(Driver.getDriver(), "{\"isDownload\":\"no\",\"fileToVerify\":[\"TCCLIQINTEG038.csv\"],\"fileToDownload\":[\"\"]}");
    downloadattachmentsfNxLY.execute();
    downloadattachmentsfNxLY.validate();
  }
}
