package Tronarko.Satelites;

import Tronarko.Tronarko.Tozte;

public class Satelite {

	private String NOME;

	private Tozte mTozteInicial;
	private int mTaxa;
	private String mCor = "";

	public Satelite(String eNome, Tozte eTozteInicial, int eTaxa, String eCor) {

		this.NOME = eNome;

		this.mTaxa = eTaxa;
		this.mCor = eCor;

		mTozteInicial = eTozteInicial;

	}

	public int getCrescente() {
		return mTaxa;
	}

	public int getCheia() {
		return mTaxa;
	}

	public int getMinguante() {
		return mTaxa;
	}

	public int getNova() {
		return mTaxa;
	}

	public int getTaxa() {
		return mTaxa;
	}

	public String getCor() {
		return mCor;
	}

	public String getDef() {

		return NOME + " - { CRESCENTE = " + getCrescente() + " CHEIA = " + getCheia() + " MINGUANTE = " + getMinguante()
				+ " NOVA = " + getNova() + " } : " + getIntervalo() + " : " + mCor + " - Inicio : "
				+ mTozteInicial.toString();

	}

	public int getIntervalo() {
		return 4 * mTaxa;
	}

	public String getCiclo() {

		int F1 = mTaxa;
		int F2 = 2 * mTaxa;
		int F3 = 3 * mTaxa;
		int F4 = 4 * mTaxa;

		return NOME + " - { CRESCENTE = [ 0 - " + (F1 - 1) + " ] CHEIA = [ " + F1 + " - " + (F2 - 1) + " ]"
				+ " MINGUANTE = [ " + (F2) + " - " + (F3 - 1) + " ] NOVA = " + (F3) + " - " + (F4 - 1) + " ] } ";

	}

	public long getQuantidade(Tozte TozteC) {

		Tozte Inicio = new Tozte(mTozteInicial.getSuperarko(), mTozteInicial.getHiperarko(),
				mTozteInicial.getTronarko());

		long ret = 0;

		ret = TozteC.getSuperarkosTotal() - Inicio.getSuperarkosTotal();

		while (ret > getIntervalo()) {
			ret -= getIntervalo();
		}

		return ret;
	}

	public String getInfo(Tozte TozteC) {

		int F1 = mTaxa;
		int F2 = 2 * mTaxa;
		int F3 = 3 * mTaxa;
		int F4 = 4 * mTaxa;

		String ret = "";

		ret += "SATELITE : " + NOME + " \n\n";

		ret += "\t - Tozte : " + TozteC.getTexto() + "\n";
		ret += "\t - Fase Atual : " + getFase((int) getQuantidade(TozteC)) + "\n";
		ret += "\t - Quantidade : " + getQuantidade(TozteC) + "\n";
		ret += "\t - Proxima : " + getProximo(TozteC) + "\n";
		ret += "\t - Proxima Fase : " + getProximaFase(TozteC) + "\n\n";

		ret += "\t - Fase Nova : [1 a " + F1 + "]\n";

		ret += "\t - Fase Crescente : [" + (F1) + " a " + F2 + "]\n";

		ret += "\t - Fase Cheia : [" + F2 + " a " + F3 + "]\n";

		ret += "\t - Fase Minguante : [" + F3 + " a " + F4 + "]\n";

		return ret;

	}

	public int getProximoValor(Tozte TozteC) {
		int t = (int) getQuantidade(TozteC) + getProximo(TozteC) + 1;
		if (t >= getIntervalo()) {
			t -= getIntervalo();
		}
		return t;
	}

	public Fases getProximaFase(Tozte TozteC) {
		return getFase(getProximoValor(TozteC));
	}

	public int getProximo(Tozte TozteC) {
		int ret = 0;

		int aTozteQuantidade = (int) getQuantidade(TozteC);

		int F1 = mTaxa;
		int F2 = 2 * mTaxa;
		int F3 = 3 * mTaxa;
		int F4 = 4 * mTaxa;

		if (aTozteQuantidade >= 0 && aTozteQuantidade <= F1) {
			ret = F1 - aTozteQuantidade;
		}

		if (aTozteQuantidade > F1 && aTozteQuantidade <= F2) {
			ret = F2 - aTozteQuantidade;
		}

		if (aTozteQuantidade > F2 && aTozteQuantidade <= F3) {
			ret = F3 - aTozteQuantidade;
		}

		if (aTozteQuantidade > F3 && aTozteQuantidade < F4) {
			ret = F4 - aTozteQuantidade;
		}

		return ret;
	}

	public Fases getFase(Tozte TozteC) {
		return getFase((int) getQuantidade(TozteC));
	}

	public Fases getFase(int aTozteQuantidade) {
		Fases ret = Fases.NOVA;

		int F1 = mTaxa;
		int F2 = 2 * mTaxa;
		int F3 = 3 * mTaxa;
		int F4 = 4 * mTaxa;

		if (aTozteQuantidade >= 0 && aTozteQuantidade <= F1) {
			ret = Fases.NOVA;
		}

		if (aTozteQuantidade > F1 && aTozteQuantidade <= F2) {
			ret = Fases.CRESCENTE;
		}

		if (aTozteQuantidade > F2 && aTozteQuantidade <= F3) {
			ret = Fases.CHEIA;
		}

		if (aTozteQuantidade > F3 && aTozteQuantidade < F4) {
			ret = Fases.MINGUANTE;
		}

		return ret;
	}

	public void Mostar_Tudo(int eTronarko) {

		Tozte TozteC = new Tozte(1, 1, eTronarko);

		for (int i = 0; i < 500; i++) {

			System.out.println(TozteC.toString() + " :: " + getFase(TozteC).toString());

			TozteC = TozteC.adicionar_Superarko(1);
		}

	}

}
