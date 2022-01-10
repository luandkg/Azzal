package Tronarko.Eventos;

import Tronarko.Intervalos.Tron_Intervalo;
import Tronarko.Tronarko.Tozte;
import Tronarko.Tronarko.Tron;

public class PequenoEvento {

	private String mNome;
	private Tozte mTozte;

	private Tron mTronInicio;
	private Tron mTronFim;

	public PequenoEvento(String eNome, Tozte eTozte) {

		this.mNome = eNome;
		this.mTozte = eTozte;

		mTronInicio = new Tron(1, 0, 0, mTozte.getSuperarko(), mTozte.getHiperarko(), mTozte.getTronarko());
		mTronFim = new Tron(10, 99, 99, mTozte.getSuperarko(), mTozte.getHiperarko(), mTozte.getTronarko());

	}

	public final String getNome() {
		return this.mNome;
	}

	public final Tozte getTozte() {
		return this.mTozte;
	}

	public final Tron getTronInicio() {
		return this.mTronInicio;
	}

	public final Tron getTronFim() {
		return this.mTronFim;
	}

	public Tron_Intervalo getTronIntervalo() {
		return new Tron_Intervalo(getNome(), getTronInicio(), getTronFim());
	}

	public long getSuperarkos() {
		return 1;
	}

	public long getArkos() {
		return getTronIntervalo().getArkosComFim();
	}

	public long Comecar(Tozte eTozte) {

		long ret = 0;

		long i = eTozte.getSuperarkosTotal();
		long e = this.getTozte().getSuperarkosTotal();

		if (i <= e) {
			ret = e - i;
		}

		return ret;
	}

	public long Terminar(Tozte eTozte) {

		long ret = 0;

		long i = eTozte.getSuperarkosTotal();
		long e = this.getTozte().getSuperarkosTotal();

		if (i >= e) {
			ret = i - e;
		}

		return ret;
	}

	public long Passou(Tozte eTozte) {

		long ret = 0;

		long i = eTozte.getSuperarkosTotal();
		long e = this.getTozte().getSuperarkosTotal();

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
		long e = this.getTozte().getSuperarkosTotal();

		long C = Comecar(eTozte);
		long T = Terminar(eTozte);

		if (C == 0 && T == 0) {

			ret = e - i;

		}

		return ret;
	}

	public boolean JaTerminou(Tozte eTozte) {

		long i = eTozte.getSuperarkosTotal();
		long e = this.getTozte().getSuperarkosTotal() + 1;

		boolean ret = false;

		if (i >= e) {
			ret = true;
		}

		return ret;

	}

	public boolean JaComecou(Tozte eTozte) {

		long i = eTozte.getSuperarkosTotal();
		long e = this.getTozte().getSuperarkosTotal();

		boolean ret = false;

		if (i >= e) {
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

		return getNome() + "  -> " + getTozte().toString();

	}

	public String getInfo() {

		String ret = "";

		ret += "\n" + " - Evento : " + this.getNome();
		ret += "\n" + " - Tozte : " + this.getTozte().toString();
		ret += "\n" + " - Superarkos : " + getStringSuperarkos(this.getSuperarkos());

		ret += "\n" + " - Inicio : " + this.getTronInicio().toString();
		ret += "\n" + " - Fim : " + this.getTronFim().toString();
		ret += "\n" + " - Intervalo : " + this.getTronIntervalo().toString();

		ret += "\n" + " - Arkos : " + getStringArkos(this.getArkos());

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

}
