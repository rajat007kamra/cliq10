package actions.attachments;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import actions.PageHelper;
import io.qameta.allure.Step;

/**
 * 
 * @author Arun.Kapoor
 *
 */

public class AttachmentsHelper {
	public WebDriver driver;
	final static Logger logger = Logger.getLogger(AttachmentsHelper.class);

	public AttachmentsHelper(WebDriver driver) {
		this.driver = driver;
	}

	public String attachmentList = "div.detached-attchs-wgt div.ez-wgt-wide-itm.cursor-pntr.bold span.ez-wgt-wide-itm-lbl";
	public String downloadBtn = "div.DECKLAYER-PARENT[style*='z-index: 1'] div[title='%1S'] span.ez-wgt-wide-itm-download[title='DOWNLOAD ATTACHMENT']";
	public String editZoneExpandLoc = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.REALM-HD-RGHT div.shuffle-trg.TRG-BASE";
	public String disabledAttachmentPath = "div.DECKLAYER-PARENT[style*='z-index: 1;'] div.realm-dominant-attachment div.ez-drawer-option.TRG-BASE-DISABLED[title*='%1S']";

	@Step("Fetch the file attachment name {attach}")
	public void findAttachment(String attachmentName) {
		String fileName = null;
		List<WebElement> fileElements = this.driver.findElements(By.cssSelector(attachmentList));
		for (int i = 0; i < fileElements.size(); i++) {
			fileName = PageHelper.getText(this.driver, fileElements.get(i));
			if ((fileName.trim()).contains(attachmentName)) {
				logger.info(fileName + " file exists");
				String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
				if (extension.equals("XLSX")||extension.equals("xlsx")) {
					logger.info(fileName + " extension is " + extension);
				} else if (extension.equals("CSV")||extension.equals("csv")) {
					logger.info(fileName + " extension is " + extension);
				} else if (extension.equals("PDF")||extension.equals("pdf")) {
					logger.info(fileName + " extension is " + extension);
				} else {
					logger.error(fileName + " extension is " + extension);
				}
				break;
			} else {
				String errorMsg = attachmentName + " file not exists";
				logger.info(errorMsg);	Assert.fail(errorMsg);
			}
		}
	}
	
	@Step("Fetch the file attachment index {0}")
	public Integer getFileIndex(String name) {
		int counter = -1;
		String fileName = null; String subFileName = null;
		List<WebElement> fileElements = this.driver.findElements(By.cssSelector(attachmentList));
		for (int i = 0; i < fileElements.size(); i++) {
			fileName = PageHelper.getText(this.driver, fileElements.get(i));
			if (fileName.contains(".XLSX")) {
				subFileName = fileName.substring(0, fileName.indexOf(".XLSX"));
			} else if (fileName.contains(".CSV")) {
				subFileName = fileName.substring(0, fileName.indexOf(".CSV"));
			} else if (fileName.contains(".PDF")) {
				subFileName = fileName.substring(0, fileName.indexOf(".PDF"));
			} else {
				logger.error("Extenion of file is other than XLSX, CSV, PDF. Please check");
			}
//			System.out.println(subFileName);
			if ((fileName.trim()).startsWith(subFileName)) {
				counter = i;
				break;
			}
		}
		return counter;
	}

	@Step("Download file {varValue}")
	public String downloadAttachment(String name) throws InterruptedException {
		By byDownloadButton = By.cssSelector(String.format(downloadBtn, name));
		PageHelper.waitForElementPresence(this.driver, byDownloadButton, PageHelper.DEFAULT_TIMEOUT_SEC);
		WebElement downloadButtonElem = this.driver.findElement(byDownloadButton);
		PageHelper.clickUsingJs(this.driver, downloadButtonElem);
		logger.info("Download button clicked for filename - " +name);
		return name;
	}

//	Method to verify whether Alert popup exists or not
	public boolean isAlertPresent() {
		boolean foundAlert = false;
		WebDriverWait wait = new WebDriverWait(driver, 2);
		try {
			wait.until(ExpectedConditions.alertIsPresent());
			foundAlert = true;
		} catch (TimeoutException eTO) {
			foundAlert = false;
		}
		return foundAlert;
	}

	@Step("Expand edit zone")
	public void expandEditZone() {
		By byEditExpand = By.cssSelector(editZoneExpandLoc);
		PageHelper.waitForElementVisibility(this.driver, byEditExpand, PageHelper.DEFAULT_TIMEOUT_SEC);
		WebElement expandEditElem = this.driver.findElement(byEditExpand);
		String title = expandEditElem.getAttribute("title");
		if (title.equals("EXPAND")) {
			PageHelper.clickUsingJs(this.driver, expandEditElem);
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
		if(driver.findElements(By.cssSelector(String.format(disabledAttachmentPath, iconName))).size()>0)
		{
			isActive = true;
		}		
		return isActive;
	}

	//Check whether user has access of any icon
	public void checkIconAccess(WebDriver driver, String iconName, String isAccess) {
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