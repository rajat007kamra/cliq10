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

public class FILES_MONITORINGFEEDS_FILEOUTBOUNDEXCEL_DEMOEMPLOYEE extends Base {
  @Description("FILES_MONITORINGFEEDS_FILEOUTBOUNDEXCEL_DEMOEMPLOYEE")
  @Link("UPLOAD A NEW FILE AND VERIFY ATTACHMENT FOR DEMO EMPLOYEE")
  @Test(groups = { "default"})
  public void files_monitoringfeeds_fileoutboundexcel_demoemployee() {
    Login loginudRlD = new Login(Driver.getDriver(), "{\"user\":\"BOTZ.001\"}");
    loginudRlD.execute();
    loginudRlD.validate();
    NavigateScreen navigatescreene2NCp = new NavigateScreen(Driver.getDriver(), "{\"process\":\"FEEDS\",\"selectRow\":\"1\",\"column\":[{\"text\":\"COMPLETED\",\"title\":\"STATUS\"},{\"text\":\"ODS TEAM*\",\"title\":\"FEED\"},{\"text\":\"TEAM\",\"title\":\"PROCESS\"}],\"realm\":\"SETTINGS\"}");
    navigatescreene2NCp.execute();
    navigatescreene2NCp.validate();
    Record performrightclickvdYHy = new Record(Driver.getDriver(), "{\"RowNumber\":\"1\",\"Option\":\"CLONE\"}");
    performrightclickvdYHy.execute();
    performrightclickvdYHy.validate();
    UploadDocument uploaddocumentxRE4e = new UploadDocument(Driver.getDriver(), "{\"path\":\"testdata/TCCLIQINTEG038.csv\",\"name\":\"TCCLIQINTEG038.csv\"}");
    uploaddocumentxRE4e.execute();
    uploaddocumentxRE4e.validate();
    Attachments downloadattachmentsECSTt = new Attachments(Driver.getDriver(), "{\"isDownload\":\"no\",\"fileToVerify\":[\"TCCLIQINTEG038.csv\"],\"fileToDownload\":[\"\"]}");
    downloadattachmentsECSTt.execute();
    downloadattachmentsECSTt.validate();
  }
}
