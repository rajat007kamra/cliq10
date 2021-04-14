package actions.readflyoutdata;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.google.gson.Gson;

import actions.Actions;
import actions.PageHelper;
import actions.readflyoutdata.model.Column;
import actions.readflyoutdata.model.ReadFlyoutDataModel;
import selenium.driver.Driver;

/**
 * 
 * @author Arun.Kapoor
 *
 */

public class ReadFlyoutData extends Driver implements Actions {
	private Instant startTime;
	private Instant endTime;
	private WebDriver driver;
	private ReadFlyoutDataHelper readCellHelper;
	private ReadFlyoutDataModel readCellModel;

	final static Logger logger = Logger.getLogger(ReadFlyoutData.class);

	public ReadFlyoutData(WebDriver driver, String jsonString) {
		super(driver);
		this.driver = driver;
		this.readCellHelper = new ReadFlyoutDataHelper(this.driver);
		this.readCellModel = new Gson().fromJson(jsonString, ReadFlyoutDataModel.class);
	}

	public ReadFlyoutData(WebDriver driver, List<Column> columns) {
		super(driver);
		this.driver = driver;
		this.readCellHelper = new ReadFlyoutDataHelper(this.driver);
		this.readCellModel = new ReadFlyoutDataModel();
		this.readCellModel.setColumn(columns);
	}

	@Override
	public boolean execute() {
		startTime = Instant.now();
		try {
			if (this.readCellModel.getSelectRow() != null && !this.readCellModel.getSelectRow().isEmpty()) {
				this.readCellHelper.selectRow(this.readCellModel.getSelectRow());
			}
			Thread.sleep(5000);
			List<Column> columns = this.readCellModel.getColumn();
			for (Column column : columns) {
//				logger.info("Fetch column number in table for given column " + column.getTitle());
				int columnNumber = readCellHelper.getColumnNumber(column.getTitle());
				if(columnNumber>=0)
				{
					readCellHelper.getCellValue(columnNumber, column.getTitle());
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
		try {
			List<Column> columns = this.readCellModel.getColumn();
			for (Column column : columns) {
				int columnNumber = readCellHelper.getColumnNumber(column.getTitle());
				if(columnNumber>=0)
				{
					String columnValue = readCellHelper.getCellValue(columnNumber, column.getTitle());
					Assert.assertEquals(columnValue.trim(), column.getExpected().trim().toUpperCase());
				}
				else {
					logger.error(column.getTitle() +" does not exists");
				}
			}
			endTime = Instant.now();
			logger.info("[RESPTIME] " + Duration.between(startTime, endTime).toMillis());
		return true;
		}
		catch (Exception e) {
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
