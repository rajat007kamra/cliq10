package tests.ui;

import actions.login.Login;
import actions.navigatescreen.NavigateScreen;
import actions.uploaddocument.UploadDocument;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import selenium.context.Base;
import selenium.driver.Driver;
import selenium.listeners.WebDriverListener;

@Listeners(WebDriverListener.class)
public class UploadDocumentTest extends Base {
	@Test
	public void uploadAttachment() throws InterruptedException {
		Login login = new Login(Driver.getDriver(),
				"{\"password\":\"Monitor@0720\",\"user\":\"ARUN.KAPOOR\",\"subtenant\":\"SE2\"}");
		login.execute();
		login.validate();

		NavigateScreen nav = new NavigateScreen(Driver.getDriver(),
				"{\"realm\":\"ANALYTICS\",\"process\":\"TEMPLATES\",\"column\":[{\"title\":\"STATE\",\"text\":\"ACTIVE\"},{\"title\":\"TEMPLATE NAME\",\"text\":\"TESTTEMP\"}],\"selectRow\":\"1\"}");
		nav.execute();
		nav.validate();

		UploadDocument upload = new UploadDocument(Driver.getDriver(),
				"{\"path\":\"/Users/Public/report_16.xlsx\", \"name\": \"TESTTEMP2.xlsx\"}");
		upload.execute();
		upload.validate();
	}
}
