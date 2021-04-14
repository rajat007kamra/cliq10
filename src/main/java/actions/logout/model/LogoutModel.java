package actions.logout.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LogoutModel {
	@SerializedName("verifyLogoutText")
	@Expose
	private String verifyLogoutText;

	public String getLogoutText() {
		return verifyLogoutText;
	}

	public void setUser(String logoutText) {
		this.verifyLogoutText = logoutText;
	}
}
