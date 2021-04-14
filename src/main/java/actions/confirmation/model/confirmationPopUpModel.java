package actions.confirmation.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class confirmationPopUpModel {
	@SerializedName("option")
	@Expose
	private String option;

	public String getConfirmation() {
		return option;
	}

	public void setConfirmation(String confirmation) {
		this.option = confirmation;
	}
}
