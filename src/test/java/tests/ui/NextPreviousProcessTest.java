package tests.ui;

import actions.login.Login;
import actions.navigatescreen.NavigateScreen;
import actions.nextpreviousprocesses.NextPreviousProcesses;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import selenium.context.Base;
import selenium.driver.Driver;
import selenium.listeners.WebDriverListener;

@Listeners(WebDriverListener.class)
public class NextPreviousProcessTest extends Base {
	@Test
	public void nextPreviousPrevious() throws InterruptedException { 
		
		Login login = new Login(Driver.getDriver(), "{\"password\":\"Monitor@0720\",\"user\":\"ARUN.KAPOOR\",\"subtenant\":\"SE2\"}");
        login.execute();
        login.validate();
		
        NavigateScreen nav = new NavigateScreen(Driver.getDriver(), "{\"realm\": \"ANALYTICS\", \"process\":\"REPORTS\"}");
        nav.execute();
        nav.validate();
        
        NextPreviousProcesses nextPrev = new NextPreviousProcesses(Driver.getDriver(), "{\"processesName\": \"PREVIOUS\"}");
        nextPrev.execute();
	}
}