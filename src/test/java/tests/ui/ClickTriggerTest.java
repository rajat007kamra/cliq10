package tests.ui;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import actions.login.Login;
import actions.navigatescreen.NavigateScreen;
import actions.openflyouts.OpenFlyouts;
import selenium.context.Base;
import selenium.driver.Driver;
import selenium.listeners.WebDriverListener;

@Listeners(WebDriverListener.class)
public class ClickTriggerTest extends Base {
	@Test
	public void clickTrigger() throws InterruptedException { 
		
		Login login = new Login(Driver.getDriver(), "{\"password\":\"Monitor@0720\",\"user\":\"ARUN.KAPOOR\",\"subtenant\":\"SE2\"}");
        login.execute();
        login.validate();
		
		//TEMPLATES, PROCESSES, TEAMS, pages tested
        NavigateScreen nav = new NavigateScreen(Driver.getDriver(), "{\"realm\": \"ANALYTICS\", \"process\":\"TEMPLATES\"}");
        nav.execute();
        nav.validate();
        
        //For ADMIN-> PROCESSES - (QUICKLIST= PROCESS ASSIGNMENTS), (WORKFLOW= APPROVERS, RELATIONSHIPS (ALL))
        //For PROCESSES-> TEAMS - (QUICKLIST= TEAM ASSIGNMENTS), (WORKFLOW= APPROVERS, RELATIONSHIPS (ALL))
        //For ANALYTICS-> TEMPLATES - (QUICKLIST= SPLIT BY), (WORKFLOW= ACTIONS)
        OpenFlyouts trigger = new OpenFlyouts(Driver.getDriver(),
				"{\"moduleName\":\"ANALYTICS\", \"flexName\":\"QUICKLIST\", \"flyoutName\":\"APPROVAL RULES\"}");
		trigger.execute();
		trigger.validate();
	}
}