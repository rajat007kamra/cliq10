package actions.notifications;

import java.time.Duration;
import java.time.Instant;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import actions.Actions;
import actions.PageHelper;
import io.qameta.allure.Step;
import selenium.driver.Driver;

public class Notifications extends Driver implements Actions {
	private Instant startTime;
	private Instant endTime;
	private WebDriver driver;
	final static Logger logger = Logger.getLogger(Notifications.class);

	public Notifications(WebDriver driver, String jsonString) {
		super(driver);
		this.driver = driver;
	}

	@FindBy(css = "div.DECKLAYER-PARENT[style*='z-index: 1;'] div.NOTIFS-TRG[title='NOTIFICATIONS']")
	private WebElement notificationIcon;

	@FindBy(css = "div.DECKLAYER-PARENT[style*='z-index: 1;'] div.NOTIFDASH-WGT div.NOTIFDASH-WGT-HDR")
	private WebElement notificationHeader;

	@Override
	public boolean execute() {
		try {
			Thread.sleep(3000);
			startTime = Instant.now();
			logger.info("Click on notifications icon");
			clickNotifications();
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return false;
		}
	}

	@Override
	public boolean validate() {
		try {
			verifyNotificationPopUp();
			endTime = Instant.now();
			logger.info("[RESPTIME] " + Duration.between(startTime, endTime).toMillis());
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return false;
		}
	}

	@Override
	public void setup() {

	}

	@Override
	public void tearDown() {

	}

	@Step("Click NOTIFICATIONS icon")
	private void clickNotifications() {
		PageHelper.click(this.driver, notificationIcon);
	}

	@Step("Click NOTIFICATIONS popup")
	private boolean verifyNotificationPopUp() {
		PageHelper.waitForElementToBeDisplayed(this.driver, notificationHeader);
		String headerText = PageHelper.getText(this.driver, notificationHeader);
		logger.info("Notification header found ::- " + headerText);
		if (headerText.contains("NEW NOTIFICATIONS")) {
			return true;
		} else {
			return false;
		}
	}
}
