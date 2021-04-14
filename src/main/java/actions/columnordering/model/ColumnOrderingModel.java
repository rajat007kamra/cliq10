package actions.columnordering.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ColumnOrderingModel {
	@SerializedName("column")
    @Expose
    private String column;
	
	@SerializedName("location")
    @Expose
    private String location;
	
//	@SerializedName("column")
//    @Expose
//    private List<String> column = null;
	
	public String getColumn() {
        return column;
    }

	public void setColumn(String columns) {
		this.column = columns;		
	}
	
	public String getLocation() {
        return location;
    }

	public void setLocation(String index) {
		this.location = index;
	}
	
//	public List<String> getColumn() {
//        return column;
//    }
//
//	public void setColumn(List<String> columns) {
//		this.column = columns;		
//	}
}
