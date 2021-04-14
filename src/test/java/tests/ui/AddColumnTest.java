package tests.ui;

import actions.navigatescreen.NavigateScreen;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import actions.addcolumn.AddColumn;
import actions.columnchooser.ColumnChooser;
import actions.login.Login;
import selenium.context.Base;
import selenium.driver.Driver;
import selenium.listeners.WebDriverListener;

@Listeners(WebDriverListener.class)
public class AddColumnTest extends Base {
	@Test
	public void addColumn() throws InterruptedException { 
		
		Login login = new Login(Driver.getDriver(), "{\"password\":\"Monitor@0720\",\"user\":\"ARUN.KAPOOR\",\"subtenant\":\"SE2\"}");
        login.execute();
        login.validate();
		
        NavigateScreen nav = new NavigateScreen(Driver.getDriver(), "{\"realm\": \"ADMIN\", \"process\":\"FEEDS\"}");
        nav.execute();
        nav.validate();
		
        ColumnChooser chooser = new ColumnChooser(Driver.getDriver(), "");
        chooser.execute();
        chooser.validate();
        
		AddColumn addCol = new AddColumn(Driver.getDriver(), "{\"column\":[\"\", \"PROCESS DUTY\", \"VERSION SERIAL\"], \"confirmationMessage\":\"REFRESHED!\"}");
		addCol.execute();
		addCol.validate();
	}
}
