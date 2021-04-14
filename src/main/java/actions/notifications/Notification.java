package actions.notifications;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.google.gson.Gson;

import actions.Actions;
import actions.notifications.model.Item;
import actions.notifications.model.NotificationModel;
import selenium.driver.Driver;

/**
 * 
 * @author SurendraShekhawat
 *
 */
public class Notification extends Driver implements Actions {
	private Instant startTime;
	private Instant endTime;
	private WebDriver driver;
	private NotificationHelper notificationHelper;
	private NotificationTabHelper notificationTabHelper;
	private NotificationModel notificationModel;
	final static Logger logger = Logger.getLogger(Notification.class);

	public Notification(WebDriver driver, String jsonString) {
		super(driver);
		this.driver = driver;
		notificationHelper = new NotificationHelper(this.driver);
		notificationTabHelper = new NotificationTabHelper(this.driver);
		notificationModel = new Gson().fromJson(jsonString, NotificationModel.class);
	}

	@Override
	public boolean execute() {
		try {
			startTime = Instant.now();
			logger.info("Click at notification Icon");
			notificationHelper.clickNotificationIcon();
			logger.info("Verify that notification icon is active");
			boolean notificationIconActive = notificationHelper.isNotificationIconActive();
			Assert.assertTrue(notificationIconActive, "Notification Icon must be in active state");
			logger.info("Verify that notification widget is open");
			boolean notificationWidgetOpen = notificationHelper.isNotificationWidgetOpen();
			Assert.assertTrue(notificationWidgetOpen, "Notification Widget must be open");

			if (!notificationModel.getTab().isEmpty() && notificationModel.getTab() == null) {
				logger.info("Navigate to tab " + notificationModel.getTab());
			}
			if (!notificationModel.getActionType().isEmpty() && notificationModel.getActionType() == null) {
				if (notificationModel.getActionType().trim().toUpperCase().equals("APPROVE")) {
					logger.info("Click approval all");
					notificationTabHelper.approveAll(notificationModel.getTab());
				} else if (notificationModel.getActionType().trim().toUpperCase().equals("DENY")) {
					logger.info("Click deny all");
					notificationTabHelper.denyAll(notificationModel.getTab());
				}
			}

			if (!notificationModel.getItems().isEmpty() && notificationModel.getItems() == null) {
				List<Item> items = notificationModel.getItems();
				for (Item item : items) {
					logger.info(item.getActionType() + " " + item.getProcess() + " and " + item.getDescription());
					notificationTabHelper.operateRow(item.getProcess(), item.getDescription(),
							item.getActionType().toUpperCase());
				}
			}
		} catch (Exception e) {
			Assert.assertTrue(false, e.getMessage());
			return false;
		}
		return true;
	}

	@Override
	public boolean validate() {
		endTime = Instant.now();
		logger.info("[RESPTIME] " + Duration.between(startTime, endTime).toMillis());
		return false;
	}

	@Override
	public void setup() {

	}

	@Override
	public void tearDown() {

	}
}
