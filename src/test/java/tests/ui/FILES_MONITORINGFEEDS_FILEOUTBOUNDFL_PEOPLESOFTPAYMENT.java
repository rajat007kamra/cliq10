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

public class FILES_MONITORINGFEEDS_FILEOUTBOUNDFL_PEOPLESOFTPAYMENT extends Base {
  @Description("FILES_MONITORINGFEEDS_FILEOUTBOUNDFL_PEOPLESOFTPAYMENT")
  @Link("UPLOAD A NEW FILE AND VERIFY ATTACHMENT FOR PEOPLES OF TPAYMENT")
  @Test(groups = { "default"})
  public void files_monitoringfeeds_fileoutboundfl_peoplesoftpayment() {
    Login loginqeyYH = new Login(Driver.getDriver(), "{\"user\":\"BOTZ.001\"}");
    loginqeyYH.execute();
    loginqeyYH.validate();
    NavigateScreen navigatescreenozzqV = new NavigateScreen(Driver.getDriver(), "{\"process\":\"FEEDS\",\"selectRow\":\"1\",\"column\":[{\"text\":\"COMPLETED\",\"title\":\"STATUS\"},{\"text\":\"ODS TEAM*\",\"title\":\"FEED\"},{\"text\":\"TEAM\",\"title\":\"PROCESS\"}],\"realm\":\"SETTINGS\"}");
    navigatescreenozzqV.execute();
    navigatescreenozzqV.validate();
    Record performrightclickqkolj = new Record(Driver.getDriver(), "{\"RowNumber\":\"1\",\"Option\":\"CLONE\"}");
    performrightclickqkolj.execute();
    performrightclickqkolj.validate();
    UploadDocument uploaddocumentLJRzr = new UploadDocument(Driver.getDriver(), "{\"path\":\"testdata/TCCLIQINTEG038.csv\",\"name\":\"TCCLIQINTEG038.csv\"}");
    uploaddocumentLJRzr.execute();
    uploaddocumentLJRzr.validate();
    Attachments downloadattachmentsybQs2 = new Attachments(Driver.getDriver(), "{\"isDownload\":\"no\",\"fileToVerify\":[\"TCCLIQINTEG038.csv\"],\"fileToDownload\":[\"\"]}");
    downloadattachmentsybQs2.execute();
    downloadattachmentsybQs2.validate();
  }
}
