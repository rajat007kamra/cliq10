package actions.favorites.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FavoritesModel {
	@SerializedName("action")
	@Expose
	private String action;
	
	@SerializedName("name")
	@Expose
	private String name;

	public String getActionName() {
		return action;
	}

	public void setActionName(String actionName) {
		this.action = actionName;
	}
	
	public String getFavoriteName() {
		return name;
	}

	public void setFavoriteName(String addName) {
		this.name = addName;
	}
}
