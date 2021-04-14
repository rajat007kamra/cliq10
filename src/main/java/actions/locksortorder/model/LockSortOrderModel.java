package actions.locksortorder.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LockSortOrderModel {
	@SerializedName("column")
    @Expose
    private List<String> column = null;
	
	@SerializedName("totalLocked")
    @Expose
    private String totalLocked;
	
	public List<String> getColumn() {
        return column;
    }

	public void setColumn(List<String> columns) {
		this.column = columns;		
	}
	
	public String getTotal() {
        return totalLocked;
    }

	public void setTotal(String locked) {
		this.totalLocked = locked;
	}
}
