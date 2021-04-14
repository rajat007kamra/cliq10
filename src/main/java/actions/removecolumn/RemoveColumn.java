package actions.removecolumn;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.google.gson.Gson;

import actions.Actions;
import actions.PageHelper;
import actions.removecolumn.model.RemoveColumnModel;
import common.variables.CommonVariables;
import io.qameta.allure.Step;
import junit.framework.Assert;
import selenium.driver.Driver;

/**
 * 
 * @author Arun.Kapoor
 *
 */

public class RemoveColumn extends Driver implements Actions {
	private Instant startTime;
	private Instant endTime;
	private WebDriver driver;
	private RemoveColumnModel removeColumnModel;
	final static Logger logger = Logger.getLogger(RemoveColumn.class);

	public RemoveColumn(WebDriver driver, String jsonString) {
		super(driver);
		this.driver = driver;
		this.removeColumnModel = new Gson().fromJson(jsonString, RemoveColumnModel.class);
	}

	private String variablesList = "div.CHOOSER-WGT-ITEM";
	private String removeColumn = "div.CHOOSER-WGT-ITEM-CELL-TRG";
	
	@FindBy(css = "div.CHOOSER-WGT-SUBMIT-TRG")
	private WebElement submitColumns;
	
	@FindBy(css = "div.cmplt-noti-dlg-lbl.cmplt-noti-update-dlg-lbl.inln-blck.v-mid-algn")
	private WebElement checkMessage;
	
	@Override
	public boolean execute() {
		try {
			startTime = Instant.now();
			removeColumns(this.removeColumnModel.getColumn());
			submitColumns();
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
            Assert.assertTrue(e.getMessage(), false);
			return false;
		}
	}

	@Override
	public boolean validate() {
		try {
			Assert.assertEquals(validateRemoveColumnMsg().trim(), (this.removeColumnModel.getVerifyMessage()).trim());
			endTime = Instant.now();
			logger.info("[RESPTIME] " + Duration.between(startTime, endTime).toMillis());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
            Assert.assertTrue(e.getMessage(), false);
			return false;
		}
	}

	@Override
	public void setup() {
		
	}

	@Override
	public void tearDown() {
		
	}

	@Step("Reading column")
	private WebElement getColumnElement(String columnName) {
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

	@Step("Removing column")
	private void removeColumn(String columnName) throws Exception {
	    WebElement columnElem = getColumnElement(columnName);
	    if (columnElem != null) {
	    	WebElement closeElem = columnElem.findElement(By.cssSelector(removeColumn));
		    PageHelper.click(this.driver, closeElem);
		     if (getColumnElement(columnName)== null) {
		         logger.info(columnName +"Column name is not exists in active columns list now");
		     }
		}
	    else {
	    	logger.error(columnName +" Column name does not exists");
	    }
	}
	
	//Method calling removeColumn
	private void removeColumns(List<String> columnList) throws Exception {
	    for(String col: columnList) {
	        removeColumn(col);
	    }
	}
	
	@Step("Click submit icon in columns variable list")
	private void submitColumns()
	{
		PageHelper.click(this.driver, submitColumns);
	}
	
	@Step("verify Message after submit clicked")
	private String validateRemoveColumnMsg() {
		logger.info("waiting for refresh message popup");
		PageHelper.waitForElementToBeDisplayed(this.driver, checkMessage);
		CommonVariables.notificationMsg = PageHelper.getText(this.driver, checkMessage);
		logger.info("Notification message found ::- " + CommonVariables.notificationMsg);
		return CommonVariables.notificationMsg;
	}
}
