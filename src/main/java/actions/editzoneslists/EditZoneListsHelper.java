package actions.editzoneslists;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import actions.PageHelper;
import io.qameta.allure.Step;

/**
 * 
 * @author Arun.Kapoor
 *
 */

public class EditZoneListsHelper {
	private WebDriver driver;
	final static Logger logger = Logger.getLogger(EditZoneListsHelper.class);

	private String formVariablesLabelXPath = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.EZ-WGT-FLD-DSPLY-FLYOUT";
	private String formVariableInputXPath = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.EZ-WGT-ENTRY-FLD-WRP-FLYOUT input.ENTRYFLD-TXT-EZ-WGT-FLYOUT";
	private String formVariablesValueXPath = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.STND-PAD-BODY div.EZ-WGT-ENTRY-FLD-WRP-FLYOUT div.ENTRYFLD-EZ-WGT div.ENTRYFLD-TRG-EZ-WGT-DROP";
	private String formListValuesXPath = "div.DROP-WGT-OPTION";

	public EditZoneListsHelper(WebDriver driver) {
		this.driver = driver;
	}

	@Step("Fetch the variable number of column {0}")
	public Integer getFieldNumber(String columnName) {
		int counter = -1;
		String variable = null;
		String updateVariable = null;
		List<WebElement> variableElements = this.driver.findElements(By.cssSelector(formVariablesLabelXPath));
		for (int i = 0; i < variableElements.size(); i++) {
			variable = PageHelper.getText(this.driver, variableElements.get(i));
			if (variable.contains("*")) {
				updateVariable = variable.replace("*", "");
			} else {
				updateVariable = variable;
			}
			if ((updateVariable.trim()).equals(columnName)) {
				counter = i;
				break;
			}
		}
		return counter;
	}

	@Step("Click variable list to open")
	public void clickList(String columnName, String colValue, int index) throws InterruptedException {
		List<WebElement> arrowIconList = this.driver.findElements(By.cssSelector(formVariablesValueXPath));
		for (int i = 0; i < arrowIconList.size(); i++) {
			if (i == index) {
				arrowIconList.get(i).click();
				logger.info("List of '" + columnName + "' clicked");
				Thread.sleep(5000);
				List<WebElement> listValues = this.driver.findElements(By.cssSelector(formListValuesXPath));
				logger.info("Total values in list " + listValues.size());
				Thread.sleep(5000);
				setFieldValue(colValue, index);
				break;
			} else {

			}
		}
	}

	@Step("Set value in variable")
	public String setFieldValue(String varValue, int index) throws InterruptedException {
		List<WebElement> lockList = this.driver.findElements(By.cssSelector(formVariableInputXPath));
		for (int i = 0; i < lockList.size(); i++) {
			if (i == index) {
				lockList.get(i).click();
				Thread.sleep(2000);
				PageHelper.sendKeys(this.driver, lockList.get(i), varValue);
				Thread.sleep(5000);
				break;
			} else {

			}
		}
		return varValue;
	}

	@Step("Select value and verify")
	public void readFieldValues(String value, String columnName) {
		boolean found = false;
		List<WebElement> listValues = this.driver.findElements(By.cssSelector(formListValuesXPath));
//		logger.info("Total values in list " + listValues.size());
		for (int i = 0; i < listValues.size(); i++) {
			String text = listValues.get(i).getText();
			if (text.trim().toUpperCase().equals(value)) {
				logger.info(value + " exists in " + columnName);
				found = true;
				listValues.get(i).click();
				break;
			}
		}
		if (!found) {
			logger.info(value + " not exists in " + columnName);
		}
	}

//	Method calling readFieldValues
	public void verifyListValues(String listValue, String columnName) throws Exception {
		if (listValue != null && !listValue.isEmpty()) {
			readFieldValues(listValue.trim().toUpperCase(), columnName);
		} else {
			logger.info("Value cannot be blank");
		}
	}
}
