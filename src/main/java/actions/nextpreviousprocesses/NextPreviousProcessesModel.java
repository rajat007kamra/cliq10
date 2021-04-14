package actions.nextpreviousprocesses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NextPreviousProcessesModel {
	@SerializedName("processesName")
	@Expose
	private String processesName;

	public String getProcessesName() {
		return processesName;
	}

	public void setProcessesName(String processes_name) {
		this.processesName = processes_name;
	}
}
