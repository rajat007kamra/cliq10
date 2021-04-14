package tests.ui;

import actions.filterrow.FilterRow;
import actions.login.Login;
import actions.navigatescreen.NavigateScreen;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import selenium.context.Base;
import selenium.driver.Driver;
import selenium.listeners.WebDriverListener;

@Listeners(WebDriverListener.class)
public class FilterRowTest extends Base {
	@Test
	public void searchRecord() throws InterruptedException {
		Login login = new Login(Driver.getDriver(),
				"{\"password\":\"Monitor@0720\",\"user\":\"ARUN.KAPOOR\",\"subtenant\":\"SE2\"}");
		login.execute();
		login.validate();

//		NavigateScreen nav = new NavigateScreen(Driver.getDriver(),
//				"{\n" + "  \"realm\": \"ANALYTICS\",\n" + "  \"process\": \"TEMPLATES\",\n" + "  \"column\": [\n"
//						+ "    {\n" + "      \"title\": \"STATE\",\n" + "      \"text\": \"ACTIVE\"\n" + "    },\n"
//						+ "    {\n" + "      \"title\": \"TEMPLATE NAME\",\n" + "      \"text\": \"TESTTEMP\"\n"
//						+ "    }\n" + "  ],\n" + "\"selectRow\": \"1\"\n" + "}");
//		nav.execute();
//		nav.validate();
		
		NavigateScreen nav = new NavigateScreen(Driver.getDriver(),
				"{\"realm\": \"ANALYTICS\", \"process\":\"TEMPLATES\"}");
		nav.execute();
		nav.validate();
		
		FilterRow row = new FilterRow(Driver.getDriver(), "{\"column\":[{\"text\":\"INACTIVE\",\"title\":\"STATE\"}]}");
		row.execute();
		row.validate();
	}
}
