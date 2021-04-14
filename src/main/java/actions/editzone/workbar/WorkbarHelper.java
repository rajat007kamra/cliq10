package actions.editzone.workbar;

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
public class WorkbarHelper {
	private WebDriver driver;
	private String disabled = "DISABLED";

	public WorkbarHelper(WebDriver driver) {
		this.driver = driver;
	}

	private String actionLocator = "div.HOR-FLEX-EL[title*='%S']";
	private String popupLocator = "div.cmplt-noti-dlg-lbl.cmplt-noti-update-dlg-lbl.inln-blck.v-mid-algn";

	@Step("Click on action {actionName}")
	public void clickAction(String actionName) {
		if (isActionEnabled(actionName)) {
			WebElement actionElem = driver.findElement(By.cssSelector(getActionLocator(actionName)));
			PageHelper.click(this.driver, actionElem);
		}
	}

	@Step("Check if {actionName} button is enabled")
	public boolean isActionEnabled(String actionName) {
		By actionBy = By.cssSelector(getActionLocator(actionName));
		PageHelper.waitForElementVisibility(driver, actionBy, PageHelper.XXX_TIMEOUT_SEC);
		String className = driver.findElement(actionBy).getAttribute("class");
		if (className.contains(disabled)) {
			return true;
		} else {
			return false;
		}
	}

	public String getPopupMessage() {
		By popUpBy = By.cssSelector(popupLocator);
		PageHelper.waitForElementVisibility(this.driver, popUpBy, PageHelper.XXX_TIMEOUT_SEC);
		WebElement popUpElement = this.driver.findElement(popUpBy);

		return PageHelper.getText(this.driver, popUpElement);
	}

	private String getActionLocator(String actionName) {
		switch (actionName) {
		case "ADD":
			return String.format(actionLocator, "ADD NEW");
		case "REVISE":
			return String.format(actionLocator, "REVISE");
		case "SUBMIT":
			return String.format(actionLocator, "SUBMIT");
		case "SAVE":
			return String.format(actionLocator, "SAVE");
		case "DELETE":
			return String.format(actionLocator, "DELETE");
		case "WITHDRAW":
			return String.format(actionLocator, "WITHDRAW");
		case "APPROVE":
			return String.format(actionLocator, "APPROVE");
		case "REJECT":
			return String.format(actionLocator, "REJECT");
		case "DISCARD":
			return String.format(actionLocator, "DISCARD");
		case "CLOSE":
			return String.format(actionLocator, "CLOSE");
		}

		return null;
	}

}
