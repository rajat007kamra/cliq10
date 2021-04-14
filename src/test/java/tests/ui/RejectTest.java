package tests.ui;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import actions.checkmessage.CheckMessage;
import actions.editzone.workbar.Reject;
import actions.login.Login;
import actions.navigatescreen.NavigateScreen;
import selenium.context.Base;
import selenium.driver.Driver;
import selenium.listeners.WebDriverListener;

@Listeners(WebDriverListener.class)
public class RejectTest extends Base {
	@Test
	public void approveRecord() throws InterruptedException {
		Login login = new Login(Driver.getDriver(),
				"{\"password\":\"Monitor@2019\",\"user\":\"ARUN.KAPOOR\",\"subtenant\":\"SE2\"}");
		login.execute();
		login.validate();

		NavigateScreen nav = new NavigateScreen(Driver.getDriver(),
				"{\n" + "  \"realm\": \"ANALYTICS\",\n" + "  \"process\": \"TEMPLATES\",\n" + "  \"column\": [\n"
						+ "    {\n" + "      \"title\": \"STATE\",\n" + "      \"text\": \"ACTIVE\"\n" + "    },\n"
						+ "    {\n" + "      \"title\": \"TEMPLATE NAME\",\n"
						+ "      \"text\": \"WITHDRAW TEST TEMPLATE\"\n" + "    }\n" + "  ],\n"
						+ "\"selectRow\": \"1\"\n" + "}");
		nav.execute();
		nav.validate();

		Reject reject = new Reject(Driver.getDriver(), "");
		reject.execute();

		CheckMessage confirm = new CheckMessage(Driver.getDriver(), "");
		confirm.execute();

		reject.validate();
	}
}
