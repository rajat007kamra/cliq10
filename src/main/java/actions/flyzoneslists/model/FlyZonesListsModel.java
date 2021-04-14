package actions.flyzoneslists.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FlyZonesListsModel {
	@SerializedName("listtype")
	@Expose
	private String listtype;
	
	@SerializedName("column")
	@Expose
	private List<Column> column = null;

	public String getListType() {
		return listtype;
	}

	public void setListType(String var) {
		this.listtype = var;
	}
	
	public List<Column> getColumn() {
		return column;
	}

	public void setColumn(List<Column> column) {
		this.column = column;
	}
}
