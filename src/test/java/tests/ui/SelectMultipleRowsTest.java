package tests.ui;

import actions.login.Login;
import actions.navigatescreen.NavigateScreen;
import actions.selectmultiplerows.SelectMultipleRows;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import selenium.context.Base;
import selenium.driver.Driver;
import selenium.listeners.WebDriverListener;

@Listeners(WebDriverListener.class)
public class SelectMultipleRowsTest extends Base {
	@Test
	public void navigateTest() throws InterruptedException {

		Login login = new Login(Driver.getDriver(),
				"{\"password\":\"Monitor@0720\",\"user\":\"ARUN.KAPOOR\",\"subtenant\":\"SE2\"}");
		login.execute();
		login.validate();

		NavigateScreen nav = new NavigateScreen(Driver.getDriver(),
				"{\"realm\": \"ANALYTICS\", \"process\":\"TEMPLATES\"}");
		nav.execute();
		nav.validate();
		
		SelectMultipleRows multiRows = new SelectMultipleRows(Driver.getDriver(), "");
		multiRows.execute();
	}
}