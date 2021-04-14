package actions.reportsfilter;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import actions.PageHelper;
import io.qameta.allure.Step;

public class ReportFilterHelper {
	private WebDriver driver;
	final static Logger logger = Logger.getLogger(ReportFilterHelper.class);
	
	private String refreshIcon = "div.DECKLAYER-PARENT[style*='z-index: 1;'] div.GRID-BUTTON.TOP-TOOLBAR-TRG-REFRESH";
	private String filterTextbox = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.FLY div.REPORTS-WGT-PANEL1 table.GRID-WGT-HDRS input.GRID-WGT-FLTR-TXT";
	private String filterResults = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.FLY div.REPORTS-WGT-PANEL1 div.REPORTS-CELL.GRID-WGT-ROW-ACTIVE div.REPORTS-WGT-GRID-BTN";
	
	public ReportFilterHelper(WebDriver driver) {
		this.driver = driver;
	}

	@Step("Set filter text {filterText} at column {columnNumber}")
	public void setFilterText(String filterText) {
		String filter = PageHelper.appendHashCode(this.driver, filterText);		
		WebElement columnTextElem = driver.findElement(By.cssSelector(filterTextbox));
		columnTextElem.sendKeys(filter);
		columnTextElem.sendKeys(Keys.ENTER);
	}
	
	public void validateReports(String filterText)
	{
		PageHelper.waitInSeconds(this.driver, PageHelper.XX_TIMEOUT_SEC);
		if(this.driver.findElements(By.cssSelector(filterResults)).size()>0)
		{
			logger.info(filterText +" validated successfully");
		}
		else {
			Assert.fail(filterText +" record not found");
		}
	}
	
	public void clickRefresh() throws InterruptedException
	{
		TimeUnit.SECONDS.sleep(PageHelper.X_TIMEOUT_SEC);
		this.driver.findElement(By.cssSelector(refreshIcon)).click();
		logger.info("Refresh icon clicked on reports process");
	}
}
