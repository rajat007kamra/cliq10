package tests.ui;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import actions.editzone.workbar.Revise;
import actions.login.Login;
import actions.navigatescreen.NavigateScreen;
import selenium.context.Base;
import selenium.driver.Driver;
import selenium.listeners.WebDriverListener;

@Listeners(WebDriverListener.class)
public class ReviseTest extends Base {
	@Test
	public void reviseRecord() throws InterruptedException {
		Login login = new Login(Driver.getDriver(),
				"{\"password\":\"Monitor@2019\",\"user\":\"ARUN.KAPOOR\",\"subtenant\":\"SE2\"}");
		login.execute();
		login.validate();

		NavigateScreen nav = new NavigateScreen(Driver.getDriver(),
				"{\n" + "  \"realm\": \"ANALYTICS\",\n" + "  \"process\": \"TEMPLATES\",\n" + "  \"column\": [\n"
						+ "    {\n" + "      \"title\": \"STATE\",\n" + "      \"text\": \"ACTIVE\"\n" + "    },\n"
						+ "    {\n" + "      \"title\": \"TEMPLATE NAME\",\n"
						+ "      \"text\": \"TESTING TEMPLATE_03JUNE\"\n" + "    }\n" + "  ],\n"
						+ "\"selectRow\": \"1\"\n" + "}");
		nav.execute();
		nav.validate();

		Revise revise = new Revise(Driver.getDriver(), "");
		revise.execute();
		revise.validate();
	}
}
