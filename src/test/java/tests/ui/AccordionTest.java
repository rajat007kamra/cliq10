package tests.ui;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import actions.accordion.Accordion;
import actions.login.Login;
import selenium.context.Base;
import selenium.driver.Driver;
import selenium.listeners.WebDriverListener;

@Listeners(WebDriverListener.class)
public class AccordionTest extends Base{
	@Test
	public void accordion() throws InterruptedException {        
        Login login = new Login(Driver.getDriver(), "{\"password\":\"Monitor@0720\",\"user\":\"ARUN.KAPOOR\",\"subtenant\":\"SE2\"}");
        login.execute();
        login.validate();
        
        Accordion accord = new Accordion(Driver.getDriver(), "{\"accordion\":\"right\", \"accordionState\":\"collapse\"}");
        accord.execute();
        accord.validate();
	}
}