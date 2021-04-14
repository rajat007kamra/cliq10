package actions;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import selenium.driver.Driver;

/**
 * 
 * @author Arun.Kapoor
 *
 */

public abstract class EditZoneActions extends Driver implements Actions {
	final static Logger logger = Logger.getLogger(RealmActions.class);

	public EditZoneActions(WebDriver driver) {
		super(driver);
	}

	@FindBy(css = "div.DECKLAYER-PARENT[style*='z-index: 1;'] div.FLY-CLARIFIERS-TRG[title='CLARIFICATION']")
	public WebElement editZoneClarifierIcon;

	@FindBy(css = "div.DECKLAYER-PARENT[style*='z-index: 1;'] div.FLY-REVISE-TRG[title='REVISE']")
	public WebElement editZoneReviseIcon;

	@FindBy(css = "div.DECKLAYER-PARENT[style*='z-index: 1;'] div.FLY-SAVE-TRG[title='SAVE']")
	public WebElement editZoneSaveIcon;

	@FindBy(css = "div.DECKLAYER-PARENT[style*='z-index: 1;'] div.FLY-SUBMIT-TRG[title='SUBMIT']")
	public WebElement editZoneSubmitIcon;

	@FindBy(css = "div.DECKLAYER-PARENT[style*='z-index: 1;'] div.FLY-DEL-TRG[title='DISCARD']")
	public WebElement editZoneDeleteIcon;
	
	@FindBy(css = "div.DECKLAYER-PARENT[style*='z-index: 1;'] div.FLY-TOP-TRG.HOR-FLEX-EL-CLEAR[title='CLEAR']")
	public WebElement editZoneClearIcon;

	@FindBy(css = "div.DECKLAYER-PARENT[style*='z-index: 1;'] div.FLY-PIN-TRG[title='PIN']")
	public WebElement editZonePinIcon;

	@FindBy(css = "div.DECKLAYER-PARENT[style*='z-index: 1;'] div.FLY-RFRSH-TRG[title='REFRESH']")
	public WebElement editZoneRefreshIcon;

	@FindBy(css = "div.DECKLAYER-PARENT[style*='z-index: 1;'] div.FLY-CLOSE-TRG[title='CLOSE']")
	public WebElement editZoneCloseIcon;

	// Method to verify whether the CLARIFIER button is enabled or disabled
	public boolean verifyEditZoneClarifier(WebDriver driver) {
		PageHelper.waitInSeconds(driver, PageHelper.X_TIMEOUT_SEC);
		String className = this.editZoneClarifierIcon.getAttribute("class");
		if (className.contains("DISABLED")) {
			String erroMsg = "Either clarifier icon is disabled or not found";
			logger.error(erroMsg);
			Assert.assertTrue(false, erroMsg);
			return false;
		} else {
			logger.info("Clarifier icon is enabled");
			return true;
		}
	}

	// Method to verify whether the EDIT/REVISE button is enabled or disabled
	public boolean verifyEditZoneRevise(WebDriver driver) {
		PageHelper.waitInSeconds(driver, PageHelper.X_TIMEOUT_SEC);
		String className = this.editZoneReviseIcon.getAttribute("class");
		if (className.contains("DISABLED")) {
			String erroMsg = "Either revise icon is disabled or not found";
			logger.error(erroMsg);
			Assert.assertTrue(false, erroMsg);
			return false;
		} else {
			logger.info("Edit/Revise icon is enabled");
			return true;
		}
	}

	// Method to verify whether the SAVE button is enabled or disabled
	public boolean verifyEditZoneSave(WebDriver driver) {
		PageHelper.waitInSeconds(driver, PageHelper.X_TIMEOUT_SEC);
		String className = this.editZoneSaveIcon.getAttribute("class");
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

	// Method to verify whether the SUBMIT button is enabled or disabled
	public boolean verifyEditZoneSubmit(WebDriver driver) {
		PageHelper.waitInSeconds(driver, PageHelper.X_TIMEOUT_SEC);
		String className = this.editZoneSubmitIcon.getAttribute("class");
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

	// Method to verify whether the DELETE button is enabled or disabled
	public boolean verifyEditZoneDelete(WebDriver driver) {
		PageHelper.waitInSeconds(driver, PageHelper.X_TIMEOUT_SEC);
		String className = this.editZoneDeleteIcon.getAttribute("class");
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

	// Method to verify whether the CLEAR button is enabled or disabled
	public boolean verifyEditZoneClear(WebDriver driver) {
		PageHelper.waitInSeconds(driver, PageHelper.X_TIMEOUT_SEC);
		String className = this.editZoneClearIcon.getAttribute("class");
		if (className.contains("DISABLED")) {
			String erroMsg = "Either clear icon is disabled or not found";
			logger.error(erroMsg);
			Assert.assertTrue(false, erroMsg);
			return false;
		} else {
			logger.info("Clear icon is enabled");
			return true;
		}
	}

	// Method to verify whether the PIN button is enabled or disabled
	public boolean verifyEditZonePin(WebDriver driver) {
		PageHelper.waitInSeconds(driver, PageHelper.X_TIMEOUT_SEC);
		String className = this.editZonePinIcon.getAttribute("class");
		if (className.contains("DISABLED")) {
			String erroMsg = "Either pin icon is disabled or not found";
			logger.error(erroMsg);
			Assert.assertTrue(false, erroMsg);
			return false;
		} else {
			logger.info("Pin icon is enabled");
			return true;
		}
	}

	// Method to verify whether the REFRESH button is enabled or disabled
	public boolean verifyEditZoneRefresh(WebDriver driver) {
		PageHelper.waitInSeconds(driver, PageHelper.X_TIMEOUT_SEC);
		String className = this.editZoneRefreshIcon.getAttribute("class");
		if (className.contains("DISABLED")) {
			String erroMsg = "Either refresh icon is disabled or not found";
			logger.error(erroMsg);
			Assert.assertTrue(false, erroMsg);
			return false;
		} else {
			logger.info("Refresh icon is enabled");
			return true;
		}
	}

	// Method to verify whether the CLOSE button is enabled or disabled
	public boolean verifyEditZoneClose(WebDriver driver) {
		PageHelper.waitInSeconds(driver, PageHelper.X_TIMEOUT_SEC);
		String className = this.editZoneCloseIcon.getAttribute("class");
		if (className.contains("DISABLED")) {
			String erroMsg = "Either close icon is disabled or not found";
			logger.error(erroMsg);
			Assert.assertTrue(false, erroMsg);
			return false;
		} else {
			logger.info("Close icon is enabled");
			return true;
		}
	}
}
