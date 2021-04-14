package actions.reject.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RejectModel {
	@SerializedName("access")
	@Expose
	private String access;
	
	public String getAccess() {
		return access;
	}

	public void setAccess(String message) {
		this.access = message;
	}
}
