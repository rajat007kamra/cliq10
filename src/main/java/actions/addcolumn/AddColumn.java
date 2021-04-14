package actions.addcolumn;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.google.gson.Gson;

import actions.Actions;
import actions.PageHelper;
import actions.addcolumn.model.AddColumnModel;
import common.variables.CommonVariables;
import io.qameta.allure.Step;
import junit.framework.Assert;
import selenium.driver.Driver;


/**
 * 
 * @author Arun.Kapoor
 *
 */

public class AddColumn extends Driver implements Actions {
	private Instant startTime;
	private Instant endTime;
	private WebDriver driver;
	private AddColumnModel addColumnModel;
	final static Logger logger = Logger.getLogger(AddColumn.class);

	public AddColumn(WebDriver driver, String jsonString) {
		super(driver);
		this.driver = driver;
		this.addColumnModel = new Gson().fromJson(jsonString, AddColumnModel.class);
	}

	private String variablesList = "div.CHOOSER-WGT-ITEM span.CHOOSER-WGT-VAR";
	private String columnList = "span.SRCH-WGT-SUGG-MATCHED";

	@FindBy(css = "div.CHOOSER-WGT input.SRCH-WGT-ENTRYFLD")
	private WebElement searchField;

	@FindBy(css = "div.CHOOSER-WGT-SUBMIT-TRG")
	private WebElement submitColumns;

	@FindBy(css = "div.cmplt-noti-dlg-lbl.cmplt-noti-update-dlg-lbl.inln-blck.v-mid-algn")
	private WebElement checkMessage;

	@Override
	public boolean execute() {
		try {
			startTime = Instant.now();
			logger.info("Number of columns already exists - " + countText());
			Thread.sleep(10000);
			findColumns(this.addColumnModel.getColumn());
			submitColumns();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue("Add column not found", false);
			return false;
		}
	}

	@Override
	public boolean validate() {
		try {
			Assert.assertEquals(validateAddColumnMsg().trim(), (this.addColumnModel.getVerifyMessage()).trim());
			endTime = Instant.now();
			logger.info("[RESPTIME] " + Duration.between(startTime, endTime).toMillis());
			logger.info("Number of columns after adding new cloumns - " + countText());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(e.getMessage(), false);
			return false;
		}
	}

	@Override
	public void setup() {

	}

	@Override
	public void tearDown() {

	}

	// Method counting number of columns already exists in VARIABLES List
	public int countText() {
		List<WebElement> colList = this.driver.findElements(By.cssSelector(variablesList));
		int columnCount = colList.size();
		return columnCount;
	}

	@Step("Adding column")
	private void addColumn(String columnName) throws InterruptedException {
		if (columnName == null || columnName == "" || columnName.isEmpty()) {
			logger.error("Column name could not be empty or null");
		} else {
			searchField.clear();	
			searchField.sendKeys(columnName);
			Thread.sleep(5000);
			List<WebElement> colExists = this.driver.findElements(By.cssSelector(columnList));
			if (colExists.size() > 0) {
				for (int i = 0; i < colExists.size(); i++) {
					String colText = colExists.get(i).getText();
					if (colText.trim().equals(columnName)) {
						colExists.get(i).click();
						logger.info(columnName + " added into variables list");
						break;
					}
				}
			} else {
				logger.error(columnName + " does not exists");
			}
		}
	}

	// Method calling addColumn
	private void findColumns(List<String> columnList) throws InterruptedException {
		for (String col : columnList) {
			addColumn(col);
		}
	}

	@Step("Click submit icon in columns variable list")
	private void submitColumns() {
		verifySubmit(this.driver);
		PageHelper.click(this.driver, submitColumns);
	}

	@Step("verify Message after submit clicked")
	private String validateAddColumnMsg() {
		logger.info("waiting for refresh message popup");
		PageHelper.waitForElementToBeDisplayed(this.driver, checkMessage);
		CommonVariables.notificationMsg = PageHelper.getText(this.driver, checkMessage);
		logger.info("Notification message found ::- " + CommonVariables.notificationMsg);
		return CommonVariables.notificationMsg;
	}
	
	// Method to verify whether the SUBMIT button is enabled or disabled
	public boolean verifySubmit(WebDriver driver) {
		PageHelper.locateElement(driver, submitColumns);
		String className = this.submitColumns.getAttribute("class");
		if (className.contains("DISABLED")) {
			logger.error("Either submit icon is disabled or not found");
			return false;
		} else {
			logger.info("Submit icon is enabled");
			return true;
		}
	}
}