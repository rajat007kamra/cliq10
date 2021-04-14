package actions.sortcolumns.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SortColumnModel {
	@SerializedName("column")
	@Expose
	private String column;

	@SerializedName("order")
	@Expose
	private String order;

	@SerializedName("selectRow")
	@Expose
	private String selectRow;

	public String getColumnName() {
		return column;
	}

	public void setColumnName(String colname) {
		this.column = colname;
	}

	public String getSortOrder() {
		return order;
	}

	public void setSortOrder(String order) {
		this.order = order;
	}

	public String getSelectRow() {
		return selectRow;
	}

	public void setSelectRow(String selectRow) {
		this.selectRow = selectRow;
	}
}
