package actions.sortcolumns;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Sleeper;

import com.google.gson.Gson;

import actions.Actions;
import actions.PageHelper;
import actions.sortcolumns.model.SortColumnModel;
import io.qameta.allure.Step;
import junit.framework.Assert;
import selenium.driver.Driver;

public class SortColumn extends Driver implements Actions {
	private Instant startTime;
	private Instant endTime;
	private WebDriver driver;
	private SortColumnModel sortColumnModel;
	final static Logger logger = Logger.getLogger(SortColumn.class);

	public SortColumn(WebDriver driver, String jsonString) {
		super(driver);
		this.driver = driver;
		this.sortColumnModel = new Gson().fromJson(jsonString, SortColumnModel.class);
	}

	private String columnsHeaderLocator = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.GRID-WGT[style*='z-index: 1'] table.GRID-WGT-HDRS div.GRID-WGT-HDR-DSPLY";
	private String sortingOrder = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.GRID-WGT[style*='z-index: 1'] table.GRID-WGT-HDRS div.GRID-WGT-SORT-TRG";

	@FindBy(css = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.GRID-WGT[style*='z-index: 1'] table.GRID-WGT-HDRS div.GRID-WGT-SORT-TRG.GRID-WGT-SORT-TRG-ACTIVE[title*='SORT DESCENDING']")
	private WebElement ascendingClass;
	
	private String tableRows = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.GRID-WGT[style*='z-index: 1'] div.GRID-IDX-WGT div";
	
	@Override
	public boolean execute() {
		try {
			Thread.sleep(5000);
			startTime = Instant.now();
			setSorting(sortingOrder);
			if(sortColumnModel.getSelectRow()!=null && !sortColumnModel.getSelectRow().isEmpty()) {
				selectRow(sortColumnModel.getSelectRow());
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(e.getMessage(), false);
			return false;
		}
	}

	@Override
	public boolean validate() {
		try {
			validateSortOrder();
			endTime = Instant.now();
			logger.info("[RESPTIME] " + Duration.between(startTime, endTime).toMillis());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(e.getMessage(), false);
			return false;
		}
	}

	@Override
	public void setup() {

	}

	@Override
	public void tearDown() {

	}

	@Step("Fetch the column number of column {filterColumnName}")
	public Integer getColumnNumber() {
		int counter = -1;
		List<WebElement> selectorElements = this.driver.findElements(By.cssSelector(columnsHeaderLocator));
		for (int i = 0; i < selectorElements.size(); i++) {
			String columnName = PageHelper.getText(this.driver, selectorElements.get(i));
			String updatedColumnName = columnName.replace("*", "");
			if ((updatedColumnName.trim()).equals(this.sortColumnModel.getColumnName().trim())) {
				counter = i;
				break;
			}
		}
		return counter;
	}

	@Step("Set order on column")
	public void setSorting(String className) throws InterruptedException {
		int i;
		List<WebElement> lockList = this.driver.findElements(By.cssSelector(className));
		for (i = 0; i < lockList.size(); i++) {
			if (i == getColumnNumber()) {
				if ((this.sortColumnModel.getSortOrder().trim().toUpperCase()).equals("ASCENDING")) {
					PageHelper.clickUsingJs(this.driver, lockList.get(i));
				} else if ((this.sortColumnModel.getSortOrder().trim().toUpperCase()).equals("DESCENDING")) {
					PageHelper.clickUsingJs(this.driver, lockList.get(i));
					Thread.sleep(5000);
					PageHelper.clickUsingJs(this.driver, ascendingClass);
				} else {
					logger.error("Sort order type not exists");
				}

				break;
			} else {

			}
		}
	}

	@Step("validating sort applied on column")
	private void validateSortOrder() {
		List<WebElement> sortElem = this.driver.findElements(By.cssSelector(sortingOrder));
		for (int i = 0; i < sortElem.size(); i++) {
			if (i == getColumnNumber()) {
				if ((this.sortColumnModel.getSortOrder().trim().toUpperCase()).equals("ASCENDING")) {
					String className = sortElem.get(i).getAttribute("class");
					if (className.contains("GRID-WGT-SORT-TRG-UP") && (className.contains("ACTIVE"))) {
						logger.info(
								"Ascending order is active for '" + this.sortColumnModel.getColumnName() + "' column");
					} else {
						logger.error("Either asscending order icon not found or not become active");
						Assert.fail();
					}
					break;
				}
				if ((this.sortColumnModel.getSortOrder().trim().toUpperCase()).equals("DESCENDING")) {
					String className = sortElem.get(i).getAttribute("class");
					if (className.contains("GRID-WGT-SORT-TRG-DWN") && (className.contains("ACTIVE"))) {
						logger.info(
								"Descending order is active for '" + this.sortColumnModel.getColumnName() + "' column");
					} else {
						logger.error("Either descending order icon not found or not become active");
						Assert.fail();
					}
					break;
				}
			} else {

			}
		}
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
