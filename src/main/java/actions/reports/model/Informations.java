
package actions.reports.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Informations {
	@SerializedName("variable")
	@Expose
	private String variable;
	@SerializedName("expected")
	@Expose
	private String expected;

	public String getVariable() {
		return variable;
	}

	public void setVariable(String name) {
		this.variable = name;
	}

	public String getExpected() {
		return expected;
	}

	public void setExpected(String value) {
		this.expected = value;
	}
}
