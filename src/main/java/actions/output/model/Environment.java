
package actions.output.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Environment {

	@SerializedName("type")
	@Expose
	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Environment withType(String type) {
		this.type = type;
		return this;
	}

}
