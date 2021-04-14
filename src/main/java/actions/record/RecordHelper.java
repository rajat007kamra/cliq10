package actions.record;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import actions.PageHelper;
import io.qameta.allure.Step;

/**
 * 
 * @author Surendra.Shekhawat
 *
 */
public class RecordHelper {
	private WebDriver driver;
	final static Logger logger = Logger.getLogger(RecordHelper.class);

	public RecordHelper(WebDriver driver) {
		this.driver = driver;
	}

	private String tableRows = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.GRID-WGT[style*='z-index: 1'] table.TABLE-WGT tr";
	private String rightMenuOptions = "div.CONTEXTMENU-WGT div.CNTXT-MNU-ITM-%1S>div";
	private String closeFlyOut = "div.FLY.rslts-wgt div.HOR-FLEX-EL.FLY-CLOSE-TRG[title='CLOSE']";
	private String mergeFlyOut = "div.FLY.ROW-MERGE-WGT div.ROW-MERGE-WGT-TRG[title='MERGE']";

	public WebElement getRowElement(String rowNumber) {
		List<WebElement> tableRowElements = this.driver.findElements(By.cssSelector(tableRows));
		return tableRowElements.get(Integer.parseInt(rowNumber));
	}

	@Step("Clone row number {rowNumber}")
	public void cloneRow(String option, String rowNumber) throws InterruptedException {
		PageHelper.waitInSeconds(this.driver, PageHelper.X_TIMEOUT_SEC);
		WebElement rowElement = getRowElement(rowNumber);
		Actions action = new Actions(this.driver);
		action.moveToElement(rowElement).build().perform();
		action.click(rowElement).build().perform();
		action.contextClick(rowElement).build().perform();
		By cloneBy = null;
		if (option.equals("CLONE")) {
			cloneBy = By.cssSelector(String.format(rightMenuOptions, option));
		}
		if (option.equals("MERGE")) {
			cloneBy = By.cssSelector(String.format(rightMenuOptions, option));
		}
		PageHelper.waitForElementVisibility(driver, cloneBy, PageHelper.X_TIMEOUT_SEC);
		PageHelper.clickUsingJs(this.driver, this.driver.findElement(cloneBy));
		PageHelper.waitInSeconds(this.driver, PageHelper.X_TIMEOUT_SEC);
//		// Wait for error flyout if occurs close it.
//		WebElement flyOutElem = this.driver.findElement(By.cssSelector(closeFlyOut));
//		if (PageHelper.isElementDisplayed(flyOutElem)) {
//			PageHelper.click(this.driver, flyOutElem);
//		}
//		selectRow();
	}

	private void selectRow() throws InterruptedException {
		Thread.sleep(5000);
		List<WebElement> tableRowElements = this.driver.findElements(By.cssSelector(tableRows));
		WebElement rowNuberElement = tableRowElements.get(0);
		PageHelper.clickUsingJs(this.driver, rowNuberElement);
		PageHelper.waitInSeconds(this.driver, PageHelper.XX_TIMEOUT_SEC);
	}

	public void validateCloseErrorWidget() throws InterruptedException {
		// Wait for error flyout if occurs close it.
		WebElement flyOutElem = this.driver.findElement(By.cssSelector(closeFlyOut));
		if (PageHelper.isElementDisplayed(flyOutElem)) {
			PageHelper.click(this.driver, flyOutElem);
		}
		selectRow();
	}

	public void validateMergeWidget() throws InterruptedException {
		WebElement flyOutElem = this.driver.findElement(By.cssSelector(mergeFlyOut));
		if (PageHelper.isElementDisplayed(flyOutElem)) {
			logger.info("Merge widget validated successfully");
		}
		selectRow();
	}
}
