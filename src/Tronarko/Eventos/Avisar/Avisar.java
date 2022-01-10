package Tronarko.Eventos.Avisar;

public class Avisar {

	private String eNome = "";
	private int eHiperarko = 0;
	private int eSuperarko = 0;
	private int mTronarko = 0;
	private int mEdicao = 0;

	public Avisar(String aNome, int aHiperarko, int aSuperarko, int aTronarko, int aEdicao) {
		eNome = aNome;
		eHiperarko = aHiperarko;
		eSuperarko = aSuperarko;
		mTronarko = aTronarko;
		mEdicao = aEdicao;
	}

	
	public int getOrdem() {
		return eSuperarko + ((eHiperarko - 1) * 50) + (mTronarko * 500);
	}

	public String getNome() {
		return eNome;
	}

	public int getHiperarko() {
		return eHiperarko;
	}

	public int getSuperarko() {
		return eSuperarko;
	}



	public int getTronarko() {
		return mTronarko;
	}

	public void setTronarko(int eTronarko) {
		mTronarko = eTronarko;
	}

	public int getEdicao() {
		return mEdicao;
	}

	public void setEdicao(int eEdicao) {
		mEdicao = eEdicao;
	}

	public String toString() {

		return "\t - " + this.getNome() + "  ->  [ " + this.getSuperarko() + "/" + this.getHiperarko() + "/" + this.getTronarko() + " ] - " + this.getEdicao();

	}
}
