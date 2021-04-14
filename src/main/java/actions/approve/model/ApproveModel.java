package actions.approve.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApproveModel {
	@SerializedName("access")
	@Expose
	private String access;
	
	public String getAccess() {
		return access;
	}

	public void setAccess(String access) {
		this.access = access;
	}
}
