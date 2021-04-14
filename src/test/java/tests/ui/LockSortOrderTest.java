package tests.ui;

import actions.navigatescreen.NavigateScreen;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import actions.locksortorder.LockSortOrder;
import actions.login.Login;
import selenium.context.Base;
import selenium.driver.Driver;
import selenium.listeners.WebDriverListener;

@Listeners(WebDriverListener.class)
public class LockSortOrderTest extends Base {
	@Test
	public void lockSortOrder() throws InterruptedException { 
		
		Login login = new Login(Driver.getDriver(), "{\"password\":\"Monitor@0720\",\"user\":\"ARUN.KAPOOR\",\"subtenant\":\"SE2\"}");
        login.execute();
        login.validate();
		
        NavigateScreen nav = new NavigateScreen(Driver.getDriver(), "{\"realm\": \"ANALYTICS\", \"process\":\"TEMPLATES\"}");
        nav.execute();
        nav.validate();
		
		LockSortOrder lock = new LockSortOrder(Driver.getDriver(), "{\"column\":[\"PROCESS123\", \"STATE\", \"METHOD\"], \"totalLocked\":\"2\"}");
		lock.execute();
		lock.validate();
	}
}
