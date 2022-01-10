package Tronarko.Eventos.Avisar;

public class AvisarGrandeEvento {

	private String eNome = "";

	private int eHiperarkoInicio = 0;
	private int eSuperarkoInicio = 0;

	private int eHiperarkoFim = 0;
	private int eSuperarkoFim = 0;

	private int eIntervalo = 0;
	private int eInicio = 0;

	private int eAntes = 0;
	private int eDepois = 0;

	private int mTronarko = 0;
	private int mEdicao = 0;

	private String mTozteInicio;
	private String mTozteFim;

	public AvisarGrandeEvento(String aNome, int aHiperarkoInicio, int aSuperarkoInicio, int aHiperarkoFim,
			int aSuperarkoFim, int aIntervalo, int aInicio, int aAntes, int aDepois) {
		eNome = aNome;

		eHiperarkoInicio = aHiperarkoInicio;
		eSuperarkoInicio = aSuperarkoInicio;

		eHiperarkoFim = aHiperarkoFim;
		eSuperarkoFim = aSuperarkoFim;

		eIntervalo = aIntervalo;
		eInicio = aInicio;
		eAntes = aAntes;
		eDepois = aDepois;

		mTozteInicio = eSuperarkoInicio + "/" + eHiperarkoInicio;
		mTozteFim = eSuperarkoFim + "/" + eHiperarkoFim;

	}

	public String getNome() {
		return eNome;
	}

	public int getHiperarkoInicio() {
		return eHiperarkoInicio;
	}

	public int getSuperarkoInicio() {
		return eSuperarkoInicio;
	}

	public int getHiperarkoFim() {
		return eHiperarkoFim;
	}

	public int getSuperarkoFim() {
		return eSuperarkoFim;
	}

	public int getDuracao() {
		return (eSuperarkoFim - eSuperarkoInicio) + 1;
	}

	public int getAntes() {
		return eAntes;
	}

	public int getDepois() {
		return eDepois;
	}

	public int getIntervalo() {
		return eIntervalo;
	}

	public int getInicio() {
		return eInicio;
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

		int Edicao = 1;

		int ProcurandoTronarko = this.getInicio();
		while (ProcurandoTronarko < eProcurarTronarko) {
			ProcurandoTronarko += this.getIntervalo();
			Edicao += 1;
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

	public int getOrdem() {
		return getSuperarkoInicio() + ((getHiperarkoInicio() - 1) * 50) + (mTronarko * 500);
	}

	public String toString() {

		String st = "";

		if (eIntervalo == 1) {
			st = "Tronarko";
		}
		if (eIntervalo > 1) {
			st = "Tronarkos";
		}

		return getNome() + "  ->  [ " + mTozteInicio + " a " + mTozteFim + " ] a cada " + eIntervalo + " " + st
				+ " desde " + eInicio;

	}

}
