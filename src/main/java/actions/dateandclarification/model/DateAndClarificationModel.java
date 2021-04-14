package actions.dateandclarification.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DateAndClarificationModel {
	@SerializedName("column")
    @Expose
    private List<String> column = null;
	
	public List<String> getColumn() {
        return column;
    }

	public void setColumn(List<String> columns) {
		this.column = columns;		
	}
}
