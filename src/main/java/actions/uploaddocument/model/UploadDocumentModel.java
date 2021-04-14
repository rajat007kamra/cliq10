package actions.uploaddocument.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UploadDocumentModel {
	@SerializedName("name")
	@Expose
	private String name;
	@SerializedName("value")
	@Expose
	private String value;
	@SerializedName("path")
	@Expose
	private String path;
	@SerializedName("access")
	@Expose
	private String access;
	
	public String getAccess() {
		return access;
	}

	public void setAccess(String access) {
		this.access = access;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
