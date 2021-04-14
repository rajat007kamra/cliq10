package actions.reportsfilter;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.google.gson.Gson;

import actions.Actions;
import actions.PageHelper;
import actions.reportsfilter.model.Column;
import actions.reportsfilter.model.ReportFilterModel;
import selenium.driver.Driver;

public class ReportFilterRow extends Driver implements Actions {
	private Instant startTime;
	private Instant endTime;
	private WebDriver driver;
	private ReportFilterHelper filterHelper;
	private ReportFilterModel filterModel;

	final static Logger logger = Logger.getLogger(ReportFilterRow.class);

	public ReportFilterRow(WebDriver driver, String jsonString) {
		super(driver);
		this.driver = driver;
		this.filterHelper = new ReportFilterHelper(this.driver);
		this.filterModel = new Gson().fromJson(jsonString, ReportFilterModel.class);
	}

	public ReportFilterRow(WebDriver driver, List<Column> columns) {
		super(driver);
		this.driver = driver;
		this.filterHelper = new ReportFilterHelper(this.driver);
		this.filterModel = new ReportFilterModel();
		this.filterModel.setColumn(columns);
	}

	@Override
	public boolean execute() {
		try {
			TimeUnit.SECONDS.sleep(PageHelper.X_TIMEOUT_SEC);
		//	filterHelper.clickRefresh();
			startTime = Instant.now();
			List<Column> columns = this.filterModel.getColumn();
			for (Column column : columns) {
				logger.info("Set text " + PageHelper.appendHashCode(this.driver, column.getText()));
				filterHelper.setFilterText(PageHelper.appendHashCode(this.driver, column.getText()));
			}
		} catch (Exception e) {
			Assert.assertTrue(false, e.getMessage());
			return false;
		}
		return true;
	}

	@Override
	public boolean validate() {
		try
		{
			List<Column> columns = this.filterModel.getColumn();
			for (Column column : columns) {
				filterHelper.validateReports(PageHelper.appendHashCode(this.driver, column.getText()));
			}
			endTime = Instant.now();
			logger.info("[RESPTIME] " + Duration.between(startTime, endTime).toMillis());
			PageHelper.waitInSeconds(driver, PageHelper.XXX_TIMEOUT_SEC);
			return true;
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			Assert.assertTrue(false, e.getMessage());
			return false;
		}
	}

	@Override
	public void setup() {
//		totalRowCount = filterHelper.getTotalRowCount();
//		displayedRowCountBeforeFilter = filterHelper.getDisplayedRowCount();
	}

	@Override
	public void tearDown() {
		logger.info("To be configured later");
	}
}