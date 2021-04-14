package actions.readflyoutrows.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReadFlyoutRowsModel {
	@SerializedName("zone")
    @Expose
    private String zone;
	
	@SerializedName("count")
    @Expose
    private String rowCount;
	
	public String getZoneName() {
        return zone;
    }

	public void setZoneName(String name) {
		this.zone = name;		
	}
	
	public String getCount() {
        return rowCount;
    }

	public void setCount(String count) {
		this.rowCount = count;		
	}
}
