package actions.populatehorizontalflyout;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import actions.PageHelper;
import io.qameta.allure.Step;

/**
 * 
 * @author Arun.Kapoor
 *
 */

public class PopulateHorizontalFlyoutHelper {
	private WebDriver driver;
	final static Logger logger = Logger.getLogger(PopulateHorizontalFlyoutHelper.class);

	private String viewZoneTableLocator = "div.DECKLAYER-PARENT[style*='z-index: 1;'] div.FLY div.FLYOUT-CNT-WRP table.GRID-WGT-HDRS";
	private String tableHeader = "div.GRID-WGT-HDR-DSPLY";
	private String table = "div.DECKLAYER-PARENT[style*='z-index: 1;'] div.FLY div.FLYOUT-CNT-WRP div.GRID-WGT-BODY-INNER div.GRID-WGT-TBLS table.TABLE-WGT";
	private String tableRow = "tr.GRID-WGT-ROW";
	private String tableCol = "td.GCO";
	private String cellInput = "input.ENTRYFLD-TXT-GRID-CELL-EDTR";
	private String addIcon = "div.DECKLAYER-PARENT[style*='z-index: 1;'] div.GRID-WGT div.GRID-WGD-HDR-CTRL[title*='ADD NEW']";

	public PopulateHorizontalFlyoutHelper(WebDriver driver) {
		this.driver = driver;
	}

	@Step("Fetch the column number of column {columnName}")
	public Integer getColumnNumber(String columnName) {
		int counter = -1;
		WebElement tableElement = this.driver.findElement(By.cssSelector(viewZoneTableLocator));
		PageHelper.waitForElementToBeDisplayed(this.driver, tableElement);
		List<WebElement> selectorElements = tableElement.findElements(By.cssSelector(tableHeader));
		for (int i = 0; i < selectorElements.size(); i++) {
			String colName = PageHelper.getText(this.driver, selectorElements.get(i));
			String updatedColumnName = colName.replace("*", "");
			if (updatedColumnName.trim().equalsIgnoreCase(columnName)) {
				counter = i;
				break;
			}
		}
		return counter;
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
				Thread.sleep(10000);
				PageHelper.click(this.driver, findCells.get(i));
				Thread.sleep(5000);
				List<WebElement> findInputBox = row.findElements(By.cssSelector(cellInput));
				WebElement setText = findInputBox.get(0);
				PageHelper.locateElement(this.driver, setText);
				PageHelper.sendKeys(this.driver, setText, inputText);
				Thread.sleep(5000);
				setText.sendKeys(Keys.TAB);
				row.click();
				break;
			}
		}
	}

	@Step("Click PLUS in user rights table")
	public void clickAddNew() throws InterruptedException {
		WebElement plus = this.driver.findElement(By.cssSelector(addIcon));
		if (PageHelper.isElementEnabled(plus)) {
			PageHelper.click(this.driver, plus);
		} else {
			logger.error("PLUS icon is disabled in user rights table");
		}
	}
}