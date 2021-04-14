package actions.record.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RecordModel {

	@SerializedName("RowNumber")
	@Expose
	private String rowNumber;
	@SerializedName("Option")
	@Expose
	private String option;

	public String getRowNumber() {
		return rowNumber;
	}

	public void setRowNumber(String rowNumber) {
		this.rowNumber = rowNumber;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}
}
