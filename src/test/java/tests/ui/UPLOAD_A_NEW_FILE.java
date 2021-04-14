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

public class UPLOAD_A_NEW_FILE extends Base {
  @Description("UPLOAD A NEW FILE")
  @Link("UPLOAD A NEW FILE")
  @Test(groups = { "default"})
  public void upload_a_new_file() {
    Login loginbriC7 = new Login(Driver.getDriver(), "{\"user\":\"BOTZ.001\"}");
    loginbriC7.execute();
    loginbriC7.validate();
    NavigateScreen navigatescreenk6PgZ = new NavigateScreen(Driver.getDriver(), "{\"process\":\"FEEDS\",\"selectRow\":\"1\",\"column\":[{\"text\":\"COMPLETED\",\"title\":\"STATUS\"},{\"text\":\"UNIVERSAL*\",\"title\":\"FEED\"},{\"text\":\"CLIENT*\",\"title\":\"PROCESS\"}],\"realm\":\"SETTINGS\"}");
    navigatescreenk6PgZ.execute();
    navigatescreenk6PgZ.validate();
    Record performrightclickzcLnj = new Record(Driver.getDriver(), "{\"RowNumber\":\"1\",\"Option\":\"CLONE\"}");
    performrightclickzcLnj.execute();
    performrightclickzcLnj.validate();
    UploadDocument uploaddocument63gUy = new UploadDocument(Driver.getDriver(), "{\"path\":\"testdata/TCCLIQINTEG038.csv\",\"name\":\"TCCLIQINTEG038.csv\"}");
    uploaddocument63gUy.execute();
    uploaddocument63gUy.validate();
  }
}
