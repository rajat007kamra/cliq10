
package actions.output.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Mappings {

	@SerializedName("properties")
	@Expose
	private Properties properties;

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public Mappings withProperties(Properties properties) {
		this.properties = properties;
		return this;
	}

}
