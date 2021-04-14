
package actions.readflyoutdata.model;

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

	public void setTitle(String title) {
		this.title = title;
	}

	public String getExpected() {
		return expected;
	}

	public void setExpected(String text) {
		this.expected = text;
	}
}
