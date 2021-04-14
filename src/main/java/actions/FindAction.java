package actions;

import java.util.HashMap;
import java.util.Map;

import actions.accordion.Accordion;
import actions.addapprover.AddApprover;
import actions.addnew.AddNew;
import actions.approve.Approve;
import actions.attachments.Attachments;
import actions.browsernavigation.BrowserNavigation;
import actions.checkmessage.CheckMessage;
import actions.checkrecord.CheckRecord;
import actions.checkvariable.CheckVariable;
import actions.clarifier.Clarifier;
import actions.clearfilter.ClearFilter;
import actions.closeerror.CloseError;
import actions.closewidget.CloseWidget;
import actions.columnchooser.ColumnChooser;
import actions.confirmation.confirmationPopUp;
import actions.delete.Delete;
import actions.deleteattachments.DeleteAttachments;
import actions.directory.Directory;
import actions.discard.Discard;
import actions.editzoneslists.EditZonesLists;
import actions.equation.Equation;
import actions.exportviewzone.ExportViewZone;
import actions.favorites.Favorites;
import actions.filterrow.FilterRow;
import actions.flyzoneslists.FlyZonesLists;
import actions.forgotpassword.ForgotPassword;
import actions.formview.FormView;
import actions.globalsearch.GlobalSearch;
import actions.integratedview.IntegratedView;
import actions.login.Login;
import actions.logout.Logout;
import actions.navigatescreen.NavigateScreen;
import actions.openflyouts.OpenFlyouts;
import actions.opensharing.OpenSharing;
import actions.pastedata.PasteData;
import actions.populateform.PopulateForm;
import actions.populatehorizontalflyout.PopulateHorizontalFlyout;
import actions.populatehorizontalsharing.PopulateHorizontalSharing;
import actions.populateverticalflyout.PopulateVerticalFlyout;
import actions.populateverticalsharing.PopulateVerticalSharing;
import actions.preferences.Preferences;
import actions.readflyoutrows.ReadFlyoutRows;
import actions.record.Record;
import actions.refresh.Refresh;
import actions.reject.Reject;
import actions.reports.Reports;
import actions.reportsfilter.ReportFilterRow;
import actions.reset.Reset;
import actions.revise.Revise;
import actions.save.Save;
import actions.sortcolumns.SortColumn;
import actions.submit.Submit;
import actions.switchBrowsertab.SwitchBrowserTab;
import actions.timelineactions.AsOfVersions;
import actions.timelineactions.SelectAsOfDate;
import actions.timelineactions.TimeMachine;
import actions.timelineactions.VariableHistory;
import actions.timelineactions.VariableHistoryBySource;
import actions.timelineactions.VersionHistory;
import actions.treeview.TreeView;
import actions.uploaddocument.UploadDocument;
import actions.withdraw.Withdraw;

/**
 * 
 * @author Surendra.Shekhawat
 *
 */
public class FindAction {

	public Map<String, Class<?>> createActionMap() {
		Map<String, Class<?>> actionMap = new HashMap<String, Class<?>>();
		actionMap.put("LOGIN", Login.class);
		actionMap.put("LOGOUT", Logout.class);
		actionMap.put("NAVIGATE-SCREEN", NavigateScreen.class);
		actionMap.put("CHECK-VARIABLE", CheckVariable.class);
		actionMap.put("OPEN-FORM", FormView.class);
		actionMap.put("POPULATE-FORM", PopulateForm.class);
		actionMap.put("OPEN-FLYOUT", OpenFlyouts.class);
		actionMap.put("POPULATE-VERTICALFLYOUT", PopulateVerticalFlyout.class);
		actionMap.put("POPULATE-HORIZONTALFLYOUT", PopulateHorizontalFlyout.class);
		actionMap.put("CHECK-FLYOUTROWCOUNT", ReadFlyoutRows.class);
		actionMap.put("CLOSE-WIDGET", CloseWidget.class);
		actionMap.put("CREATE-RECORD", AddNew.class);
		actionMap.put("REVISE-RECORD", Revise.class);
		actionMap.put("SAVE-RECORD", Save.class);
		actionMap.put("SUBMIT-RECORD", Submit.class);
		actionMap.put("WITHDRAW-RECORD", Withdraw.class);
		actionMap.put("DELETE-RECORD", Delete.class);
		actionMap.put("DISCARD-RECORD", Discard.class);
		actionMap.put("APPROVE-RECORD", Approve.class);
		actionMap.put("DENY-RECORD", Reject.class);
		actionMap.put("CHECK-MESSAGE", CheckMessage.class);
		actionMap.put("DOWNLOAD-ATTACHMENTS", Attachments.class);
		actionMap.put("DELETE-ATTACHMENTS", DeleteAttachments.class);
		actionMap.put("UPLOAD-DOCUMENT", UploadDocument.class);
		actionMap.put("OPEN-INTEGRATEDVIEW", IntegratedView.class);
		actionMap.put("OPEN-TREEVIEW", TreeView.class);
		actionMap.put("REFRESH-SCREEN", Refresh.class);
		actionMap.put("RESET-SCREEN", Reset.class);
		actionMap.put("CHECK-CONFIRMATION", confirmationPopUp.class);
		actionMap.put("OPEN-SHARING", OpenSharing.class);
		actionMap.put("POPULATE-HORIZONTALSHARING", PopulateHorizontalSharing.class);
		actionMap.put("POPULATE-VERTICALSHARING", PopulateVerticalSharing.class);
		actionMap.put("PASTE-DATA", PasteData.class);
		actionMap.put("EXPORT-VIEWZONE", ExportViewZone.class);
		actionMap.put("PERFORM-SORT", SortColumn.class);
		actionMap.put("PERFORM-VZFILTER", FilterRow.class);
		actionMap.put("CLEAR-VZFILTER", ClearFilter.class);
		actionMap.put("ADD-FAVORITES", Favorites.class);
		actionMap.put("PERFORM-GLOBALSEARCH", GlobalSearch.class);
		actionMap.put("PERFORM-RIGHTCLICK", Record.class);
		actionMap.put("MODIFY-ACCORDION", Accordion.class);
		actionMap.put("OPEN-CLARIFIER", Clarifier.class);
		actionMap.put("OPEN-DIRECTORY", Directory.class);
		actionMap.put("OPEN-VARIABLEHISTORY", VariableHistory.class);
		actionMap.put("OPEN-VERSIONHISTORY", VersionHistory.class);
		actionMap.put("OPEN-SOURCEHISTORY", VariableHistoryBySource.class);
		actionMap.put("OPEN-ASOFVERSION", AsOfVersions.class);
		actionMap.put("OPEN-SELECTASOFDATE", SelectAsOfDate.class);
		actionMap.put("OPEN-EQUATION", Equation.class);
		actionMap.put("OPEN-PREFERENCES", Preferences.class);
		actionMap.put("OPEN-CHOOSER", ColumnChooser.class);
		actionMap.put("CLOSE-ERROR", CloseError.class);
		actionMap.put("CHECK-EDITZONELIST", EditZonesLists.class);
		actionMap.put("CHECK-FLYZONELIST", FlyZonesLists.class);
		actionMap.put("SWITCH-TAB", SwitchBrowserTab.class);
		actionMap.put("BROWSER-NAVIGATION", BrowserNavigation.class);
		actionMap.put("FORGOT-PASSWORD", ForgotPassword.class);
		actionMap.put("CHECK-RECORD", CheckRecord.class);
		actionMap.put("TIME-MACHINE", TimeMachine.class);
		actionMap.put("PERFORM-REPORTFILTER", ReportFilterRow.class);
		actionMap.put("ADD-APPROVER", AddApprover.class);
		actionMap.put("EXECUTE-REPORT", Reports.class);

		return actionMap;
	}
}
