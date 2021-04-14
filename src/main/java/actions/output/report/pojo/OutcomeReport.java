
package actions.output.report.pojo;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OutcomeReport {

    @SerializedName("profile")
    @Expose
    private List<Profile> profile = null;
    @SerializedName("summary")
    @Expose
    private List<Summary> summary = null;
    @SerializedName("quicklist")
    @Expose
    private List<Quicklist> quicklist = null;
    @SerializedName("workflow")
    @Expose
    private List<Workflow> workflow = null;
    @SerializedName("notes")
    @Expose
    private List<Note> notes = null;
    @SerializedName("attachment")
    @Expose
    private List<Attachment> attachment = null;
    
    @SerializedName("TestName")
    @Expose
    private String testName = null;
    
    @SerializedName("Test Description")
    @Expose
    private String testDescription = null;

    public List<Profile> getProfile() {
        return profile;
    }

    public void setProfile(List<Profile> profile) {
        this.profile = profile;
    }

    public List<Summary> getSummary() {
        return summary;
    }

    public void setSummary(List<Summary> summary) {
        this.summary = summary;
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

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    public List<Attachment> getAttachment() {
        return attachment;
    }

    public void setAttachment(List<Attachment> attachment) {
        this.attachment = attachment;
    }

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public String getTestDescription() {
		return testDescription;
	}

	public void setTestDescription(String testDescription) {
		this.testDescription = testDescription;
	}
}
