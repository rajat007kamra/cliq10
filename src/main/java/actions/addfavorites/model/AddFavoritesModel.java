package actions.addfavorites.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddFavoritesModel {
	@SerializedName("name")
	@Expose
	private String name;
	
	public String getFavoriteName() {
		return name;
	}

	public void setFavoriteName(String addName) {
		this.name = addName;
	}
}
