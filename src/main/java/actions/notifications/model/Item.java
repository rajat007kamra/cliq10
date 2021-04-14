
package actions.notifications.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item {

	@SerializedName("Process")
	@Expose
	private String process;
	@SerializedName("Description")
	@Expose
	private String description;
	@SerializedName("ActionType")
	@Expose
	private String actionType;

	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}

	public Item withProcess(String process) {
		this.process = process;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Item withDescription(String description) {
		this.description = description;
		return this;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public Item withActionType(String actionType) {
		this.actionType = actionType;
		return this;
	}

}
