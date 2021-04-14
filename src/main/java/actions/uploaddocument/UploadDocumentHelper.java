package actions.uploaddocument;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import actions.PageHelper;
import io.qameta.allure.Step;

/**
 * 
 * @author Arun.Kapoor
 *
 */

public class UploadDocumentHelper {
	private WebDriver driver;
	final static Logger logger = Logger.getLogger(UploadDocumentHelper.class);
	private String uploadButton = "div.TOP-TOOLBAR-TRG-UPLOAD";
	private String expandMoreOptions = "div.TOP-TOOLBAR-TRG-MOREOPTIONS";
	private String uploadInput = "div.fileupload-wgt > input";
	private String uploadedDoc = "div.ez-wgt-wide-itm[title='%1s']";
	private String errorFlyout = "div.rslts-wgt";
	private String closeErroFlyout = "div.rslts-wgt div.FLY-CLOSE-TRG";
	private String checkuploadAttachmentDisabled = "div.TOP-TOOLBAR-TRG-UPLOAD.TRG-BASE-DISABLED[title='%1s']";
	private String moreOptions = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.TOP-TOOLBAR-TRG-MOREOPTIONS";
	private String suffleLocatorCollapsed = "div.DECKLAYER-PARENT[style*='z-index: 1;'] div.REALM-HD-RGHT-QONCIERGE div[class*='shuffle-trg TRG-BASE trg-base material-icons'][title='EXPAND']";
	private String suffleLocatorExpand = "div.DECKLAYER-PARENT[style*='z-index: 1;'] div.REALM-HD-RGHT-QONCIERGE div[class*='shuffle-trg TRG-BASE trg-base material-icons'][title='COLLAPSE']";

	public UploadDocumentHelper(WebDriver driver) {
		this.driver = driver;
	}

	// Method to click on MoreOptions
	public void clickMoreOptions(WebDriver driver) {
		PageHelper.waitInSeconds(driver, PageHelper.X_TIMEOUT_SEC);
		WebElement moreOptionElem = driver.findElement(By.cssSelector(moreOptions));
		PageHelper.clickUsingJs(driver, moreOptionElem);
	}

	/**
	 *
	 * @param docPath
	 */
	@Step("Upload document {docPath}")
	public void uploadDocument(String docPath) {
		try {
			File file = new File(docPath);
			String path = file.getAbsolutePath();
			WebElement uploadInputElem = this.driver.findElement(By.cssSelector(uploadInput));
			TimeUnit.SECONDS.sleep(PageHelper.X_TIMEOUT_SEC);
			uploadInputElem.sendKeys(path);
			TimeUnit.SECONDS.sleep(PageHelper.XX_TIMEOUT_SEC);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 *
	 */
	@Step("Verify that UPLOAD DOCUMENT button is available")
	public void validateUploadDocButton() {
		WebElement moreOpt = this.driver.findElement(By.cssSelector(expandMoreOptions));
		PageHelper.click(this.driver, moreOpt);
		WebElement uploadButtonElem = this.driver.findElement(By.cssSelector(uploadButton));
		PageHelper.waitForElementToBeDisplayed(this.driver, uploadButtonElem);
		boolean isVisible = PageHelper.isElementDisplayed(uploadButtonElem);
		Assert.assertTrue(isVisible, "UPLOAD button is missing");
	}

	/**
	 *
	 * @param docName
	 * @throws InterruptedException
	 */
	@Step("verify that document is uploaded {docName}")
	public void verifyDocumentUploaded(String docName) throws InterruptedException {
		PageHelper.waitForElementPresence(this.driver,
				By.cssSelector(String.format(uploadedDoc, docName.toUpperCase())), PageHelper.DEFAULT_TIMEOUT_SEC);
		List<WebElement> uploadedElem = this.driver
				.findElements(By.cssSelector(String.format(uploadedDoc, docName.toUpperCase())));
		boolean isUploaded = PageHelper.isElementPresent(uploadedElem);
		Assert.assertTrue(isUploaded, "DOCUMENT is not uploaded");
	}

	public void closeErrorPopup() {
		try {
			PageHelper.waitInSeconds(this.driver, PageHelper.L_TIMEOUT_SEC);
			By byErrorFlyout = By.cssSelector(errorFlyout);

			if (PageHelper.isElementDisplayed(this.driver.findElement(byErrorFlyout))) {
				WebElement closeElem = this.driver.findElement(By.cssSelector(closeErroFlyout));
				PageHelper.click(this.driver, closeElem);
			}

		} catch (Exception e) {
			System.out.println("Ignore the error if occurs");
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
		if (driver.findElements(By.cssSelector(String.format(checkuploadAttachmentDisabled, iconName))).size() > 0) {
			isActive = true;
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

	public void expandEditZone() {
		List<WebElement> editZoneSuffle = this.driver.findElements(By.cssSelector(suffleLocatorCollapsed));
		if (editZoneSuffle.size() > 0) {
			Actions actions = new Actions(this.driver);
			actions.moveToElement(this.driver.findElement(By.cssSelector(suffleLocatorCollapsed))).build().perform();
			PageHelper.clickUsingJs(this.driver, this.driver.findElement(By.cssSelector(suffleLocatorCollapsed)));
		}
	}

	public void closeEditZone() {
		List<WebElement> editZoneSuffle = this.driver.findElements(By.cssSelector(suffleLocatorExpand));
		if (editZoneSuffle.size() > 0) {
			Actions actions = new Actions(this.driver);
			actions.moveToElement(this.driver.findElement(By.cssSelector(suffleLocatorExpand))).build().perform();
			PageHelper.clickUsingJs(this.driver, this.driver.findElement(By.cssSelector(suffleLocatorExpand)));
		}
	}
}
