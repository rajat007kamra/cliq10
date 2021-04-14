package actions.reports.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Users {
	@SerializedName("name")
	@Expose
	private String name;
	@SerializedName("inAppIcon")
	@Expose
	private String in_app;
	@SerializedName("emailIcon")
	@Expose
	private String email;

	public String getUserName() {
		return name;
	}

	public void setUserName(String user) {
		this.name = user;
	}
	
	public String getInAppIcon() {
		return in_app;
	}

	public void setInAppIcon(String app) {
		this.in_app = app;
	}

	public String getEmailIcon() {
		return email;
	}

	public void setEmailIcon(String value) {
		this.email = value;
	}
}
