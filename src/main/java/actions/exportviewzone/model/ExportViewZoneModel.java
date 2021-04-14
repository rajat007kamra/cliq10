package actions.exportviewzone.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ExportViewZoneModel {
	@SerializedName("expected")
	@Expose
	private String expected;
	@SerializedName("access")
	@Expose
	private String access;
	
	public String getVerifyMessage() {
		return expected;
	}

	public void setVerifyMessage(String message) {
		this.expected = message;
	}
	
	public String getAccess() {
		return access;
	}

	public void setAccess(String access) {
		this.access = access;
	}
}
