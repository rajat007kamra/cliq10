package actions.output.report.pojo;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OutcomeSummary {
	@SerializedName("outcome")
	@Expose
	private List<NonNestedOutcome> outcome = null;

	public List<NonNestedOutcome> getOutcome() {
		return outcome;
	}

	public void setOutcome(List<NonNestedOutcome> outcome) {
		this.outcome = outcome;
	}

}
