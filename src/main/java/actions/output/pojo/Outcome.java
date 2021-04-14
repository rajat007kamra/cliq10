
package actions.output.pojo;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Outcome {

	@SerializedName("summary")
	@Expose
	private List<String> summary = null;
	@SerializedName("profile")
	@Expose
	private List<String> profile = null;
	@SerializedName("attachment")
	@Expose
	private List<String> attachment = null;
	@SerializedName("quicklist")
	@Expose
	private List<Quicklist> quicklist = null;
	@SerializedName("workflow")
	@Expose
	private List<Workflow> workflow = null;

	public List<String> getSummary() {
		return summary;
	}

	public void setSummary(List<String> summary) {
		this.summary = summary;
	}

	public List<String> getProfile() {
		return profile;
	}

	public void setProfile(List<String> profile) {
		this.profile = profile;
	}

	public List<String> getAttachment() {
		return attachment;
	}

	public void setAttachment(List<String> attachment) {
		this.attachment = attachment;
	}

	public List<Quicklist> getQuicklist() {
		return quicklist;
	}

	public void setQuicklist(List<Quicklist> quicklist) {
		this.quicklist = quicklist;
	}

	public List<Workflow> getWorkflow() {
		return workflow;
	}

	public void setWorkflow(List<Workflow> workflow) {
		this.workflow = workflow;
	}
}
