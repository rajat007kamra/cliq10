package tests.ui;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import actions.login.Login;
import actions.notifications.Notification;
import selenium.context.Base;
import selenium.driver.Driver;
import selenium.listeners.WebDriverListener;

@Listeners(WebDriverListener.class)
public class NotificationsTest extends Base {
	@Test
	public void notifications() throws InterruptedException {
		Login login = new Login(Driver.getDriver(),
				"{\"password\":\"Monitor@0720\",\"user\":\"ARUN.KAPOOR\",\"subtenant\":\"SE2\"}");
		login.execute();
		login.validate();

		Notification notify = new Notification(Driver.getDriver(), "{\"Tab\":\"APPROVAL\"}");
		notify.execute();
		notify.validate();
	}
}