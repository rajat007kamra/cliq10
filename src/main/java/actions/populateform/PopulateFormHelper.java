package actions.populateform;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import actions.PageHelper;
import io.qameta.allure.Step;

/**
 * 
 * @author Arun.Kapoor
 *
 */

public class PopulateFormHelper {

	private WebDriver driver;
	final static Logger logger = Logger.getLogger(PopulateFormHelper.class);
	private String variablesList = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.EZ-WGT-FLD-DSPLY-FLYOUT";
	private String valueList = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.EZ-WGT-ENTRY-FLD-WRP-FLYOUT input.ENTRYFLD-TXT-EZ-WGT-FLYOUT";

	public PopulateFormHelper(WebDriver driver) {
		this.driver = driver;
	}

	@Step("Fetch the variable number of column {columnName}")
	public Integer getFieldNumber(String columnName) {
		int counter = -1;
		String variable = null;
		String updateVariable = null;
		List<WebElement> variableElements = this.driver.findElements(By.cssSelector(variablesList));
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

	@Step("Set value in variable")
	public String setFieldValue(String varValue, int index) throws InterruptedException {
		int i;
		List<WebElement> lockList = this.driver.findElements(By.cssSelector(valueList));
		for (i = 0; i < lockList.size(); i++) {
			if (i == index) {
				PageHelper.click(this.driver, lockList.get(i));
				Thread.sleep(2000);
//				PageHelper.waitInSeconds(this.driver, PageHelper.X_TIMEOUT_SEC);
				PageHelper.sendKeys(this.driver, lockList.get(i), varValue);
				Thread.sleep(5000);
//				PageHelper.waitInSeconds(this.driver, PageHelper.X_TIMEOUT_SEC);
				lockList.get(i).sendKeys(Keys.TAB);
				break;
			} else {

			}
		}
		return varValue;
	}
}
