
package actions.checkvariable.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Column {
	@SerializedName("variable")
	@Expose
	private String variable;
	
	@SerializedName("expected")
	@Expose
	private String expected;

	public String getTitle() {
		return variable;
	}

	public void setTitle(String varname) {
		this.variable = varname;
	}

	public String getText() {
		return expected;
	}

	public void setText(String text) {
		this.expected = text;
	}
}
