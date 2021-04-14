package actions.locksortorder;

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

public class LockSortOrderHelper {
	public WebDriver driver;
	final static Logger logger = Logger.getLogger(LockSortOrderHelper.class);
	
	public LockSortOrderHelper(WebDriver driver) {
		this.driver = driver;
	}
	
	public String columnsHeader = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.GRID-WGT[style*='z-index: 1'] table.GRID-WGT-HDRS div.GRID-WGT-HDR-DSPLY";
	public String lockColumn = "div.DECKLAYER-PARENT[style*='z-index: 1'] div[title='%1S'] div.GRID-WGT-SORT-LOCK-TRG[title='LOCK ORDER']";
	public String lockedColumns = "div.DECKLAYER-PARENT[style*='z-index: 1'] table.GRID-WGT-HDRS div.GRID-WGT-SORT-LOCK-TRG-LOCKED";
	
	@Step("Get column name")
	public WebElement getColumn(String columnName) {
	    List<WebElement> columnList = this.driver.findElements(By.cssSelector(columnsHeader));
	    WebElement colElem = null;
	    for(WebElement elm: columnList) {
	    	String columnTitle = PageHelper.getText(this.driver, elm);
	    	String updatedColumnTitle = columnTitle.replace("*", "");
	        if(updatedColumnTitle.trim().equals(columnName)) {
	        	colElem = elm;
	            break;
	        }
	    }
	    return colElem;
	}

	@Step("Lock column sort")
	public void lockColumnSort(String columnName) throws Exception {
	    WebElement element = getColumn(columnName);
	    if (element != null) {
	    	WebElement elem = driver.findElement(By.cssSelector(String.format(lockColumn, columnName)));
	    	Thread.sleep(3000);
	        PageHelper.click(this.driver, elem);
	        logger.error(columnName +" column sort locked");
		}
	    else {
	    	logger.error(columnName +" column name does not exists");
	    }
	}
	
	//Method calling lockColumnSort
	public void lockColumnsSort(List<String> columnList) throws Exception {
	    for(String col: columnList) {
	    	lockColumnSort(col);
	    }
	}
	
	@Step("Verify locked columns")
	public int getlockedColumns()
	{
		List<WebElement> lockedElems = this.driver.findElements(By.cssSelector(lockedColumns));
		logger.info("Number of locked columns are - " +lockedElems.size());
		int totalLocked = lockedElems.size();
		return totalLocked;
	}
}
