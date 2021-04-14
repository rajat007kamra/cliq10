package tests.ui;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import actions.checkmessage.CheckMessage;
import actions.editzone.workbar.AddNew;
import actions.editzone.workbar.Submit;
import actions.login.Login;
import actions.navigatescreen.NavigateScreen;
import actions.openflyouts.OpenFlyouts;
import actions.populateform.PopulateForm;
import actions.populateverticalflyout.PopulateVerticalFlyout;
import selenium.context.Base;
import selenium.driver.Driver;
import selenium.listeners.WebDriverListener;

@Listeners(WebDriverListener.class)
public class SubmitTest extends Base {
	@Test
	public void navigateTest() throws InterruptedException {

		Login login = new Login(Driver.getDriver(),
				"{\"password\":\"Monitor@0720\",\"user\":\"ARUN.KAPOOR\",\"subtenant\":\"SE2\"}");
		login.execute();
		login.validate();

		NavigateScreen nav = new NavigateScreen(Driver.getDriver(),
				"{\"realm\": \"ANALYTICS\", \"process\":\"TEMPLATES\"}");
		nav.execute();
		nav.validate();

		AddNew createNew = new AddNew(Driver.getDriver(), "");
		createNew.execute();
		createNew.validate();

		OpenFlyouts trigger;
		trigger = new OpenFlyouts(Driver.getDriver(), "{\"moduleName\":\"ANALYTICS\", \"flexName\":\"PROFILE\"}");
		trigger.execute();

		PopulateForm enterData = new PopulateForm(Driver.getDriver(),
				"{\"templateTypeField\":\"EXCEL\", \"methodField\":\"BURST MODE\", \"extentField\":\"SPLIT BY\", \"processField\":\"ACCOUNT SETTING\", \"templateNameField\":\"Test Template\"}");
		enterData.execute();

		trigger = new OpenFlyouts(Driver.getDriver(),
				"{\"moduleName\":\"ANALYTICS\", \"flexName\":\"QUICKLIST\", \"flyoutName\":\"REPORT SCHEDULER\"}");
		trigger.execute();
		trigger.validate();

		PopulateVerticalFlyout setFlyout = new PopulateVerticalFlyout(Driver.getDriver(),
				"{\"frequencyField\":\"DAILY\", \"startDate\":\"06/05/2020\"}");
		setFlyout.execute();

		Submit submit = new Submit(Driver.getDriver(), "");
		submit.execute();

		CheckMessage msg = new CheckMessage(Driver.getDriver(), "");
		msg.execute();

		submit.validate();
	}
}
