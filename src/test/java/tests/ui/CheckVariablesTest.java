package tests.ui;

import actions.checkvariable.CheckVariable;
import actions.formview.FormView;
import actions.login.Login;
import actions.navigatescreen.NavigateScreen;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import selenium.context.Base;
import selenium.driver.Driver;
import selenium.listeners.WebDriverListener;

@Listeners(WebDriverListener.class)
public class CheckVariablesTest extends Base {
	@Test
	public void checkVariable() throws InterruptedException { 
		
		Login login = new Login(Driver.getDriver(), "{\"password\":\"Monitor@0720\",\"user\":\"ARUN.KAPOOR\",\"subtenant\":\"SE2\"}");
        login.execute();
        login.validate();
		
        NavigateScreen nav = new NavigateScreen(Driver.getDriver(), 
        		"{\n" +
				"  \"realm\": \"ANALYTICS\",\n" +
				"  \"process\": \"TEMPLATES\",\n" +
				"  \"column\": " +
				"  [\n" +
				"    {\n" +
				"      \"title\": \"STATE\",\n" +
				"      \"text\": \"REVISING\"\n" +
				"    },\n" +
				"    {\n" +
				"      \"title\": \"TEMPLATE NAME\",\n" +
				"      \"text\": \"TESTING TEMPLATE_03JUNE\"\n" +
				"    }\n" +
				"  ],\n" +
				"\"selectRow\": \"1\"\n" +
				"}");
        nav.execute();
        nav.validate();
        
        FormView form = new FormView(Driver.getDriver(), "");
        form.execute();
        form.validate();
        
        CheckVariable checkV = new CheckVariable(Driver.getDriver(), 
        		"{\n" + 
        		"  \"column\": [\n" + 
        		"    {\n" + 
        		"      \"variable\": \"TEMPLATE NAME\",\n" + 
        		"      \"expected\": \"TESTING TEMPLATE_03JUNE\"\n" + 
        		"    }\n" +
        		"  ]\n" + 
        		"}");
        checkV.execute();
        checkV.validate();
	}
}