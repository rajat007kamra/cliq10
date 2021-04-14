
package actions.output.report.pojo;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Workflow {

    @SerializedName("path")
    @Expose
    private String path;
    @SerializedName("flyout")
    @Expose
    private List<Flyout> flyout = null;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<Flyout> getFlyout() {
        return flyout;
    }

    public void setFlyout(List<Flyout> flyout) {
        this.flyout = flyout;
    }

}
