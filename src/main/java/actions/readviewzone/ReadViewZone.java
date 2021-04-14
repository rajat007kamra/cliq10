package actions.readviewzone;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.google.gson.Gson;

import actions.Actions;
import actions.PageHelper;
import actions.readviewzone.model.Column;
import actions.readviewzone.model.ReadViewZoneModel;
import selenium.driver.Driver;

/**
 * 
 * @author Arun.Kapoor
 *
 */

public class ReadViewZone extends Driver implements Actions {
	private Instant startTime;
	private Instant endTime;
	private WebDriver driver;
	private ReadViewZoneHelper readCellHelper;
	private ReadViewZoneModel readCellModel;

	final static Logger logger = Logger.getLogger(ReadViewZone.class);

	public ReadViewZone(WebDriver driver, String jsonString) {
		super(driver);
		this.driver = driver;
		this.readCellHelper = new ReadViewZoneHelper(this.driver);
		this.readCellModel = new Gson().fromJson(jsonString, ReadViewZoneModel.class);
	}

	public ReadViewZone(WebDriver driver, List<Column> columns) {
		super(driver);
		this.driver = driver;
		this.readCellHelper = new ReadViewZoneHelper(this.driver);
		this.readCellModel = new ReadViewZoneModel();
		this.readCellModel.setColumn(columns);
	}

	@Override
	public boolean execute() {
		try {
			Thread.sleep(5000);
			startTime = Instant.now();
			List<Column> columns = this.readCellModel.getColumn();
			for (Column column : columns) {
//				logger.info("Fetch column number in table for given column " + column.getTitle());
				int columnNumber = readCellHelper.getColumnNumber(column.getTitle());
				if(columnNumber>=0)
				{
					String columnValue = readCellHelper.getCellValue(columnNumber);
					logger.info("Value fetched from " +column.getTitle()  + " is- " + columnValue);
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
					String columnValue = readCellHelper.getCellValue(columnNumber);
					if(columnValue.trim().equals("---") || columnValue.trim()==null || columnValue.trim().isEmpty())
					{
						logger.error("Value for '" +column.getTitle() +"' found :- " +columnValue);
						Assert.fail();
					}
					else {
						Assert.assertEquals(columnValue.trim().toUpperCase(), column.getExpected().trim().toUpperCase());
					}
					endTime = Instant.now();
					logger.info("[RESPTIME] " + Duration.between(startTime, endTime).toMillis());
				}
				else {
					logger.error(column.getTitle() +" does not exists");
				}
			}
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
