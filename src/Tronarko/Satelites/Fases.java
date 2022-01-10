package Tronarko.Satelites;

public enum Fases {

	CRESCENTE(0), CHEIA(1), MINGUANTE(2), NOVA(3);

	private int mValor;

	Fases(int eValor) {
		mValor = eValor;
	}

	public int getValor() {
		return mValor;
	}

	public String toString() {
		String ret = "";

		if (mValor == 0) {
			ret = "CRESCENTE";
		}
		if (mValor == 1) {
			ret = "CHEIA";

		}
		if (mValor == 2) {
			ret = "MINGUANTE";

		}
		if (mValor == 3) {
			ret = "NOVA";

		}
		return ret;
	}
}
