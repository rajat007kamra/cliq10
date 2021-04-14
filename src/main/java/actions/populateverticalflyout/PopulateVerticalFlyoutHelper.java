package actions.populateverticalflyout;

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

public class PopulateVerticalFlyoutHelper {

	private WebDriver driver;
	final static Logger logger = Logger.getLogger(PopulateVerticalFlyoutHelper.class);

	private String profileVariables = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.EZ-WGT-ITEM div.EZ-WGT-PROF-ENTRY-FLD-WRP-MAIN input.ENTRYFLD-TXT";
	private String summaryVariablesList = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.EZ-WGT-ITEM div.EZ-WGT-FLD-DSPLY-MAIN";
	private String summaryValuesList = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.EZ-WGT-ITEM div.EZ-WGT-ENTRY-FLD-WRP-MAIN div.ENTRYFLD.ENTRYFLD-EZ-WGT input.ENTRYFLD-TXT";
	private String commonVariablesList = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.EZ-WGT-FLD-DSPLY-FLYOUT";
	private String commonValueList = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.EZ-WGT-ENTRY-FLD-WRP-FLYOUT input.ENTRYFLD-TXT-EZ-WGT-FLYOUT";

	public PopulateVerticalFlyoutHelper(WebDriver driver) {
		this.driver = driver;
	}

	@Step("Fetch the variable number of column {0}")
	public Integer getFieldNumber(String flexName, String columnName) {
		int counter = -1;
		String variable = null;
		String updateVariable = null;
		List<WebElement> variableElements = null;
		if (flexName.equals("SUMMARY")) {
			variableElements = this.driver.findElements(By.cssSelector(summaryVariablesList));
		} else if (flexName.equals("QUICKLIST") || flexName.equals("WORKFLOW")) {
			variableElements = this.driver.findElements(By.cssSelector(commonVariablesList));
		}
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
	public String setFieldValue(String flexName, String varValue, int index) throws InterruptedException {
		int i;
		List<WebElement> lockList = null;
		if (flexName.equals("SUMMARY")) {
			 lockList = this.driver.findElements(By.cssSelector(summaryValuesList));
		} else if (flexName.equals("QUICKLIST") || flexName.equals("WORKFLOW")) {
			lockList = this.driver.findElements(By.cssSelector(commonValueList));
		}
		for (i = 0; i < lockList.size(); i++) {
			if (i == index) {
				Thread.sleep(5000);
				lockList.get(i).click();
				Thread.sleep(20000);
				PageHelper.clearText(this.driver, lockList.get(i));
				Thread.sleep(10000);
				PageHelper.sendKeys(this.driver, lockList.get(i), varValue);
				Thread.sleep(5000);
				lockList.get(i).sendKeys(Keys.TAB);
				break;
			} else {

			}
		}
		return varValue;
	}

	@Step("Set value in profile variables")
	public void SetProfileFields(String varName, String varValue) throws InterruptedException {
		String placeholder = null;
		List<WebElement> profileFields = this.driver.findElements(By.cssSelector(profileVariables));
		System.out.println("PROFILE VARIABLES COUNT = " + profileFields.size());
		for (int i = 0; i < profileFields.size(); i++) {
			placeholder = profileFields.get(i).getAttribute("placeholder");
			if (placeholder.equals(varName)) {
				Thread.sleep(5000);
				profileFields.get(i).click();
				Thread.sleep(20000);
				PageHelper.clearText(this.driver, profileFields.get(i));
				Thread.sleep(10000);
				PageHelper.sendKeys(this.driver, profileFields.get(i), varValue);
				Thread.sleep(5000);
				profileFields.get(i).sendKeys(Keys.TAB);
				break;
			} else {
				logger.error(varName + " not exists");
			}
		}
	}
}
