
package actions.populatehorizontalsharing.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PopulateHorizontalSharingModel {

	@SerializedName("column")
	@Expose
	private List<Column> column = null;

	@SerializedName("section")
    @Expose
    private String section;
	
	public List<Column> getColumn() {
		return column;
	}

	public void setColumn(List<Column> column) {
		this.column = column;
	}
	
	public String getSection() {
        return section;
    }

    public void getSection(String sec) {
        this.section = sec;
    }
}
