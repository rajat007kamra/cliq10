package actions.opensharing.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OpenSharingModel {
	@SerializedName("realm")
	@Expose
	private String realm;
	@SerializedName("flexname")
	@Expose
	private String flexname;
	@SerializedName("flyoutname")
	@Expose
	private String flyoutname;

	public String getModuleName() {
		return realm;
	}

	public void setModuleName(String module_name) {
		this.realm = module_name;
	}

	public String getFlexName() {
		return flexname;
	}

	public void setFlexName(String flex_name) {
		this.flexname = flex_name;
	}

	public String getFlyoutName() {
		return flyoutname;
	}

	public void setFlyoutName(String flyout_name) {
		this.flyoutname = flyout_name;
	}
}
