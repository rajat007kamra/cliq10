package tests.ui;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import actions.confirmation.confirmationPopUp;
import actions.editzone.workbar.Delete;
import actions.login.Login;
import actions.navigatescreen.NavigateScreen;
import selenium.context.Base;
import selenium.driver.Driver;
import selenium.listeners.WebDriverListener;

@Listeners(WebDriverListener.class)
public class DeleteTest extends Base {
	@Test
	public void deleteRecord() throws InterruptedException {
		Login login = new Login(Driver.getDriver(),
				"{\"password\":\"Monitor@2019\",\"user\":\"ARUN.KAPOOR\",\"subtenant\":\"SE2\"}");
		login.execute();
		login.validate();

		NavigateScreen nav = new NavigateScreen(Driver.getDriver(),
				"{\n" + "  \"realm\": \"ANALYTICS\",\n" + "  \"process\": \"TEMPLATES\",\n" + "  \"column\": [\n"
						+ "    {\n" + "      \"title\": \"STATE\",\n" + "      \"text\": \"ONBOARDING\"\n" + "    },\n"
						+ "    {\n" + "      \"title\": \"TEMPLATE NAME\",\n"
						+ "      \"text\": \"TEST TEMPLATE_2020-06-17 07:04:32.211\"\n" + "    }\n" + "  ],\n"
						+ "\"selectRow\": \"1\"\n" + "}");
		nav.execute();
		nav.validate();

		Delete delete = new Delete(Driver.getDriver(), "");
		delete.execute();

		confirmationPopUp confirm = new confirmationPopUp(Driver.getDriver(), "{\"confirmationOption\":\"YES\"}");
		confirm.execute();
	}
}
