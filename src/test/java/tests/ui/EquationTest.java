package tests.ui;

import actions.equation.Equation;
import actions.login.Login;
import actions.navigatescreen.NavigateScreen;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import selenium.context.Base;
import selenium.driver.Driver;
import selenium.listeners.WebDriverListener;

@Listeners(WebDriverListener.class)
public class EquationTest extends Base {
	@Test
	public void equationTest() throws InterruptedException {
		Login login = new Login(Driver.getDriver(), "{\"password\":\"Monitor@0720\",\"user\":\"ARUN.KAPOOR\",\"subtenant\":\"SE2\"}");
        login.execute();
        login.validate();
		
        NavigateScreen nav = new NavigateScreen(Driver.getDriver(), "{\"realm\": \"ADMIN\", \"process\":\"DICTIONARY\"}");
        nav.execute();
        nav.validate();
        
        Equation equat = new Equation(Driver.getDriver(), "");
        equat.execute();
        equat.validate();
	}
}