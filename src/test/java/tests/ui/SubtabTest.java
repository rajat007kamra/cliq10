package tests.ui;

import actions.login.Login;
import actions.navigatescreen.NavigateScreen;
import actions.subtab.Subtab;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import selenium.context.Base;
import selenium.driver.Driver;
import selenium.listeners.WebDriverListener;

@Listeners(WebDriverListener.class)
public class SubtabTest extends Base {
	@Test
	public void navigateTest() throws InterruptedException { 
		
		Login login = new Login(Driver.getDriver(), "{\"password\":\"Monitor@0720\",\"user\":\"ARUN.KAPOOR\",\"subtenant\":\"SE2\"}");
        login.execute();
        login.validate();
		
        NavigateScreen nav = new NavigateScreen(Driver.getDriver(), "{\n" +
				"  \"realm\": \"PROCESSES\",\n" +
				"  \"process\": \"TEAMS\",\n" +
				"  \"column\": [\n" +
				"    {\n" +
				"      \"title\": \"STATE\",\n" +
				"      \"text\": \"ACTIVE\"\n" +
				"    },\n" +
				"    {\n" +
				"      \"title\": \"TEAM\",\n" +
				"      \"text\": \"\"\n" +
				"    }\n" +
				"  ],\n" +
				"\"selectRow\": \"1\"\n" +
				"}");
        nav.execute();
        nav.validate();
        
        Subtab sub = new Subtab(Driver.getDriver(), "{\"title\":\"ACTIVE\"}");
        sub.execute();
        sub.validate();
	}
}