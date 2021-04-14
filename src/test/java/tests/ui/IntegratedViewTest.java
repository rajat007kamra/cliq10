package tests.ui;

import actions.integratedview.IntegratedView;
import actions.login.Login;
import actions.navigatescreen.NavigateScreen;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import selenium.context.Base;
import selenium.driver.Driver;
import selenium.listeners.WebDriverListener;

@Listeners(WebDriverListener.class)
public class IntegratedViewTest extends Base {
	@Test
	public void integratedView() throws InterruptedException { 
		
		Login login = new Login(Driver.getDriver(), "{\"password\":\"Monitor@0720\",\"user\":\"ARUN.KAPOOR\",\"subtenant\":\"SE2\"}");
        login.execute();
        login.validate();
		
        NavigateScreen nav = new NavigateScreen(Driver.getDriver(), "{\n" +
				"  \"realm\": \"PROCESSES\",\n" +
				"  \"process\": \"ENTITIES\",\n" +
				"  \"column\": " +
				"  [\n" +
				"    {\n" +
				"      \"title\": \"STATE\",\n" +
				"      \"text\": \"ACTIVE\"\n" +
				"    },\n" +
				"    {\n" +
				"      \"title\": \"ENTITY\",\n" +
				"      \"text\": \"RYAN PROPERTIES\"\n" +
				"    }\n" +
				"  ],\n" +
				"\"selectRow\": \"1\"\n" +
				"}");
        nav.execute();
        nav.validate();
        
        IntegratedView integrate = new IntegratedView(Driver.getDriver(), "");
        integrate.execute();
        integrate.validate();
	}
}