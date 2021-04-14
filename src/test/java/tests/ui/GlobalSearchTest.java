package tests.ui;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import actions.globalsearch.GlobalSearch;
import actions.login.Login;
import actions.navigatescreen.NavigateScreen;
import selenium.context.Base;
import selenium.driver.Driver;
import selenium.listeners.WebDriverListener;

@Listeners(WebDriverListener.class)
public class GlobalSearchTest extends Base {
	@Test
	public void globalSearchTest() throws InterruptedException {

		Login login = new Login(Driver.getDriver(),
				"{\"password\":\"Monitor@0720\",\"user\":\"ARUN.KAPOOR\",\"subtenant\":\"SE2\"}");
		login.execute();
		login.validate();

		NavigateScreen nav = new NavigateScreen(Driver.getDriver(),
				"{\"realm\": \"ANALYTICS\", \"process\":\"TEMPLATES\"}");
		nav.execute();
		nav.validate();

		GlobalSearch gsearch = new GlobalSearch(Driver.getDriver(),
				"{\"text\": \"WITHDRAW TEST TEMPLATE\"}");
		gsearch.execute();
	}
}
