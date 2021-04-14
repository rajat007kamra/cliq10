package actions.deleteattachments;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import actions.PageHelper;
import io.qameta.allure.Step;

/**
 * 
 * @author Arun.Kapoor
 *
 */

public class DeleteAttachmentHelper {
	public WebDriver driver;
	final static Logger logger = Logger.getLogger(DeleteAttachmentHelper.class);
	
	public DeleteAttachmentHelper(WebDriver driver) {
		this.driver = driver;
	}
	
	private String attachmentsName = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.ez-attcmts-wgt div.ez-wgt-wide-itm";	
	public String attachmentList = "div.detached-attchs-wgt div.ez-wgt-wide-itm.cursor-pntr.bold span.ez-wgt-wide-itm-lbl";
	public String deleteBtn = "div.detached-attchs-wgt div.ez-wgt-wide-itm.cursor-pntr.bold span[title='DELETE']";
	
	@Step("Fetch the file attachment index {0}")
	public Integer getFileIndex(String name) {
		int counter = -1;
		String fileName = null;String subFileName = null;
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
			if ((fileName.trim()).startsWith(subFileName)) {
				counter = i;
				break;
			}
		}
		return counter;
	}
	
	@Step("Delete file {varValue}")
	public String deleteAttachment(String name, int index) throws InterruptedException {
		int i;
		List<WebElement> fileList = this.driver.findElements(By.cssSelector(deleteBtn));
		for (i = 0; i < fileList.size(); i++) {
			if (i == index)
			{
				fileList.get(i).click();
				logger.info("Delete icon clicked for file name - " +name);
				break;
			} else {

			}
		}
		return name;
	}
	
	@Step("Display list of all attachments")
	public void displayAttachmentList() {
		List<WebElement> attachmentList = this.driver.findElements(By.cssSelector(attachmentsName));
		logger.info("Attached files name are :- ");
		for (WebElement list : attachmentList) {
			logger.info(list.getAttribute("title"));
		}
	}
}