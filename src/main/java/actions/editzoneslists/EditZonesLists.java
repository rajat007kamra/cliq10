package actions.editzoneslists;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.google.gson.Gson;

import actions.Actions;
import actions.PageHelper;
import actions.editzoneslists.model.Column;
import actions.editzoneslists.model.EditZonesListsModel;
import common.variables.CommonVariables;
import selenium.driver.Driver;

public class EditZonesLists extends Driver implements Actions {
	private WebDriver driver;
	private EditZoneListsHelper editZoneListsHelper;
	private EditZonesListsModel editZonesListsModel;

	final static Logger logger = Logger.getLogger(EditZonesLists.class);

	public EditZonesLists(WebDriver driver, String jsonString) {
		super(driver);
		this.driver = driver;
		this.editZoneListsHelper = new EditZoneListsHelper(this.driver);
		this.editZonesListsModel = new Gson().fromJson(jsonString, EditZonesListsModel.class);
	}
	
	public EditZonesLists(WebDriver driver, List<Column> columns) {
		super(driver);
		this.driver = driver;
		this.editZoneListsHelper = new EditZoneListsHelper(this.driver);
		this.editZonesListsModel = new EditZonesListsModel();
		this.editZonesListsModel.setColumn(columns);
	}

	@Override
	public boolean execute() {
		try {
			List<Column> columns = this.editZonesListsModel.getColumn();
			for (Column column : columns) {
				logger.info("Fetch column number in table for given column " + column.getListName());
				int columnNumber = editZoneListsHelper.getFieldNumber(column.getListName());
				if(columnNumber>=0)
				{
					logger.info(column.getListName() +" exists");
					String appendText = PageHelper.appendHashCode(this.driver, column.getListValue());
					logger.info("Set text " + appendText + " in column number " + columnNumber);
					editZoneListsHelper.clickList(column.getListName(), appendText, columnNumber);
					editZoneListsHelper.verifyListValues(appendText, column.getListName());
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
