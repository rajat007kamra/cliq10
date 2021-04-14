package actions.nextpreviousprocesses;

import java.time.Duration;
import java.time.Instant;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.google.gson.Gson;

import actions.Actions;
import actions.PageHelper;
import actions.nextpreviousprocesses.model.NextPreviousProcessesModel;
import selenium.driver.Driver;

/**
 * 
 * @author Arun.Kapoor
 *
 */

public class NextPreviousProcesses extends Driver implements Actions {
	private Instant startTime;
	private Instant endTime;
	private WebDriver driver;
	private NextPreviousProcessesModel previousProcessesModel;
	final static Logger logger = Logger.getLogger(NextPreviousProcesses.class);

	public NextPreviousProcesses(WebDriver driver, String jsonString) {
		super(driver);
		this.driver = driver;
		this.previousProcessesModel = new Gson().fromJson(jsonString, NextPreviousProcessesModel.class);
	}

	@FindBy(css = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.BACK-NAV-TRG[title='PREVIOUS PROCESS']")
	private WebElement previousProcessIcon;

	@FindBy(css = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.BACK-NAV-TRG[title='NEXT PROCESS']")
	private WebElement nextProcessIcon;

	@FindBy(css = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.VZ-TAB-SELECTED div.VZ-TAB-DSPLY")
	private WebElement tabName;

	@Override
	public boolean execute() {
		try {
			startTime = Instant.now();
			currentTabName();
			clickOnProcesses(this.previousProcessesModel.getProcessesName().trim().toUpperCase());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false, e.getMessage());
			return false;
		}
	}

	@Override
	public boolean validate() {
		endTime = Instant.now();
		logger.info("[RESPTIME] " + Duration.between(startTime, endTime).toMillis());
		return false;
	}

	@Override
	public void setup() {

	}

	@Override
	public void tearDown() {

	}

	// Method to verify whether the PREVIOUS PROCESS is enabled or disabled
	public boolean verifyPreviousProcess(WebDriver driver) {
		PageHelper.waitForElementToBeDisplayed(driver, previousProcessIcon);
		String className = this.previousProcessIcon.getAttribute("class");
		if (className.contains("DISABLED")) {
			logger.error("Either previous process icon is disabled or not found");
			return false;
		} else {
			logger.info("Previous process icon is enabled");
			return true;
		}
	}

	// Method to verify whether the NEXT PROCESS is enabled or disabled
	public boolean verifyNextProcess(WebDriver driver) {
		PageHelper.waitForElementToBeDisplayed(driver, nextProcessIcon);
		String className = this.nextProcessIcon.getAttribute("class");
		if (className.contains("DISABLED")) {
			logger.error("Either next process is disabled or not found");
			return false;
		} else {
			logger.info("Next process icon is enabled");
			return true;
		}
	}

	// Method to click on PROCESS
	private void clickOnProcesses(String process_name) {
		if ((process_name).equals("NEXT")) {
			verifyNextProcess(this.driver);
			PageHelper.click(this.driver, nextProcessIcon);
			logger.info("Next icon clicked successfully");
		} else if ((process_name).equals("PREVIOUS")) {
			verifyPreviousProcess(this.driver);
			PageHelper.click(this.driver, previousProcessIcon);
			logger.info("Previous icon clicked successfully");
		} else {
			logger.error("Process name not exists in application");
		}
		currentTabName();
	}

	// Method to get current Tab name in focus
	private void currentTabName() {
		String focusedTabName = PageHelper.getText(this.driver, tabName);
		logger.info("Currently selected tab is ::- " + focusedTabName);
	}
}
