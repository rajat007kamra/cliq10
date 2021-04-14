package actions.filldata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FillDataModel {
	@SerializedName("fieldName")
	@Expose
	private String fieldName;
	@SerializedName("fieldValue")
	@Expose
	private String fieldValue;

	public String getFieldName() {
		return fieldValue;
	}

	public void setFieldName(String name) {
		this.fieldName = name;
	}

	public String getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(String value) {
		this.fieldValue = value;
	}
}
