package actions.checkrecord;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.google.gson.Gson;

import actions.Actions;
import actions.checkrecordmodel.CheckRecordModel;
import actions.checkrecordmodel.Column;
import selenium.driver.Driver;

/**
 * 
 * @author Arun.Kapoor
 * @Date 20-10-2020
 *
 */

public class CheckRecord extends Driver implements Actions {
	private Instant startTime;
	private Instant endTime;
	private WebDriver driver;
	private CheckRecordHelper checkRecordHelper;
	private CheckRecordModel checkRecordModel;
	final static Logger logger = Logger.getLogger(CheckRecord.class);

	public CheckRecord(WebDriver driver, String jsonString) {
		super(driver);
		this.driver = driver;
		this.checkRecordHelper = new CheckRecordHelper(this.driver);
		this.checkRecordModel = new Gson().fromJson(jsonString, CheckRecordModel.class);
	}

	public CheckRecord(WebDriver driver, List<Column> columns) {
		super(driver);
		this.driver = driver;
		this.checkRecordHelper = new CheckRecordHelper(this.driver);
		this.checkRecordModel = new CheckRecordModel();
		this.checkRecordModel.setColumn(columns);
	}

	@Override
	public boolean execute() {
		try {
			startTime = Instant.now();
			if (checkRecordModel.getColumn() != null && !checkRecordModel.getColumn().isEmpty()) {
				List<Column> columns = this.checkRecordModel.getColumn();
				for (Column column : columns) {
					String colName = column.getTitle().trim().toUpperCase();
					int rowCount = checkRecordHelper.getColumnNumber(colName);
					if (rowCount >= 0) {
						logger.info("Fetch column position in table for given column " + colName);
						logger.info(colName + " column found at position in view zone -"
								+ rowCount);
						logger.info("Total number of records found in view zone are "
								+ checkRecordHelper.findVisibleRecords());
					}
					else {
						Assert.assertTrue(false, colName +" does not exists");
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
		try {
			validateCode();
			endTime = Instant.now();
			logger.info("[RESPTIME] " + Duration.between(startTime, endTime).toMillis());

		} catch (Exception e) {
			Assert.assertTrue(false, e.getMessage());
			return false;
		}
		return true;
	}

	@Override
	public void setup() {

	}

	@Override
	public void tearDown() {

	}

	private void validateCode() {
		try {
			if (checkRecordModel.getRecords() != null && !checkRecordModel.getRecords().isEmpty()) {
				if (checkRecordModel.getColumn() != null && !checkRecordModel.getColumn().isEmpty()) {
//					if (this.checkRecordModel.getRecords().trim().toUpperCase().equals("SELF") || this.checkRecordModel.getRecords().trim().toUpperCase().equals("ASSIGNED")) {
					if (this.checkRecordModel.getRecords().trim().toUpperCase().equals("ASSIGNED")) {
						if (this.checkRecordModel.getCheck() != null && !this.checkRecordModel.getCheck().isEmpty()) {
							String record = this.checkRecordModel.getCheck().trim().toUpperCase();
							List<Column> columns = this.checkRecordModel.getColumn();
							for (Column column : columns) {
								String colName = column.getTitle().trim().toUpperCase();
								int colNo = checkRecordHelper.getColumnNumber(colName);
								if (checkRecordHelper.getColumnNumber(colName) >= 0) {
									if (record.equals("ALL")) {
										checkRecordHelper.checkAllRowsValue(colNo,
												column.getTitle().trim().toUpperCase(),
												column.getText().trim().toUpperCase(), record);
										logger.info("All records verified successfully");
									} else {
										checkRecordHelper.selectRecord(this.checkRecordModel.getCheck());
										checkRecordHelper.checkAllRowsValue(colNo,
												column.getTitle().trim().toUpperCase(),
												column.getText().trim().toUpperCase(), record);
										logger.info("Records verified successfully");
									}
								}
								else {
									Assert.assertTrue(false, colName +" does not exists");
								}
							}
						} else {
							Assert.assertTrue(false, "In context record parameter is required");
						}
					}
				} 
//				else if (this.checkRecordModel.getRecords().trim().toUpperCase().equals("ASSIGNED")) {
//
//				}
				else if (this.checkRecordModel.getRecords().trim().toUpperCase().equals("SELF")) {
					checkRecordHelper.findRecords(this.checkRecordModel.getRecords().trim().toUpperCase());
				}
				else if (this.checkRecordModel.getRecords().trim().toUpperCase().equals("RESTRICTED")) {
					checkRecordHelper.findRecords(this.checkRecordModel.getRecords().trim().toUpperCase());
				} else if (this.checkRecordModel.getRecords().trim().toUpperCase().equals("MAX")) {
					checkRecordHelper.findRecords(this.checkRecordModel.getRecords().trim().toUpperCase());
				}
			} else {
				Assert.assertTrue(false, "Records parameter is mandatory in context");
			}
		} catch (Exception e) {
			Assert.assertTrue(false, e.getMessage());
		}
	}
}
