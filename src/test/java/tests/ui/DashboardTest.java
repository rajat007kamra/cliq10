package tests.ui;

import actions.login.Login;
import actions.navigatescreen.NavigateScreen;
import actions.openflyouts.OpenFlyouts;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import selenium.context.Base;
import selenium.driver.Driver;
import selenium.listeners.WebDriverListener;

@Listeners(WebDriverListener.class)
public class DashboardTest extends Base {
	@Test
	public void dashboardTest() throws InterruptedException {

		Login login = new Login(Driver.getDriver(),
				"{\"password\":\"Monitor@0720\",\"user\":\"ARUN.KAPOOR\",\"subtenant\":\"SE2\"}");
		login.execute();
		login.validate();

		NavigateScreen nav = new NavigateScreen(Driver.getDriver(), "{\"realm\": \"ANALYTICS\", \"process\":\"DASHBOARDS\"}");
        nav.execute();
        nav.validate();
        
        OpenFlyouts clicktriggerhAUdf = new OpenFlyouts(Driver.getDriver(), "{\"flexname\":\"QUICKLIST\",\"flyoutname\":\"DASHBOARD ASSIGNMENTS\",\"realm\":\"ANALYTICS\"}");
        clicktriggerhAUdf.execute();
        clicktriggerhAUdf.validate();
	}
}