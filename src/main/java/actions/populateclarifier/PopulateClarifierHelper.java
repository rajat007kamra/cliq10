package actions.populateclarifier;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Assert;
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

public class PopulateClarifierHelper {

	private WebDriver driver;
	final static Logger logger = Logger.getLogger(PopulateClarifierHelper.class);
	private String variablesList = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.FLY.CLARIFIER-PAD div.STND-PAD-HDR";
	private String valueList = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.FLY.CLARIFIER-PAD div.CLRFS-WGT-ITM input.ENTRYFLD-TXT-STND-PAD";
	private String addIcon = "div.DECKLAYER-PARENT[style*='z-index: 1;'] div.FLY div.STND-PAD-TRG.TRG-BASE.trg-base.material-icons.STND-PAD-TRG-DFLT[title*='ADD ROW";
	
	public PopulateClarifierHelper(WebDriver driver) {
		this.driver = driver;
	}

	@Step("Fetch the variable number of column {0}")
	public Integer getFieldNumber(String columnName) {
		int counter = -1;
		String variable = null; String updateVariable = null;
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
			if (i == index)
			{
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
	
	@Step("Click PLUS in user rights table")
	public void clickAddNew() throws InterruptedException {
		WebElement plus = this.driver.findElement(By.cssSelector(addIcon));
		if (PageHelper.isElementEnabled(plus)) {
			PageHelper.click(this.driver, plus);
		} else {
			logger.error("PLUS icon is disabled");
			Assert.fail("Plus Icon is disabled in Clarifier Widget");
		}
	}

}
