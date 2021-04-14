package actions.addapprover.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddApproverModel {
		@SerializedName("addapprover")
		@Expose
		private String addnew;

		public String getaddnew() {
			return addnew;
		}

		public void setaddnew(String message) {
			this.addnew = message;
		}

}
