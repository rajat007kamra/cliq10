package actions.notifications.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NotificationsTabModel {
	@SerializedName("notificationTabName")
	@Expose
	private String notificationTabName;

	public String getNotificationTabName() {
		return notificationTabName;
	}

	public void setNotificationTabName(String notification_tab) {
		this.notificationTabName = notification_tab;
	}
}
