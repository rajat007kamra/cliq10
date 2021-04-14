package tests.ui;

import actions.login.Login;
import actions.navigatescreen.NavigateScreen;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import selenium.context.Base;
import selenium.driver.Driver;
import selenium.listeners.WebDriverListener;

@Listeners(WebDriverListener.class)
public class NavigateScreenTest extends Base {
	@Test
	public void navigateTest() throws InterruptedException {

		Login login = new Login(Driver.getDriver(),
				"{\"password\":\"Monitor@2019\",\"user\":\"ARUN.KAPOOR\",\"subtenant\":\"SE2\"}");
		login.execute();
		login.validate();

		NavigateScreen nav = new NavigateScreen(Driver.getDriver(),
				"{\n" + "  \"realm\": \"ANALYTICS\",\n" + "  \"process\": \"TEMPLATES\",\n" + "  \"column\": " + "  [\n"
						+ "    {\n" + "      \"title\": \"STATE\",\n" + "      \"text\": \"INACTIVE\"\n" + "    },\n"
						+ "    {\n" + "      \"title\": \"TEMPLATE NAME\",\n" + "      \"text\": \"TEMP1\"\n"
						+ "    }\n" + "  ],\n" + "\"selectRow\": \"1\"\n" + "}");
		nav.execute();
		nav.validate();
	}
}