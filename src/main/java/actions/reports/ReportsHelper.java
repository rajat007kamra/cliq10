package actions.reports;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import actions.PageHelper;
import io.qameta.allure.Step;

public class ReportsHelper {
	private WebDriver driver;
	final static Logger logger = Logger.getLogger(ReportsHelper.class);

	private String refreshIcon = "div.DECKLAYER-PARENT[style*='z-index: 1;'] div.GRID-BUTTON.TOP-TOOLBAR-TRG-REFRESH";
	private String filterTextbox = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.FLY div.REPORTS-WGT-PANEL1 table.GRID-WGT-HDRS input.GRID-WGT-FLTR-TXT";
	private String filterResults = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.FLY div.REPORTS-WGT-PANEL1 div.REPORTS-CELL.GRID-WGT-ROW-ACTIVE div.REPORTS-WGT-GRID-BTN";
	private String runButton = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.FLY div.REPORTS-WGT-GRID-BTN";
	private String panel1TableRows = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.FLY div.REPORTS-WGT-PANEL1 div.GRID-IDX-WGT div";
	private String panel2TableRows = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.FLY[style*='z-index: 1'] div.REPORTS-WGT-PANEL2 div.GRID-IDX-WGT div";

	private String parametersValue = "//div[@class='DECKLAYER-PARENT' and contains(@style,'z-index: 1;')]//div[@title='%1S']/following::input[1]";
	private String sharingTabList = "div.reportrun-tabs-holder div.reportrun-tab";
	private String addIcon = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.STND-PAD-ELS-BLCK.STND-PAD-ELS-BLCK-ROW-FLEX div[title='ADD ROW']";
	private String addUsersInput = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.STND-PAD-ELS-BLCK-ROW-FLEX input.ENTRYFLD-TXT[placeholder='Type user name']";
	private String switchIcons = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.FLY.REPORTRUN-WGT div.switch input[type='checkbox']";
	private String addEmailInput = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.STND-PAD-ELS-BLCK-ROW-FLEX input.ENTRYFLD-TXT[placeholder='Type email address']";
	private String addFTPInput = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.STND-PAD-ELS-BLCK-ROW-FLEX input.ENTRYFLD-TXT[placeholder='Type FTP Name']";

	private String cancelButton = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.button-cancel";
	private String onlyRunButton = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.button-run";
	private String runAndShareButton = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.button-runshare";
	private String flyCloseIcon = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.FLY.REPORTRUN-WGT[style*='z-index: 2'] div.FLY-CLOSE-TRG[title='CLOSE']";

	private String reportsHeader = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.REPORTS-WGT-PANEL2 div.GRID-WGT table.GRID-WGT-HDRS div[title='%1S']";
	private String reportsFilter = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.REPORTS-WGT-PANEL2 div.GRID-WGT table.GRID-WGT-HDRS td.GRID-WGT-FLTR-OUTER input.GRID-WGT-FLTR-TXT";
	private String reportWidgetIcons = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.REPORTS-WGT-PANEL2 div.STND-PAD-HDR-MAIN-BTM-BOR div.REPORTS-HEADER-TRG[title='%1S']";

	private String reportsWidgetHeader = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.FLY.REPORTRUN-WGT[style*='z-index: 2'] div.toolbar-header";

	private String rowsXPath = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.FLY.REPORTRUN-WGT div.REPORT-WGT-TABPANEL div.STND-PAD-ELS-BLCK-ROW-FLEX";
	private String xyz = "input.ENTRYFLD-TXT-STND-PAD-RESTRICTED[placeholder='Type user name']";

	private String informationFieldName = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.FLY.REPORTRUN-WGT div.STND-PAD-HDR.STND-PAD-HDR-DFLT.STND-PAD-ITEM-EL";
	private String informationFieldValue = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.FLY.REPORTRUN-WGT div.ENTRYFLD.ENTRYFLD-STND-PAD-WID";

	public ReportsHelper(WebDriver driver) {
		this.driver = driver;
	}

	@Step("Set filter text {filterText} at column {columnNumber}")
	public void setFilterText(String filterText) {
		String filter = PageHelper.appendHashCode(this.driver, filterText);
		WebElement columnTextElem = driver.findElement(By.cssSelector(filterTextbox));
		columnTextElem.sendKeys(filter);
		columnTextElem.sendKeys(Keys.ENTER);
	}

	@Step("Validate template searched text")
	public void validateTemplatesResult(String filterText) {
		PageHelper.waitInSeconds(this.driver, PageHelper.XX_TIMEOUT_SEC);
		if (this.driver.findElements(By.cssSelector(filterResults)).size() > 0) {
			logger.info(filterText + " validated successfully");
		} else {
			Assert.fail(filterText + " record not found");
		}
	}

	@Step("Click refresh icon")
	public void clickRefresh() {
		this.driver.findElement(By.cssSelector(refreshIcon)).click();
		logger.info("Refresh icon clicked on reports process");
	}

	@Step("Click run icon")
	public void clickRun(String rowNumber, String option) throws InterruptedException {
		if (option.equals("YES")) {
			int rowNo = Integer.parseInt(rowNumber) - 1;
			PageHelper.waitForElementVisibility(this.driver, By.cssSelector(runButton), PageHelper.X_TIMEOUT_SEC);
			List<WebElement> runIconLists = this.driver.findElements(By.cssSelector(runButton));
			System.out.println(runIconLists.size());
			for (int i = 0; i < runIconLists.size(); i++) {
				if (i == rowNo) {
					Thread.sleep(3000);
					runIconLists.get(i).click();
					logger.info("Run clicked for row number " + rowNumber);
				}
			}
		} else if (option.equals("NO")) {
			logger.error("To fill details RUN value should be YES");
		} else {
			Assert.assertTrue(false, "Run value could be YES or NO");
		}
	}

	@Step("Select row number {rowNumber}")
	public void selectRow(String rowNumber, String widget) throws InterruptedException {
		List<WebElement> tableRowElements = null;
		Thread.sleep(10000);
		if (widget.equals("TEMPLATES")) {
			tableRowElements = this.driver.findElements(By.cssSelector(panel1TableRows));
			for (int i = 0; i < tableRowElements.size(); i++) {
				int rowNum = Integer.parseInt(rowNumber) - 1;
				if (i == rowNum) {
					Thread.sleep(5000);
					PageHelper.click(this.driver, tableRowElements.get(i));
					break;
				}
			}
		} else if (widget.equals("REPORTS")) {
			Thread.sleep(5000);
			tableRowElements = this.driver.findElements(By.cssSelector(panel2TableRows));
			for (int i = 0; i < tableRowElements.size(); i++) {
				int rowNum = Integer.parseInt(rowNumber) - 1;
				if (i == rowNum) {
					Thread.sleep(5000);
					PageHelper.click(this.driver, tableRowElements.get(i));
					break;
				}
			}
		} else {
			Assert.assertTrue(false, widget + " not valid");
		}
	}

	@Step("Set parameter value {fieldValue} in {fieldName} field")
	public void setParamValue(String fieldName, String fieldValue) throws InterruptedException {
		PageHelper.waitForElementVisibility(this.driver, By.xpath(String.format(parametersValue, fieldName)),
				PageHelper.X_TIMEOUT_SEC);
		WebElement valueField = this.driver.findElement(By.xpath(String.format(parametersValue, fieldName)));
		valueField.click();
		valueField.clear();
		valueField.sendKeys(fieldValue);
	}

	@Step("Click tab {tabName}")
	public void clickSharingTab(String tabName) {
		List<WebElement> tabs = this.driver.findElements(By.cssSelector(sharingTabList));
		for (int i = 0; i < tabs.size(); i++) {
			String name = tabs.get(i).getText();
			if (name.trim().equals(tabName)) {
				tabs.get(i).click();
				String isSelected = tabs.get(i).getAttribute("class");
				if (isSelected.contains("reportrun-tab-selected")) {
					logger.info(tabName + " opened successfully");
				} else {
					Assert.assertTrue(false, tabName + " either not exists or not found");
				}
			}
		}
	}

	@Step("Set text in {uname}")
	public void setReportSharingData(String header, String text) throws InterruptedException {
		List<WebElement> addIcons = null;
		List<WebElement> inputs = null;
		int index = 0;
		addIcons = this.driver.findElements(By.cssSelector(addIcon));
		if (header.equals("USERS")) {
			addIcons.get(0).click();
			inputs = this.driver.findElements(By.cssSelector(addUsersInput));
		} else if (header.equals("EMAIL")) {
			addIcons.get(1).click();
			inputs = this.driver.findElements(By.cssSelector(addEmailInput));
		} else if (header.equals("FTP")) {
			addIcons.get(2).click();
			inputs = this.driver.findElements(By.cssSelector(addFTPInput));
		} else {
			Assert.assertTrue(false, header + " not found");
		}
		inputs.get(inputs.size() - 1).sendKeys(text);
		Thread.sleep(3000);
		inputs.get(inputs.size() - 1).sendKeys(Keys.TAB);
	}

	@Step("Click button {buttonName}")
	public void clickButton(String iconName) {
		if (iconName.equals("CANCEL")) {
			if (this.driver.findElements(By.cssSelector(cancelButton)).size() > 0) {
				this.driver.findElement(By.cssSelector(cancelButton)).click();
			} else {
				Assert.assertTrue(false, iconName + " either not found or disabled");
			}
		} else if (iconName.equals("RUN") || iconName.equals("RE-RUN")) {
			if (this.driver.findElements(By.cssSelector(onlyRunButton)).size() > 0) {
				this.driver.findElement(By.cssSelector(onlyRunButton)).click();
			} else {
				Assert.assertTrue(false, iconName + " either not found or disabled");
			}
		} else if (iconName.equals("RUN & SHARE") || iconName.equals("RE-RUN & SHARE") || iconName.equals("SHARE")) {
			if (this.driver.findElements(By.cssSelector(runAndShareButton)).size() > 0) {
				this.driver.findElement(By.cssSelector(runAndShareButton)).click();
			} else {
				Assert.assertTrue(false, iconName + " either not found or disabled");
			}
		} else {
			Assert.assertTrue(false, iconName + " not exists");
		}
	}

	@Step("Close run fly widget")
	public void closeRunFlyWidget() {
		if (this.driver.findElements(By.cssSelector(flyCloseIcon)).size() > 0) {
			this.driver.findElement(By.cssSelector(flyCloseIcon)).click();
		} else {

		}
	}

	@Step("Fetch the column number of column {columnName}")
	public Integer getColumnNumber(String columnName) {
		int counter = -1;
		List<WebElement> tableHeaders = this.driver
				.findElements(By.cssSelector(String.format(reportsHeader, columnName)));
		for (int i = 0; i < tableHeaders.size(); i++) {
			String colName = tableHeaders.get(i).getAttribute("title");
			if (colName.trim().equalsIgnoreCase(columnName)) {
				counter = i;
				break;
			}
		}
		return counter;
	}

	@Step("Set filter text {filterText} at column {columnNumber}")
	public WebElement setReportsWidgetFilter(int columnNumber, String filterText) {
		List<WebElement> filterElements = this.driver.findElements(By.cssSelector(reportsFilter));
		WebElement filterElement = filterElements.get(columnNumber);
		PageHelper.sendKeys(driver, filterElement, filterText);

		return filterElement;
	}

	// Method to verify whether the REPORT WIDGET ICON is enabled or disabled
	public boolean verifyReportsWidgetIcon(String iconName) {
		PageHelper.waitInSeconds(this.driver, PageHelper.X_TIMEOUT_SEC);
		WebElement icon = this.driver.findElement(By.cssSelector(String.format(reportWidgetIcons, iconName)));
		String className = icon.getAttribute("class");
		if (className.contains("DISABLED")) {
			logger.error("Either " + iconName + " is disabled or not found");
			return false;
		} else {
			logger.info(iconName + " icon is enabled");
			return true;
		}
	}

	@Step("Click report widget icon {iconName}")
	public void clickReportsWidgetIcon(String iconName) {
		PageHelper.waitInSeconds(this.driver, PageHelper.X_TIMEOUT_SEC);
		WebElement icon = this.driver.findElement(By.cssSelector(String.format(reportWidgetIcons, iconName)));
		icon.click();
		logger.info("Clicked on " + iconName);
		PageHelper.waitForPageLoad(this.driver);
	}

	@Step("Validate icon {iconName}")
	public void verifyReportsWidgetHeader(String iconName, String actualHeader) throws InterruptedException {
		WebElement widgetHeader = this.driver.findElement(By.cssSelector(reportsWidgetHeader));
		String header = PageHelper.getText(this.driver, widgetHeader);
		System.out.println("WIDGET HEADER FOUND ::- " + header);
		if (header.contains(actualHeader)) {
			logger.info(iconName + " widget opened successfully");
			Thread.sleep(5000);
//			this.driver.findElement(By.cssSelector(flyCloseIcon)).click();
//			logger.info(iconName +" widget closed");
		} else {
			Assert.assertTrue(false, "Either RERUN widget not opened or not found");
		}
	}

	@Step("{iconName} icon value is {iconValue}")
	public void selectSwitchIcon(String iconName, String iconValue, int i) {
		String reportSharingContextLOcator = "div.DECKLAYER-PARENT[style*=' z-index: 1;'] div[class='width-100-pct']";
		String rows = "div.STND-PAD-ELS-BLCK-ROW-FLEX";
		String inputs = "div.txt-cntr-algn";

		List<WebElement> reportSharingElems = this.driver.findElements(By.cssSelector(reportSharingContextLOcator));
		List<WebElement> rowElems = reportSharingElems.get(0).findElements(By.cssSelector(rows));

		WebElement rowElm = rowElems.get(0);

////		List<WebElement> onOffIcons = this.driver.findElements(By.cssSelector(switchIcons));
//		checked = isAttribtuePresent(onOffIcons.get(i), "checked");
//		if((checked==true) && iconValue.equals("YES")) {
//			logger.info(iconName +" icon is already checked");
//		}
//		else if((checked==true) && iconValue.equals("NO")) {
//			onOffIcons.get(i).click();
//		}
//		else if((checked==false) && iconValue.equals("YES")) {
////			inputs.get(inputs.size() - 1).sendKeys(uname);
//			onOffIcons.get(i).click();
//		}
//		else if((checked==true) && iconValue.equals("NO")) {
//			logger.info(iconName +" icon is already unchecked");
//		}
//		else {
//			Assert.assertTrue(false, iconValue +" not valid. Could be YES/NO");
//		}		
	}

	// Method to verify is CHECKED attribute exists for 'IN-APP' & 'EMAIL' icons in
	// users
	private boolean isAttribtuePresent(WebElement element, String attribute) {
		Boolean result = false;
		try {
			String value = element.getAttribute(attribute);
			if (value != null) {
				result = true;
			}
		} catch (Exception e) {
		}

		return result;
	}

	@Step("Fetch the field number of field {fieldName}")
	public Integer getVariableNumber(String fieldName) {
		int counter = -1;
		List<WebElement> varHeaders = this.driver.findElements(By.cssSelector(informationFieldName));
		for (int i = 0; i < varHeaders.size(); i++) {
			String colName = varHeaders.get(i).getAttribute("title");
			if (colName.trim().equalsIgnoreCase(fieldName)) {
				counter = i;
				break;
			}
		}
		return counter;
	}

	@Step("Read value of variable")
	public String getVariableValue(int index, String varName) throws InterruptedException {
		int i;
		String varValue = null;
		List<WebElement> valueLists = this.driver.findElements(By.cssSelector(informationFieldValue));
		for (i = 0; i < valueLists.size(); i++) {
			if (i == index) {
				Thread.sleep(5000);
				valueLists.get(i).click();
				Thread.sleep(5000);
				varValue = valueLists.get(i).getAttribute("title");
				logger.info("Value fetched from " + varName + " is- " + varValue);
				System.out.println(varValue);
				break;
			}
		}
		return varValue;
	}
}
