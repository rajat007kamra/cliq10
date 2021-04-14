package actions.uploaddocument;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import actions.uploaddocument.model.ResultModel;


/**
 * 
 * @author SurendraShekhawat
 *
 */
public class WriteResults {
	private String excelSheet;
	private ResultModel resultModel;

	public WriteResults(String excelSheet, ResultModel resultModel) {
		this.excelSheet = excelSheet;
		this.resultModel = resultModel;
	}

	private FileInputStream getFile() throws FileNotFoundException {
		return new FileInputStream(new File(this.excelSheet));
	}

	private XSSFWorkbook getWorkBook(FileInputStream file) throws IOException {
		return new XSSFWorkbook(file);
	}

	private XSSFSheet getSheet(XSSFWorkbook workbook) throws IOException {
		return workbook.getSheetAt(0);
	}

	private void writeExcel(int rowNumber, XSSFSheet sheet) {
		Row row = sheet.createRow(rowNumber);
		Cell conditioncell = row.createCell(0);
		conditioncell.setCellValue(this.resultModel.getConditions());
		Cell screencell = row.createCell(1);
		screencell.setCellValue(this.resultModel.getScreen());
		Cell testScenariocell = row.createCell(2);
		testScenariocell.setCellValue(this.resultModel.getTestScenario());
		Cell filecell = row.createCell(3);
		filecell.setCellValue(this.resultModel.getFile());
		Cell functionalcell = row.createCell(4);
		functionalcell.setCellValue(this.resultModel.getFunctional());
		Cell performancecell = row.createCell(5);
		performancecell.setCellValue(this.resultModel.getPerformance());
		Cell overallcell = row.createCell(6);
		overallcell.setCellValue(this.resultModel.getOverall());
		Cell feedoutcomecell = row.createCell(7);
		feedoutcomecell.setCellValue(this.resultModel.getFeedOutcome());
		Cell sampleoutcomecell = row.createCell(8);
		sampleoutcomecell.setCellValue(this.resultModel.getSampleOutcome());
		Cell cleansingOutcomecell = row.createCell(9);
		cleansingOutcomecell.setCellValue(this.resultModel.getCleansingOutcome());
		Cell errorOutcomecell = row.createCell(10);
		errorOutcomecell.setCellValue(this.resultModel.getErrorOutcome());
		Cell recordOutcomecell = row.createCell(11);
		recordOutcomecell.setCellValue(this.resultModel.getRecordOutcome());
		Cell totalRowscell = row.createCell(12);
		totalRowscell.setCellValue(this.resultModel.getTotalRows());
		Cell loadedcell = row.createCell(13);
		loadedcell.setCellValue(this.resultModel.getLoaded());
		Cell errorcell = row.createCell(14);
		errorcell.setCellValue(this.resultModel.getError());
		Cell ignoredcell = row.createCell(15);
		ignoredcell.setCellValue(this.resultModel.getIgnored());
		Cell loadedPercentcell = row.createCell(16);
		loadedPercentcell.setCellValue(this.resultModel.getLoadedPrecent());
		Cell emailSentcell = row.createCell(17);
		emailSentcell.setCellValue(this.resultModel.getEmaiSent());
		Cell textMessageSentcell = row.createCell(18);
		textMessageSentcell.setCellValue(this.resultModel.getTextMessageSent());
		Cell scheduleTimecell = row.createCell(19);
		scheduleTimecell.setCellValue(this.resultModel.getScheduleTiming());
		Cell arrivedTimecell = row.createCell(20);
		arrivedTimecell.setCellValue(this.resultModel.getArrivedTiming());
		Cell submittedTimecell = row.createCell(21);
		submittedTimecell.setCellValue(this.resultModel.getSubmittedTiming());
		Cell completedTimecell = row.createCell(22);
		completedTimecell.setCellValue(this.resultModel.getCompletedTiming());
		Cell errorTypecell = row.createCell(23);
		errorTypecell.setCellValue(this.resultModel.getErrorType());
		Cell errorDescriptioncell = row.createCell(24);
		errorDescriptioncell.setCellValue(this.resultModel.getErrorDescription());
		Cell errorCountcell = row.createCell(25);
		errorCountcell.setCellValue(this.resultModel.getErrorCount());
		Cell loadingTimecell = row.createCell(26);
		loadingTimecell.setCellValue(this.resultModel.getLoadingTime());
	}

	private void closeFile(FileInputStream file, XSSFWorkbook workbook) throws IOException {
		file.close();
		FileOutputStream outFile = new FileOutputStream(new File(this.excelSheet));
		workbook.write(outFile);
		outFile.close();
	}

	public Integer getLastBlankRow(XSSFSheet sheed) {
		return sheed.getPhysicalNumberOfRows();
	}

	public void writeWorkbook() throws IOException {
		FileInputStream file = getFile();
		XSSFWorkbook workBook = getWorkBook(file);
		XSSFSheet sheet = getSheet(workBook);
		Integer lastBlankRow = getLastBlankRow(sheet);
		writeExcel(lastBlankRow, sheet);
		closeFile(file, workBook);
	}
	
	
	/*
	 * public static void main(String[] args) throws IOException { ResultModel
	 * resultModel = new ResultModel(); resultModel.setArrivedTiming("11.00.00 AM");
	 * resultModel.setCleansingOutcome("LOADED");
	 * resultModel.setCompletedTiming("12:00:00"); resultModel.setEmaiSent("SENT");
	 * resultModel.setConditions("A+B+C"); resultModel.setScreen("FEEDS"); String
	 * excelSheet = "C:/Users/SurendraShekhawat/Downloads/FileOutcomeTemplate.xlsx";
	 * WriteResults writeResults = new WriteResults(excelSheet, resultModel);
	 * writeResults.writeWorkbook(); System.out.println("Workbook updated"); }
	 */
}
