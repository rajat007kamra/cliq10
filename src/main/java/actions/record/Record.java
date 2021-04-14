package actions.record;

import java.time.Duration;
import java.time.Instant;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.google.gson.Gson;

import actions.Actions;
import actions.record.model.RecordModel;
import selenium.driver.Driver;

public class Record extends Driver implements Actions {
	private Instant startTime;
	private Instant endTime;
	private WebDriver driver;
	private RecordHelper recordHelper;
	private RecordModel recordModel;
	final static Logger logger = Logger.getLogger(Record.class);

	public Record(WebDriver driver, String jsonString) {
		super(driver);
		this.driver = driver;
		recordModel = new Gson().fromJson(jsonString, RecordModel.class);
		this.recordHelper = new RecordHelper(this.driver);
	}

	// TBD Need to handle multiple options here ..
	@Override
	public boolean execute() {
		try {
			startTime = Instant.now();
			Integer rowNumber = Integer.valueOf(recordModel.getRowNumber());
			logger.info("Select row " + recordModel.getRowNumber());
			logger.info("Option " + recordModel.getOption() + " selected");
			recordHelper.cloneRow(recordModel.getOption().trim().toUpperCase(), String.valueOf(rowNumber - 1));
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
		return true;
	}

	@Override
	public boolean validate() {
		try {
			if (recordModel.getOption().trim().toUpperCase().equals("CLONE")) {
				recordHelper.validateCloseErrorWidget();
			} else if (recordModel.getOption().trim().toUpperCase().equals("MERGE")) {
				recordHelper.validateMergeWidget();
			} else {

			}
			endTime = Instant.now();
			logger.info("[RESPTIME] " + Duration.between(startTime, endTime).toMillis());
			return true;
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
	}

	@Override
	public void setup() {
		// TODO Auto-generated method stub

	}

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub

	}

}