package actions.reports;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.google.gson.Gson;

import actions.Actions;
import actions.PageHelper;
import actions.reports.model.Informations;
import actions.reports.model.Parameters;
import actions.reports.model.ReportsFilter;
import actions.reports.model.ReportsModel;
import actions.reports.model.Users;
import common.variables.CommonVariables;
import io.qameta.allure.Step;
import selenium.driver.Driver;

public class Reports extends Driver implements Actions {
	private Instant startTime;
	private Instant endTime;
	private WebDriver driver;
	private ReportsHelper reportHelper;
	private ReportsModel reportModel;

	final static Logger logger = Logger.getLogger(Reports.class);

	public Reports(WebDriver driver, String jsonString) {
		super(driver);
		this.driver = driver;
		this.reportHelper = new ReportsHelper(this.driver);
		this.reportModel = new Gson().fromJson(jsonString, ReportsModel.class);
	}

	private String cancelButton = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.button-cancel";

	@FindBy(css = "div.cmplt-noti-dlg-lbl.cmplt-noti-dlg-lbl-top")
	private WebElement topMessage;

	@Override
	public boolean execute() {
		try {
			startTime = Instant.now();
			executeCode();
			return true;
		} catch (Exception e) {
			Assert.assertTrue(false, e.getMessage());
			return false;
		}
	}

	@Override
	public boolean validate() {
		try {
			if (this.reportModel.getButton() != null && !this.reportModel.getButton().isEmpty()) {
				if (this.reportModel.getButton().trim().toUpperCase().equals("RUN")
						|| this.reportModel.getButton().trim().toUpperCase().equals("RUN & SHARE")) {
					Assert.assertEquals(validateRunOrRunShare().trim(), ("REPORT GENERATION"));
				} else if (this.reportModel.getButton().trim().toUpperCase().equals("CANCEL")) {
					if (this.driver.findElements(By.cssSelector(cancelButton)).size() > 0) {
						Assert.assertTrue(false, "Cancel icon not clicked");
					}
				}
			}
			if (this.reportModel.getReportIcon() != null && !this.reportModel.getReportIcon().isEmpty()) {
				if (this.reportModel.getReportIcon().trim().toUpperCase().equals("RERUN")){
					reportHelper.verifyReportsWidgetHeader(this.reportModel.getReportIcon().trim().toUpperCase(), "RE-RUN");
				}
				else if (this.reportModel.getReportIcon().trim().toUpperCase().equals("SHARE")){
					reportHelper.verifyReportsWidgetHeader(this.reportModel.getReportIcon().trim().toUpperCase(), "SHARE");
				}
				else if (this.reportModel.getReportIcon().trim().toUpperCase().equals("APPROVE")){
					
				}
				else if (this.reportModel.getReportIcon().trim().toUpperCase().equals("DENY")){
					
				}
				else if (this.reportModel.getReportIcon().trim().toUpperCase().equals("DOWNLOAD")){
					Assert.assertEquals(validateRunOrRunShare().trim(), ("DOWNLOAD INITIATED"));
				}
				else if (this.reportModel.getReportIcon().trim().toUpperCase().equals("INFORMATION")){
					reportHelper.verifyReportsWidgetHeader(this.reportModel.getReportIcon().trim().toUpperCase(), "INFORMATION");
				}
			}
			
			if (this.reportModel.getInformations() != null && !this.reportModel.getInformations().isEmpty()) {
				List<Informations> infoElems = this.reportModel.getInformations();
				for (Informations info : infoElems) {
					int inofNum = reportHelper.getVariableNumber(info.getVariable().trim().toUpperCase());
					if (inofNum >= 0) {
						String fieldValue = reportHelper.getVariableValue(inofNum, info.getVariable().trim().toUpperCase());
						Assert.assertEquals(fieldValue.trim(), info.getExpected().trim().toUpperCase());
					} else {
						logger.error(info.getVariable() + " does not exists");
					}
				}
			}
			endTime = Instant.now();
			logger.info("[RESPTIME] " + Duration.between(startTime, endTime).toMillis());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false, e.getMessage());
			return false;
		}
	}

	@Override
	public void setup() {

	}

	@Override
	public void tearDown() {
		logger.info("To be configured later");
	}

	public void executeCode() throws InterruptedException {
//		reportHelper.clickRefresh();
		Thread.sleep(5000);
		startTime = Instant.now();
		if (reportModel.getTemplateName() != null && !reportModel.getTemplateName().isEmpty()) {
			logger.info("Set text "
					+ PageHelper.appendHashCode(this.driver, reportModel.getTemplateName().trim().toUpperCase()));
			reportHelper.setFilterText(
					PageHelper.appendHashCode(this.driver, reportModel.getTemplateName().trim().toUpperCase()));
			Thread.sleep(5000);
			selectWidgetRow("TEMPLATES");
		} else {
			Assert.assertTrue(false, "Templates search is mandatory");
		}
		Thread.sleep(5000);
		if (reportModel.getParameters() != null && !reportModel.getParameters().isEmpty()) {
			List<Parameters> parameters = this.reportModel.getParameters();
			for (Parameters params : parameters) {
				logger.info("Set value is " + params.getParamName().trim().toUpperCase() + " in "
						+ params.getParamName().trim().toUpperCase());
				reportHelper.setParamValue(params.getParamName().trim().toUpperCase(),
						params.getParamValue().trim().toUpperCase());
			}
		}
		if (reportModel.getUsers() != null && !reportModel.getUsers().isEmpty()) {
			reportHelper.clickSharingTab("EMAIL & IN-APP");
			List<Users> users = this.reportModel.getUsers();
			for (Users user : users) {
				//set user name
				if(user.getUserName()!=null && !user.getUserName().isEmpty())
				{
					logger.info("Set user - " + user.getUserName());
					reportHelper.setReportSharingData("USERS", user.getUserName());
				}
				else {
					Assert.assertTrue(false, "user name cannot be blank or null");
				}
				//set in-app icon
				if(user.getInAppIcon()!=null && !user.getInAppIcon().isEmpty()) {
					reportHelper.selectSwitchIcon("IN-APP", user.getInAppIcon().trim().toUpperCase(), 0);
				}
				//set email icon
				if(user.getEmailIcon()!=null && !user.getEmailIcon().isEmpty()) {
					reportHelper.selectSwitchIcon("EMAIL", user.getEmailIcon().trim().toUpperCase(), 1);
				}
				
			}
		}
		if (reportModel.getEmails() != null && !reportModel.getEmails().isEmpty()) {
			reportHelper.clickSharingTab("EMAIL & IN-APP");
			List<String> emails = this.reportModel.getEmails();
			for (String email : emails) {
				//set external email
				if(email!=null && !email.isEmpty())
				{
					logger.info("Set email - " + email);
					reportHelper.setReportSharingData("EMAIL", email);
				}
				else {
					Assert.assertTrue(false, "external email cannot be blank or null");
				}
			}
		}
		if (reportModel.getFTP() != null && !reportModel.getFTP().isEmpty()) {
			reportHelper.clickSharingTab("FTP");
			List<String> ftps = this.reportModel.getFTP();
			for (String ftp : ftps) {
				if(ftp!=null && !ftp.isEmpty())
				{
					logger.info("Set FTP - " + ftp);
					reportHelper.setReportSharingData("FTP", ftp);
				}
				else {
					Assert.assertTrue(false, "ftp cannot be blank or null");
				}
			}
		}
		if (reportModel.getButton() != null && !reportModel.getButton().isEmpty()) {			
			reportHelper.clickButton(reportModel.getButton().trim().toUpperCase());
			logger.info("Clicked " +reportModel.getButton().trim().toUpperCase() +" button");
		}
		if (reportModel.getReports() != null && !reportModel.getReports().isEmpty()) {
			WebElement filterRowElem = null;
			reportHelper.closeRunFlyWidget();
			List<ReportsFilter> filterTexts = this.reportModel.getReports();
			for (ReportsFilter report : filterTexts) {
				logger.info("Fetch column number in table for given column " + report.getTitle().trim().toUpperCase());
				int columnNumber = reportHelper.getColumnNumber(report.getTitle().trim().toUpperCase());
				if (columnNumber >= 0) {
					logger.info("Set value - " + report.getText().trim().toUpperCase() + " in column "
							+ report.getTitle().trim().toUpperCase());
					filterRowElem = reportHelper.setReportsWidgetFilter(columnNumber,
							report.getText().trim().toUpperCase());
				} else {
					logger.error(report.getTitle() + " does not exists");
				}
			}
			PageHelper.waitInSeconds(this.driver, PageHelper.XXX_TIMEOUT_SEC);
			PageHelper.sendKeys(driver, filterRowElem, Keys.ENTER, false);
			// Small static wait required to wait for page load initialization
			logger.warn("Static wait introduced");
			PageHelper.waitInSeconds(this.driver, PageHelper.X_TIMEOUT_SEC);
			logger.info("Wait for page to load");
			PageHelper.waitForPageLoad(this.driver);
			selectWidgetRow("REPORTS");
		}
		if (reportModel.getReportIcon() != null && !reportModel.getReportIcon().isEmpty()) {
			reportHelper.verifyReportsWidgetIcon(reportModel.getReportIcon().trim().toUpperCase());
			reportHelper.clickReportsWidgetIcon(reportModel.getReportIcon().trim().toUpperCase());
		}
		
		if (reportModel.getInformations() != null && !reportModel.getInformations().isEmpty()) {
			List<Informations> getInfoTexts = this.reportModel.getInformations();
			for (Informations text : getInfoTexts) {
				System.out.println("abc");
				int fieldNumber = reportHelper.getVariableNumber(text.getVariable().trim().toUpperCase());
				logger.info("Fetched variable number " +fieldNumber + " for " +text.getVariable());
				if(fieldNumber>=0)
				{
					reportHelper.getVariableValue(fieldNumber, text.getVariable());
					// Small static wait required to wait for page load initialization
					logger.warn("Static wait introduced");
					PageHelper.waitInSeconds(this.driver, PageHelper.X_TIMEOUT_SEC);
					logger.info("Wait for page to load");
					PageHelper.waitForPageLoad(this.driver);
				}
				else {
					logger.error(text.getVariable() +" does not exists");
				}
			}
		}
	}

	public void selectWidgetRow(String widget) throws InterruptedException {
		// Small static wait required to wait for page load initialization
//		Thread.sleep(5000);
		if (widget.equals("TEMPLATES")) {
			this.reportHelper.selectRow(this.reportModel.getSelectTemplateRow(), widget);
			logger.info(this.reportModel.getSelectTemplateRow() + " row selected successfully");
			PageHelper.waitInSeconds(this.driver, PageHelper.X_TIMEOUT_SEC);
			if(this.reportModel.getRun()!=null && !reportModel.getRun().isEmpty()) {
				reportHelper.clickRun(this.reportModel.getSelectTemplateRow(), this.reportModel.getRun().trim().toUpperCase());
			}			
		} else if (widget.equals("REPORTS")) {
			this.reportHelper.selectRow(this.reportModel.getSelectReportRow(), widget);
			logger.info(this.reportModel.getSelectReportRow() + " row selected successfully");
			PageHelper.waitInSeconds(this.driver, PageHelper.X_TIMEOUT_SEC);
		} else {
			Assert.assertTrue(false, widget + " not exists");
		}
	}

	@Step("Get message")
	private String validateRunOrRunShare() {
		logger.info("waiting for message popup");
		PageHelper.waitForElementToBeDisplayed(this.driver, topMessage);
		CommonVariables.notificationMsg = PageHelper.getText(this.driver, topMessage);
		logger.info("Notification message found ::- " + CommonVariables.notificationMsg);
		return CommonVariables.notificationMsg;
	}
}