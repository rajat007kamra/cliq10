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

public class FILES_MONITORINGFEEDS_FILEINBOUNDXML_MOODYRATINGS extends Base {
  @Description("FILES_MONITORINGFEEDS_FILEINBOUNDXML_MOODYRATINGS")
  @Link("UPLOAD A NEW FILE AND VERIFY ATTACHMENT FOR MOODY RATINGS")
  @Test(groups = { "default"})
  public void files_monitoringfeeds_fileinboundxml_moodyratings() {
    Login loginhsfJe = new Login(Driver.getDriver(), "{\"user\":\"BOTZ.001\"}");
    loginhsfJe.execute();
    loginhsfJe.validate();
    NavigateScreen navigatescreenUjWPl = new NavigateScreen(Driver.getDriver(), "{\"process\":\"FEEDS\",\"selectRow\":\"1\",\"column\":[{\"text\":\"COMPLETED\",\"title\":\"STATUS\"},{\"text\":\"ODS TEAM*\",\"title\":\"FEED\"},{\"text\":\"TEAM\",\"title\":\"PROCESS\"}],\"realm\":\"SETTINGS\"}");
    navigatescreenUjWPl.execute();
    navigatescreenUjWPl.validate();
    Record performrightclickPp2H0 = new Record(Driver.getDriver(), "{\"RowNumber\":\"1\",\"Option\":\"CLONE\"}");
    performrightclickPp2H0.execute();
    performrightclickPp2H0.validate();
    UploadDocument uploaddocumentQUYwz = new UploadDocument(Driver.getDriver(), "{\"path\":\"testdata/TCCLIQINTEG038.csv\",\"name\":\"TCCLIQINTEG038.csv\"}");
    uploaddocumentQUYwz.execute();
    uploaddocumentQUYwz.validate();
    Attachments downloadattachments4mNmq = new Attachments(Driver.getDriver(), "{\"isDownload\":\"no\",\"fileToVerify\":[\"TCCLIQINTEG038.csv\"],\"fileToDownload\":[\"\"]}");
    downloadattachments4mNmq.execute();
    downloadattachments4mNmq.validate();
  }
}
