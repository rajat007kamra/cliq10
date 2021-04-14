package tests.ui;

import actions.accordion.Accordion;
import actions.clarifier.Clarifier;
import actions.login.Login;
import actions.navigatescreen.NavigateScreen;
import actions.populateclarifier.PopulateClarifier;
import actions.populateform.PopulateForm;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import selenium.context.Base;
import selenium.driver.Driver;
import selenium.listeners.WebDriverListener;

@Listeners(WebDriverListener.class)
public class ClarifierTest extends Base {
	@Test
	public void navigateTest() throws InterruptedException {

		Login login = new Login(Driver.getDriver(),
				"{\"password\":\"Tubelight90175$\",\"user\":\"RAJAT.KAMRA\",\"subtenant\":\"SE2\"}");
		login.execute();
		login.validate();

		NavigateScreen navigatescreenCtBhK = new NavigateScreen(Driver.getDriver(), "{\"process\":\"APPROVERS\",\"selectRow\":\"1\",\"column\":[{\"text\":\"REVISING\",\"title\":\"STATE\"}],\"realm\":\"SETTINGS\"}");
	    navigatescreenCtBhK.execute();
		navigatescreenCtBhK.validate();
        
        Accordion accord = new Accordion(Driver.getDriver(), "{\"accordion\":\"right\", \"state\":\"expand\"}");
        accord.execute();
        accord.validate();
        
        Clarifier clarify = new Clarifier(Driver.getDriver(), "");
        clarify.execute();
        clarify.validate();
        
        PopulateClarifier enterdataformviewQc3du = new PopulateClarifier(Driver.getDriver(), "{\"column\":[{\"title\":\"variable\",\"text\":\"TEAM ADDRESS CITY\"},{\"title\":\"value\",\"text\":\"09/06/2020\"}]}");
        enterdataformviewQc3du.execute();
        enterdataformviewQc3du.validate();
	}
}