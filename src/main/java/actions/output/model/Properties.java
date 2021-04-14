
package actions.output.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Properties {

	@SerializedName("score")
	@Expose
	private Score score;
	@SerializedName("issue")
	@Expose
	private Issue issue;
	@SerializedName("attribution")
	@Expose
	private Attribution attribution;
	@SerializedName("excon")
	@Expose
	private Excon excon;
	@SerializedName("node")
	@Expose
	private Node node;
	@SerializedName("environment")
	@Expose
	private Environment environment;
	@SerializedName("subtenant")
	@Expose
	private Subtenant subtenant;
	@SerializedName("sector")
	@Expose
	private Sector sector;
	@SerializedName("testcaseId")
	@Expose
	private TestcaseId testcaseId;
	@SerializedName("user")
	@Expose
	private User user;
	@SerializedName("realm")
	@Expose
	private Realm realm;
	@SerializedName("screen")
	@Expose
	private Screen screen;
	@SerializedName("row")
	@Expose
	private Row row;
	@SerializedName("widget")
	@Expose
	private Widget widget;
	@SerializedName("variable")
	@Expose
	private Variable variable;
	@SerializedName("problem")
	@Expose
	private Problem problem;
	@SerializedName("link")
	@Expose
	private Link link;
	@SerializedName("notes")
	@Expose
	private Notes notes;
	@SerializedName("loadtime")
	@Expose
	private Loadtime loadtime;
	@SerializedName("browser")
	@Expose
	private Browser browser;
	@SerializedName("testtype")
	@Expose
	private Testtype testtype;

	public Score getScore() {
		return score;
	}

	public void setScore(Score score) {
		this.score = score;
	}

	public Properties withScore(Score score) {
		this.score = score;
		return this;
	}

	public Issue getIssue() {
		return issue;
	}

	public void setIssue(Issue issue) {
		this.issue = issue;
	}

	public Properties withIssue(Issue issue) {
		this.issue = issue;
		return this;
	}

	public Attribution getAttribution() {
		return attribution;
	}

	public void setAttribution(Attribution attribution) {
		this.attribution = attribution;
	}

	public Properties withAttribution(Attribution attribution) {
		this.attribution = attribution;
		return this;
	}

	public Excon getExcon() {
		return excon;
	}

	public void setExcon(Excon excon) {
		this.excon = excon;
	}

	public Properties withExcon(Excon excon) {
		this.excon = excon;
		return this;
	}

	public Node getNode() {
		return node;
	}

	public void setNode(Node node) {
		this.node = node;
	}

	public Properties withNode(Node node) {
		this.node = node;
		return this;
	}

	public Environment getEnvironment() {
		return environment;
	}

	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}

	public Properties withEnvironment(Environment environment) {
		this.environment = environment;
		return this;
	}

	public Subtenant getSubtenant() {
		return subtenant;
	}

	public void setSubtenant(Subtenant subtenant) {
		this.subtenant = subtenant;
	}

	public Properties withSubtenant(Subtenant subtenant) {
		this.subtenant = subtenant;
		return this;
	}

	public Sector getSector() {
		return sector;
	}

	public void setSector(Sector sector) {
		this.sector = sector;
	}

	public Properties withSector(Sector sector) {
		this.sector = sector;
		return this;
	}

	public TestcaseId getTestcaseId() {
		return testcaseId;
	}

	public void setTestcaseId(TestcaseId testcaseId) {
		this.testcaseId = testcaseId;
	}

	public Properties withTestcaseId(TestcaseId testcaseId) {
		this.testcaseId = testcaseId;
		return this;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Properties withUser(User user) {
		this.user = user;
		return this;
	}

	public Realm getRealm() {
		return realm;
	}

	public void setRealm(Realm realm) {
		this.realm = realm;
	}

	public Properties withRealm(Realm realm) {
		this.realm = realm;
		return this;
	}

	public Screen getScreen() {
		return screen;
	}

	public void setScreen(Screen screen) {
		this.screen = screen;
	}

	public Properties withScreen(Screen screen) {
		this.screen = screen;
		return this;
	}

	public Row getRow() {
		return row;
	}

	public void setRow(Row row) {
		this.row = row;
	}

	public Properties withRow(Row row) {
		this.row = row;
		return this;
	}

	public Widget getWidget() {
		return widget;
	}

	public void setWidget(Widget widget) {
		this.widget = widget;
	}

	public Properties withWidget(Widget widget) {
		this.widget = widget;
		return this;
	}

	public Variable getVariable() {
		return variable;
	}

	public void setVariable(Variable variable) {
		this.variable = variable;
	}

	public Properties withVariable(Variable variable) {
		this.variable = variable;
		return this;
	}

	public Problem getProblem() {
		return problem;
	}

	public void setProblem(Problem problem) {
		this.problem = problem;
	}

	public Properties withProblem(Problem problem) {
		this.problem = problem;
		return this;
	}

	public Link getLink() {
		return link;
	}

	public void setLink(Link link) {
		this.link = link;
	}

	public Properties withLink(Link link) {
		this.link = link;
		return this;
	}

	public Notes getNotes() {
		return notes;
	}

	public void setNotes(Notes notes) {
		this.notes = notes;
	}

	public Properties withNotes(Notes notes) {
		this.notes = notes;
		return this;
	}

	public Loadtime getLoadtime() {
		return loadtime;
	}

	public void setLoadtime(Loadtime loadtime) {
		this.loadtime = loadtime;
	}

	public Properties withLoadtime(Loadtime loadtime) {
		this.loadtime = loadtime;
		return this;
	}

	public Browser getBrowser() {
		return browser;
	}

	public void setBrowser(Browser browser) {
		this.browser = browser;
	}

	public Properties withBrowser(Browser browser) {
		this.browser = browser;
		return this;
	}

	public Testtype getTesttype() {
		return testtype;
	}

	public void setTesttype(Testtype testtype) {
		this.testtype = testtype;
	}

	public Properties withTesttype(Testtype testtype) {
		this.testtype = testtype;
		return this;
	}

}
