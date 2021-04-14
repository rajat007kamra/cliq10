package actions.pastedata.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PasteDataModel {
	@SerializedName("access")
	@Expose
	private String access;
	@SerializedName("data")
	@Expose
	private String data;

	public String getAccess() {
		return access;
	}

	public void setAccess(String access) {
		this.access = access;
	}
	
	public String getData() {
		return data;
	}

	public void setData(String text) {
		this.data = text;
	}
}
