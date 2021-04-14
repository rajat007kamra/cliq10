package actions.reports.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReportsModel {
	@SerializedName("template")
	@Expose
	private String template = null;
	
	@SerializedName("selectTemplateRow")
	@Expose
	private String selectTemplateRow = null;
	
	@SerializedName("run")
	@Expose
	private String run = null;
	
	@SerializedName("parameters")
	@Expose
	private List<Parameters> parameters = null;

	@SerializedName("users")
    @Expose
    private List<Users> users = null;
	
	@SerializedName("emails")
    @Expose
    private List<String> email = null;
	
	@SerializedName("ftps")
    @Expose
    private List<String> ftp = null;
	
	@SerializedName("button")
	@Expose
	private String button = null;
	
	@SerializedName("reports")
	@Expose
	private List<ReportsFilter> reports = null;
	
	@SerializedName("selectReportRow")
	@Expose
	private String selectReportRow = null;
	
	@SerializedName("reportIcon")
	@Expose
	private String reportIcon = null;
	
	@SerializedName("informations")
	@Expose
	private List<Informations> informations = null;
	
	public String getTemplateName() {
		return template;
	}

	public void setTemplateName(String name) {
		this.template = name;
	}
	
	public String getSelectTemplateRow() {
		return selectTemplateRow;
	}

	public void setSelectTemplateRow(String selectRow) {
		this.selectTemplateRow = selectRow;
	}
	
	public String getRun() {
		return run;
	}

	public void setRun(String option) {
		this.run = option;
	}
	
	public List<Parameters> getParameters() {
		return parameters;
	}

	public void setParameters(List<Parameters> column) {
		this.parameters = column;
	}
	
	public List<Users> getUsers() {
		return users;
	}

	public void setUsers(List<Users> name) {
		this.users = name;
	}
	
	public List<String> getEmails() {
		return email;
	}

	public void setEmails(List<String> email) {
		this.email = email;
	}
	
	public List<String> getFTP() {
		return ftp;
	}

	public void setFTP(List<String> ftp) {
		this.ftp = ftp;
	}
	
	public String getButton() {
		return button;
	}

	public void setButton(String name) {
		this.button = name;
	}
	
	public List<ReportsFilter> getReports() {
		return reports;
	}

	public void setReports(List<ReportsFilter> column) {
		this.reports = column;
	}
	
	public String getSelectReportRow() {
		return selectReportRow;
	}

	public void setSelectReportRow(String selectRow) {
		this.selectReportRow = selectRow;
	}
	
	public String getReportIcon() {
		return reportIcon;
	}

	public void setReportIcon(String name) {
		this.reportIcon = name;
	}
	
	public List<Informations> getInformations() {
		return informations;
	}

	public void setInformations(List<Informations> column) {
		this.informations = column;
	}
}
