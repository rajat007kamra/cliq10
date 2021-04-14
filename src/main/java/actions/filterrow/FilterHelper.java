package actions.filterrow;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import actions.PageHelper;
import io.qameta.allure.Step;

public class FilterHelper {

	private WebDriver driver;
	private String viewZoneTableLocator = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.GRID-WGT[style*='z-index: 1'] table.GRID-WGT-HDRS";
	private String selectorTable = "div.GRID-WGT-HDR-DSPLY";
	private String filterTable = "input.GRID-WGT-FLTR-TXT[type='text']";
	private String countLocator = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.GRID-WGT[style*='z-index: 1'] span.GRID-MSG-WGT-LABEL";
	private String tableRows = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.GRID-WGT[style*='z-index: 1'] div.GRID-IDX-WGT div";
	
	public FilterHelper(WebDriver driver) {
		this.driver = driver;
	}

	@Step("Fetch the column number of column {filterColumnName}")
	public Integer getColumnNumber(String filterColumnName) {
		int counter = -1;
		WebElement tableElement = driver.findElement(By.cssSelector(viewZoneTableLocator));
		PageHelper.waitForElementToBeDisplayed(this.driver, tableElement);
		List<WebElement> selectorElements = tableElement.findElements(By.cssSelector(selectorTable));
		for (int i = 0; i < selectorElements.size(); i++) {
			String columnName = PageHelper.getText(driver, selectorElements.get(i));
			String updatedColumnName = columnName.replace("*", "");
			if (updatedColumnName.trim().equalsIgnoreCase(filterColumnName)) {
				counter = i;
				break;
			}
		}
		return counter;
	}

	@Step("Set filter text {filterText} at column {columnNumber}")
	public WebElement setFilterText(String filterText, int columnNumber) throws InterruptedException {
		String filter = PageHelper.appendHashCode(this.driver, filterText);
		List<WebElement> filterElements = driver.findElements(By.cssSelector(viewZoneTableLocator + " " + filterTable));
		WebElement filterElement = filterElements.get(columnNumber);
		PageHelper.sendKeys(driver, filterElement, filter);
		Thread.sleep(2000);
		PageHelper.sendKeys(driver, filterElement, Keys.ENTER, false);
		Thread.sleep(8000);
		// TODO Handle if Action under wait message occurs
		return filterElement;
	}

	@Step("Calculate total row count")
	public int getTotalRowCount() {
		PageHelper.waitForElementVisibility(this.driver, By.cssSelector(countLocator), PageHelper.XXX_TIMEOUT_SEC);
		WebElement countElement = driver.findElement(By.cssSelector(countLocator));
		String countString = PageHelper.getText(this.driver, countElement);
		if (countString != null) {
			String rowCount = countString.split("OF")[1].trim().replace(",", "");
			return Integer.parseInt(rowCount);
		}
		return 0;
	}

	@Step("Calculate total displayed row count")
	public int getDisplayedRowCount() {
		PageHelper.waitForElementVisibility(this.driver, By.cssSelector(countLocator), PageHelper.XXX_TIMEOUT_SEC);
		WebElement countElement = driver.findElement(By.cssSelector(countLocator));
		String countString = PageHelper.getText(this.driver, countElement);
		if (countString != null) {
			String rowCount = countString.split("OF")[1].trim().replace(",", "");
			return Integer.parseInt(rowCount);
		}
		return 0;
	}

	@Step("Select row number {rowNumber}")
	public void selectRow(String rowNumber) {
		List<WebElement> tableRowElements = this.driver.findElements(By.cssSelector(tableRows));
		for(int i=0; i<tableRowElements.size(); i++)
		{
			int rowNum = Integer.parseInt(rowNumber)-1;
			if(i==rowNum)
			{
				PageHelper.waitInSeconds(this.driver, PageHelper.XX_TIMEOUT_SEC);
				WebElement rowNuberElement = tableRowElements.get(rowNum);
				PageHelper.clickUsingJs(this.driver, rowNuberElement);
			}
		}
	}
}
