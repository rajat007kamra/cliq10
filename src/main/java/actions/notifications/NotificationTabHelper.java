package actions.notifications;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import actions.PageHelper;
import io.qameta.allure.Step;

/**
 * 
 * @author SurendraShekhawat
 *
 */
public class NotificationTabHelper {
	private WebDriver driver;
	private String notificationTabs = "div.DECKLAYER-PARENT[style*='z-index: 1;'] div.NOTIFDASH-WGT-TAB";
	private String notificationTabsActive = "div.DECKLAYER-PARENT[style*='z-index: 1;'] div.NOTIFDASH-WGT-TAB-ACTIVE";
	private String approvelAllLocator = "div.DECKLAYER-PARENT[style*='z-index: 1;'] div.NOTIFDASH-WGT-COLUMNHEAD div.NOTIFDASH-WGT-CELL-APPROVE";
	private String denyAllLocator = "div.DECKLAYER-PARENT[style*='z-index: 1;'] div.NOTIFDASH-WGT-COLUMNHEAD div.NOTIFDASH-WGT-CELL-REJECT";
	private String processFilterLocator = "div.DECKLAYER-PARENT[style*='z-index: 1;'] div.NOTIFDASH-WGT-FLT-TABLENAME > input";
	private String descriptionFilterLocator = "div.DECKLAYER-PARENT[style*='z-index: 1;'] div.NOTIFDASH-WGT-FLT-CAPTION > input";
	private String rowsLocator = "div.DECKLAYER-PARENT[style*='z-index: 1;'] div.NOTIFDASH-WGT-ITEM";
	private String processColumnLocator = "div.NOTIFDASH-WGT-CELL-TABLENAME";
	private String descColumnLocator = "div.NOTIFDASH-WGT-CELL-CAPTION";
	private String approveRowLocator = "div.NOTIFDASH-WGT-CELL-APPROVE";
	private String denyRowLocator = "div.NOTIFDASH-WGT-CELL-REJECT";

	public NotificationTabHelper(WebDriver driver) {
		this.driver = driver;
	}

	@Step("Got to tab {tabName}")
	public void gotToTab(String tabName) throws Exception {
		List<WebElement> tabElemnts = this.driver.findElements(By.cssSelector(notificationTabs));
		for (WebElement tabElem : tabElemnts) {
			String tab = PageHelper.getText(this.driver, tabElem);
			if (tab.trim().contains(tabName)) {
				PageHelper.clickUsingJs(this.driver, tabElem);
				WebElement activeELem = this.driver.findElement(By.cssSelector(notificationTabsActive));
				PageHelper.waitForElementToBeDisplayed(this.driver, activeELem);
				String activeTab = PageHelper.getText(this.driver, activeELem);
				if (!activeTab.trim().contains(tabName)) {
					throw new Exception("Tab " + tabName + " is not active");
				}
				break;
			}
		}
	}

	@Step("Click approve all for tab {tabeName}")
	public void approveAll(String tabName) {
		WebElement approveAllElem = this.driver.findElement(By.cssSelector(approvelAllLocator));
		PageHelper.clickUsingJs(this.driver, approveAllElem);
		WebElement rowElm = this.driver.findElement(By.cssSelector(rowsLocator));
		PageHelper.waitForInvisiblity(this.driver, rowElm);
	}

	@Step("Click Deny all for tab {tabName}")
	public void denyAll(String tabName) {
		WebElement denyAllElem = this.driver.findElement(By.cssSelector(denyAllLocator));
		PageHelper.clickUsingJs(this.driver, denyAllElem);
		WebElement rowElm = this.driver.findElement(By.cssSelector(rowsLocator));
		PageHelper.waitForInvisiblity(this.driver, rowElm);
	}

	@Step("approve {approve} or Deny {deny} row with process name {process} and description {description}")
	public void operateRow(String process, String description, String actionType) throws Exception {
		filterRow(process, description);
		WebElement filterRow = getFilterRow(process, description);
		if (actionType.equalsIgnoreCase("APPROVE")) {
			WebElement approveElem = filterRow.findElement(By.cssSelector(approveRowLocator));
			PageHelper.clickUsingJs(this.driver, approveElem);
		} else if (actionType.equalsIgnoreCase("DENY") || actionType.equalsIgnoreCase("REJECT")) {
			WebElement denyElem = filterRow.findElement(By.cssSelector(denyRowLocator));
			PageHelper.clickUsingJs(this.driver, denyElem);
		}
		PageHelper.waitForInvisiblity(this.driver, filterRow);
	}

	@Step("Filter row witth process {process} and description {description}")
	private void filterRow(String process, String description) throws Exception {
		setPorcessFilter(process);
		setDescriptionFilter(description);
		PageHelper.waitInSeconds(this.driver, PageHelper.X_TIMEOUT_SEC);
		boolean rowFiltered = isRowFiltered(process, description);
		if (!rowFiltered) {
			throw new Exception("Row is not filtered");
		}
	}

	@Step("Check if filtered row contains process {process} and description {description}")
	private WebElement getFilterRow(String process, String description) {
		List<WebElement> rowElms = this.driver.findElements(By.cssSelector(rowsLocator));
		WebElement rowElm = rowElms.get(0);
		return rowElm;
	}

	private void setPorcessFilter(String filterText) {
		By processBy = By.cssSelector(processFilterLocator);
		PageHelper.waitForElementVisibility(this.driver, processBy, PageHelper.XX_TIMEOUT_SEC);
		WebElement processElem = this.driver.findElement(processBy);
		PageHelper.sendKeys(this.driver, processElem, filterText);
		PageHelper.sendKeys(this.driver, processElem, Keys.ENTER, false);
	}

	private void setDescriptionFilter(String descText) {
		By descBy = By.cssSelector(descriptionFilterLocator);
		PageHelper.waitForElementVisibility(this.driver, descBy, PageHelper.XX_TIMEOUT_SEC);
		WebElement descElem = this.driver.findElement(descBy);
		PageHelper.sendKeys(this.driver, descElem, descText);
		PageHelper.sendKeys(this.driver, descElem, Keys.ENTER, false);
	}

	private boolean isRowFiltered(String process, String description) {
		List<WebElement> rowElms = this.driver.findElements(By.cssSelector(rowsLocator));
		WebElement rowElm = rowElms.get(0);
		WebElement processColElm = rowElm.findElement(By.cssSelector(processColumnLocator));
		WebElement descColElm = rowElm.findElement(By.cssSelector(descColumnLocator));
		String processName = PageHelper.getText(this.driver, processColElm);
		String descName = PageHelper.getText(this.driver, descColElm);
		if (processName.contains(process) && descName.contains(description)) {
			return true;
		}
		return false;
	}

}
