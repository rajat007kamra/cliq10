package actions.readviewzone;

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

public class ReadViewZoneHelper {

	private WebDriver driver;
	final static Logger logger = Logger.getLogger(ReadViewZoneHelper.class);
	private String columnsHeader = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.GRID-WGT[style*='z-index: 1'] table.GRID-WGT-HDRS div.GRID-WGT-HDR-DSPLY";
	private String columnsText = "div.DECKLAYER-PARENT[style*='z-index: 1;'] div.GRID-WGT[style*='z-index: 1'] div.GRID-WGT-TBLS tr.GRID-WGT-ROW-ACTIVE td.GCO";
	
	
	public ReadViewZoneHelper(WebDriver driver) {
		this.driver = driver;
	}

	@Step("Fetch the variable number of column {0}")
	public Integer getColumnNumber(String columnName) {
		int counter = -1;
		String variable = null; String updateVariable = null;
		List<WebElement> variableElements = this.driver.findElements(By.cssSelector(columnsHeader));
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
	public String getCellValue(int index) throws InterruptedException {
		int i;
		String varValue = null;
		List<WebElement> lockList = this.driver.findElements(By.cssSelector(columnsText));
		for (i = 0; i < lockList.size(); i++) {
			if (i == index)
			{
				Thread.sleep(10000);
				varValue = lockList.get(i).getText();
				break;
			} else {

			}
		}
		return varValue;
	}
}
