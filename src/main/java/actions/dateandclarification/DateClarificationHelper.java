package actions.dateandclarification;

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

public class DateClarificationHelper {
	private WebDriver driver;
	final static Logger logger = Logger.getLogger(DateClarificationHelper.class);
	
	public DateClarificationHelper(WebDriver driver) {
		this.driver = driver;
	}
	
	public String variablesList = "div.CHOOSER-WGT-ITEM";
	public String dateAndClarification = "div.STND-PAD-CLRF-TRG";
	public String expectedFlyClarifierHeaderText = "DATE & SOURCE CLARIFICATION";
	
	@Step("Reading columns name")
	public WebElement getColumnElement(String columnName) {
	    List<WebElement> columnList = this.driver.findElements(By.cssSelector(variablesList));
	    WebElement columnElem = null;
	    for(WebElement elm: columnList) {
	    	String columnText = PageHelper.getText(this.driver, elm);
	        String updatedColumnText = columnText.replace("close", "").replace("event", "");
	        if(updatedColumnText.trim().equals(columnName)) {
	            columnElem = elm;
	            break;
	        }
	    }
	    return columnElem;
	}
	
	@Step("Click on date and clarification icon")
	public void dateAndClarifications(String columnName) throws Exception {
		try {
			WebElement columnElem = getColumnElement(columnName);
			if (columnElem == null) {
				throw new Exception("Column name does not exists");
			}
			WebElement closeElem = columnElem.findElement(By.cssSelector(dateAndClarification));
			PageHelper.click(this.driver, closeElem);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	//Method calling dateAndClarifications
	public void clickDateAndClarifications(List<String> columnList) throws Exception {
	    for(String col: columnList) {
	    	dateAndClarifications(col);
	    }
	}
}
