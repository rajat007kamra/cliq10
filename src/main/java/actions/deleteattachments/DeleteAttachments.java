package actions.deleteattachments;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.google.gson.Gson;

import actions.Actions;
import actions.PageHelper;
import actions.deleteattachments.model.DeleteAttachmentsModel;
import common.variables.CommonVariables;
import io.qameta.allure.Step;
import junit.framework.Assert;
import selenium.driver.Driver;

/**
 * 
 * @author Arun.Kapoor
 *
 */

public class DeleteAttachments extends Driver implements Actions {
	private WebDriver driver;
	private DeleteAttachmentsModel deleteModel;
	private DeleteAttachmentHelper deleteHelper;
	final static Logger logger = Logger.getLogger(DeleteAttachments.class);

	public DeleteAttachments(WebDriver driver, String jsonString) {
		super(driver);
		this.driver = driver;
		this.deleteHelper = new DeleteAttachmentHelper(this.driver);
		this.deleteModel = new Gson().fromJson(jsonString, DeleteAttachmentsModel.class);
	}

	@FindBy(css = "div.DECKLAYER-PARENT[style*='z-index: 1;'] div.realm-dominant-attachment")
	private WebElement attachmentsIcon;
	
	@FindBy(css = "div.detached-attchs-wgt div.ez-attcmts-wgt div.ez-attcmts-wgt-head div.smpl-dlg-cls-trg-ACTIVE")
	private WebElement closeAttachmentIcon;

	@Override
	public boolean execute() {
		try {
			Thread.sleep(5000);
			clickAttachments();
			Thread.sleep(5000);
			List<String> files = this.deleteModel.getDeleteFileName();
			for (String file : files) {
				logger.info("Fetch file number in attachments list for given filename " + file);
				int fileIndex = deleteHelper.getFileIndex(PageHelper.appendHashCode(this.driver, file));
				if (fileIndex >= 0) {
					logger.info(file + " file exists");
					deleteHelper.deleteAttachment(PageHelper.appendHashCode(this.driver, file), fileIndex);
				} else {
					logger.error(file + " file not exists");
				}
			}
			CommonVariables.actionTime = System.currentTimeMillis();
			PageHelper.waitInSeconds(this.driver, PageHelper.X_TIMEOUT_SEC);
			PageHelper.click(this.driver, closeAttachmentIcon);
			logger.info("Close attachment clicked");
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
			List<String> files = this.deleteModel.getDeleteFileName();
			for (String file : files) {
				int fileIndex = deleteHelper.getFileIndex(PageHelper.appendHashCode(this.driver, file));
				if (fileIndex >= 0) {
					logger.error(file + " file not deleted. PLease check");
				} else {
					logger.info(file + " not exists i.e. deleted successfully");
				}
			}
			CommonVariables.responseTime = System.currentTimeMillis();
			CommonVariables.timeTaken = (CommonVariables.responseTime - CommonVariables.actionTime);
			logger.info("[RESPTIME] " + CommonVariables.timeTaken);
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

	@Step("Click on attachments")
	public void clickAttachments() throws InterruptedException {
		verifyAttachments(this.driver);
		PageHelper.click(this.driver, attachmentsIcon);
		logger.info("click on attachments");
		deleteHelper.displayAttachmentList();
	}
}