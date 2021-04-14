package actions.filldata;

import java.time.Instant;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.google.gson.Gson;

import actions.Actions;
import actions.PageHelper;
import io.qameta.allure.Step;
import selenium.driver.Driver;

/**
 * 
 * @author Arun.Kapoor
 *
 */

public class FillData extends Driver implements Actions {
	private Instant startTime;
	private Instant endTime;
	private WebDriver driver;
	private FillDataModel fillDataModel;
	
	public FillData(WebDriver driver, String jsonString) {
		super(driver);
		this.driver = driver;
		this.fillDataModel = new Gson().fromJson(jsonString, FillDataModel.class);
	}

	private String fieldNameLocator = "div.DECKLAYER-PARENT[style*='z-index: 1;'] div.EZ-WGT-FLD-DSPLY-FLYOUT"; 
	
	private String fieldValueLocator = "div.DECKLAYER-PARENT[style*='z-index: 1;'] div.EZ-WGT-ENTRY-FLD-WRP-FLYOUT input";
	
	@FindBy(css = "div.DECKLAYER-PARENT[style*='z-index: 1;'] div.EZ-WGT-ENTRY-FLD-WRP-FLYOUT input+div.ENTRYFLD-TRG-EXPOSED")
	private WebElement fieldValueDropDownExposed;

	@FindBy(css = "div.DECKLAYER-PARENT[style*='z-index: 1;'] div.EZ-WGT-ENTRY-FLD-WRP-FLYOUT input+div.ENTRYFLD-TRG-MODAL-DROP")
	private WebElement fieldValueDropDown;
	
	@Override
	public boolean execute() {
		try {
			startTime = Instant.now();
			setFieldValue(this.fillDataModel.getFieldValue());
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean validate() {
		endTime = Instant.now();
		// logger.info("[RESPTIME] " + Duration.between(startTime, endTime).toMillis());
		return false;
	}

	@Override
	public void setup() {
		
	}

	@Override
	public void tearDown() {
		
	}

	public Integer countCaptions()
	{
		int counter = -1;
		List<WebElement> captionsList = this.driver.findElements(By.cssSelector(fieldNameLocator));
		for(int i=0; i< captionsList.size(); i++) {
			String captionName = PageHelper.getText(this.driver, captionsList.get(i));
			if(captionName.equals(this.fillDataModel.getFieldName()))
			{
				counter = i;
				break;
			}
		}
		return counter;
	}
	
	@Step("Select field value")
	private void setFieldValue(String fieldValue) {
		try {
			int i;
			List<WebElement> fieldsList = this.driver.findElements(By.cssSelector(fieldValueLocator));
			for(i=0; i< fieldsList.size(); i++) {
				if(i == countCaptions())
				{
					PageHelper.clearText(this.driver, fieldsList.get(i));
					PageHelper.sendKeys(this.driver, fieldsList.get(i), fieldValue);
					PageHelper.waitForElementToBeDisplayed(this.driver, fieldValueDropDownExposed);
					fieldsList.get(i).sendKeys(Keys.TAB);
					PageHelper.waitForElementToBeDisplayed(this.driver, fieldValueDropDown);
					break;
				}
			}
		} catch (Exception e) {
			Assert.assertTrue(false, e.getMessage());
		}
	}
}