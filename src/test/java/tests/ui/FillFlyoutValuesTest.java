package tests.ui;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import actions.login.Login;
import actions.navigatescreen.NavigateScreen;
import actions.openflyouts.OpenFlyouts;
import actions.populateform.PopulateForm;
import actions.revise.Revise;
import selenium.context.Base;
import selenium.driver.Driver;
import selenium.listeners.WebDriverListener;

@Listeners(WebDriverListener.class)
public class FillFlyoutValuesTest extends Base {
	@Test
	public void navigateTest() throws InterruptedException {

		Login login = new Login(Driver.getDriver(),
				"{\"password\":\"Monitor@0720\",\"user\":\"ARUN.KAPOOR\",\"subtenant\":\"SE2\"}");
		login.execute();
		login.validate();

		// TEMPLATES, PROCESSES, TEAMS, pages tested
		NavigateScreen nav = new NavigateScreen(Driver.getDriver(),
				"{\n" + "  \"realm\": \"ANALYTICS\",\n" + "  \"process\": \"TEMPLATES\",\n" + "  \"column\": [\n"
						+ "    {\n" + "      \"title\": \"STATE\",\n" + "      \"text\": \"ACTIVE\"\n" + "    },\n"
						+ "    {\n" + "      \"title\": \"TEMPLATE NAME\",\n"
						+ "      \"text\": \"TESTING TEMPLATE_03JUNE\"\n" + "    }\n" + "  ],\n"
						+ "\"selectRow\": \"1\"\n" + "}");
		nav.execute();
		nav.validate();

		Revise revise = new Revise(Driver.getDriver(), "{}");
		revise.execute();
		revise.validate();
	    
		// For ADMIN-> PROCESSES - (QUICKLIST= PROCESS ASSIGNMENTS), (WORKFLOW=
		// APPROVERS, RELATIONSHIPS (ALL))
		// For PROCESSES-> TEAMS - (QUICKLIST= TEAM ASSIGNMENTS), (WORKFLOW= APPROVERS,
		// RELATIONSHIPS (ALL))
		// For ANALYTICS-> TEMPLATES - (QUICKLIST= SPLIT BY), (WORKFLOW= ACTIONS)		
		OpenFlyouts clicktrigger7IBDF = new OpenFlyouts(Driver.getDriver(), "{\"flexname\":\"QUICKLIST\",\"flyoutname\":\"REPORT SCHEDULER\",\"realm\":\"ANALYTICS\"}");
	    clicktrigger7IBDF.execute();
	    clicktrigger7IBDF.validate();
	    
	    

		PopulateForm enterdata = new PopulateForm(Driver.getDriver(), "{\"column\":[{\"variable\":\"END-OF-MONTH\",\"value\":\"NO\"}]}");
		enterdata.execute();
		enterdata.validate();
	}
}