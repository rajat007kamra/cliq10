
package actions.editzoneslists.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Column {
	@SerializedName("listname")
	@Expose
	private String listname;
	
	@SerializedName("listvalue")
	@Expose
	private String listvalue;

	public String getListName() {
		return listname;
	}

	public void setListName(String title) {
		this.listname = title;
	}

	public String getListValue() {
		return listvalue;
	}

	public void setListValue(String text) {
		this.listvalue = text;
	}
}
