
package actions.output.pojo;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Workflow {

	@SerializedName("path")
	@Expose
	private String path;
	@SerializedName("flyout")
	@Expose
	private List<String> flyout = null;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public List<String> getFlyout() {
		return flyout;
	}

	public void setFlyout(List<String> flyout) {
		this.flyout = flyout;
	}

}
