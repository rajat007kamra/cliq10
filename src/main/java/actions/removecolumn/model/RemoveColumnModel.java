package actions.removecolumn.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RemoveColumnModel {
	@SerializedName("column")
    @Expose
    private List<String> column = null;
	
	@SerializedName("confirmationMessage")
	@Expose
	private String confirmationMessage;
	
	public List<String> getColumn() {
        return column;
    }

	public void setColumn(List<String> columns) {
		this.column = columns;		
	}
	
	public String getVerifyMessage() {
		return confirmationMessage;
	}

	public void setVerifyMessage(String message) {
		this.confirmationMessage = message;
	}
}
