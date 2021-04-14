
package actions.checkrecordmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Column {
	@SerializedName("title")
	@Expose
	private String title;
	
	@SerializedName("expected")
	@Expose
	private String expected;

	public String getTitle() {
		return title;
	}

	public void setTitle(String varname) {
		this.title = varname;
	}

	public String getText() {
		return expected;
	}

	public void setText(String text) {
		this.expected = text;
	}
}
