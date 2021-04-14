package tests.ui;

import actions.checkmessage.CheckMessage;
import actions.checkvariable.CheckVariable;
import actions.formview.FormView;
import actions.login.Login;
import actions.navigatescreen.NavigateScreen;
import actions.populateform.PopulateForm;
import actions.revise.Revise;
import actions.submit.Submit;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import selenium.context.Base;
import selenium.driver.Driver;
import selenium.listeners.WebDriverListener;

@Listeners(WebDriverListener.class)
public class UpdateVariablesTest extends Base {
	@Test
	public void checkVariable() throws InterruptedException { 
		
		Login login = new Login(Driver.getDriver(), "{\"password\":\"Monitor@0920\",\"user\":\"ARUN.KAPOOR\",\"subtenant\":\"MEZOCLIQ\"}");
        login.execute();
        login.validate();
		
        NavigateScreen nav = new NavigateScreen(Driver.getDriver(), 
        		"{\n" +
				"  \"realm\": \"SETTINGS\",\n" +
				"  \"process\": \"TEMPLATES\",\n" +
				"  \"column\": " +
				"  [\n" +
				"    {\n" +
				"      \"title\": \"STATE\",\n" +
				"      \"text\": \"ACTIVE\"\n" +
				"    },\n" +
				"    {\n" +
				"      \"title\": \"REPORT TEMPLATE\",\n" +
				"      \"text\": \"REVISE JSON TEMPLATE\"\n" +
				"    }\n" +
				"  ],\n" +
				"\"selectRow\": \"1\"\n" +
				"}");
        nav.execute();
        nav.validate();
        
        Revise revise = new Revise(Driver.getDriver(), "");
        revise.execute();
        revise.validate();
        
        FormView form = new FormView(Driver.getDriver(), "");
        form.execute();
        form.validate();
        
        CheckVariable check1 = new CheckVariable(Driver.getDriver(), "{\"variable\":\"TEMPLATE NAME\",\"expected\":\"REVISE JSON TEMPLATE\"}");
        check1.execute();
        check1.validate();
        
        PopulateForm update = new PopulateForm(Driver.getDriver(), 
        		"{\n" + 
        		"  \"column\": [\n" + 
        		"    {\n" + 
        		"      \"title\": \"TEMPLATE NAME\",\n" + 
        		"      \"text\": \"UPDATE REVISE JSON TEMPLATE\"\n" + 
        		"    }\n" +
        		"  ]\n" + 
        		"}");
        update.execute();
//        update.validate();
        
        Submit submit = new Submit(Driver.getDriver(), "");
		submit.execute();

		CheckMessage msg = new CheckMessage(Driver.getDriver(),
				"{\"actionName\":\"submit\", \"message\":\"SUBMITTED!\"}");
		msg.execute();
        
        CheckVariable check2 = new CheckVariable(Driver.getDriver(), "{\"variable\":\"TEMPLATE NAME\",\"expected\":\"UPDATE REVISE JSON TEMPLATE\"}");
        check2.execute();
        check2.validate();        
	}
}