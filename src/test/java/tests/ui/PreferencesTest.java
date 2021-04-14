package tests.ui;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import actions.login.Login;
import actions.preferences.Preferences;
import selenium.context.Base;
import selenium.driver.Driver;
import selenium.listeners.WebDriverListener;

@Listeners(WebDriverListener.class)
public class PreferencesTest extends Base {
	@Test
	public void preferences() throws InterruptedException {        
        Login login = new Login(Driver.getDriver(), "{\"password\":\"Monitor@0720\",\"user\":\"ARUN.KAPOOR\",\"subtenant\":\"SE2\"}");
        login.execute();
        login.validate();
        
        Preferences prefer = new Preferences(Driver.getDriver(), "{\"preference\":\"reset settings\"}");
        prefer.execute();
        prefer.validate();
	}
}
