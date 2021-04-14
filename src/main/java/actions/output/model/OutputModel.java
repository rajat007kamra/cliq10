
package actions.output.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OutputModel {

	@SerializedName("mappings")
	@Expose
	private Mappings mappings;

	public Mappings getMappings() {
		return mappings;
	}

	public void setMappings(Mappings mappings) {
		this.mappings = mappings;
	}

	public OutputModel withMappings(Mappings mappings) {
		this.mappings = mappings;
		return this;
	}

}
