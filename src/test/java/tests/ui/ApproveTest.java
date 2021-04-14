package tests.ui;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import actions.checkmessage.CheckMessage;
import actions.editzone.workbar.Approve;
import actions.login.Login;
import actions.navigatescreen.NavigateScreen;
import selenium.context.Base;
import selenium.driver.Driver;
import selenium.listeners.WebDriverListener;

@Listeners(WebDriverListener.class)
public class ApproveTest extends Base {
	@Test
	public void approveRecord() throws InterruptedException {
		Login login = new Login(Driver.getDriver(),
				"{\"password\":\"ELECTION DAY 202\",\"user\":\"INDIV-APPROVER-B.BOT\",\"subtenant\":\"MEZOCLIQ\"}");
		login.execute();
		login.validate();

		NavigateScreen nav = new NavigateScreen(Driver.getDriver(),
				"{\n" + "  \"realm\": \"PROCESSES\",\n" + "  \"process\": \"ENTITIES\",\n" + "  \"column\": [\n"
						+ "    {\n" + "      \"title\": \"STATE\",\n" + "      \"text\": \"ONBOARDING\"\n" + "    },\n"
						+ "    {\n" + "      \"title\": \"ENTITY\",\n"
						+ "      \"text\": \"BOT APPROVED ENTITY N0615-0515-10561-1\"\n" + "    }\n" + "  ],\n"
						+ "\"selectRow\": \"1\"\n" + "}");
		nav.execute();
		nav.validate();

		Approve approve = new Approve(Driver.getDriver(), "");
		approve.execute();

		CheckMessage confirm = new CheckMessage(Driver.getDriver(), "");
		confirm.execute();

		approve.validate();
	}
}
