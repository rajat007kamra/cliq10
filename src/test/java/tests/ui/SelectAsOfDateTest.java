package tests.ui;

import actions.login.Login;
import actions.navigatescreen.NavigateScreen;
import actions.timelineactions.SelectAsOfDate;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import selenium.context.Base;
import selenium.driver.Driver;
import selenium.listeners.WebDriverListener;

@Listeners(WebDriverListener.class)
public class SelectAsOfDateTest extends Base {
	@Test
	public void selectAsOfDateTest() {
		Login login = new Login(Driver.getDriver(),
				"{\"password\":\"Monitor@0720\",\"user\":\"ARUN.KAPOOR\",\"subtenant\":\"SE2\"}");
		login.execute();
		login.validate();

		NavigateScreen nav = new NavigateScreen(Driver.getDriver(), "{\n" +
				"  \"realm\": \"ANALYTICS\",\n" +
				"  \"process\": \"TEMPLATES\",\n" +
				"  \"column\": " +
				"  [\n" +
				"    {\n" +
				"      \"title\": \"STATE\",\n" +
				"      \"text\": \"ACTIVE\"\n" +
				"    },\n" +
				"    {\n" +
				"      \"title\": \"TEMPLATE NAME\",\n" +
				"      \"text\": \"TEST TEMPLATE-31\"\n" +
				"    }\n" +
				"  ],\n" +
				"\"selectRow\": \"1\"\n" +
				"}");
        nav.execute();
        nav.validate();
        
		SelectAsOfDate selectDate = new SelectAsOfDate(Driver.getDriver(), "");
		selectDate.execute();
		selectDate.validate();
	}
}