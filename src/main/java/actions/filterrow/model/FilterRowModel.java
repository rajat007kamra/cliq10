package actions.filterrow.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FilterRowModel {
	@SerializedName("templateState")
	@Expose
	private String templateState;
	@SerializedName("templateName")
	@Expose
	private String templateName;

	public String gettemplateState() {
		return templateState;
	}

	public void settemplateState(String state) {
		this.templateState = state;
	}

	public String gettemplateName() {
		return templateName;
	}

	public void templateName(String name) {
		this.templateName = name;
	}
}
