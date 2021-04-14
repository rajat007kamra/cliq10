package actions.browsernavigation;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.google.gson.Gson;

import actions.Actions;
import actions.PageHelper;
import io.qameta.allure.Step;
import selenium.driver.Driver;

public class BrowserNavigation extends Driver implements Actions {
    
	private WebDriver driver;
	private BrowserNavigationModel backmodel;
    final static Logger logger =Logger.getLogger(BrowserNavigation.class);
    
    @FindBy(css = "input.LOGIN-FIELD-USER")
	private WebElement usernameField;
    
    @FindBy(css = "div.DECKLAYER-PARENT[style*='z-index: 1;'] div.USER-AVATAR-LBL")
	private WebElement userSettings;
    
	public BrowserNavigation(WebDriver driver, String jsonString) {
		super(driver);
		this.driver = driver;
        this.backmodel=new Gson().fromJson(jsonString, BrowserNavigationModel.class);
	}

	@Override
	public boolean execute() {
	    try {
	    	//Thread.sleep(10000);
			PageHelper.waitInSeconds(this.driver, PageHelper.XX_TIMEOUT_SEC);
			browserNavigation(this.backmodel.getnavigation());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false, e.getMessage());
			return false;
		}
	}

	@Override
	public boolean validate() {
		return false;
	}

	@Override
	public void setup() {
		
	}

	@Override
	public void tearDown() {
	
	}
	@Step("Browser Button Click {expectedString}")
	public static void navigate(WebDriver driver,String expectedString) {
		if(expectedString.trim().toUpperCase().equals("BACK"))
			{
			driver.navigate().back();
			logger.info("Browser Back Button Clicked");
			}
		else if(expectedString.trim().toUpperCase().equals("FORWARD")) {
			driver.navigate().forward();
		}
		else {
			logger.info(expectedString+" Not Valid ");
		}
		}
	public void browserNavigation(String direction) {
		navigate(this.driver,this.backmodel.getnavigation());
		if(PageHelper.isElementDisplayed(usernameField)) {
			logger.info("Username Field Found");
		}
		else if(PageHelper.isElementDisplayed(userSettings)){
			logger.info("Security Breached-->Contact Administrator");
		}
		else {
			logger.info("Timeout Error");
		}
	}

}
