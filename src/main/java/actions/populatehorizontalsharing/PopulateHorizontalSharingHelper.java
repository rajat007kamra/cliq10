package actions.populatehorizontalsharing;

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

public class PopulateHorizontalSharingHelper {
	private WebDriver driver;
	final static Logger logger = Logger.getLogger(PopulateHorizontalSharingHelper.class);
	
	private String table = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.STND-PAD-BODY[style*='z-index: 1'] div.GRID-WGT div.GRID-WGT-BODY-INNER div.GRID-WGT-TBLS table.TABLE-WGT";
	private String tableRowCell = "tr.GRID-WGT-ROW-ACTIVE td.GCO";
	private String addRow = "div.GRID-WGD-HDR-CTRL[title='ADD NEW']";
	
	public PopulateHorizontalSharingHelper(WebDriver driver) {
		this.driver = driver;
	}
	
	@Step("Set text in user rights table")
	public void findTableAndEnter(String uName, String uRights) throws InterruptedException
	{
		WebElement row = null;
		List<WebElement> getInputCell = null;
		WebElement input = null;
		
		clickAddNew();
		List<WebElement> getTables = this.driver.findElements(By.cssSelector(table));
		WebElement tab = getTables.get(1);
		
		List<WebElement> getRowsCell = tab.findElements(By.cssSelector(tableRowCell));
		
		Thread.sleep(5000);
		//Set text in User column
		row = getRowsCell.get(0);
		PageHelper.click(this.driver, row);
		Thread.sleep(5000);
		getInputCell = row.findElements(By.cssSelector("input.ENTRYFLD-TXT-GRID-CELL-EDTR"));
		input = getInputCell.get(0);
		PageHelper.locateElement(this.driver, input);
		PageHelper.sendKeys(this.driver, input, uName);
		Thread.sleep(5000);
		input.sendKeys(Keys.TAB);
		//Set text in Right column
		row = getRowsCell.get(1);
		PageHelper.click(this.driver, row);
		Thread.sleep(5000);
		getInputCell = row.findElements(By.cssSelector("input.ENTRYFLD-TXT-GRID-CELL-EDTR"));
		input = getInputCell.get(0);
		PageHelper.locateElement(this.driver, input);
		PageHelper.sendKeys(this.driver, input, uRights);
		Thread.sleep(5000);
		input.sendKeys(Keys.TAB);
	}
	
	@Step("Click PLUS in user rights table")
	public void clickAddNew()
	{
		List<WebElement> add = this.driver.findElements(By.cssSelector(addRow));
		WebElement click = add.get(1);
		if(PageHelper.isElementEnabled(click))
		{
			PageHelper.click(this.driver, click);
		}
		else {
			logger.error("PLUS icon is disabled in user rights table");
		}
	}
}
