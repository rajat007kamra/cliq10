package actions.uploaddocument.model;

public class ResultModel {

	private String conditions;
	private String screen;
	private String testScenario;
	private String file;

	/**
	 * Status
	 */

	private String functional;
	private String performance;
	private String overall;

	/**
	 * State
	 */

	private String feedOutcome;
	private String sampleOutcome;
	private String cleansingOutcome;
	private String errorOutcome;
	private String recordOutcome;

	/**
	 * Metrics
	 */

	private String totalRows;
	private String loaded;
	private String error;
	private String ignored;
	private String loadedPrecent;

	/**
	 * Alerts
	 */

	private String emaiSent;
	private String textMessageSent;

	/**
	 * Timings
	 */

	private String scheduleTiming;
	private String arrivedTiming;
	private String submittedTiming;
	private String completedTiming;

	/**
	 * Errors
	 */

	private String errorType;
	private String errorDescription;
	private String errorCount;

	/**
	 * Performance results
	 */

	private String loadingTime;

	public String getConditions() {
		return conditions;
	}

	public void setConditions(String conditions) {
		this.conditions = conditions;
	}

	public String getScreen() {
		return screen;
	}

	public void setScreen(String screen) {
		this.screen = screen;
	}

	public String getTestScenario() {
		return testScenario;
	}

	public void setTestScenario(String testScenario) {
		this.testScenario = testScenario;
	}

	public String getFile() {
		return file;
	}

	public void flyout_(String file) {
		this.file = file;
	}

	public String getFunctional() {
		return functional;
	}

	public void setFunctional(String functional) {
		this.functional = functional;
	}

	public String getPerformance() {
		return performance;
	}

	public void setPerformance(String performance) {
		this.performance = performance;
	}

	public String getOverall() {
		return overall;
	}

	public void setOverall(String overall) {
		this.overall = overall;
	}

	public String getFeedOutcome() {
		return feedOutcome;
	}

	public void setFeedOutcome(String feedOutcome) {
		this.feedOutcome = feedOutcome;
	}

	public String getSampleOutcome() {
		return sampleOutcome;
	}

	public void setSampleOutcome(String sampleOutcome) {
		this.sampleOutcome = sampleOutcome;
	}

	public String getCleansingOutcome() {
		return cleansingOutcome;
	}

	public void setCleansingOutcome(String cleansingOutcome) {
		this.cleansingOutcome = cleansingOutcome;
	}

	public String getErrorOutcome() {
		return errorOutcome;
	}

	public void setErrorOutcome(String errorOutcome) {
		this.errorOutcome = errorOutcome;
	}

	public String getRecordOutcome() {
		return recordOutcome;
	}

	public void setRecordOutcome(String recordOutcome) {
		this.recordOutcome = recordOutcome;
	}

	public String getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(String totalRows) {
		this.totalRows = totalRows;
	}

	public String getLoaded() {
		return loaded;
	}

	public void setLoaded(String loaded) {
		this.loaded = loaded;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getIgnored() {
		return ignored;
	}

	public void setIgnored(String ignored) {
		this.ignored = ignored;
	}

	public String getLoadedPrecent() {
		return loadedPrecent;
	}

	public void setLoadedPrecent(String loadedPrecent) {
		this.loadedPrecent = loadedPrecent;
	}

	public String getEmaiSent() {
		return emaiSent;
	}

	public void setEmaiSent(String emaiSent) {
		this.emaiSent = emaiSent;
	}

	public String getScheduleTiming() {
		return scheduleTiming;
	}

	public void setScheduleTiming(String scheduleTiming) {
		this.scheduleTiming = scheduleTiming;
	}

	public String getArrivedTiming() {
		return arrivedTiming;
	}

	public void setArrivedTiming(String arrivedTiming) {
		this.arrivedTiming = arrivedTiming;
	}

	public String getSubmittedTiming() {
		return submittedTiming;
	}

	public void setSubmittedTiming(String submittedTiming) {
		this.submittedTiming = submittedTiming;
	}

	public String getCompletedTiming() {
		return completedTiming;
	}

	public void setCompletedTiming(String completedTiming) {
		this.completedTiming = completedTiming;
	}

	public String getErrorType() {
		return errorType;
	}

	public void setErrorType(String errorType) {
		this.errorType = errorType;
	}

	public String getErrorDescription() {
		return errorDescription;
	}

	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}

	public String getErrorCount() {
		return errorCount;
	}

	public void setErrorCount(String errorCount) {
		this.errorCount = errorCount;
	}

	public String getLoadingTime() {
		return loadingTime;
	}

	public void setLoadingTime(String loadingTime) {
		this.loadingTime = loadingTime;
	}

	public String getTextMessageSent() {
		return textMessageSent;
	}

	public void setTextMessageSent(String textMessageSent) {
		this.textMessageSent = textMessageSent;
	}

}
