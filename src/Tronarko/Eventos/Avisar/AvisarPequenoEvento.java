package Tronarko.Eventos.Avisar;

public class AvisarPequenoEvento {

	private String eNome = "";
	private int eHiperarko = 0;
	private int eSuperarko = 0;
	private int eTronarkoInicio = 0;

	private int eAntes = 0;
	private int eDepois = 0;

	private int eIntervalo = 0;
	private int mTronarko = 0;
	private int mEdicao = 0;

	public AvisarPequenoEvento(String aNome, int aHiperarko, int aSuperarko, int aTronarkoInicio, int aIntervalo,
			int aAntes, int aDepois) {
		eNome = aNome;
		eHiperarko = aHiperarko;
		eSuperarko = aSuperarko;
		eTronarkoInicio = aTronarkoInicio;
		eAntes = aAntes;
		eDepois = aDepois;

		eIntervalo = aIntervalo;

	}

	public int getInicio() {return eTronarkoInicio;}
	
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

	public int getTronarkoInicio() {
		return eTronarkoInicio;
	}

	public int getIntervalo() {
		return eIntervalo;
	}

	public int getAntes() {
		return eAntes;
	}

	public int getDepois() {
		return eDepois;
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

	public int getTronarkoProximoDe(int eProcurarTronarko) {


		int ProcurandoTronarko = this.getInicio();
		while (ProcurandoTronarko < eProcurarTronarko) {
			ProcurandoTronarko += this.getIntervalo();
		}

		return ProcurandoTronarko;
	}

	public int getEdicaoroximoDe(int eProcurarTronarko) {

		int Edicao = 1;

		int ProcurandoTronarko = this.getInicio();
		while (ProcurandoTronarko < eProcurarTronarko) {
			ProcurandoTronarko += this.getIntervalo();
			Edicao += 1;
		}

		return Edicao;
	}
	
	public String toString() {

		String st = "";

		if (eIntervalo == 1) {
			st = " Tronarko";
		}
		if (eIntervalo > 1) {
			st = " Tronarkos";
		}
		
		
		return "\t - " + this.getNome() + "  ->  [ " + this.getSuperarko() + "/" + this.getHiperarko() + " ] a cada "
				+ this.getIntervalo() + st + " desde " + this.getTronarkoInicio();

	}
}
