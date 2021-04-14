package actions.readflyoutdata;

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

public class ReadFlyoutDataHelper {

	private WebDriver driver;
	final static Logger logger = Logger.getLogger(ReadFlyoutDataHelper.class);
	private String columnsHeader = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.FLY table.GRID-WGT-HDRS div.GRID-WGT-HDR-DSPLY";
	private String columnsText = "div.DECKLAYER-PARENT[style*='z-index: 1;'] div.FLY div.GRID-WGT-TBLS tr.GRID-WGT-ROW-ACTIVE td.GCO";
	private String tableRows = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.FLY div.GRID-IDX-WGT div";
	
	public ReadFlyoutDataHelper(WebDriver driver) {
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
	
	@Step("Read value of column")
	public String getCellValue(int index, String colName) throws InterruptedException {
		int i;
		String varValue = null;
		List<WebElement> lockList = this.driver.findElements(By.cssSelector(columnsText));
		for (i = 0; i < lockList.size(); i++) {
			if (i == index)
			{
				Thread.sleep(10000);
				varValue = lockList.get(i).getText();
				if(varValue.equals("---"))
				{
					logger.info("Value fetched from " +colName  + " is- " + varValue);
				}
				else {
					logger.info("Value fetched from " +colName  + " is- " + varValue);
				}
//				System.out.println(varValue);
				break;
			}
		}
		return varValue;
	}
	
	@Step("Select row number {rowNumber}")
	public void selectRow(String rowNumber) {
		List<WebElement> tableRowElements = this.driver.findElements(By.cssSelector(tableRows));
		for(int i=0; i<tableRowElements.size(); i++)
		{
			int rowNum = Integer.parseInt(rowNumber)-1;
			if(i==rowNum)
			{
				WebElement rowNuberElement = tableRowElements.get(rowNum);
				PageHelper.clickUsingJs(this.driver, rowNuberElement);
			}
		}		
	}
}
