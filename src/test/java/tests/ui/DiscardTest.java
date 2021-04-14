package tests.ui;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import actions.confirmation.confirmationPopUp;
import actions.discard.Discard;
import actions.login.Login;
import actions.logout.Logout;
import actions.navigatescreen.NavigateScreen;
import actions.revise.Revise;
import selenium.context.Base;
import selenium.driver.Driver;
import selenium.listeners.WebDriverListener;

@Listeners(WebDriverListener.class)
public class DiscardTest extends Base {
	@Test
	public void discardRecord() throws InterruptedException {
		Login loginMBE3E = new Login(Driver.getDriver(), "{\"user\":\"BOTZ.001\"}");
		loginMBE3E.execute();
		loginMBE3E.validate();
		NavigateScreen navigatescreenijysk = new NavigateScreen(Driver.getDriver(), "{\"process\":\"APPROVERS\",\"selectRow\":\"1\",\"column\":[{\"text\":\"OFFBOARDING\",\"title\":\"STATE\"},{\"text\":\"TEAM\",\"title\":\"APPROVER\"}],\"realm\":\"SETTINGS\"}");
		navigatescreenijysk.execute();
		navigatescreenijysk.validate();
//		Revise reviserecordDb1Hf = new Revise(Driver.getDriver(), "{}");
//		reviserecordDb1Hf.execute();
//		reviserecordDb1Hf.validate();
		Discard discardrecordgceC2 = new Discard(Driver.getDriver(), "{}");
		discardrecordgceC2.execute();
		discardrecordgceC2.validate();
		confirmationPopUp checkconfirmationhGfHV = new confirmationPopUp(Driver.getDriver(), "{\"option\":\"YES\"}");
		checkconfirmationhGfHV.execute();
		checkconfirmationhGfHV.validate();
		Logout logout3ufFN = new Logout(Driver.getDriver(), "{}");
		logout3ufFN.execute();
		logout3ufFN.validate();
	}
}
