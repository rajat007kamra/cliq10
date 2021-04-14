package actions.checkvariable;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.google.gson.Gson;

import actions.Actions;
import actions.PageHelper;
import actions.checkvariable.model.CheckVariableModel;
import actions.checkvariable.model.Column;
import selenium.driver.Driver;

/**
 * 
 * @author Arun.Kapoor
 *
 */

public class CheckVariable extends Driver implements Actions {
	private Instant startTime;
	private Instant endTime;
	private WebDriver driver;
	private CheckVariableHelper checkVariableHelper;
	private CheckVariableModel checkVariableModel;

	final static Logger logger = Logger.getLogger(CheckVariable.class);

	public CheckVariable(WebDriver driver, String jsonString) {
		super(driver);
		this.driver = driver;
		this.checkVariableHelper = new CheckVariableHelper(this.driver);
		this.checkVariableModel = new Gson().fromJson(jsonString, CheckVariableModel.class);
	}

	public CheckVariable(WebDriver driver, List<Column> columns) {
		super(driver);
		this.driver = driver;
		this.checkVariableHelper = new CheckVariableHelper(this.driver);
		this.checkVariableModel = new CheckVariableModel();
		this.checkVariableModel.setColumn(columns);
	}

	private String formVariablesLabelXPath = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.EZ-WGT-FLD-DSPLY-FLYOUT";
	private String viewColumnsHeaderXPath = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.GRID-WGT[style*='z-index: 1'] table.GRID-WGT-HDRS div.GRID-WGT-HDR-DSPLY";
	private String flyColumnsHeaderXPath = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.FLY table.GRID-WGT-HDRS div.GRID-WGT-HDR-DSPLY";

	private String formVariablesValueXPath = "div.DECKLAYER-PARENT[style*='z-index: 1'] div.EZ-WGT-ENTRY-FLD-WRP-FLYOUT div.ENTRYFLD-EZ-WGT";
	private String viewColumnsValueXPath = "div.DECKLAYER-PARENT[style*='z-index: 1;'] div.GRID-WGT[style*='z-index: 1'] div.GRID-WGT-TBLS tr.GRID-WGT-ROW-ACTIVE td.GCO";
	private String flyColumnsValueXPath = "div.DECKLAYER-PARENT[style*='z-index: 1;'] div.FLY div.GRID-WGT-TBLS tr.GRID-WGT-ROW-ACTIVE td.GCO";

	@Override
	public boolean execute() {
		try {
			startTime = Instant.now();
			List<Column> columns = this.checkVariableModel.getColumn();
			for (Column column : columns) {
				String appendText = PageHelper.appendHashCode(this.driver, column.getText().trim().toUpperCase());
				if (this.checkVariableModel.getZone().trim().toUpperCase().equals("EDIT ZONE")) {
					checkVariableHelper.getValues(column.getTitle(), formVariablesLabelXPath, formVariablesValueXPath, appendText, "EDIT ZONE");
				} 
				else if (this.checkVariableModel.getZone().trim().toUpperCase().equals("VIEW ZONE")) {
					if (this.checkVariableModel.getSelectRow() != null && !this.checkVariableModel.getSelectRow().isEmpty()) {
						this.checkVariableHelper.selectRow(this.checkVariableModel.getSelectRow());
					}
					Thread.sleep(5000);
					checkVariableHelper.getValues(column.getTitle(), viewColumnsHeaderXPath, viewColumnsValueXPath, appendText, "VIEW ZONE");
				}
				else if (this.checkVariableModel.getZone().trim().toUpperCase().equals("FLY ZONE")) 
				{
					if (this.checkVariableModel.getSelectRow() != null && !this.checkVariableModel.getSelectRow().isEmpty()) {
						this.checkVariableHelper.selectRow(this.checkVariableModel.getSelectRow());
					}
					Thread.sleep(5000);
					checkVariableHelper.getValues(column.getTitle(), flyColumnsHeaderXPath, flyColumnsValueXPath, appendText, "FLY ZONE");
				}
			}
		} catch (Exception e) {
			Assert.assertTrue(false, e.getMessage());
			return false;
		}
		return true;
	}

	@Override
	public boolean validate() {
		try {
			List<Column> columns = this.checkVariableModel.getColumn();
			for (Column column : columns) {
				String appendText = PageHelper.appendHashCode(this.driver, column.getText().trim().toUpperCase());
				if (this.checkVariableModel.getZone().trim().toUpperCase().equals("EDIT ZONE")) {
					checkVariableHelper.getValueAndVerify(column.getTitle(), formVariablesLabelXPath,
							formVariablesValueXPath, appendText, "EDIT ZONE");
				} else if (this.checkVariableModel.getZone().trim().toUpperCase().equals("VIEW ZONE")) {
					checkVariableHelper.getValueAndVerify(column.getTitle(), viewColumnsHeaderXPath,
							viewColumnsValueXPath, appendText, "VIEW ZONE");
				} else if (this.checkVariableModel.getZone().trim().toUpperCase().equals("FLY ZONE")) {
					checkVariableHelper.getValueAndVerify(column.getTitle(), flyColumnsHeaderXPath,
							flyColumnsValueXPath, appendText, "FLY ZONE");
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

	}
}