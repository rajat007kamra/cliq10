package actions.notifications;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import actions.PageHelper;
import io.qameta.allure.Step;

/**
 * 
 * @author SurendraShekhawat
 *
 */
public class NotificationHelper {
	private WebDriver driver;
	private String notificationIcon = "div.DECKLAYER-PARENT[style*='z-index: 1;'] div.NOTIFS-TRG[title='NOTIFICATIONS']";
	private String notificationIconActive = "div.DECKLAYER-PARENT[style*='z-index: 1;'] div.TRG-BASE-ACTIVE[title='NOTIFICATIONS']";
	private String notificationPopup = "div.NOTIFDASH-WGT";
	private String refreshLocator = "div.DECKLAYER-PARENT[style*='z-index: 1;'] div.NOTIFDASH-WGT-INNER > div.FLY-TOP-TRG[title='REFRESH']";
	private String closeLocator = "div.DECKLAYER-PARENT[style*='z-index: 1;'] div.NOTIFDASH-WGT-INNER > div.FLY-TOP-TRG[title='CLOSE']";
	private String popUpHeader = "div.DECKLAYER-PARENT[style*='z-index: 1;'] div.NOTIFDASH-WGT-INNER > div.NOTIFDASH-WGT-HDR";

	public NotificationHelper(WebDriver driver) {
		this.driver = driver;
	}

	@Step("Click at notification icon")
	public void clickNotificationIcon() {
		By notificationBy = By.cssSelector(notificationIcon);
		PageHelper.waitForElementVisibility(this.driver, notificationBy, PageHelper.XX_TIMEOUT_SEC);
		WebElement notificationElem = this.driver.findElement(notificationBy);
		PageHelper.clickUsingJs(this.driver, notificationElem);
	}

	@Step("Check if notification Icon is active")
	public boolean isNotificationIconActive() {
		By notificationActiveBy = By.cssSelector(notificationIconActive);
		PageHelper.waitForElementVisibility(this.driver, notificationActiveBy, PageHelper.XXX_TIMEOUT_SEC);
		WebElement notificationActiveElem = this.driver.findElement(notificationActiveBy);
		return PageHelper.isElementDisplayed(notificationActiveElem);
	}

	@Step("Check if notification widget is open")
	public boolean isNotificationWidgetOpen() {
		By notificationWgteBy = By.cssSelector(notificationPopup);
		PageHelper.waitForElementVisibility(this.driver, notificationWgteBy, PageHelper.XXX_TIMEOUT_SEC);
		WebElement notificationWgtElem = this.driver.findElement(notificationWgteBy);
		return PageHelper.isElementDisplayed(notificationWgtElem);
	}

	@Step("Click to refresh the notification widget")
	public void clickNotificationWidgetRefresh() {
		WebElement refreshElem = this.driver.findElement(By.cssSelector(refreshLocator));
		PageHelper.clickUsingJs(this.driver, refreshElem);
	}

	@Step("Click to close the notofication widget")
	public void clickNotificationWidgetClose() {
		WebElement closeElem = this.driver.findElement(By.cssSelector(closeLocator));
		PageHelper.clickUsingJs(this.driver, closeElem);
	}

	@Step("Fetch the text from notification header")
	public String getNotificationWidgetHeader() {
		WebElement headerElem = this.driver.findElement(By.cssSelector(popUpHeader));
		return PageHelper.getText(this.driver, headerElem);
	}
}
