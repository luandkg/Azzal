package Tronarko;

public class Ocorrencia {

	private String mNome;

	private int mSuperarko;
	private int mHiperarko;
	private int mTronarkoInicio;

	private int mQuantidade;
	private int mIntervaloTronarko;

	public Ocorrencia(String eNome, int eSuperarko, int eHiperarko, int eTronarkoInicio, int eQuantidade,
			int eIntervaloTronarko) {

		this.mNome = eNome;

		this.mSuperarko = eSuperarko;
		this.mHiperarko = eHiperarko;
		this.mTronarkoInicio = eTronarkoInicio;

		this.mQuantidade = eQuantidade;
		this.mIntervaloTronarko = eIntervaloTronarko;

	}

	public String getNome() {
		return mNome;
	}

	public int getSuperarko() {
		return mSuperarko;
	}

	public int getSuperarkoFim() {
		return (this.getSuperarko() + this.getQuantidade() - 1);
	}

	public int getHiperarko() {
		return mHiperarko;
	}

	public int getTronarkoInicio() {
		return mTronarkoInicio;
	}

	public int getQuantidade() {
		return mQuantidade;
	}

	public int getIntervaloTronarko() {
		return mIntervaloTronarko;
	}

	public Modos getPeriodo() {

		Modos ret = null;

		if (getIntervaloTronarko() == 1) {
			return Modos.PERIODICO;
		}

		if (getIntervaloTronarko() > 1) {
			return Modos.CICLICO;
		}

		return ret;
	}

	public String toString() {

		return "\t - " + getPeriodo().toString() + " - " + this.getNome() + "  ->  [ " + this.getSuperarko() + "/"
				+ this.getHiperarko() + " a " + (this.getSuperarko() + this.getQuantidade() - 1) + "/"
				+ this.getHiperarko() + " :: " + this.getQuantidade() + " ] desde " + this.getTronarkoInicio()
				+ " com Intervalo de " + this.getIntervaloString();

		// return " Ocorrencia O1 = new Ocorrencia(" + "\"" + this.getNome() + "\"," +
		// this.getSuperarko() + "," + this.getHiperarko() +
		//
		// "," + this.getTronarkoInicio() + "," + this.getQuantidade() + "," +
		// this.getIntervaloTronarko() +

		// ");";

	}

	public String getIntervaloString() {

		String ret = "";

		if (getIntervaloTronarko() == 1) {
			ret = this.getIntervaloTronarko() + " Tronarko";
		}

		if (getIntervaloTronarko() > 1) {

			ret = this.getIntervaloTronarko() + " Tronarkos";

		}
		return ret;

	}

	public static enum Modos {

		PERIODICO(0), CICLICO(1);

		private int mValor;

		Modos(int eValor) {
			mValor = eValor;
		}

		public int getValor() {
			return mValor;
		}

		public String toString() {
			String ret = "";

			if (mValor == 0) {
				ret = "PERIODICO";
			}
			if (mValor == 1) {
				ret = "CICLICO";
			}

			return ret;
		}
	}
}
