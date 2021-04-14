package tests.ui;

import actions.directory.Directory;
import actions.login.Login;
import actions.navigatescreen.NavigateScreen;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import selenium.context.Base;
import selenium.driver.Driver;
import selenium.listeners.WebDriverListener;

@Listeners(WebDriverListener.class)
public class DirectoryTest extends Base {
	@Test
	public void equationTest() throws InterruptedException {
		Login login = new Login(Driver.getDriver(), "{\"password\":\"Monitor@0720\",\"user\":\"ARUN.KAPOOR\",\"subtenant\":\"SE2\"}");
        login.execute();
        login.validate();
		
        NavigateScreen nav = new NavigateScreen(Driver.getDriver(), "{\"realm\": \"PROCESSES\", \"process\":\"VENDORS\"}");
        nav.execute();
        nav.validate();
        
        Directory dir = new Directory(Driver.getDriver(), "");
        dir.execute();
        dir.validate();
	}
}