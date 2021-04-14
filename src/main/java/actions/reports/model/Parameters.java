
package actions.reports.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Parameters {
	@SerializedName("name")
	@Expose
	private String name;
	@SerializedName("value")
	@Expose
	private String value;

	public String getParamName() {
		return name;
	}

	public void setParamName(String title) {
		this.name = title;
	}

	public String getParamValue() {
		return value;
	}

	public void setParamValue(String value) {
		this.value = value;
	}
}
