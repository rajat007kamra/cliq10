package tests.ui;

import actions.navigatescreen.NavigateScreen;
import actions.removefavorites.RemoveFavorites;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import actions.login.Login;
import selenium.context.Base;
import selenium.driver.Driver;
import selenium.listeners.WebDriverListener;

@Listeners(WebDriverListener.class)
public class RemoveFavoritesTest extends Base {
	@Test
	public void removeColumn() throws InterruptedException { 
		
		Login login = new Login(Driver.getDriver(), "{\"password\":\"Monitor@0720\",\"user\":\"ARUN.KAPOOR\",\"subtenant\":\"SE2\"}");
        login.execute();
        login.validate();
		
        NavigateScreen nav = new NavigateScreen(Driver.getDriver(), "{\"realm\": \"ANALYTICS\", \"process\":\"TEMPLATES\"}");
        nav.execute();
        nav.validate();
        
		RemoveFavorites removeFav = new RemoveFavorites(Driver.getDriver(), "{\"column\":[\"ABC\", \"TESTING123\"]}");
		removeFav.execute();
		removeFav.validate();
	}
}