package actions.filterrow;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.google.gson.Gson;

import actions.Actions;
import actions.PageHelper;
import actions.filterrow.model.Column;
import actions.filterrow.model.FilterModel;
import common.variables.CommonVariables;
import selenium.driver.Driver;

public class FilterRow extends Driver implements Actions {
	private Instant startTime;
	private Instant endTime;
	private WebDriver driver;
//	private int totalRowCount;
//	private int displayedRowCountBeforeFilter;
	private FilterHelper filterHelper;
	private FilterModel filterModel;
	private int retryCount = 3;

	final static Logger logger = Logger.getLogger(FilterRow.class);

	public FilterRow(WebDriver driver, String jsonString) {
		super(driver);
		this.driver = driver;
		this.filterHelper = new FilterHelper(this.driver);
		this.filterModel = new Gson().fromJson(jsonString, FilterModel.class);
	}

	public FilterRow(WebDriver driver, List<Column> columns) {
		super(driver);
		this.driver = driver;
		this.filterHelper = new FilterHelper(this.driver);
		this.filterModel = new FilterModel();
		this.filterModel.setColumn(columns);
	}

	@FindBy(css = "div.cmplt-noti-dlg-lbl.cmplt-noti-dlg-lbl-top")
	private WebElement CheckTopMessage;
	
	private String CheckTopMessageElem = "div.cmplt-noti-dlg-lbl.cmplt-noti-dlg-lbl-top";
	
	@Override
	public boolean execute() {
		try {
			WebElement filterRowElem = null;
			List<Column> columns = this.filterModel.getColumn();
			for (Column column : columns) {
				logger.info("Fetch column number in table for given column " + column.getTitle());
				int columnNumber = filterHelper.getColumnNumber(column.getTitle());
				logger.info("Set text " + column.getText() + " in column number " + columnNumber);
				filterRowElem = filterHelper.setFilterText(column.getText(), columnNumber);
			}
//			PageHelper.waitInSeconds(this.driver, PageHelper.XXX_TIMEOUT_SEC);
			Thread.sleep(10000);
			startTime = Instant.now();
//			PageHelper.sendKeys(driver, filterRowElem, Keys.ENTER, false);
			CommonVariables.actionTime = new Date().getTime();
			// Small static wait required to wait for page load initialization
			logger.warn("Static wait introduced");
			PageHelper.waitInSeconds(this.driver, PageHelper.X_TIMEOUT_SEC);
			logger.info("Wait for page to load");
//			PageHelper.waitForPageLoad(this.driver);
//			System.out.println("working till page load");
//			Thread.sleep(30000);
		} catch (Exception e) {
			Assert.assertTrue(false, e.getMessage());
			return false;
		}
		return true;
	}

	@Override
	public boolean validate() {
		try 
		{
			String errorMsg = null;
			if(this.driver.findElements(By.cssSelector(CheckTopMessageElem)).size()>0)
			{
				CommonVariables.notificationMsg = PageHelper.getText(this.driver, CheckTopMessage);
				logger.info("Notification message found ::- " + CommonVariables.notificationMsg);
				if(CommonVariables.notificationMsg.equals("NO RECORDS FOUND"))
				{
					errorMsg = "NO RECORDS FOUND";
					logger.warn(errorMsg);	Assert.fail(errorMsg);
				}
				else {
					selectViewZoneRow();
					logger.info("Row filtered");
					// TODO retry will be implemented later if required or we need to implement
					// check row data
				}			
			}
			else {
				selectViewZoneRow();
				logger.info("Row filtered");
			}
			endTime = Instant.now();
			logger.info("[RESPTIME] " + Duration.between(startTime, endTime).toMillis());
			PageHelper.waitInSeconds(this.driver, PageHelper.X_TIMEOUT_SEC);
			PageHelper.waitInSeconds(driver, PageHelper.XXX_TIMEOUT_SEC);
			return true;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			Assert.assertTrue(false, e.getMessage());
			return false;
		}
	}

	@Override
	public void setup() {
//		totalRowCount = filterHelper.getTotalRowCount();
//		displayedRowCountBeforeFilter = filterHelper.getDisplayedRowCount();
	}

	@Override
	public void tearDown() {
		logger.info("To be configured later");
	}

	public void selectViewZoneRow()
	{
		if (this.filterModel.getSelectRow() != null && !this.filterModel.getSelectRow().isEmpty()) {
			// Small static wait required to wait for page load initialization
			logger.warn("Static wait introduced");
			PageHelper.waitInSeconds(this.driver, PageHelper.XX_TIMEOUT_SEC);
			logger.info("Wait for page to load");
//			PageHelper.waitForPageLoad(this.driver);
			this.filterHelper.selectRow(this.filterModel.getSelectRow());
			logger.info(this.filterModel.getSelectRow() +" row selected successfully");
		}
	}
}