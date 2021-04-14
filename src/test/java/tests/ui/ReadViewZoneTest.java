package tests.ui;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import actions.login.Login;
import actions.navigatescreen.NavigateScreen;
import actions.readflyoutdata.ReadFlyoutData;
import selenium.context.Base;
import selenium.driver.Driver;
import selenium.listeners.WebDriverListener;

@Listeners(WebDriverListener.class)
public class ReadViewZoneTest extends Base {
	@Test
	public void readViewZoneTest() throws InterruptedException {

		Login login = new Login(Driver.getDriver(),
				"{\"password\":\"Monitor@0720\",\"user\":\"ARUN.KAPOOR\",\"subtenant\":\"SE2\"}");
		login.execute();
		login.validate();

		 NavigateScreen navigatescreenWb0EK = new NavigateScreen(Driver.getDriver(), "{\"process\":\"TEMPLATES\",\"column\":[{\"text\":\"QUERY TEMP\",\"title\":\"TEMPLATE NAME\"}],\"realm\":\"ANALYTICS\"}");
		    navigatescreenWb0EK.execute();
		    navigatescreenWb0EK.validate();

		ReadFlyoutData read = new ReadFlyoutData(Driver.getDriver(), "{\"column\":[{\"expected\":\"HOLDING\",\"title\":\"PROCESS\"}]}");
		read.execute();
		read.validate();
	}
}
