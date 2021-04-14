
package actions.populatehorizontalsharing.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Column {
	@SerializedName("user")
	@Expose
	private String user;
	
	@SerializedName("rights")
	@Expose
	private String rights;

	public String getUser() {
		return user;
	}

	public void setUser(String name) {
		this.user = name;
	}

	public String getRights() {
		return rights;
	}

	public void setRights(String right) {
		this.rights = right;
	}
}
