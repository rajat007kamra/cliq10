package actions.checkvariable;

import java.util.List;

import io.qameta.allure.Step;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import actions.PageHelper;
import common.variables.CommonVariables;

/**
 * 
 * @author Arun.Kapoor
 *
 */

public class CheckVariableHelper {
	private WebDriver driver;
	final static Logger logger = Logger.getLogger(CheckVariableHelper.class);

	public String tableRows = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.FLY div.GRID-IDX-WGT div";

	public CheckVariableHelper(WebDriver driver) {
		this.driver = driver;
	}

	@Step("Fetch the variable number of column {columnName}")
	public Integer getFieldNumber(String columnName, String lableXpath) {
		int counter = -1;
		String variable = null;
		String updateVariable = null;
		List<WebElement> variableElements = this.driver.findElements(By.cssSelector(lableXpath));
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

	@Step("Fetch the value of variable {text}")
	public String getFieldValue(int index, String valueXpath, String text, String zone) throws InterruptedException {
		int i;
		String varValue = null;
		List<WebElement> lockList = this.driver.findElements(By.cssSelector(valueXpath));
		for (i = 0; i < lockList.size(); i++) {
			if (i == index) {
				if (zone.trim().toUpperCase().equals("EDIT ZONE")) {
					Thread.sleep(5000);
					lockList.get(i).click();
					Thread.sleep(20000);
					varValue = lockList.get(i).getAttribute("title");
					if (varValue.contains(text)) {
						if (varValue.contains(":")) {
							String separator = ":";
							int sepPos = varValue.indexOf(separator);
							varValue = varValue.substring(sepPos + separator.length());
						}
					}
					break;
				} else if (zone.trim().toUpperCase().equals("VIEW ZONE")) {
					Thread.sleep(10000);
					varValue = lockList.get(i).getText();
					break;
				} else if (zone.trim().toUpperCase().equals("FLY ZONE")) {
					Thread.sleep(10000);
					varValue = lockList.get(i).getText();
					if (varValue.equals("---")) {
						logger.info("Value is- " + varValue);
					} else {
						logger.info("Value is- " + varValue);
					}
					break;
				}

			} else {

			}
		}
		return varValue;
	}

	// Common method for execute to get the field(variable/title) value
	public void getValues(String name, String labelXPath, String value, String text, String zone)
			throws InterruptedException {
		int colNo = getFieldNumber(name, labelXPath);
		if (colNo >= 0) {
			String fieldText = getFieldValue(colNo, value, text.trim().toUpperCase(), zone);
			logger.info("Value fetched from " + name + " is- " + fieldText.trim());
			CommonVariables.actionTime = System.currentTimeMillis();
			// Small static wait required to wait for page load initialization
			logger.warn("Static wait introduced");
			PageHelper.waitInSeconds(this.driver, PageHelper.X_TIMEOUT_SEC);
			logger.info("Wait for page to load");
			PageHelper.waitForPageLoad(this.driver);
		} else {
			String errorMsg = name + " does not exists";
			logger.error(errorMsg);
			Assert.fail(errorMsg);
		}
	}

	// Common method for get value and validate
	public void getValueAndVerify(String title, String varXpath, String valueXpath, String text, String zone) throws InterruptedException {
		String fieldText = null;
		int colNo = getFieldNumber(title, varXpath);
		if (colNo >= 0) {
			fieldText = getFieldValue(colNo, valueXpath, text, zone);
			Assert.assertEquals(fieldText.trim(), text);
			logger.info(text + " validated successfully");
		} else {
			String errorMsg = title + " does not exists";
			logger.error(errorMsg);
			Assert.fail(errorMsg);
		}
	}

	@Step("Select row number {rowNumber}")
	public void selectRow(String rowNumber) {
		List<WebElement> tableRowElements = this.driver.findElements(By.cssSelector(tableRows));
		for (int i = 0; i < tableRowElements.size(); i++) {
			int rowNum = Integer.parseInt(rowNumber) - 1;
			if (i == rowNum) {
				WebElement rowNuberElement = tableRowElements.get(rowNum);
				PageHelper.clickUsingJs(this.driver, rowNuberElement);
			}
		}
	}
}