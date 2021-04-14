package actions.preferences.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PreferencesModel {
	@SerializedName("preference")
	@Expose
	private String preference;
	
	public String getPreferenceOption() {
		return preference;
	}

	public void setPreferenceOption(String option) {
		this.preference = option;
	}
}
