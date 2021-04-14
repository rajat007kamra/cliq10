package actions.uploaddocument;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.google.gson.Gson;

import actions.Actions;
import actions.PageHelper;
import actions.output.base.OutputController;
import actions.output.pojo.Outcome;
import actions.uploaddocument.model.UploadDocumentModel;
import selenium.driver.Driver;

/**
 * 
 * @author Arun.Kapoor
 *
 */

public class UploadDocument extends Driver implements Actions {
	private WebDriver driver;
	private String testName;
	private String testDescription;
	private Instant startTime;
	private Instant endTime;
	private UploadDocumentHelper uploadDocumentHelper;
	private UploadDocumentModel uploadDocumentModel;
	private static String outputjsonpath = "target/";
	private static String inputjsonpath = "src/test/resources/testdata/outcome/fileoutcome.json";
	final static Logger logger = Logger.getLogger(UploadDocument.class);

	public UploadDocument(WebDriver driver, String jsonString) {
		super(driver);
		this.driver = driver;
		this.uploadDocumentHelper = new UploadDocumentHelper(this.driver);
		this.uploadDocumentModel = new Gson().fromJson(jsonString, UploadDocumentModel.class);
	}

	public UploadDocument(WebDriver driver, String jsonString, String testName, String testDescription) {
		super(driver);
		this.driver = driver;
		this.testName = testName;
		this.testDescription = testDescription;
		this.uploadDocumentHelper = new UploadDocumentHelper(this.driver);
		this.uploadDocumentModel = new Gson().fromJson(jsonString, UploadDocumentModel.class);
	}

	@FindBy(css = "div.cmplt-noti-dlg-lbl.cmplt-noti-update-dlg-lbl.inln-blck.v-mid-algn")
	private WebElement checkMainMessage;

	@FindBy(css = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.TOP-TOOLBAR-TRG-MOREOPTIONS")
	private WebElement moreOptions;
	
	@Override
	public boolean execute() {
		try {
			uploadDocumentHelper.clickMoreOptions(this.driver);
//			PageHelper.click(this.driver, moreOptions);
			PageHelper.waitInSeconds(this.driver, PageHelper.L_TIMEOUT_SEC);
//			Thread.sleep(100000);
			startTime = Instant.now();
			if (this.uploadDocumentModel.getAccess() != null && !this.uploadDocumentModel.getAccess().isEmpty()) {
				uploadDocumentHelper.checkIconAccess(this.driver, "UPLOAD ATTACHMENT", this.uploadDocumentModel.getAccess().trim().toUpperCase());
			} else {
				logger.info("validate upload document button is available");
				// uploadDocumentHelper.validateUploadDocButton();
				logger.info("Upload document " + this.uploadDocumentModel.getPath());
				uploadDocumentHelper.closeErrorPopup();
				
				uploadDocumentHelper.uploadDocument(this.uploadDocumentModel.getPath());
				
			}			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false, e.getMessage());
			return false;
		}
		return true;
	}

	@Override
	public boolean validate() {
		try {
//			TimeUnit.SECONDS.sleep(10);
//			PageHelper.waitForTextToPresent(this.driver, checkMainMessage, "SAVED!", PageHelper.FILE_UPLOAD_TIME);
//			logger.info("Verify document " + this.uploadDocumentModel.getName() + " is uploaded");
			endTime = Instant.now();
			logger.info("[RESPTIME] " + Duration.between(startTime, endTime).toMillis());
			TimeUnit.MINUTES.sleep(2);
			uploadDocumentHelper.expandEditZone();
			if (this.testDescription != null) {
				// Wait for some time before starting to collect data
				collectOutCome(this.testName, this.testDescription);
			}
			uploadDocumentHelper.closeEditZone();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false, e.getMessage());
			return false;
		}
		return true;
	}

	@Override
	public void setup() {

	}

	@Override
	public void tearDown() {
		
	}

	private void collectOutCome(String testName, String testDescription) throws IOException {
		FileWriter file = new FileWriter(outputjsonpath + this.driver.hashCode() + ".json");
		try {
			Outcome outcome = new Gson().fromJson(new FileReader(inputjsonpath), Outcome.class);
			OutputController outputController = new OutputController(this.driver, new Gson().toJson(outcome), testName,
					testDescription);
			Map<String, String> values = outputController.readData();
			file.write(new Gson().toJson(values));
		} finally {
			file.close();
		}
	}
}
