package tests.ui;

import actions.login.Login;
import actions.navigatescreen.NavigateScreen;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import selenium.context.Base;
import selenium.driver.Driver;
import selenium.listeners.WebDriverListener;

@Listeners(WebDriverListener.class)
public class Sharing extends Base {
	@Test
	public void navigateTest() throws InterruptedException {

		Login login = new Login(Driver.getDriver(),
				"{\"password\":\"Judgement Day 02\",\"user\":\"BOTZ.001\"}");
		login.execute();
		login.validate();

		NavigateScreen nav = new NavigateScreen(Driver.getDriver(),
				"{\n" + "  \"realm\": \"SETTINGS\",\n" + "  \"process\": \"REPORT TEMPLATES\",\n" + "  \"column\": " + "  [\n"
						+ "    {\n" + "      \"title\": \"STATE\",\n" + "      \"text\": \"INACTIVE\"\n" + "    },\n"
						+ "    {\n" + "      \"title\": \"TEMPLATE NAME\",\n" + "      \"text\": \"TEMP1\"\n"
						+ "    }\n" + "  ],\n" + "\"selectRow\": \"1\"\n" + "}");
		nav.execute();
		nav.validate();
	}
}