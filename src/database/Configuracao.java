package database;

public class Configuracao {

	private static String prefix = null;

	public static String getToken() {
		return DataBaseManager.getConfig("SELECT value FROM kawori.config where type=\"token\"");
	}

	public static String getPrefix() {
		if (prefix == null)
			prefix = DataBaseManager.getConfig("SELECT value FROM kawori.config where type=\"prefix\"");

		return prefix;
	}

}
