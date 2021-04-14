package actions.flyzoneslists;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import actions.PageHelper;
import io.qameta.allure.Step;

/**
 * 
 * @author Arun.Kapoor
 *
 */

public class FlyZonesListsHelper {
	private WebDriver driver;
	final static Logger logger = Logger.getLogger(FlyZonesListsHelper.class);

	private String flyVerticalVariablesXPath = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.EZ-WGT-FLD-DSPLY-FLYOUT";
	private String flyVerticalValueListIconXPath = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.STND-PAD-BODY div.EZ-WGT-ENTRY-FLD-WRP-FLYOUT div.ENTRYFLD-EZ-WGT div.ENTRYFLD-TRG-EZ-WGT-DROP";
	private String flyListValuesXPath = "div.DROP-WGT-OPTION";

	private String flyZoneTableLocator = "div.DECKLAYER-PARENT[style*='z-index: 1;'] div.FLY div.FLYOUT-CNT-WRP table.GRID-WGT-HDRS";
	private String tableHeader = "div.GRID-WGT-HDR-DSPLY";
	private String table = "div.DECKLAYER-PARENT[style*='z-index: 1;'] div.FLY div.FLYOUT-CNT-WRP div.GRID-WGT-BODY-INNER div.GRID-WGT-TBLS table.TABLE-WGT";
	private String tableRow = "tr.GRID-WGT-ROW";
	private String tableCol = "td.GCO";
	private String flyHorizontalValueListIconXPath = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.FLY div.ENTRYFLD div.ENTRYFLD-TRG-GRID-CELL-EDTR-DROP";
	private String addIcon = "div.DECKLAYER-PARENT[style*='z-index: 1;'] div.GRID-WGT div.GRID-WGD-HDR-CTRL[title*='ADD NEW']";

	public FlyZonesListsHelper(WebDriver driver) {
		this.driver = driver;
	}

	@Step("Fetch the variable number of column {0}")
	public Integer getFieldNumber(String columnName) {
		int counter = -1;
		String variable = null;
		String updateVariable = null;
		List<WebElement> variableElements = this.driver.findElements(By.cssSelector(flyVerticalVariablesXPath));
		for (int i = 0; i < variableElements.size(); i++) {
			variable = PageHelper.getText(this.driver, variableElements.get(i));
			if (variable.contains("*")) {
				updateVariable = variable.replace("*", "");
			} else {
				updateVariable = variable;
			}
			if ((updateVariable.trim()).equals(columnName)) {
				counter = i;
				break;
			}
		}
		return counter;
	}

	@Step("Fetch the column number of column {columnName}")
	public Integer getColumnNumber(String columnName) {
		int counter = -1;
		String updatedColumnName = null;
		WebElement tableElement = this.driver.findElement(By.cssSelector(flyZoneTableLocator));
		PageHelper.waitForElementToBeDisplayed(this.driver, tableElement);
		List<WebElement> selectorElements = tableElement.findElements(By.cssSelector(tableHeader));
		for (int i = 0; i < selectorElements.size(); i++) {
			String colName = PageHelper.getText(this.driver, selectorElements.get(i));
			if (colName.contains("*")) {
				updatedColumnName = colName.replace("*", "");
			} else {
				updatedColumnName = colName;
			}
			if (updatedColumnName.trim().equalsIgnoreCase(columnName)) {
				counter = i;
				break;
			}
		}
		return counter;
	}

	@Step("Click variable list to open")
	public void clickList(String columnName, int index) throws InterruptedException {
		int i;
		List<WebElement> arrowIcon = this.driver.findElements(By.cssSelector(flyVerticalValueListIconXPath));
		for (i = 0; i < arrowIcon.size(); i++) {
			if (i == index) {
				Thread.sleep(5000);
				arrowIcon.get(i).click();
				logger.info("List of '" + columnName + "' clicked");
				break;
			} else {

			}
		}
	}

	@Step("Select value and verify")
	public void readFieldValues(String value, String columnName) {
		String text = null;
		boolean found = false;
		List<WebElement> listValues = this.driver.findElements(By.cssSelector(flyListValuesXPath));
		logger.info("Total values in " + columnName + " list - " + listValues.size());
		for (int i = 0; i < listValues.size(); i++) {
			text = listValues.get(i).getText();
			if (text.trim().toUpperCase().contains(value)) {
				logger.info(value + " exists in " + columnName + " list");
				found = true;
				listValues.get(i).click();
				break;
			}
		}
		if (!found) {
			logger.info(value + " not exists in " + columnName + " list");
		}
	}

//	Method calling readFieldValues
	public void verifyListValues(String listValue, String columnName) throws Exception {
		if (listValue != null && !listValue.isEmpty()) {
			readFieldValues(listValue.trim().toUpperCase(), columnName);
		} else {
			logger.info("Value cannot be blank");
		}
	}

	@Step("Set filter text {filterText} at column {columnNumber}")
	public void setFilterText(int columnNumber, String inputText) throws InterruptedException {
		List<WebElement> getTables = this.driver.findElements(By.cssSelector(table));
		WebElement tab = getTables.get(0);
		List<WebElement> getRows = tab.findElements(By.cssSelector(tableRow));
		WebElement row = getRows.get(0);
		List<WebElement> findCells = row.findElements(By.cssSelector(tableCol));
		for (int i = 0; i < findCells.size(); i++) {
			if (i == columnNumber) {
				PageHelper.waitInSeconds(this.driver, PageHelper.L_TIMEOUT_SEC);
				this.driver.findElement(By.cssSelector("body")).click();
				Thread.sleep(10000);
				PageHelper.click(this.driver, findCells.get(i));
				Thread.sleep(10000);
				this.driver.findElement(By.cssSelector(flyHorizontalValueListIconXPath)).click();
				Thread.sleep(15000);
				break;
			}
		}
	}

	@Step("Click PLUS in flyout")
	public void clickAddNew() throws InterruptedException {
		WebElement plus = this.driver.findElement(By.cssSelector(addIcon));
		if (PageHelper.isElementEnabled(plus)) {
			PageHelper.click(this.driver, plus);
		} else {
			logger.error("PLUS icon is disabled in user rights table");
		}
	}
}
