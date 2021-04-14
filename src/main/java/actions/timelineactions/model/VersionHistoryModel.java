package actions.timelineactions.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VersionHistoryModel {
	@SerializedName("displayHistory")
	@Expose
	private String displayHistory;
	
	public String getDisplayHistory() {
		return displayHistory;
	}

	public void setDisplayHistory(String display) {
		this.displayHistory = display;
	}
}
