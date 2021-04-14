
package actions.navigatescreen.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Column {

	@SerializedName("text")
	@Expose
	private String text;
	@SerializedName("title")
	@Expose
	private String title;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Column withText(String text) {
		this.text = text;
		return this;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Column withTitle(String title) {
		this.title = title;
		return this;
	}

}
