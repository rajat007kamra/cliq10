package tests.ui;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import actions.login.Login;
import actions.navigatescreen.NavigateScreen;
import actions.record.Record;
import selenium.context.Base;
import selenium.driver.Driver;
import selenium.listeners.WebDriverListener;

@Listeners(WebDriverListener.class)
public class RecordTest extends Base {
	@Test
	public void recordTest() throws InterruptedException {

		Login login = new Login(Driver.getDriver(),
				"{\"password\":\"Monitor@0720\",\"user\":\"ARUN.KAPOOR\",\"subtenant\":\"SE2\"}");
		login.execute();
		login.validate();

		 NavigateScreen navigatescreenWb0EK = new NavigateScreen(Driver.getDriver(), "{\"process\":\"TEMPLATES\",\"column\":[{\"text\":\"QUERY TEMP\",\"title\":\"TEMPLATE NAME\"}],\"realm\":\"ANALYTICS\"}");
	    navigatescreenWb0EK.execute();
	    navigatescreenWb0EK.validate();
	    
	    Record record = new Record(Driver.getDriver(), "{\"RowNumber\":\"1\",\"option\":\"CLONE\"}");
	    record.execute();
	    record.validate();
	}
}
