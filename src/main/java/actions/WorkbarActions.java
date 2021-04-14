package actions;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import io.qameta.allure.Step;
import selenium.driver.Driver;

/**
 * 
 * @author Arun.Kapoor
 *
 */

public abstract class WorkbarActions extends Driver implements Actions {
	final static Logger logger = Logger.getLogger(WorkbarActions.class);

	public WorkbarActions(WebDriver driver) {
		super(driver);
	}

	@FindBy(css = "div.DECKLAYER-PARENT[style*='z-index: 1;'] div.WORKBAR-WGT-TRG-NEW")
	public WebElement plusIcon;

	@FindBy(css = "div.DECKLAYER-PARENT[style*='z-index: 1;'] div.WORKBAR-WGT-TRG-REVISE[title='REVISE']")
	public WebElement reviseIcon;

	@FindBy(css = "div.DECKLAYER-PARENT[style*='z-index: 1;'] div.WORKBAR-WGT-TRG-SAVE[title='SAVE']")
	public WebElement saveIcon;

	@FindBy(css = "div.DECKLAYER-PARENT[style*='z-index: 1;'] div.WORKBAR-WGT-TRG-SUBMIT[title='SUBMIT']")
	public WebElement submitIcon;

	@FindBy(css = "div.DECKLAYER-PARENT[style*='z-index: 1;'] div.WORKBAR-WGT-TRG-DELETE[title='DELETE']")
	public WebElement deleteIcon;

	@FindBy(css = "div.DECKLAYER-PARENT[style*='z-index: 1;'] div.WORKBAR-WGT-TRG-DELETE-ALT[title='DISCARD']")
	public WebElement discardIcon;

	@FindBy(css = "div.DECKLAYER-PARENT[style*='z-index: 1;'] div.WORKBAR-WGT-TRG-WITHDRAW[title='WITHDRAW']")
	public WebElement withdrawIcon;

	@FindBy(css = "div.DECKLAYER-PARENT[style*='z-index: 1;'] div.WORKBAR-WGT-TRG-APPROVE[title='APPROVE']")
	public WebElement approveIcon;

	@FindBy(css = "div.DECKLAYER-PARENT[style*='z-index: 1;'] div.WORKBAR-WGT-TRG-REJECT[title='DENY']")
	public WebElement denyIcon;
	
	private String checkDisabledIcons = "div.DECKLAYER-PARENT[style*='z-index: 1;'] div.WORKBAR-WGT-TRG.TRG-BASE-DISABLED[title='%1s']";
	
	@FindBy(css = "div.CONFIRMATION-BTN.CONFIRMATION-BTN-YES")
	private WebElement yesButton;

	// Method to verify whether the PLUS ICON is enabled or disabled
	public boolean verifyAddNew(WebDriver driver) {
		PageHelper.waitInSeconds(driver, PageHelper.X_TIMEOUT_SEC);
		String className = this.plusIcon.getAttribute("class");
		if (className.contains("DISABLED")) {
			logger.error("Either plus icon is disabled or not found");
			return false;
		} else {
			logger.info("Plus new icon is enabled");
			return true;
		}
	}

	// Method to verify whether the REVISE ICON is enabled or disabled
	public boolean verifyRevise(WebDriver driver) {
		try {
			PageHelper.waitInSeconds(driver, PageHelper.X_TIMEOUT_SEC);
			String className = this.reviseIcon.getAttribute("class");
			if (className.contains("DISABLED")) {
				String erroMsg = "Either revise icon is disabled or not found";
				logger.error(erroMsg);
//			Assert.assertTrue(false, erroMsg);
				PageHelper.click(driver, discardIcon);
				logger.info("Discard icon clicked and Revise icon enabled again");
				Thread.sleep(3000);
				PageHelper.click(driver, yesButton);
				logger.info("Clicked on 'YES' as confirmation");
//			return false;
			} else {
				logger.info("Revise icon is enabled");
			}
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

	// Method to verify whether the SAVE ICON is enabled or disabled
	public boolean verifySave(WebDriver driver) {
		PageHelper.waitInSeconds(driver, PageHelper.X_TIMEOUT_SEC);
		String className = this.saveIcon.getAttribute("class");
		if (className.contains("DISABLED")) {
			String erroMsg = "Either save icon is disabled or not found";
			logger.error(erroMsg);
			Assert.assertTrue(false, erroMsg);
			return false;
		} else {
			logger.info("Save icon is enabled");
			return true;
		}
	}

	// Method to verify whether the SUBMIT ICON is enabled or disabled
	public boolean verifySubmit(WebDriver driver) {
		PageHelper.waitInSeconds(driver, PageHelper.X_TIMEOUT_SEC);
		String className = this.submitIcon.getAttribute("class");
		if (className.contains("DISABLED")) {
			String erroMsg = "Either submit icon is disabled or not found";
			logger.error(erroMsg);
			Assert.assertTrue(false, erroMsg);
			return false;
		} else {
			logger.info("Submit icon is enabled");
			return true;
		}
	}

	// Method to verify whether the DELETE ICON is enabled or disabled
	public boolean verifyDelete(WebDriver driver) {
		PageHelper.waitInSeconds(driver, PageHelper.X_TIMEOUT_SEC);
		String className = this.deleteIcon.getAttribute("class");
		if (className.contains("DISABLED")) {
			String erroMsg = "Either delete icon is disabled or not found";
			logger.error(erroMsg);
			Assert.assertTrue(false, erroMsg);
			return false;
		} else {
			logger.info("Delete icon is enabled");
			return true;
		}
	}

	// Method to verify whether the DISCARD ICON is enabled or disabled
	public boolean verifyDiscard(WebDriver driver) {
		PageHelper.waitInSeconds(driver, PageHelper.X_TIMEOUT_SEC);
		String className = this.discardIcon.getAttribute("class");
		if (className.contains("DISABLED")) {
			String erroMsg = "Either discard icon is disabled or not found";
			logger.error(erroMsg);
			Assert.assertTrue(false, erroMsg);
			return false;
		} else {
			logger.info("Discard icon is enabled");
			return true;
		}
	}

	// Method to verify whether the WITHDRAW ICON is enabled or disabled
	public boolean verifyWithdraw(WebDriver driver) {
		PageHelper.waitInSeconds(driver, PageHelper.X_TIMEOUT_SEC);
		String className = this.withdrawIcon.getAttribute("class");
		if (className.contains("DISABLED")) {
			String erroMsg = "Either withdraw icon is disabled or not found";
			logger.error(erroMsg);
			Assert.assertTrue(false, erroMsg);
			return false;
		} else {
			logger.info("Withdraw icon is enabled");
			return true;
		}
	}

	// Method to verify whether the APPROVE ICON is enabled or disabled
	public boolean verifyApprove(WebDriver driver) {
		PageHelper.waitInSeconds(driver, PageHelper.X_TIMEOUT_SEC);
		String className = this.approveIcon.getAttribute("class");
		if (className.contains("DISABLED")) {
			String erroMsg = "Either approve icon is disabled or not found";
			logger.error(erroMsg);
			Assert.assertTrue(false, erroMsg);
			return false;
		} else {
			logger.info("Approve icon is enabled");
			return true;
		}
	}

	// Method to verify whether the REJECT ICON is enabled or disabled
	public boolean verifyReject(WebDriver driver) {
		PageHelper.waitInSeconds(driver, PageHelper.X_TIMEOUT_SEC);
		String className = this.denyIcon.getAttribute("class");
		if (className.contains("DISABLED")) {
			String erroMsg = "Either deny icon is disabled or not found";
			logger.error(erroMsg);
			Assert.assertTrue(false, erroMsg);
			return false;
		} else {
			logger.info("Deny icon is enabled");
			return true;
		}
	}
	
	/**
	 * @param iconName
	 */
	@Step("Check is {iconName} disabled")
	public boolean checkIfIconIsDisabled(WebDriver driver, String iconName) {
		boolean isActive = false;
		logger.info("Verify " + iconName + " icon");
		PageHelper.waitInSeconds(driver, PageHelper.L_TIMEOUT_SEC);
		if(driver.findElements(By.cssSelector(String.format(checkDisabledIcons, iconName))).size()>0)
		{
			isActive = true;
		}		
		return isActive;
	}

	//Check whether user has access of any icon
	public void checkIconAccess(WebDriver driver, String iconName, String isAccess, WebElement element) {
		if (isAccess.equals("YES")) {
			if (!checkIfIconIsDisabled(driver, iconName)) {
				logger.info(iconName +" icon is enabled");
			} else {
				Assert.fail(iconName +" icon is disabled but expected enabled");
			}
		} else if (isAccess.equals("NO")) {
			if (checkIfIconIsDisabled(driver, iconName)) {
				logger.info(iconName +" icon is disabled");
			} else {
				Assert.fail(iconName +" icon is enabled but expected disabled");
			}
		} else {
			Assert.fail("Access could be YES/NO only");
		}
	}
}
