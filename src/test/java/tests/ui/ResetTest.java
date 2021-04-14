package tests.ui;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import actions.confirmation.confirmationPopUp;
import actions.login.Login;
import actions.navigatescreen.NavigateScreen;
import actions.reset.Reset;
import selenium.context.Base;
import selenium.driver.Driver;
import selenium.listeners.WebDriverListener;

@Listeners(WebDriverListener.class)
public class ResetTest extends Base {
	@Test
	public void reset() throws InterruptedException {
		Login login = new Login(Driver.getDriver(),
				"{\"password\":\"Monitor@0720\",\"user\":\"ARUN.KAPOOR\",\"subtenant\":\"SE2\"}");
		login.execute();
		login.validate();

		NavigateScreen nav = new NavigateScreen(Driver.getDriver(),
				"{\"realm\": \"ANALYTICS\", \"process\":\"TEMPLATES\"}");
		nav.execute();
		nav.validate();

		Reset reset = new Reset(Driver.getDriver(), "");
		reset.execute();
		reset.validate();
		
		confirmationPopUp confirm = new confirmationPopUp(Driver.getDriver(), "{\"option\":\"YES\"}");
		confirm.execute();
		
	}
}
