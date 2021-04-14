package actions.navigatescreen.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import actions.filterrow.model.Column;

public class NavigateScreenModel {
	@SerializedName("process")
	@Expose
	private String process;
	@SerializedName("column")
	@Expose
	private List<Column> column = null;
	@SerializedName("realm")
	@Expose
	private String realm;
	@SerializedName("access")
	@Expose
	private String access;

	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}

	public List<Column> getColumn() {
		return column;
	}

	public void setColumn(List<Column> column) {
		this.column = column;
	}

	public String getRealm() {
		return realm;
	}

	public void setRealm(String realm) {
		this.realm = realm;
	}
	
	public String getAccess() {
		return access;
	}

	public void setAccess(String access) {
		this.access = access;
	}
}
