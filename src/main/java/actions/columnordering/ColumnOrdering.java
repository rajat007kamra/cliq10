package actions.columnordering;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.google.gson.Gson;

import actions.Actions;
import actions.PageHelper;
import actions.columnordering.model.ColumnOrderingModel;
import common.variables.CommonVariables;
import io.qameta.allure.Step;
import selenium.driver.Driver;

/**
 * 
 * @author Arun.Kapoor
 *
 */

public class ColumnOrdering extends Driver implements Actions {
	private Instant startTime;
	private Instant endTime;
	private WebDriver driver;
	private ColumnOrderingModel columnOrderModel;
	final static Logger logger = Logger.getLogger(ColumnOrdering.class);
	
	public ColumnOrdering(WebDriver driver, String jsonString) {
		super(driver);
		this.driver = driver;
		this.columnOrderModel = new Gson().fromJson(jsonString, ColumnOrderingModel.class);
	}

	private String variablesList = "div.CHOOSER-WGT-ITEM";
	
	@FindBy(css = "div.CHOOSER-WGT-SUBMIT-TRG")
	private WebElement submitColumns;
	
	@FindBy(css = "div.cmplt-noti-dlg-lbl.cmplt-noti-update-dlg-lbl.inln-blck.v-mid-algn")
	private WebElement checkMessage;
	
	@Override
	public boolean execute() {
		try {
			startTime = Instant.now();
			dragAndDrop();
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false, e.getMessage());
			return false;
		}
	}

	@Override
	public boolean validate() {
		try {
			endTime = Instant.now();
			logger.info("[RESPTIME] " + Duration.between(startTime, endTime).toMillis());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
            Assert.assertTrue(false, e.getMessage());
			return false;
		}
	}

	@Override
	public void setup() {
		
	}

	@Override
	public void tearDown() {
		
	}
	
	@Step("Get source column")
	private WebElement getSourceColumn(String columnName)
	{
		WebElement sourceIndex = null;
		List<WebElement> columnList = this.driver.findElements(By.cssSelector(variablesList));
		for(int i=0; i<columnList.size(); i++)
		{
			String colText = columnList.get(i).getText();
			String updatedColText = colText.replace("close", "").replace("event", "");
			if(updatedColText.trim().equals(columnName))
			{
				sourceIndex = columnList.get(i);
				break;
			}
		}
		return sourceIndex;
	}
	
	@Step("Get target column")
	private WebElement getTargetColumn(int newIndex)
	{
		WebElement targetIndex = null;
		List<WebElement> columnList = this.driver.findElements(By.cssSelector(variablesList));
		for(int i=0; i<columnList.size(); i++)
		{
			if(i==newIndex)
			{
				targetIndex = columnList.get(i);
				System.out.println("TARGET ::- " +targetIndex);
				break;
			}
		}
		return targetIndex;
	}
	
	/*
	 * Dropping column always at top instead of dropping at location where user wants
	 */
	
	@Step("Drag source column and drop at target column")
	private void dragAndDrop()
	{
		PageHelper.moveSlider(this.driver, getSourceColumn(this.columnOrderModel.getColumn()), getTargetColumn(Integer.parseInt(this.columnOrderModel.getLocation())));
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
