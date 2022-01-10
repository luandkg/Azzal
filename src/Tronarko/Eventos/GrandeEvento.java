package Tronarko.Eventos;

import Tronarko.TozteCor;

import java.util.ArrayList;

import Tronarko.Intervalos.Tozte_Intervalo;
import Tronarko.Intervalos.Tron_Intervalo;
import Tronarko.Tronarko.Tozte;
import Tronarko.Tronarko.Tron;

public class GrandeEvento {

	private String mNome;
	private Tozte mInicio;
	private Tozte mFim;

	private int mIntervalo;
	private int mTronarkoInicio;

	private int mEdicao;

	private Tron mTronInicio;
	private Tron mTronFim;

	public GrandeEvento(String eNome, int eIntervalo, int eTronarkoInicio, Tozte eInicio, Tozte eFim, int eEdicao) {

		this.mNome = eNome;
		this.mInicio = eInicio;
		this.mFim = eFim;
		this.mIntervalo = eIntervalo;
		this.mTronarkoInicio = eTronarkoInicio;
		this.mEdicao = eEdicao;

		mTronInicio = new Tron(1, 0, 0, mInicio.getSuperarko(), mInicio.getHiperarko(), mInicio.getTronarko());
		mTronFim = new Tron(10, 99, 99, mFim.getSuperarko(), mFim.getHiperarko(), mFim.getTronarko());

	}

	public final String getNome() {
		return this.mNome;
	}

	public final Tozte getInicio() {
		return this.mInicio;
	}

	public final Tozte getFim() {
		return this.mFim;
	}

	public final Tron getTronInicio() {
		return this.mTronInicio;
	}

	public final Tron getTronFim() {
		return this.mTronFim;
	}

	public Tozte_Intervalo getTozteIntervalo() {
		return new Tozte_Intervalo(getNome(), getInicio(), getFim());
	}

	public Tron_Intervalo getTronIntervalo() {
		return new Tron_Intervalo(getNome(), getTronInicio(), getTronFim());
	}

	public long getSuperarkos() {
		return getTozteIntervalo().getSuperarkosComFim();
	}

	public long getArkos() {
		return getTronIntervalo().getArkosComFim();
	}

	public int getEdicao() {
		return mEdicao;
	}

	public long Comecar(Tozte eTozte) {

		long ret = 0;

		long i = eTozte.getSuperarkosTotal();
		long e = this.getInicio().getSuperarkosTotal();

		if (i <= e) {
			ret = e - i;
		}

		return ret;
	}

	public long Terminar(Tozte eTozte) {

		long ret = 0;

		long i = eTozte.getSuperarkosTotal();
		long e = this.getFim().getSuperarkosTotal();

		if (i >= e) {
			ret = i - e;
		}

		return ret;
	}

	public long Passou(Tozte eTozte) {

		long ret = 0;

		long i = eTozte.getSuperarkosTotal();
		long e = this.getInicio().getSuperarkosTotal();

		long C = Comecar(eTozte);
		long T = Terminar(eTozte);

		if (C == 0 && T == 0) {

			ret = i - e;

		}

		return ret;
	}

	public long Falta(Tozte eTozte) {

		long ret = 0;

		long i = eTozte.getSuperarkosTotal();
		long e = this.getFim().getSuperarkosTotal();

		long C = Comecar(eTozte);
		long T = Terminar(eTozte);

		if (C == 0 && T == 0) {

			ret = e - i;

		}

		return ret;
	}

	public boolean JaTerminou(Tozte eTozte) {

		long i = eTozte.getSuperarkosTotal();
		long e = this.getFim().getSuperarkosTotal();

		boolean ret = false;

		if (i > e) {
			ret = true;
		}

		return ret;

	}

	public boolean JaComecou(Tozte eTozte) {

		long i = eTozte.getSuperarkosTotal();
		long e = this.getInicio().getSuperarkosTotal();

		boolean ret = false;

		if (i > e) {
			ret = true;
		}

		return ret;

	}

	public boolean Dentro(Tozte eTozte) {

		boolean ret = false;

		long C = Comecar(eTozte);
		long T = Terminar(eTozte);

		if (C == 0 && T == 0) {

			ret = true;

		}

		return ret;
	}

	public boolean Fora(Tozte eTozte) {

		boolean ret = true;

		long C = Comecar(eTozte);
		long T = Terminar(eTozte);

		if (C == 0 && T == 0) {

			ret = false;

		}

		return ret;
	}

	public String toString() {

		return getNome() + "  ->  [ " + getInicio().toString() + " a " + getFim().toString() + " :: " + getSuperarkos()
				+ " Superarkos ] a cada " + mIntervalo + " desde " + mTronarkoInicio + " :: Edicao : " + getEdicao();

	}

	public String getInfo() {

		String ret = "";

		ret += "\n" + " - Evento : " + this.getNome();
		ret += "\n" + " - Inicio : " + this.getInicio().toString();
		ret += "\n" + " - Fim : " + this.getFim().toString();
		ret += "\n" + " - Intervalo : " + this.getTozteIntervalo().toString();
		ret += "\n" + " - Superarkos : " + getStringSuperarkos(this.getSuperarkos());

		ret += "\n" + " - Inicio : " + this.getTronInicio().toString();
		ret += "\n" + " - Fim : " + this.getTronFim().toString();
		ret += "\n" + " - Intervalo : " + this.getTronIntervalo().toString();

		ret += "\n" + " - Arkos : " + getStringArkos(this.getArkos());
		ret += "\n" + " - Edicao : " + this.getEdicao();

		return ret;
	}

	private String getStringSuperarkos(long Valor) {

		String ret = "";

		if (Valor == 0) {
			ret = " 0 Superarko";
		} else if (Valor == 1) {
			ret = " 1 Superarko";
		} else if (Valor == -1) {
			ret = " -1 Superarko";
		} else {

			ret = Valor + " Superarkos";

		}

		return ret;
	}

	private String getStringArkos(long Valor) {

		String ret = "";

		if (Valor == 0) {
			ret = " 0 Arko";
		} else if (Valor == 1) {
			ret = " 1 Arko";
		} else if (Valor == -1) {
			ret = " -1 Arko";
		} else {

			ret = Valor + " Arkos";

		}

		return ret;
	}

	public String getTozteInfo(Tozte TozteC) {
		String ret = "";

		ret += "\n" + " - Comecar : " + getStringSuperarkos(this.Comecar(TozteC));
		ret += "\n" + " - Terminou : " + getStringSuperarkos(this.Terminar(TozteC));
		ret += "\n" + " - Passou : " + getStringSuperarkos(this.Passou(TozteC));
		ret += "\n" + " - Falta : " + getStringSuperarkos(this.Falta(TozteC));
		ret += "\n" + " - Ja Comecou : " + this.JaComecou(TozteC);
		ret += "\n" + " - Ja Terminou : " + this.JaTerminou(TozteC);
		ret += "\n" + " - Dentro : " + this.Dentro(TozteC);
		ret += "\n" + " - Fora : " + this.Fora(TozteC);

		return ret;

	}

	
	public ArrayList<Tozte> getToztes(){
		ArrayList<Tozte> ret = new ArrayList<Tozte>();
		
		Tozte T1 = this.getInicio().getCopia();
		while (T1.MenorIgualQue(this.getFim())) {

			ret.add(T1.getCopia());

			T1 = T1.adicionar_Superarko(1);
		}
		
		return ret;
	}
}
