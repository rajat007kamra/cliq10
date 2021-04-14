package tests.ui;

import actions.formview.FormView;
import actions.login.Login;
import actions.navigatescreen.NavigateScreen;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import selenium.context.Base;
import selenium.driver.Driver;
import selenium.listeners.WebDriverListener;

@Listeners(WebDriverListener.class)
public class FormViewTest extends Base {
	@Test
	public void formViews() throws InterruptedException { 
		
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
        
        FormView form = new FormView(Driver.getDriver(), "");
        form.execute();
        form.validate();
	}
}