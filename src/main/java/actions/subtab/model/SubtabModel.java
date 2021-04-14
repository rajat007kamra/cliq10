package actions.subtab.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubtabModel {
	@SerializedName("title")
	@Expose
	private String title;

	public String getSubtabTitle() {
		return title;
	}

	public void getSubtabTitle(String name) {
		this.title = name;
	}
}
