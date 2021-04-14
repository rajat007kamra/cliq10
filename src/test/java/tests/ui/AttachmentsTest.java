package tests.ui;

import actions.attachments.Attachments;
import actions.login.Login;
import actions.navigatescreen.NavigateScreen;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import selenium.context.Base;
import selenium.driver.Driver;
import selenium.listeners.WebDriverListener;

@Listeners(WebDriverListener.class)
public class AttachmentsTest extends Base {
	@Test
	public void Attachment() throws InterruptedException { 
		
		Login login = new Login(Driver.getDriver(), "{\"password\":\"Monitor@0920\",\"user\":\"ARUN.KAPOOR\",\"subtenant\":\"MEZOCLIQ\"}");
        login.execute();
        login.validate();
		
        NavigateScreen nav = new NavigateScreen(Driver.getDriver(), "{\n" +
				"  \"realm\": \"SETTINGS\",\n" +
				"  \"process\": \"REPORT TEMPLATES\",\n" +
				"  \"column\": " +
				"  [\n" +
				"    {\n" +
				"      \"title\": \"STATE\",\n" +
				"      \"text\": \"ACTIVE\"\n" +
				"    },\n" +
				"    {\n" +
				"      \"title\": \"REPORT TEMPLATE\",\n" +
				"      \"text\": \"VIEW ATTACHMENT RECORD\"\n" +
				"    }\n" +
				"  ],\n" +
				"\"selectRow\": \"1\"\n" +
				"}");
        nav.execute();
        nav.validate();

        Attachments view = new Attachments(Driver.getDriver(), 
        		"{\"fileToVerify\":[\"TESTINGEXCEL.XLSX\", \"ZTESTINGEXCEL.XLSX\"], \"isDownload\":\"yes\", \"fileToDownload\":[\"TESTINGEXCEL.XLSX\",\"ZTESTINGEXCEL.XLSX\"]}");
        view.execute();
        view.validate();
	}
}