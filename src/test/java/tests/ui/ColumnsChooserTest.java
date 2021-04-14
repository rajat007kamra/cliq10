package tests.ui;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import actions.columnchooser.ColumnChooser;
import actions.login.Login;
import actions.navigatescreen.NavigateScreen;
import selenium.context.Base;
import selenium.driver.Driver;
import selenium.listeners.WebDriverListener;

@Listeners(WebDriverListener.class)
public class ColumnsChooserTest extends Base {
	@Test
	public void columnChooser() throws InterruptedException
	{
		Login login = new Login(Driver.getDriver(), "{\"password\":\"Monitor@0720\",\"user\":\"ARUN.KAPOOR\",\"subtenant\":\"SE2\"}");
        login.execute();
        login.validate();
		
        NavigateScreen nav = new NavigateScreen(Driver.getDriver(), "{\"realm\": \"ANALYTICS\", \"process\":\"TEMPLATES\"}");
        nav.execute();
        nav.validate();
		
		ColumnChooser columnList = new ColumnChooser(Driver.getDriver(), "");
		columnList.execute();
		columnList.validate();
	}
}