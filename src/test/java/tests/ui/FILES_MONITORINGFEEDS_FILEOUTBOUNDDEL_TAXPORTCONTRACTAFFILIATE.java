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

public class FILES_MONITORINGFEEDS_FILEOUTBOUNDDEL_TAXPORTCONTRACTAFFILIATE extends Base {
  @Description("FILES_MONITORINGFEEDS_FILEOUTBOUNDDEL_TAXPORTCONTRACTAFFILIATE")
  @Link("UPLOAD A NEW FILE AND VERIFY ATTACHMENT FOR TAXPORT CONTRACT AFFILIATE")
  @Test(groups = { "default"})
  public void files_monitoringfeeds_fileoutbounddel_taxportcontractaffiliate() {
    Login loginbrLuA = new Login(Driver.getDriver(), "{\"user\":\"BOTZ.001\"}");
    loginbrLuA.execute();
    loginbrLuA.validate();
    NavigateScreen navigatescreenTm4EA = new NavigateScreen(Driver.getDriver(), "{\"process\":\"FEEDS\",\"selectRow\":\"1\",\"column\":[{\"text\":\"COMPLETED\",\"title\":\"STATUS\"},{\"text\":\"ODS TEAM*\",\"title\":\"FEED\"},{\"text\":\"TEAM\",\"title\":\"PROCESS\"}],\"realm\":\"SETTINGS\"}");
    navigatescreenTm4EA.execute();
    navigatescreenTm4EA.validate();
    Record performrightclick85vRY = new Record(Driver.getDriver(), "{\"RowNumber\":\"1\",\"Option\":\"CLONE\"}");
    performrightclick85vRY.execute();
    performrightclick85vRY.validate();
    UploadDocument uploaddocumentK92gq = new UploadDocument(Driver.getDriver(), "{\"path\":\"testdata/TCCLIQINTEG038.csv\",\"name\":\"TCCLIQINTEG038.csv\"}");
    uploaddocumentK92gq.execute();
    uploaddocumentK92gq.validate();
    Attachments downloadattachmentsIhwV4 = new Attachments(Driver.getDriver(), "{\"isDownload\":\"no\",\"fileToVerify\":[\"TCCLIQINTEG038.csv\"],\"fileToDownload\":[\"\"]}");
    downloadattachmentsIhwV4.execute();
    downloadattachmentsIhwV4.validate();
  }
}
