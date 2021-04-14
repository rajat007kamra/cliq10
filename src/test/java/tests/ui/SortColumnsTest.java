package tests.ui;

import actions.navigatescreen.NavigateScreen;
import actions.sortcolumns.SortColumn;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import actions.login.Login;
import selenium.context.Base;
import selenium.driver.Driver;
import selenium.listeners.WebDriverListener;

@Listeners(WebDriverListener.class)
public class SortColumnsTest extends Base {
	@Test
	public void sortColumn() throws InterruptedException { 
		
		Login login = new Login(Driver.getDriver(), "{\"password\":\"Monitor@0720\",\"user\":\"ARUN.KAPOOR\",\"subtenant\":\"SE2\"}");
        login.execute();
        login.validate();
		
        NavigateScreen nav = new NavigateScreen(Driver.getDriver(), "{\"realm\":\"PROCESSES\", \"process\":\"TEAMS\"}");
        nav.execute();
        nav.validate();
		
		SortColumn sort = new SortColumn(Driver.getDriver(), "{\"columnName\":\"LEADER\", \"sortOrder\":\"DESCENDING\"}");
		sort.execute();
		sort.validate();
	}
}
