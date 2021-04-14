package actions.formview;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import actions.Actions;
import actions.PageHelper;
import common.variables.CommonVariables;
import io.qameta.allure.Step;
import selenium.driver.Driver;

/**
 * 
 * @author Arun.Kapoor
 *
 */

public class FormView extends Driver implements Actions {
	private Instant startTime;
	private Instant endTime;
	private WebDriver driver;
	final static Logger logger = Logger.getLogger(FormView.class);

	public FormView(WebDriver driver, String jsonString) {
		super(driver);
		this.driver = driver;
	}

	@FindBy(css = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.FLY-CLOSE-TRG")
	private WebElement closeFormView;

	@FindBy(css = "div.DECKLAYER-PARENT[style*='z-index: 1;'] div.realm-dominant-formView")
	private WebElement viewZoneFormView;
	
//	@FindBy(css = "div.DECKLAYER-PARENT[style*='z-index: 1;'] div.EZ-WGT-TRG.material-icons[title='FORM VIEW']")
//	private WebElement editZoneformView;

	@Override
	public boolean execute() {
		try {
			startTime = Instant.now();
			verifyFormView(this.driver);
			clickFormView();
			CommonVariables.actionTime = System.currentTimeMillis();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false, e.getMessage());
			return false;
		}
	}

	@Override
	public boolean validate() {
		try {
			endTime = Instant.now();
			validateFormView();
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
		try {
			TimeUnit.MINUTES.sleep(5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void tearDown() {

	}

	@Step("Click on form view")
	private void clickFormView() {
		PageHelper.waitInSeconds(this.driver, PageHelper.XX_TIMEOUT_SEC);
		PageHelper.click(this.driver, viewZoneFormView);
		logger.info("clicked on form view");
		
	}

	@Step("validate form view")
	private void validateFormView() throws InterruptedException {
		logger.info("waiting for active form view");
		Thread.sleep(5000);
		PageHelper.isElementDisplayed(closeFormView);
		logger.info("Form view is active i.e. opened");
	}

	// Method to verify whether the FORM VIEW is enabled or disabled
	public boolean verifyFormView(WebDriver driver) {
		PageHelper.waitForElementToBeDisplayed(driver, viewZoneFormView);
		String className = this.viewZoneFormView.getAttribute("class");
		if (className.contains("DISABLED")) {
			logger.error("Either form view is disabled or not found");
			return false;
		} else {
			logger.info("Form view is enabled");
			return true;
		}
	}
}
