package tests.ui;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import actions.confirmation.confirmationPopUp;
import actions.login.Login;
import actions.navigatescreen.NavigateScreen;
import actions.refresh.Refresh;
import selenium.context.Base;
import selenium.driver.Driver;
import selenium.listeners.WebDriverListener;

@Listeners(WebDriverListener.class)
public class RefreshTest extends Base {
	@Test
	public void refresh() throws InterruptedException
	{
		Login login = new Login(Driver.getDriver(), "{\"password\":\"Monitor@0720\",\"user\":\"ARUN.KAPOOR\",\"subtenant\":\"SE2\"}");
        login.execute();
        login.validate();
		
        NavigateScreen nav = new NavigateScreen(Driver.getDriver(), "{\"realm\": \"ANALYTICS\", \"process\":\"REPORTS\"}");
        nav.execute();
        nav.validate();
		
		Refresh refresh = new Refresh(Driver.getDriver(), "{\"message\":\"REFRESHED!\"}");
		refresh.execute();
		
		confirmationPopUp confirm = new confirmationPopUp(Driver.getDriver(), "{\"option\":\"YES\"}");
        confirm.execute();
        
        refresh.validate();
	}
}
