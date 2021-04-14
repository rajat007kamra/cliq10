
package actions.populateform.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Column {
	@SerializedName("variable")
	@Expose
	private String variable;
	
	@SerializedName("value")
	@Expose
	private String value;

	public String getVariable() {
		return variable;
	}

	public void setVariable(String title) {
		this.variable = title;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String text) {
		this.value = text;
	}
}
