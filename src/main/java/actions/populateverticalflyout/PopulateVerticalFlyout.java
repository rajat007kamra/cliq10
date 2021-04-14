package actions.populateverticalflyout;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.google.gson.Gson;

import actions.Actions;
import actions.PageHelper;
import actions.populateverticalflyout.model.Column;
import actions.populateverticalflyout.model.PopulateVerticalFlyoutModel;
import common.variables.CommonVariables;
import selenium.driver.Driver;

/**
 * 
 * @author Arun.Kapoor
 *
 */

public class PopulateVerticalFlyout extends Driver implements Actions {
	private Instant startTime;
	private Instant endTime;
	private WebDriver driver;
	private PopulateVerticalFlyoutHelper verticalFlyoutHelper;
	private PopulateVerticalFlyoutModel verticalFlyoutModel;

	final static Logger logger = Logger.getLogger(PopulateVerticalFlyout.class);

	public PopulateVerticalFlyout(WebDriver driver, String jsonString) {
		super(driver);
		this.driver = driver;
		this.verticalFlyoutHelper = new PopulateVerticalFlyoutHelper(this.driver);
		this.verticalFlyoutModel = new Gson().fromJson(jsonString, PopulateVerticalFlyoutModel.class);
	}

	public PopulateVerticalFlyout(WebDriver driver, List<Column> columns) {
		super(driver);
		this.driver = driver;
		this.verticalFlyoutHelper = new PopulateVerticalFlyoutHelper(this.driver);
		this.verticalFlyoutModel = new PopulateVerticalFlyoutModel();
		this.verticalFlyoutModel.setColumn(columns);
	}

	@Override
	public boolean execute() {
		try {
			startTime = Instant.now();
			String flexHeader = this.verticalFlyoutModel.getFlexName().trim().toUpperCase();
			if (flexHeader != null && !flexHeader.isEmpty()) 
			{
				if(flexHeader.equals("PROFILE")) 
				{
					List<Column> columns = this.verticalFlyoutModel.getColumn();
					for (Column column : columns) {
						verticalFlyoutHelper.SetProfileFields(column.getVariable().trim().toUpperCase(), column.getValue().trim().toUpperCase());
					}
				}
				else if(flexHeader.equals("SUMMARY") || flexHeader.equals("QUICKLIST") || flexHeader.equals("WORKFLOW")) 
				{
					List<Column> columns = this.verticalFlyoutModel.getColumn();
					for (Column column : columns) {
						logger.info("Fetch column number in table for given column " + column.getVariable());
						int columnNumber = verticalFlyoutHelper.getFieldNumber(flexHeader, column.getVariable());
						if(columnNumber>=0)
						{
							logger.info("Set text " + column.getValue() + " in column number " + columnNumber);
							verticalFlyoutHelper.setFieldValue(flexHeader, column.getValue(), columnNumber);
							CommonVariables.actionTime = new Date().getTime();
							// Small static wait required to wait for page load initialization
							logger.warn("Static wait introduced");
							PageHelper.waitInSeconds(this.driver, PageHelper.X_TIMEOUT_SEC);
							logger.info("Wait for page to load");
							PageHelper.waitForPageLoad(this.driver);
						}
						else {
							Assert.assertTrue(false, column.getVariable() +" does not exists");
						}
					}
				}
				else {
					
				}
				
			} else {
				logger.error("Flex name is mandatory");
			}
		} catch (Exception e) {
			Assert.assertTrue(false, e.getMessage());
			return false;
		}
		return true;
	}

	@Override
	public boolean validate() {
		try {		
			endTime = Instant.now();
			logger.info("[RESPTIME] " + Duration.between(startTime, endTime).toMillis());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false, e.getMessage());
			return false;
		}
	}

	@Override
	public void setup() {
		
	}

	@Override
	public void tearDown() {
		
	}
}