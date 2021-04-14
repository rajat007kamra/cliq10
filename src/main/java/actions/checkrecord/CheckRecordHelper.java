package actions.checkrecord;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import actions.PageHelper;
import io.qameta.allure.Step;

public class CheckRecordHelper {
	private WebDriver driver;
	final static Logger logger = Logger.getLogger(CheckRecordHelper.class);

	public CheckRecordHelper(WebDriver driver) {
		this.driver = driver;
	}

	private String viewColumnsHeaderXPath = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.GRID-WGT[style*='z-index: 1'] table.GRID-WGT-HDRS div.GRID-WGT-HDR-DSPLY";
	private String viewZoneTableXPathToSelectRecord = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.GRID-WGT[style*='z-index: 1'] div.GRID-IDX-WGT div";
	private String viewzoneSingleCellValue = "div.DECKLAYER-PARENT[style*='z-index: 1;'] div.GRID-WGT[style*='z-index: 1;'] div.GRID-WGT-TBLS table.TABLE-WGT tr.GRID-WGT-ROW td[row='%1S'][col='%1S'] div";
	private String viewzoneFooter = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.GRID-WGT[style*='z-index: 1'] div.GRID-TOTALS-WGT div.GRID-MSG-WGT span.GRID-MSG-WGT-LABEL";

	@Step("Fetch the column number of column {columnName}")
	public Integer getColumnNumber(String columnName) {
		int counter = -1;
		String variable = null;
		String updateVariable = null;
		List<WebElement> variableElements = this.driver.findElements(By.cssSelector(viewColumnsHeaderXPath));
		for (int i = 0; i < variableElements.size(); i++) {
			variable = PageHelper.getText(this.driver, variableElements.get(i));
			if (variable.contains("*")) {
				updateVariable = variable.replace("*", "");
			} else {
				updateVariable = variable;
			}
			if ((updateVariable.trim()).equals(columnName)) {
				counter = i;
				break;
			}
		}
		return counter;
	}

	@Step("Fetch the value of title {title}")
	public void checkAllRowsValue(int colNo, String title, String text, String record) throws InterruptedException {
		String foundText = null;
		int activeRows = findVisibleRecords();
		if (record.equals("ALL")) {
			for (int i = 0; i < activeRows; i++) {
				WebElement cellIndexText = this.driver
						.findElement(By.cssSelector(String.format(viewzoneSingleCellValue, i, colNo)));
				foundText = cellIndexText.getText();
				System.out.println(foundText);
				if (!foundText.trim().equals(text)) {
					String message = "Value found in row no- " + (i + 1) + " is " + foundText + " for title " + title
							+ " which was not expected";
					Assert.assertTrue(false, message);
				}
			}
		} else {
			int rowNum = Integer.parseInt(record);
			WebElement cellIndexText = this.driver
					.findElement(By.cssSelector(String.format(viewzoneSingleCellValue, rowNum - 1, colNo)));
			System.out.println(cellIndexText.getText());
			if (!cellIndexText.getText().trim().equals(text)) {
				String message = "Value found in row no- " + (rowNum) + " is " + cellIndexText.getText() + " for title "
						+ title + " which was not expected";
				Assert.assertTrue(false, message);
			}
		}
	}

	@Step("Select row number {rowNumber}")
	public void selectRecord(String rowNumber) {
		List<WebElement> tableRowElements = this.driver.findElements(By.cssSelector(viewZoneTableXPathToSelectRecord));
		for (int i = 0; i < tableRowElements.size(); i++) {
			try {
				int rowNum = Integer.parseInt(rowNumber) - 1;
				if (i == rowNum) {
					PageHelper.waitInSeconds(this.driver, PageHelper.XX_TIMEOUT_SEC);
					WebElement rowNuberElement = tableRowElements.get(rowNum);
					PageHelper.clickUsingJs(this.driver, rowNuberElement);
					break;
				}
			} catch (NumberFormatException e) {
				Assert.assertTrue(false,
						"Record value could be numeric or only 'ALL' not special character or any alphabet ");
			}
		}
	}

	// Method to check visible records count
	public int findVisibleRecords() throws InterruptedException {
		PageHelper.waitInSeconds(this.driver, PageHelper.X_TIMEOUT_SEC);
		WebElement valueF = this.driver.findElement(By.cssSelector(viewzoneFooter));
		String footerCountText = valueF.getText();
		logger.info("Footer record count message is- " + footerCountText);

		String preOfText = footerCountText.substring(0, footerCountText.indexOf("OF"));
		String firstCount = preOfText.replaceAll(",", "");	    
		int actualCount = Integer.parseInt(firstCount.trim());
		logger.info("Actual records visible in focused viewzone are- " + actualCount);
		return actualCount;
	}
		
	// Method to check records count
	public void findRecords(String recordsRights) throws InterruptedException {
		PageHelper.waitInSeconds(this.driver, PageHelper.X_TIMEOUT_SEC);
		WebElement valueF = this.driver.findElement(By.cssSelector(viewzoneFooter));
		String footerCountText = valueF.getText();
		logger.info("Footer record count message is- " + footerCountText);

		String preOfText = footerCountText.substring(0, footerCountText.indexOf("OF"));
		String firstCount = preOfText.replaceAll(",", "");	    
		int actualCount = Integer.parseInt(firstCount.trim());
		logger.info("Actual records visible in focused viewzone are- " + actualCount);
		
		String postOfText = footerCountText.substring(footerCountText.lastIndexOf("OF") + 2);
		String secondCount = postOfText.replaceAll(",", "");	    
		int totalCount = Integer.parseInt(secondCount.trim());
		logger.info("Total records in focused viewzone are- " + totalCount);

		if (recordsRights.equals("MAX")) {
			if (actualCount == totalCount) {
				logger.info("Loggedin user has max records priviledges");
			}
		} else if (recordsRights.equals("RESTRICTED")) {
			if (actualCount < totalCount) {
				int restrictedCount = totalCount - actualCount;
				logger.info(restrictedCount + " records are restricted for loggedin user");
			} else {
				Assert.assertTrue(false, "Records value could be only MAX or RESTRICTED");
			}
		}
		else if (recordsRights.equals("SELF")) {
			if (actualCount < totalCount) {
//				int restrictedCount = totalCount - actualCount;
				Assert.assertEquals(actualCount, 4);
				logger.info("Only self created records visible in viewzone");
			} else {
				Assert.assertTrue(false, "More than self created records visible in viewzone");
			}
		}
	}
}
