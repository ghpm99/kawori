package Gs;

public class Gs {

	private int ap, apAwak, dp, lvl;

	public Gs(String ap, String apAwak, String dp, String lvl) {

		this.setAp(stringToInt(ap));
		this.setApAwak(stringToInt(apAwak));
		this.setDp(stringToInt(dp));
		this.setLvl(stringToInt(lvl));

	}

	private int stringToInt(String a) {
		return Integer.parseInt(a.replaceAll("[^0-9]", ""));
	}

	public int getAp() {
		return ap;
	}

	public void setAp(int ap) {
		this.ap = ap;
	}

	public int getApAwak() {
		return apAwak;
	}

	public void setApAwak(int apAwak) {
		this.apAwak = apAwak;
	}

	public int getDp() {
		return dp;
	}

	public void setDp(int dp) {
		this.dp = dp;
	}

	public int getLvl() {
		return lvl;
	}

	public void setLvl(int lvl) {
		this.lvl = lvl;
	}

	public double getGs() {
		return ((ap + apAwak) / 2) + dp;
	}

	public boolean isValido() {
		boolean isValido = true;
		if (lvl > 66) {
			isValido = false;
		}
		if (ap < 0 || ap > 320) {
			isValido = false;
		}
		if (apAwak < 0 || apAwak > 320) {
			isValido = false;
		}
		if (dp < 0 || dp > 550) {
			isValido = false;
		}
		return isValido;
	}

	@Override
	public String toString() {

		return String.valueOf(ap) + ";" + String.valueOf(apAwak) + ";" + String.valueOf(dp) + ";" + String.valueOf(lvl);
	}

}
