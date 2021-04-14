package actions.login;

import core.db.UserDBUpdate;

/**
 * 
 * @author Surendra.Shekhawat
 *
 */
public class DbHelper {
	private static String dbUrl = "mongodb://172.17.66.222:27017";

	public static String getPassword(String userId) {
		UserDBUpdate userDBUpdate = new UserDBUpdate(dbUrl);
		return userDBUpdate.getPassword(userId);
	}

	public static void updatePassword(String userId, String newPassword) {
		UserDBUpdate userDBUpdate = new UserDBUpdate(dbUrl);
		userDBUpdate.updatePassword(userId, newPassword);
	}

	
	public static String getUserInfo(String userId) {
		UserDBUpdate userDBUpdate = new UserDBUpdate(dbUrl);
		return userDBUpdate.getUserDetails(userId);
	}

	public static String getSubTenant(String userId) {
		UserDBUpdate userDBUpdate = new UserDBUpdate(dbUrl);
		return userDBUpdate.getSubtenant(userId);
	}
	
	/*
	 * public static void main(String[] args) {
	 * System.out.println(getPassword("BOTZ.001")); updatePassword("BOTZ.001",
	 * "Judgement Day 02"); System.out.println(getPassword("BOTZ.001")); }
	 */

}
