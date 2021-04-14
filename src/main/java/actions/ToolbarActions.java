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

public abstract class ToolbarActions extends Driver implements Actions {
	final static Logger logger = Logger.getLogger(ToolbarActions.class);

	public ToolbarActions(WebDriver driver) {
		super(driver);
	}

	@FindBy(css = "div.DECKLAYER-PARENT[style*='z-index: 1;'] div.TOP-TOOLBAR-TRG-IV")
	public WebElement integratedView;

	@FindBy(css = "div.DECKLAYER-PARENT[style*='z-index: 1;'] div.TOP-TOOLBAR-TRG-TREE")
	public WebElement treeView;

	@FindBy(css = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.TOP-TOOLBAR-TRG-MOREOPTIONS")
	private WebElement moreOptions;

	@FindBy(css = "div.TOP-TOOLBAR-TRG-EXPORT")
	public WebElement exportListIcon;

	@FindBy(css = "div.TOP-TOOLBAR-TRG-UPLOAD")
	public WebElement uploadAttachment;

	@FindBy(css = "div.TOP-TOOLBAR-TRG-PASTE")
	public WebElement pasteData;

	private String checkIsIconDisabled = "div.DECKLAYER-PARENT[style*='z-index: 1;'] div.TOP-TOOLBAR-TRG.TRG-BASE-DISABLED[title='%1s']";
	private String checkIsExportListDisabled = "div.TOP-TOOLBAR-TRG-EXPORT.TRG-BASE-DISABLED";
    private String checkIsUploadDocumentDisabled = "div.TOP-TOOLBAR-TRG-UPLOAD.TRG-BASE-DISABLED";
    private String checkIsPasteDataDisabled = "div.TOP-TOOLBAR-TRG-PASTE.TRG-BASE-DISABLED";
	// Method to verify whether the INTEGRATED VIEW is enabled or disabled
	public boolean verifyIntegratedView(WebDriver driver) {
		PageHelper.waitForElementToBeDisplayed(driver, integratedView);
		String className = this.integratedView.getAttribute("class");
		if (className.contains("DISABLED")) {
			String erroMsg = "Either integrated view is disabled or not found";
			logger.error(erroMsg);
			Assert.assertTrue(false, erroMsg);
			return false;
		} else {
			logger.info("Integrated view is enabled");
			return true;
		}
	}

	// Method to verify whether the TREE VIEW is enabled or disabled
	public boolean verifyTreeView(WebDriver driver) {
		PageHelper.waitForElementToBeDisplayed(driver, treeView);
		String className = this.treeView.getAttribute("class");
		if (className.contains("DISABLED")) {
			String erroMsg = "Either tree view is disabled or not found";
			logger.error(erroMsg);
			Assert.assertTrue(false, erroMsg);
			return false;
		} else {
			logger.info("Tree view is enabled");
			return true;
		}
	}

	// Method to verify whether the EXPORT VIEWZONE is enabled or disabled
	public boolean verifyExportView(WebDriver driver) {
		PageHelper.waitForElementToBeDisplayed(driver, exportListIcon);
		String className = this.exportListIcon.getAttribute("class");
		if (className.contains("DISABLED")) {
			String erroMsg = "Either export list is disabled or not found";
			logger.error(erroMsg);
			Assert.assertTrue(false, erroMsg);
			return false;
		} else {
			logger.info("Export list is enabled");
			return true;
		}
	}

	// Method to verify whether the UPLOAD ATTACHMENT is enabled or disabled
	public boolean verifyUploadAttachment(WebDriver driver) {
		PageHelper.waitForElementToBeDisplayed(driver, uploadAttachment);
		String className = this.uploadAttachment.getAttribute("class");
		if (className.contains("DISABLED")) {
			String erroMsg = "Either upload file is disabled or not found";
			logger.error(erroMsg);
			Assert.assertTrue(false, erroMsg);
			return false;
		} else {
			logger.info("Upload file is enabled");
			return true;
		}
	}

	// Method to verify whether the PASTE DATA is enabled or disabled
	public boolean verifyPasteData(WebDriver driver) {
		PageHelper.waitForElementToBeDisplayed(driver, pasteData);
		String className = this.pasteData.getAttribute("class");
		if (className.contains("DISABLED")) {
			String erroMsg = "Either paste data is disabled or not found";
			logger.error(erroMsg);
			Assert.assertTrue(false, erroMsg);
			return false;
		} else {
			logger.info("Paste data is enabled");
			return true;
		}
	}

	// Method to click on MoreOptions
	public void clickMoreOptions(WebDriver driver) {
		PageHelper.click(driver, moreOptions);
	}

	/**
	 * @param iconName
	 */
	@Step("Check is {iconName} disabled")
	public boolean checkIfIconIsDisabled(WebDriver driver, String iconName) {
		boolean isActive = false;
		logger.info("Verify " + iconName + " icon");
		PageHelper.waitInSeconds(driver, PageHelper.L_TIMEOUT_SEC);
		if (iconName.equals("EXPORT LIST")) {
			if (driver.findElements(By.cssSelector(checkIsExportListDisabled)).size() > 0) {
				isActive = true;
			}
		} else if (iconName.equals("UPLOAD ATTACHMENT")) {
			if (driver.findElements(By.cssSelector(checkIsUploadDocumentDisabled)).size() > 0) {
				isActive = true;
			}
		} else if (iconName.equals("PASTE DATA")) {
			if (driver.findElements(By.cssSelector(checkIsPasteDataDisabled)).size() > 0) {
				isActive = true;
			}
		} else {
			if (driver.findElements(By.cssSelector(String.format(checkIsIconDisabled, iconName))).size() > 0) {
				isActive = true;
			}
		}
		return isActive;
	}

	// Check whether user has access of any icon
	public void checkIconAccess(WebDriver driver, String iconName, String isAccess) {
		if (isAccess.equals("YES")) {
			if (!checkIfIconIsDisabled(driver, iconName)) {
				logger.info(iconName + " icon is enabled");
			} else {
				Assert.fail(iconName + " icon is disabled but expected enabled");
			}
		} else if (isAccess.equals("NO")) {
			if (checkIfIconIsDisabled(driver, iconName)) {
				logger.info(iconName + " icon is disabled");
			} else {
				Assert.fail(iconName + " icon is enabled but expected disabled");
			}
		} else {
			Assert.fail("Access could be YES/NO only");
		}
	}
}