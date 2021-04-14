package actions.deleteattachments.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeleteAttachmentsModel {
	@SerializedName("fileToDelete")
    @Expose
    private List<String> fileToDelete = null;
	
	public List<String> getDeleteFileName() {
        return fileToDelete;
    }

	public void setDeleteFileName(List<String> name) {
		this.fileToDelete = name;		
	}
}
