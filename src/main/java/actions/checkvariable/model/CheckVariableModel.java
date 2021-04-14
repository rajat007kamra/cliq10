
package actions.checkvariable.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CheckVariableModel {
	@SerializedName("zone_name")
	@Expose
	private String zone_name = null;
	
	@SerializedName("selectRow")
	@Expose
	private String selectRow = null;
	
	@SerializedName("column")
	@Expose
	private List<Column> column = null;

	public String getZone() {
		return zone_name;
	}
	
	public void setZone(String name) {
		this.zone_name = name;
	}
	
	public String getSelectRow() {
		return selectRow;
	}

	public void setSelectRow(String selectRow) {
		this.selectRow = selectRow;
	}
	
	public List<Column> getColumn() {
		return column;
	}
	
	public void setColumn(List<Column> column) {
		this.column = column;
	}
}
