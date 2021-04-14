package tests.ui;

import actions.favorites.Favorites;
import actions.login.Login;
import actions.logout.Logout;
import actions.navigatescreen.NavigateScreen;
import io.qameta.allure.Description;
import org.testng.annotations.Test;
import selenium.context.Base;
import selenium.driver.Driver;

public class UI_SCREEN_ADDFAVORITES extends Base {
  @Description("CHECK ADD FAVOURITES")
  @Test(groups = { "default"})
  public void ui_screen_addfavorites() {
    Login loginDD0hb = new Login(Driver.getDriver(), "{\"user\":\"BOTZ.001\"}");
    loginDD0hb.setup();
    loginDD0hb.execute();
    loginDD0hb.validate();
    loginDD0hb.tearDown();
    NavigateScreen navigatescreendgaSu = new NavigateScreen(Driver.getDriver(), "{\"process\":\"REPORT TEMPLATES\",\"realm\":\"SETTINGS\"}");
    navigatescreendgaSu.setup();
    navigatescreendgaSu.execute();
    navigatescreendgaSu.validate();
    navigatescreendgaSu.tearDown();
    Favorites addfavoritesVrw3D = new Favorites(Driver.getDriver(), "{\"name\":\"TEMPLATES FAV\",\"action\":\"ADD\"}");
    addfavoritesVrw3D.setup();
    addfavoritesVrw3D.execute();
    addfavoritesVrw3D.validate();
    addfavoritesVrw3D.tearDown();
    Logout logoutwI4iK = new Logout(Driver.getDriver(), "{}");
    logoutwI4iK.setup();
    logoutwI4iK.execute();
    logoutwI4iK.validate();
    logoutwI4iK.tearDown();
  }
}
