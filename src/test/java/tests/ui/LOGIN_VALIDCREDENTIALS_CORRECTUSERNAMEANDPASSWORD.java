package tests.ui;

import actions.login.Login;
import actions.logout.Logout;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.Test;
import selenium.context.Base;
import selenium.driver.Driver;

public class LOGIN_VALIDCREDENTIALS_CORRECTUSERNAMEANDPASSWORD extends Base {
  @Epic("BASICS")
  @Feature("LOGIN")
  @Description("CHECK WITH VALID CREDENTIALS")
  @Test(groups = { "default"})
  public void login_validcredentials_correctusernameandpassword() {
    Login logincDsKQ = new Login(Driver.getDriver(), "{\"password\":\"Supernova008$\",\"subtenant\":\"MEZOCLIQ\",\"user\":\"GURKIRAT.SINGH\"}");
    logincDsKQ.setup();
    logincDsKQ.execute();
    logincDsKQ.validate();
    logincDsKQ.tearDown();
    Logout logoutiw49U = new Logout(Driver.getDriver(), "{}");
    logoutiw49U.setup();
    logoutiw49U.execute();
    logoutiw49U.validate();
    logoutiw49U.tearDown();
  }
}
