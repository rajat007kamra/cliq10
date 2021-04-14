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

public class FILES_MONITORINGFEEDS_FILEINBOUNDFL_IDCPRICES extends Base {
  @Description("FILES_MONITORINGFEEDS_FILEINBOUNDFL_IDCPRICES")
  @Link("UPLOAD A NEW FILE AND VERIFY ATTACHMENT FOR IDC PRICES")
  @Test(groups = { "default"})
  public void files_monitoringfeeds_fileinboundfl_idcprices() {
    Login loginl5vfo = new Login(Driver.getDriver(), "{\"user\":\"BOTZ.001\"}");
    loginl5vfo.execute();
    loginl5vfo.validate();
    NavigateScreen navigatescreen4fRW2 = new NavigateScreen(Driver.getDriver(), "{\"process\":\"FEEDS\",\"selectRow\":\"1\",\"column\":[{\"text\":\"COMPLETED\",\"title\":\"STATUS\"},{\"text\":\"ODS TEAM*\",\"title\":\"FEED\"},{\"text\":\"TEAM\",\"title\":\"PROCESS\"}],\"realm\":\"SETTINGS\"}");
    navigatescreen4fRW2.execute();
    navigatescreen4fRW2.validate();
    Record performrightclick72bpe = new Record(Driver.getDriver(), "{\"RowNumber\":\"1\",\"Option\":\"CLONE\"}");
    performrightclick72bpe.execute();
    performrightclick72bpe.validate();
    UploadDocument uploaddocument4OwnC = new UploadDocument(Driver.getDriver(), "{\"path\":\"testdata/TCCLIQINTEG038.csv\",\"name\":\"TCCLIQINTEG038.csv\"}");
    uploaddocument4OwnC.execute();
    uploaddocument4OwnC.validate();
    Attachments downloadattachmentsDfO9y = new Attachments(Driver.getDriver(), "{\"isDownload\":\"no\",\"fileToVerify\":[\"TCCLIQINTEG038.csv\"],\"fileToDownload\":[\"\"]}");
    downloadattachmentsDfO9y.execute();
    downloadattachmentsDfO9y.validate();
  }
}
