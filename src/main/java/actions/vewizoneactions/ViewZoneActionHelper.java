package actions.vewizoneactions;

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
public class ViewZoneActionHelper {
	private WebDriver driver;
	private String actionLocator = "div.DECKLAYER-PARENT[style*='z-index: 1;'] div.PREFS-WRP > div[title='%1s']";
	private String popUpLocator = "div.cmplt-noti-dlg-lbl.cmplt-noti-update-dlg-lbl.inln-blck.v-mid-algn";
	private String refreshAction = "div.CONFIRMATION-WGT div.CONFIRMATION-BTN-%1S";

	public ViewZoneActionHelper(WebDriver driver) {
		this.driver = driver;
	}

	@Step("Click at {actionName} button")
	public void clickAction(String actionName) throws Exception {
		if (isActionEnabled(actionName)) {
			PageHelper.clickUsingJs(this.driver, getLocatorWebElement(actionName));
		} else {
			throw new Exception("ACTION IS NOT ENABLED");
		}
	}

	@Step("Check if {actionName} button is clicked")
	public boolean isActionClicked(String actionName) {
		WebElement locatorWebElement = getLocatorWebElement(actionName);
		String className = locatorWebElement.getAttribute("class");
		if (className.contains("ACTIVE")) {
			return true;
		}
		return false;
	}

	@Step("Get Popup message text")
	public String getPopUpMessage() {
		By popUpBy = By.cssSelector(popUpLocator);
		PageHelper.waitForElementVisibility(this.driver, popUpBy, PageHelper.XXX_TIMEOUT_SEC);
		WebElement popUpElement = this.driver.findElement(popUpBy);

		return PageHelper.getText(this.driver, popUpElement);
	}

	@Step("Opt for {refresh}")
	public void clickRefreshConfirmation(boolean refresh) {
		String confirmation = "YES";
		if (!refresh) {
			confirmation = "NO";
		}
		By byLocator = By.cssSelector(String.format(refreshAction, confirmation));
		PageHelper.waitForElementVisibility(this.driver, byLocator, PageHelper.XX_TIMEOUT_SEC);
		WebElement elem = this.driver.findElement(byLocator);
		PageHelper.clickUsingJs(this.driver, elem);
	}

	@Step("Check if {actionName} is enabled")
	public boolean isActionEnabled(String actionName) {
		String className = getLocatorWebElement(actionName).getAttribute("class");
		if (className.contains("DISABLED")) {
			return true;
		}
		return false;
	}

	private WebElement getLocatorWebElement(String actionName) {
		By actionBy = By.cssSelector(getActionLocator(actionName));
		PageHelper.waitForElementVisibility(this.driver, actionBy, PageHelper.XXX_TIMEOUT_SEC);

		return this.driver.findElement(actionBy);
	}

	private String getActionLocator(String actionName) {
		switch (actionName) {
		case "REFRESH":
			return String.format(actionLocator, "REFRESH");
		case "RESET":
			return String.format(actionLocator, "RESET");
		case "COLUMN CHOOSER":
			return String.format(actionLocator, "COLUMN CHOOSER");
		case "LOGOUT":
			return String.format(actionLocator, "LOGOUT");
		}
		return null;
	}
}
