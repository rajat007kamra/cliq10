package tests.ui;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import actions.login.Login;
import actions.logout.Logout;
import selenium.context.Base;
import selenium.driver.Driver;
import selenium.listeners.WebDriverListener;

@Listeners(WebDriverListener.class)
public class LogoutTest extends Base {
	@Test
	public void clickLogoutTest() throws InterruptedException {

		Login login = new Login(Driver.getDriver(),
				"{\"password\":\"Monitor@2019\",\"user\":\"ARUN.KAPOOR\",\"subtenant\":\"SE2\"}");
		login.execute();
		login.validate();

		Logout logout = new Logout(Driver.getDriver(), "{\"verifyLogoutText\":\"LOGIN\"}");
		logout.execute();
		logout.validate();
	}
}
