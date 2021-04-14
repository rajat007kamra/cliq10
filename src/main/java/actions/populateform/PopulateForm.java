package actions.populateform;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.google.gson.Gson;

import actions.Actions;
import actions.PageHelper;
import actions.populateform.model.Column;
import actions.populateform.model.PopulateFormModel;
import selenium.driver.Driver;

/**
 * 
 * @author Arun.Kapoor
 *
 */

public class PopulateForm extends Driver implements Actions {
	private Instant startTime;
	private Instant endTime;
	private WebDriver driver;
	private PopulateFormHelper updateFormHelper;
	private PopulateFormModel updateFormModel;

	final static Logger logger = Logger.getLogger(PopulateForm.class);

	public PopulateForm(WebDriver driver, String jsonString) {
		super(driver);
		this.driver = driver;
		this.updateFormHelper = new PopulateFormHelper(this.driver);
		this.updateFormModel = new Gson().fromJson(jsonString, PopulateFormModel.class);
	}

	public PopulateForm(WebDriver driver, List<Column> columns) {
		super(driver);
		this.driver = driver;
		this.updateFormHelper = new PopulateFormHelper(this.driver);
		this.updateFormModel = new PopulateFormModel();
		this.updateFormModel.setColumn(columns);
	}

	@Override
	public boolean execute() {
		try {
			List<Column> columns = this.updateFormModel.getColumn();
			startTime = Instant.now();
			for (Column column : columns) {
				logger.info("Fetch column number in table for given column " + column.getVariable());
				int columnNumber = updateFormHelper.getFieldNumber(column.getVariable());
				if (columnNumber >= 0) {
					logger.info("Set text " + PageHelper.appendHashCode(this.driver, column.getValue().trim()) + " in column number " + columnNumber);
					updateFormHelper.setFieldValue(PageHelper.appendHashCode(this.driver, column.getValue().trim()),
							columnNumber);
					// Small static wait required to wait for page load initialization
					logger.warn("Static wait introduced");
					PageHelper.waitInSeconds(this.driver, PageHelper.X_TIMEOUT_SEC);
					logger.info("Wait for page to load");
					PageHelper.waitForPageLoad(this.driver);
				} else {
					logger.error(column.getVariable() + " does not exists");
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
		endTime = Instant.now();
		logger.info("[RESPTIME] " + Duration.between(startTime, endTime).toMillis());
		return false;
	}

	@Override
	public void setup() {

	}

	@Override
	public void tearDown() {

	}
}