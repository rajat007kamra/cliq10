
package actions.notifications.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NotificationModel {

	@SerializedName("Tab")
	@Expose
	private String tab;
	@SerializedName("ActionType")
	@Expose
	private String actionType;
	@SerializedName("Items")
	@Expose
	private List<Item> items = null;

	public String getTab() {
		return tab;
	}

	public void setTab(String tab) {
		this.tab = tab;
	}

	public NotificationModel withTab(String tab) {
		this.tab = tab;
		return this;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public NotificationModel withActionType(String actionType) {
		this.actionType = actionType;
		return this;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public NotificationModel withItems(List<Item> items) {
		this.items = items;
		return this;
	}

}
