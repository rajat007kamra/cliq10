package tests.ui;

import actions.navigatescreen.NavigateScreen;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import actions.columnchooser.ColumnChooser;
import actions.columnordering.ColumnOrdering;
import actions.login.Login;
import selenium.context.Base;
import selenium.driver.Driver;
import selenium.listeners.WebDriverListener;

@Listeners(WebDriverListener.class)
public class ColumnOrderingTest extends Base {
	@Test
	public void columnOrdering() throws InterruptedException { 
		
		Login login = new Login(Driver.getDriver(), "{\"password\":\"Monitor@0720\",\"user\":\"ARUN.KAPOOR\",\"subtenant\":\"SE2\"}");
        login.execute();
        login.validate();
		
        NavigateScreen nav = new NavigateScreen(Driver.getDriver(), "{\"realm\": \"ANALYTICS\", \"process\":\"TEMPLATES\"}");
        nav.execute();
        nav.validate();
		
        ColumnChooser chooser = new ColumnChooser(Driver.getDriver(), "");
        chooser.execute();
        chooser.validate();
        
//    	"{\"removeColumnName\":\"RECURRENCE\"}");
		ColumnOrdering order = new ColumnOrdering(Driver.getDriver(), "{\"column\":\"PROCESS\", \"location\":\"5\"}");
		order.execute();
//		order.validate();
	}
}
