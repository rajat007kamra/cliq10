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

public class FILES_MONITORINGFEEDS_FILEINBOUNDDEL_ODSACCOUNT extends Base {
  @Description("FILES_MONITORINGFEEDS_FILEINBOUNDDEL_ODSACCOUNT")
  @Link("UPLOAD A NEW FILE AND VERIFY ATTACHMENT FOR ODS ACCOUNT")
  @Test(groups = { "default"})
  public void files_monitoringfeeds_fileinbounddel_odsaccount() {
    Login loginK1eKi = new Login(Driver.getDriver(), "{\"user\":\"BOTZ.001\"}");
    loginK1eKi.execute();
    loginK1eKi.validate();
    NavigateScreen navigatescreenCtBhK = new NavigateScreen(Driver.getDriver(), "{\"process\":\"FEEDS\",\"selectRow\":\"1\",\"column\":[{\"text\":\"COMPLETED\",\"title\":\"STATUS\"},{\"text\":\"ODS TEAM*\",\"title\":\"FEED\"},{\"text\":\"TEAM\",\"title\":\"PROCESS\"}],\"realm\":\"SETTINGS\"}");
    navigatescreenCtBhK.execute();
    navigatescreenCtBhK.validate();
    Record performrightclickMnE1A = new Record(Driver.getDriver(), "{\"RowNumber\":\"1\",\"Option\":\"CLONE\"}");
    performrightclickMnE1A.execute();
    performrightclickMnE1A.validate();
    UploadDocument uploaddocumentNDhGV = new UploadDocument(Driver.getDriver(), "{\"path\":\"testdata/TCCLIQINTEG038.csv\",\"name\":\"TCCLIQINTEG038.csv\"}");
    uploaddocumentNDhGV.execute();
    uploaddocumentNDhGV.validate();
    Attachments downloadattachments1nQZg = new Attachments(Driver.getDriver(), "{\"isDownload\":\"no\",\"fileToVerify\":[\"TCCLIQINTEG038.csv\"],\"fileToDownload\":[\"\"]}");
    downloadattachments1nQZg.execute();
    downloadattachments1nQZg.validate();
  }
}
