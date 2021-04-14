package actions.populateclarifier;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.google.gson.Gson;

import actions.Actions;
import actions.PageHelper;
import actions.populateclarifier.model.Column;
import actions.populateclarifier.model.PopulateClarifierModel;
import common.variables.CommonVariables;
import selenium.driver.Driver;

/**
 * 
 * @author RAJAT.KAMRA
 *
 */

public class PopulateClarifier extends Driver implements Actions {
	private WebDriver driver;
	private PopulateClarifierHelper updateFormHelper;
	private PopulateClarifierModel updateFormModel;

	final static Logger logger = Logger.getLogger(PopulateClarifier.class);

	public PopulateClarifier(WebDriver driver, String jsonString) {
		super(driver);
		this.driver = driver;
		this.updateFormHelper = new PopulateClarifierHelper(this.driver);
		this.updateFormModel = new Gson().fromJson(jsonString, PopulateClarifierModel.class);
	}

	public PopulateClarifier(WebDriver driver, List<Column> columns) {
		super(driver);
		this.driver = driver;
		this.updateFormHelper = new PopulateClarifierHelper(this.driver);
		this.updateFormModel = new PopulateClarifierModel();
		this.updateFormModel.setColumn(columns);
	}

	@Override
	public boolean execute() {
		try {
			List<Column> columns = this.updateFormModel.getColumn();
			System.out.println(columns.size());
			for(int i=0; i<columns.size()/2;i++)
			{
				updateFormHelper.clickAddNew();
			
				for (Column column : columns) {
					
					logger.info("Fetch column number in table for given column " + column.getTitle());
					int columnNumber = updateFormHelper.getFieldNumber(column.getTitle().toUpperCase());
					if(columnNumber>=0)
					{
						logger.info("Set text " + column.getText() + " in column number " + columnNumber);
						updateFormHelper.setFieldValue(column.getText(), columnNumber);
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
