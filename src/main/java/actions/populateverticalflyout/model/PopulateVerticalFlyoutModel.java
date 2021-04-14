
package actions.populateverticalflyout.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PopulateVerticalFlyoutModel {
	@SerializedName("flexname")
	@Expose
	private String flexname;
	
	@SerializedName("column")
	@Expose
	private List<Column> column = null;

	public String getFlexName() {
		return flexname;
	}

	public void setFlexName(String flex_name) {
		this.flexname = flex_name;
	}
	
	public List<Column> getColumn() {
		return column;
	}

	public void setColumn(List<Column> column) {
		this.column = column;
	}
}
