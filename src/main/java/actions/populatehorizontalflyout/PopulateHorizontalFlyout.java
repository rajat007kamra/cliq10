package actions.populatehorizontalflyout;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.google.gson.Gson;

import actions.Actions;
import actions.PageHelper;
import actions.populatehorizontalflyout.model.Column;
import actions.populatehorizontalflyout.model.PopulateHorizontalFlyoutModel;
import common.variables.CommonVariables;
import selenium.driver.Driver;

/**
 * 
 * @author Arun.Kapoor
 *
 */

public class PopulateHorizontalFlyout extends Driver implements Actions {
	private WebDriver driver;
	private PopulateHorizontalFlyoutHelper horizontlFlyoutHelper;
	private PopulateHorizontalFlyoutModel horizontlFlyoutModel;

	final static Logger logger = Logger.getLogger(PopulateHorizontalFlyout.class);

	public PopulateHorizontalFlyout(WebDriver driver, String jsonString) {
		super(driver);
		this.driver = driver;
		this.horizontlFlyoutHelper = new PopulateHorizontalFlyoutHelper(this.driver);
		this.horizontlFlyoutModel = new Gson().fromJson(jsonString, PopulateHorizontalFlyoutModel.class);
	}

	public PopulateHorizontalFlyout(WebDriver driver, List<Column> columns) {
		super(driver);
		this.driver = driver;
		this.horizontlFlyoutHelper = new PopulateHorizontalFlyoutHelper(this.driver);
		this.horizontlFlyoutModel = new PopulateHorizontalFlyoutModel();
		this.horizontlFlyoutModel.setColumn(columns);
	}

	@Override
	public boolean execute() {
		try {
			horizontlFlyoutHelper.clickAddNew();
			List<Column> columns = this.horizontlFlyoutModel.getColumn();
			for (Column column : columns) {
				logger.info("Fetch column number in table for given column " + column.getTitle());
				int columnNumber = horizontlFlyoutHelper.getColumnNumber(column.getTitle());
				if(columnNumber>=0)
				{
					logger.info("Set text " + column.getText() + " in column number " + columnNumber);
					horizontlFlyoutHelper.setFilterText(columnNumber, PageHelper.appendHashCode(this.driver, column.getText()));
					CommonVariables.actionTime = new Date().getTime();
					// Small static wait required to wait for page load initialization
					logger.warn("Static wait introduced");
					PageHelper.waitInSeconds(this.driver, PageHelper.X_TIMEOUT_SEC);
					logger.info("Wait for page to load");
					PageHelper.waitForPageLoad(this.driver);
				}
				else {
					logger.error(column.getTitle() +" does not exists");
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
		return false;
	}

	@Override
	public void setup() {
		
	}

	@Override
	public void tearDown() {
		
	}

}

