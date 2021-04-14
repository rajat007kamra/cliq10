package actions.flyzoneslists;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.google.gson.Gson;

import actions.Actions;
import actions.PageHelper;
import actions.flyzoneslists.model.Column;
import actions.flyzoneslists.model.FlyZonesListsModel;
import common.variables.CommonVariables;
import selenium.driver.Driver;

public class FlyZonesLists extends Driver implements Actions {
	private WebDriver driver;
	private FlyZonesListsHelper flyZoneListsHelper;
	private FlyZonesListsModel flyZoneListsModel;

	final static Logger logger = Logger.getLogger(FlyZonesLists.class);

	public FlyZonesLists(WebDriver driver, String jsonString) {
		super(driver);
		this.driver = driver;
		this.flyZoneListsHelper = new FlyZonesListsHelper(this.driver);
		this.flyZoneListsModel = new Gson().fromJson(jsonString, FlyZonesListsModel.class);
	}
	
	public FlyZonesLists(WebDriver driver, List<Column> columns) {
		super(driver);
		this.driver = driver;
		this.flyZoneListsHelper = new FlyZonesListsHelper(this.driver);
		this.flyZoneListsModel = new FlyZonesListsModel();
		this.flyZoneListsModel.setColumn(columns);
	}

	@Override
	public boolean execute() {
		try {
			if(this.flyZoneListsModel.getListType().trim().toUpperCase().equals("HORIZONTAL"))
			{
				flyZoneListsHelper.clickAddNew();
				List<Column> columns = this.flyZoneListsModel.getColumn();
				for (Column column : columns) {
					logger.info("Fetch column number in table for given column " + column.getListName());
					int columnNumber = flyZoneListsHelper.getColumnNumber(column.getListName());
					if(columnNumber>=0)
					{
						logger.info(column.getListName() +" exists");
						logger.info("Set text " + column.getListValue() + " in column number " + columnNumber);
						flyZoneListsHelper.setFilterText(columnNumber, column.getListValue());
						flyZoneListsHelper.verifyListValues(column.getListValue(), column.getListName());
						CommonVariables.actionTime = System.currentTimeMillis();
						// Small static wait required to wait for page load initialization
						logger.warn("Static wait introduced");
						PageHelper.waitInSeconds(this.driver, PageHelper.X_TIMEOUT_SEC);
						logger.info("Wait for page to load");
						PageHelper.waitForPageLoad(this.driver);
					}
					else {
						logger.error(column.getListName() +" does not exists");
					}
				}
			}
			else if(this.flyZoneListsModel.getListType().trim().toUpperCase().equals("VERTICAL"))
			{
				List<Column> columns = this.flyZoneListsModel.getColumn();
				for (Column column : columns) {
					logger.info("Fetch column number in table for given column " + column.getListName());
					int columnNumber = flyZoneListsHelper.getFieldNumber(column.getListName());
					if(columnNumber>=0)
					{
						logger.info(column.getListName() +" exists");
						logger.info("Set text " + column.getListValue() + " in column number " + columnNumber);
						flyZoneListsHelper.clickList(column.getListName(), columnNumber);
						flyZoneListsHelper.verifyListValues(column.getListValue(), column.getListName());
						CommonVariables.actionTime = System.currentTimeMillis();
						// Small static wait required to wait for page load initialization
						logger.warn("Static wait introduced");
						PageHelper.waitInSeconds(this.driver, PageHelper.X_TIMEOUT_SEC);
						logger.info("Wait for page to load");
						PageHelper.waitForPageLoad(this.driver);
					}
					else {
						logger.error(column.getListName() +" does not exists");
					}
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
