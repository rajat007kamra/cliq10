package actions.attachments;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.google.gson.Gson;

//import actions.Actions;
import actions.PageHelper;
import actions.attachments.model.AttachmentsModel;
import common.variables.CommonVariables;
import io.qameta.allure.Step;
import junit.framework.Assert;
import selenium.driver.Driver;

/**
 * 
 * @author Arun.Kapoor
 *
 */

public class Attachments extends Driver implements actions.Actions {
	private Instant startTime;
	private Instant endTime;
	private WebDriver driver;
	private AttachmentsModel attachmentsModel;
	private AttachmentsHelper attachmentHelper;
	final static Logger logger = Logger.getLogger(Attachments.class);

	public Attachments(WebDriver driver, String jsonString) {
		super(driver);
		this.driver = driver;
		this.attachmentHelper = new AttachmentsHelper(this.driver);
		this.attachmentsModel = new Gson().fromJson(jsonString, AttachmentsModel.class);
	}

	@FindBy(css = "div.DECKLAYER-PARENT[style*='z-index: 1;'] div.realm-dominant-attachment div.ez-drawer-option[title*='ATTACHMENT']")
	private WebElement attachmentsIcon;

	@FindBy(css = "div.detached-attchs-wgt div.ez-attcmts-wgt div.ez-attcmts-wgt-head div.smpl-dlg-cls-trg-ACTIVE")
	private WebElement closeAttachmentIcon;

	private String attachmentsHeader = "div.detached-attchs-wgt div.ez-attcmts-wgt-head div.cursor-pntr";
	private String attachmentsName = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.ez-attcmts-wgt div.ez-wgt-wide-itm";

	@FindBy(css = "div.cmplt-noti-dlg-lbl.cmplt-noti-dlg-lbl-top")
	private WebElement topMessage;

	@FindBy(css = "div.cmplt-noti-dlg-lbl.cmplt-noti-dlg-lbl-bottom")
	private WebElement bottomMessage;
	
	@Override
	public boolean execute() {
		try {
			Thread.sleep(10000);
			startTime = Instant.now();
			if (this.attachmentsModel.getAccess() != null && !this.attachmentsModel.getAccess().isEmpty()) {
				attachmentHelper.checkIconAccess(this.driver, "ATTACHMENTS",
						this.attachmentsModel.getAccess().trim().toUpperCase());
			} else {
				//expandEditZone();
				clickAttachments();
				if (this.attachmentsModel.getFileToVerify() != null
						&& !this.attachmentsModel.getFileToVerify().isEmpty()) {
					List<String> files = this.attachmentsModel.getFileToVerify();
					for (String file : files) {
						attachmentHelper
								.findAttachment(PageHelper.appendHashCode(this.driver, file.trim().toUpperCase()));
					}
				}
				if ((this.attachmentsModel.getIsDownload().toUpperCase().trim()).equals("YES")) {
					if (this.attachmentsModel.getfileToDownload() != null
							&& !this.attachmentsModel.getfileToDownload().isEmpty()) {
						List<String> files = this.attachmentsModel.getfileToDownload();
						for (String file : files) {
							String appendFile = PageHelper.appendHashCode(this.driver, file.trim().toUpperCase());
							logger.info("Fetch file number in attachments list for given filename " + appendFile
									+ " to download");
							int fileIndex = attachmentHelper.getFileIndex(appendFile);
							if (fileIndex >= 0) {
								attachmentHelper.downloadAttachment(appendFile);
								Assert.assertEquals("DOWNLOAD INITIATED", validateDownload().trim().toUpperCase());
							} else {
								String errorMsg = PageHelper.appendHashCode(this.driver, appendFile)
										+ " file not exists";
								logger.error(errorMsg);
								Assert.fail(errorMsg);
							}
						}
					}
				} else {
					logger.info("Is download option choosed :- " + this.attachmentsModel.getIsDownload());
				}
//				CommonVariables.actionTime = System.currentTimeMillis();
//				PageHelper.waitInSeconds(this.driver, PageHelper.X_TIMEOUT_SEC);
//				PageHelper.click(this.driver, closeAttachmentIcon);
//				logger.info("Close attachment clicked");
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(e.getMessage(), false);
			return false;
		}
	}

	@Override
	public boolean validate() {
		try {
//			validateAttachments();
			endTime = Instant.now();
			logger.info("[RESPTIME] " + Duration.between(startTime, endTime).toMillis());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(e.getMessage(), false);
			return false;
		}
	}

	@Override
	public void setup() {

	}

	@Override
	public void tearDown() {

	}

	@Step("Click on attachments")
	private void clickAttachments() throws InterruptedException {
		verifyAttachments(this.driver);
		Thread.sleep(5000);
		PageHelper.click(this.driver, attachmentsIcon);
		logger.info("click on attachments");
		validateAttachmentsPopUp();
		displayAttachmentList();
	}

	@Step("Display list of all attachments")
	private void displayAttachmentList() {
		List<WebElement> attachmentList = this.driver.findElements(By.cssSelector(attachmentsName));
		logger.info("Attached files name are :- ");
		for (WebElement list : attachmentList) {
			logger.info(list.getAttribute("title"));
		}
	}

	@Step("Validate view attachments")
	private void validateAttachmentsPopUp() {
		List<WebElement> headerList = driver.findElements(By.cssSelector(attachmentsHeader));
		String headerText = headerList.get(0).getText();
		logger.info("View attachments header found ::- " + headerText);
		if (headerText.equals("ATTACHMENTS")) {
			logger.info("Attachment popup opened successfully");
		} else {
			String erroMsg = "Attachments popup not found";
			logger.error(erroMsg);
			Assert.fail(erroMsg);
		}
	}

	@Step("Verify Message after download icon clicked")
	private String validateDownload() {
		logger.info("waiting for downloading message popup");
		PageHelper.waitForElementToBeDisplayed(this.driver, topMessage);
		CommonVariables.notificationMsg = PageHelper.getText(this.driver, topMessage);
		logger.info("Top notification message found ::- " + CommonVariables.notificationMsg);
		String bottomNotification = PageHelper.getText(this.driver, bottomMessage);
		logger.info("Bottom notification message found ::- " + bottomNotification);
		return CommonVariables.notificationMsg;
	}

	// Method to verify whether the ATTACHMENTS is enabled or disabled
	public boolean verifyAttachments(WebDriver driver) {
		PageHelper.waitForElementToBeDisplayed(driver, attachmentsIcon);
		String className = this.attachmentsIcon.getAttribute("class");
		if (className.contains("DISABLED")) {
			String erroMsg = "Either attachments is disabled or not found";
			logger.error(erroMsg);
			Assert.assertTrue(erroMsg, false);
			return false;
		} else {
			logger.info("Attachments is enabled");
			return true;
		}
	}

}
