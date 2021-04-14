package actions.attachments.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AttachmentsModel {	
	@SerializedName("access")
	@Expose
	private String access;
	
	@SerializedName("fileToVerify")
    @Expose
    private List<String> fileToVerify = null;
	
	@SerializedName("isDownload")
	@Expose
	private String isDownload;
	
	@SerializedName("fileToDownload")
    @Expose
    private List<String> fileToDownload = null;
	
	public String getAccess() {
		return access;
	}

	public void setAccess(String access) {
		this.access = access;
	}
	
	public List<String> getFileToVerify() {
        return fileToVerify;
    }

	public void setFileToVerify(List<String> name) {
		this.fileToVerify = name;		
	}
	
	public String getIsDownload() {
		return isDownload;
	}

	public void setIsDownload(String download) {
		this.isDownload = download;
	}
	
	public List<String> getfileToDownload() {
        return fileToDownload;
    }

	public void setfileToDownload(List<String> name) {
		this.fileToDownload = name;		
	}
}
