package actions.readflyoutrows;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.google.gson.Gson;

import actions.Actions;
import actions.readflyoutrows.model.ReadFlyoutRowsModel;
import io.qameta.allure.Step;
import selenium.driver.Driver;

/**
 * 
 * @author Arun.Kapoor
 *
 */

public class ReadFlyoutRows extends Driver implements Actions {
	private Instant startTime;
	private Instant endTime;
	private WebDriver driver;
	private ReadFlyoutRowsModel readModel;
	final static Logger logger = Logger.getLogger(ReadFlyoutRows.class);

	public ReadFlyoutRows(WebDriver driver, String jsonString) {
		super(driver);
		this.driver = driver;
		this.readModel = new Gson().fromJson(jsonString, ReadFlyoutRowsModel.class);
	}

	private String viewzoneTableRows = "div.DECKLAYER-PARENT[style*='z-index: 1;'] div.GRID-WGT div.GRID-WGT-TBLS table.TABLE-WGT tr.GRID-WGT-ROW";
	private String viewzoneCellValue = "div.DECKLAYER-PARENT[style*='z-index: 1;'] div.GRID-WGT[style*='z-index: 1;'] div.GRID-WGT-TBLS table.TABLE-WGT tr.GRID-WGT-ROW td[row='%1S'][col='0'] div";

	private String flyzoneTableRows = "div.DECKLAYER-PARENT[style*='z-index: 1;'] div.HOR-FLY-OUTER div.GRID-WGT-TBLS table.TABLE-WGT tr.GRID-WGT-ROW";
	private String flyzoneCellValue = "div.DECKLAYER-PARENT[style*='z-index: 1;'] div.HOR-FLY-OUTER div.GRID-WGT-TBLS table.TABLE-WGT tr.GRID-WGT-ROW td[row='%1S'][col='0'] div";

	@Override
	public boolean execute() {
		startTime = Instant.now();
		try {
			logger.info("Total rows has active records :- " + countRows(flyzoneTableRows, flyzoneCellValue));
			
//			String zoneName = this.readModel.getZoneName().trim().toUpperCase();
//			if (zoneName.equals("VIEW ZONE")) {
//				logger.info("Total rows has active records :- " + countRows(viewzoneTableRows, viewzoneCellValue));
//			} else if (zoneName.equals("FLY ZONE")) {
//				logger.info("Total rows has active records :- " + countRows(flyzoneTableRows, flyzoneCellValue));
//			} else {
//				logger.error(zoneName + " is invalid. It could be only VIEW ZONE/FLY ZONE");
//			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false, e.getMessage());
			return false;
		}
	}

	@Override
	public boolean validate() {
		try {
			Assert.assertEquals(String.valueOf(countRows(flyzoneTableRows, flyzoneCellValue)), this.readModel.getCount());
			
//			String zoneName = this.readModel.getZoneName().trim().toUpperCase();
//			if (zoneName.equals("VIEW ZONE")) {
//				Assert.assertEquals(String.valueOf(countRows(viewzoneTableRows, viewzoneCellValue)), this.readModel.getCount());
//			} else if (zoneName.equals("FLY ZONE")) {
//				Assert.assertEquals(String.valueOf(countRows(flyzoneTableRows, flyzoneCellValue)), this.readModel.getCount());
//			} else {
//				logger.error(zoneName + " is invalid. It could be only VIEW ZONE/FLY ZONE");
//			}			
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

	}

	@Step("Count rows has values")
	private int countRows(String headerXPath, String cellXPath) {
		int activeRow = 0;
		List<WebElement> rowsCount = this.driver.findElements(By.cssSelector(headerXPath));
		for (int i = 0; i < rowsCount.size(); i++) {
			WebElement elem = driver.findElement(By.cssSelector(String.format(cellXPath, i)));
			String cellText = elem.getText();
			if (!cellText.equals("---") && cellText!=null && !cellText.isEmpty()) {
				activeRow = activeRow + 1;
//				logger.info("Value found in cell [0," + i + "] :- " + cellText);
			}
		}
		return activeRow;
	}
}
