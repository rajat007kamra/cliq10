package actions.checkrecordmodel;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CheckRecordModel {
	@SerializedName("records")
	@Expose
	private String records = null;
	
	@SerializedName("checkrecord")
	@Expose
	private String checkrecord = null;
	
	@SerializedName("column")
	@Expose
	private List<Column> column = null;
	
	public String getRecords() {
		return records;
	}

	public void setRecords(String select) {
		this.records = select;
	}
	
	public String getCheck() {
		return checkrecord;
	}

	public void setCheck(String select) {
		this.checkrecord = select;
	}
	
	public List<Column> getColumn() {
		return column;
	}
	
	public void setColumn(List<Column> column) {
		this.column = column;
	}
}
