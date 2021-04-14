package actions.notifications;

import java.time.Duration;
import java.time.Instant;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.google.gson.Gson;

import actions.Actions;
import actions.PageHelper;
import actions.notifications.model.NotificationsTabModel;
import io.qameta.allure.Step;
import junit.framework.Assert;
import selenium.driver.Driver;

public class NotificationsTab extends Driver implements Actions {
	private Instant startTime;
	private Instant endTime;
	private WebDriver driver;
	private NotificationsTabModel notifyTabModel;
	final static Logger logger = Logger.getLogger(NotificationsTab.class);

	public NotificationsTab(WebDriver driver, String jsonString) {
		super(driver);
		this.driver = driver;
		this.notifyTabModel = new Gson().fromJson(jsonString, NotificationsTabModel.class);
	}
	
	@FindBy(css = "div.DECKLAYER-PARENT[style*='z-index: 1;'] div.NOTIFDASH-WGT-TAB-PENDING")
	private WebElement pendingTab;
	
	@FindBy (css = "div.DECKLAYER-PARENT[style*='z-index: 1;'] div.NOTIFDASH-WGT-HDR-DUEDATE div.gwt-Label")
	private WebElement verifyPendingTab;
	
	@FindBy(css = "div.DECKLAYER-PARENT[style*='z-index: 1;'] div.NOTIFDASH-WGT-TAB-APPROVAL")
	private WebElement approvalTab;
	
	@FindBy(css = "div.DECKLAYER-PARENT[style*='z-index: 1;'] div.NOTIFDASH-WGT-TAB-ALERT")
	private WebElement alertTab;
	
	@FindBy(xpath = "//div[@class='NOTIFDASH-WGT-INNER']//div[2][@title='CLOSE']")
	private WebElement closeNotification;

	@Override
	public boolean execute() {
		try {
			Thread.sleep(3000);
			startTime = Instant.now();
			logger.info("Click on notifications icon");
			gotoNotificationsTab(this.notifyTabModel.getNotificationTabName());
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return false;
		}
	}

	@Override
	public boolean validate() {
		try {
			verifyNotificationsTab(this.notifyTabModel.getNotificationTabName());
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
	
	@Step("Go to NOTIFICATIONS tab")
	private void gotoNotificationsTab(String tabName) {
		if (tabName.equals("MY QUEUE")) {
			PageHelper.click(this.driver, pendingTab);
		} else if (tabName.equals("APPROVALS")) {
			PageHelper.click(this.driver, approvalTab);
		} else if (tabName.equals("ALERTS")) {
			PageHelper.click(this.driver, alertTab);
		} else {
			logger.error("Notification tab name not exists or not found");
		}
	}
	
	@Step("Validate NOTIFICATIONS tab")
	private void verifyNotificationsTab(String tabName) {
		if (tabName.equals("MY QUEUE")) {
			Assert.assertEquals(PageHelper.getText(this.driver, verifyPendingTab), "DUE DATE");
		} else if (tabName.equals("APPROVALS")) {
			logger.info("No unique identification found for approvals tab yet will update");			
		} else if (tabName.equals("ALERTS")) {
			logger.info("No unique identification found for alerts tab yet will update");			
		} else {
			logger.error("Notification tab name not exists or not found");
		}
		logger.info("close notifications popup");
		PageHelper.click(this.driver, closeNotification);
	}
}
