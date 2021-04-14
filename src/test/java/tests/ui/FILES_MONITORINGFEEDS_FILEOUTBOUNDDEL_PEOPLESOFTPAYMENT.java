package tests.ui;

import actions.login.Login;
import actions.navigatescreen.NavigateScreen;
import actions.record.Record;
import actions.uploaddocument.UploadDocument;
import io.qameta.allure.Description;
import io.qameta.allure.Link;
import org.testng.annotations.Test;
import selenium.context.Base;
import selenium.driver.Driver;

public class FILES_MONITORINGFEEDS_FILEOUTBOUNDDEL_PEOPLESOFTPAYMENT extends Base {
  @Description("FILES_MONITORINGFEEDS_FILEOUTBOUNDDEL_PEOPLESOFTPAYMENT")
  @Link("UPLOAD A NEW FILE WITH INVALID ENTITY MEANS, ENTITY WHICH IS NOT LISTED IN OUR SYSTEM DATABASE.")
  @Test(groups = { "default"})
  public void files_monitoringfeeds_fileoutbounddel_peoplesoftpayment() {
    Login loginDz1n8 = new Login(Driver.getDriver(), "{\"user\":\"BOTZ.001\"}");
    loginDz1n8.execute();
    loginDz1n8.validate();
    NavigateScreen navigatescreenE6rNU = new NavigateScreen(Driver.getDriver(), "{\"process\":\"FEEDS\",\"selectRow\":\"1\",\"column\":[{\"text\":\"COMPLETED\",\"title\":\"STATUS\"},{\"text\":\"ODS TEAM*\",\"title\":\"FEED\"},{\"text\":\"TEAM\",\"title\":\"PROCESS\"}],\"realm\":\"SETTINGS\"}");
    navigatescreenE6rNU.execute();
    navigatescreenE6rNU.validate();
    Record performrightclickBpZhG = new Record(Driver.getDriver(), "{\"RowNumber\":\"1\",\"Option\":\"CLONE\"}");
    performrightclickBpZhG.execute();
    performrightclickBpZhG.validate();
    UploadDocument uploaddocument9SfIi = new UploadDocument(Driver.getDriver(), "{\"path\":\"testdata/TCCLIQINTEG038.csv\",\"name\":\"TCCLIQINTEG038.csv\"}");
    uploaddocument9SfIi.execute();
    uploaddocument9SfIi.validate();
  }
}
