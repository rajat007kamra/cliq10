package actions.opensharing;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.google.gson.Gson;

import actions.PageHelper;
import actions.openflyouts.model.OpenFlyoutsModel;
import common.variables.CommonVariables;
import io.qameta.allure.Step;
import junit.framework.Assert;

/**
 * 
 * @author Arun.Kapoor
 *
 */

public class OpenSharing extends OpenSharingLocators {
	private WebDriver driver;
	private OpenFlyoutsModel openFlyoutModel;
	final static Logger logger = Logger.getLogger(OpenSharing.class);

	public OpenSharing(WebDriver driver, String jsonString) {
		super(driver);
		this.driver = driver;
		this.openFlyoutModel = new Gson().fromJson(jsonString, OpenFlyoutsModel.class);
	}

	@Override
	public boolean execute() {
		try {
			Thread.sleep(10000);
			clickFlex(this.openFlyoutModel.getFlexName());
			logger.info("Click flex name- " +this.openFlyoutModel.getFlexName());
			CommonVariables.actionTime = System.currentTimeMillis();
			openFlyout(this.openFlyoutModel.getFlyoutName());
			Thread.sleep(10000);
			logger.info("Click flyout '" +this.openFlyoutModel.getFlyoutName() +"' and open");
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return false;
		}
	}

	@Override
	public boolean validate() {
		try {
			PageHelper.waitInSeconds(this.driver, PageHelper.XX_TIMEOUT_SEC);
			validateFlyout();
			CommonVariables.responseTime = System.currentTimeMillis();
			CommonVariables.timeTaken = (CommonVariables.responseTime - CommonVariables.actionTime);
			logger.info("[RESPTIME] " + CommonVariables.timeTaken);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return false;
		}
	}

	@Override
	public void setup() {

	}

	@Override
	public void tearDown() {

	}

	// Method to check whether QUICKLIST/WORKFLOW items in collapsed or expand mode
	// If collapsed then expand it so that driver find element
	// Classes are {PROFILE=PROFILE, DETAILS=SUMMARY, QUICKLIST=QUICKLIST,
	// TRACKERS=WORKFLOW, DOCUMENTS=ATTACHMENTS, NOTES=NOTES}
	@Step("Click flex {flexName}")
	private void clickFlex(String flexName) {
		if ((this.openFlyoutModel.getFlexName().toUpperCase()).equals("SUMMARY")) {
			flexName = "DETAILS";
		} else if ((this.openFlyoutModel.getFlexName().toUpperCase()).equals("WORKFLOW")) {
			flexName = "TRACKERS";
		} else if ((this.openFlyoutModel.getFlexName().toUpperCase()).equals("ATTACHMENTS")) {
			flexName = "DOCUMENTS";
		}

		String flexOuterName = "div.DECKLAYER-PARENT[style*= 'z-index: 1;'] div.EZ-WGT-FLEX-OUTER-WRP div.EZ-WGT-FLEX-SCT";
		List<WebElement> outerFlexList = this.driver.findElements(By.cssSelector(flexOuterName));
		for (int i = 0; i < outerFlexList.size(); i++) {
			String className = outerFlexList.get(i).getAttribute("class");
			if (className.contains(flexName)) {
				String checkIsFlexCollapsed = "div.DECKLAYER-PARENT[style*= 'z-index: 1;'] div.EZ-WGT-FLEX-OUTER-WRP div.EZ-WGT-FLEX-SCT-"
						+ flexName + " div.EZ-WGT-SUPER-SCT-BDY-OUTER[style*= 'height: 0px;']";				
				if (this.driver.findElements(By.cssSelector(checkIsFlexCollapsed)).size() > 0) {
					String flexNamePath = "//div[@class='" + className
							+ "']//div[@class='EZ-WGT-SPR-SCT-HDR-DSPL EZ-WGT-ITEM-EL EZ-WGT-SPR-SCT-HDR-DSPL-MAIN cursor-pntr'][@tabindex='0']";
					// Expand or Collapse the Flex List
					WebElement flexElement = this.driver.findElement(By.xpath(flexNamePath));
					PageHelper.click(this.driver, flexElement);
				} else {
					logger.info("Flex is already expanded");
				}
			} else {

			}
		}
	}

	// Click flyout
	@Step("Open flyout {flyoutName}")
	private void openFlyout(String flyoutName) throws InterruptedException {
		if ((this.openFlyoutModel.getFlexName().toUpperCase()).equals("QUICKLIST")) {
			clickFlyout(OpenSharingLocators.innerQuicklistFlex, flyoutName);
		} else if ((this.openFlyoutModel.getFlexName().toUpperCase()).equals("WORKFLOW")) {
			clickFlyout(OpenSharingLocators.innerWorkflowFlexClass, flyoutName);
		} else {

		}
	}

	private void clickFlyout(String cssPath, String name) throws InterruptedException {
		List<WebElement> innerFlexList = this.driver.findElements(By.cssSelector(cssPath));
		for (int i = 0; i < innerFlexList.size(); i++) {
			String innerFlexName = innerFlexList.get(i).getText();
			if (innerFlexName.equals(name)) {
				PageHelper.click(this.driver, innerFlexList.get(i));
				break;
			}
		}
	}

	// Method to validate the open Flyout
	@Step("Validate flyout")
	private void validateFlyout() {
		if ((this.openFlyoutModel.getFlexName().toUpperCase()).equals("QUICKLIST")) {
			if (driver.findElements(By.cssSelector(quicklistFlyoutHeader)).size() > 0) {
				verifyFlyoutHeaderText(quicklistFlyoutHeader);
			}
		} else if ((this.openFlyoutModel.getFlexName().toUpperCase()).equals("WORKFLOW")) {
			if (driver.findElements(By.cssSelector(quicklistFlyoutHeader)).size() > 0) {
				verifyFlyoutHeaderText(quicklistFlyoutHeader);
			} else if (driver.findElements(By.cssSelector(workflowFlyoutHeader)).size() > 0) {
				verifyFlyoutHeaderText(workflowFlyoutHeader);
			}
		} else {
			Assert.fail();
		}
	}

	// Method to verify opened flyout header text
	private void verifyFlyoutHeaderText(String element) {
		String flyoutHeaderText = null;
		try {
			Thread.sleep(2000);
			flyoutHeaderText = driver.findElement(By.cssSelector(element)).getText();
			logger.info("Flyout header is ::- " + flyoutHeaderText);
			if (flyoutHeaderText.contains(this.openFlyoutModel.getFlyoutName())) {
				CommonVariables.testPassMessage = this.openFlyoutModel.getFlyoutName()
						+ " flyout opened successfully";
				logger.info(CommonVariables.testPassMessage);
			} else {
				CommonVariables.testFailMessage = this.openFlyoutModel.getFlyoutName()
						+ " is either not found or disabled";
				logger.info(CommonVariables.testFailMessage);
				Assert.fail(CommonVariables.testFailMessage);
			}
		} catch (InterruptedException e) {
			logger.error(e.getMessage(), e);
		}
	}
}
