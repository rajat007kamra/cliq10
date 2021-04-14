package tests.ui;

import actions.addnew.AddNew;
import actions.checkmessage.CheckMessage;
import actions.login.Login;
import actions.logout.Logout;
import actions.navigatescreen.NavigateScreen;
import actions.populateform.PopulateForm;
import actions.save.Save;
import io.qameta.allure.Description;
import org.testng.annotations.Test;
import selenium.context.Base;
import selenium.driver.Driver;

public class UI_BUTTON_SAVE extends Base {
  @Description("CHECK SAVE BUTTON")
  @Test(groups = { "default"})
  public void ui_button_save() {
    Login loginO3Rxt = new Login(Driver.getDriver(), "{\"user\":\"BOTZ.001\"}");
    loginO3Rxt.setup();
    loginO3Rxt.execute();
    loginO3Rxt.validate();
    loginO3Rxt.tearDown();
    NavigateScreen navigatescreenBlTRU = new NavigateScreen(Driver.getDriver(), "{\"process\":\"REPORT TEMPLATES\",\"realm\":\"SETTINGS\"}");
    navigatescreenBlTRU.setup();
    navigatescreenBlTRU.execute();
    navigatescreenBlTRU.validate();
    navigatescreenBlTRU.tearDown();
    AddNew createrecordoVfXx = new AddNew(Driver.getDriver(), "{}");
    createrecordoVfXx.setup();
    createrecordoVfXx.execute();
    createrecordoVfXx.validate();
    createrecordoVfXx.tearDown();
    PopulateForm populateform2sibU = new PopulateForm(Driver.getDriver(), "{\"column\":[{\"variable\":\"TEMPLATE TYPE\",\"value\":\"EXCEL\"},{\"variable\":\"PROCESS\",\"value\":\"VENDOR\"},{\"variable\":\"TEMPLATE NAME\",\"value\":\"BOTZ.SAVE RECORD-HASHCODE\"},{\"variable\":\"BURST MODE\",\"value\":\"NO\"},{\"variable\":\"DELIVERY FORMAT\",\"value\":\"EXCEL\"}]}");
    populateform2sibU.setup();
    populateform2sibU.execute();
    populateform2sibU.validate();
    populateform2sibU.tearDown();
    Save saverecordDkA2O = new Save(Driver.getDriver(), "{}");
    saverecordDkA2O.setup();
    saverecordDkA2O.execute();
    saverecordDkA2O.validate();
    saverecordDkA2O.tearDown();
    CheckMessage checkmessageJOshk = new CheckMessage(Driver.getDriver(), "{\"action\":\"SAVE\",\"message\":\"SAVED!\"}");
    checkmessageJOshk.setup();
    checkmessageJOshk.execute();
    checkmessageJOshk.validate();
    checkmessageJOshk.tearDown();
    Logout logout7qbnV = new Logout(Driver.getDriver(), "{}");
    logout7qbnV.setup();
    logout7qbnV.execute();
    logout7qbnV.validate();
    logout7qbnV.tearDown();
  }
}
