package tests.ui;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import actions.login.Login;
import actions.navigatescreen.NavigateScreen;
import actions.pastedata.PasteData;
import selenium.context.Base;
import selenium.driver.Driver;
import selenium.listeners.WebDriverListener;

@Listeners(WebDriverListener.class)
public class PasteDataTest extends Base {
	@Test
	public void deleteRecord() throws InterruptedException {
		Login login = new Login(Driver.getDriver(),
				"{\"password\":\"Judgement Day 02\",\"user\":\"BOTZ.001\"}");
		login.execute();
		login.validate();

		NavigateScreen navigate = new NavigateScreen(Driver.getDriver(), "{\"process\":\"REPORT TEMPLATES\",\"realm\":\"SETTINGS\"}");
		navigate.execute();
		navigate.validate();

		PasteData paste = new PasteData(Driver.getDriver(), "{\"data\":\"ACTIVE\"}");
		paste.execute();
		paste.validate();
	}
}
