package Tronarko.Satelites;

import java.util.ArrayList;

import Tronarko.Tronarko;
import Tronarko.Tronarko.Tozte;
import Tronarko.Satelites.MapaCelestial.Allux;
import Tronarko.Satelites.MapaCelestial.Ettos;
import Tronarko.Satelites.MapaCelestial.Unnos;
import Tronarko.Intervalos.Tozte_Intervalo;

public class Satelites {

	private String Nome;

	private Fases mALLUX_FASE;
	private Fases mETTOS_FASE;
	private Fases mUNNOS_FASE;

	private Ettos mEttoC;
	private Unnos mUnnoC;
	private Allux mAlluxC;

	public Satelites(String eNome, Fases eAllux, Fases eEttos, Fases eUnnos) {

		Nome = eNome;
		mALLUX_FASE = eAllux;
		mETTOS_FASE = eEttos;
		mUNNOS_FASE = eUnnos;

		mEttoC = new Ettos();
		mUnnoC = new Unnos();
		mAlluxC = new Allux();

	}
	
	
	public Allux getAllux() {
		return mAlluxC;
	}

	public Unnos getUnnos() {
		return mUnnoC;
	}
	
	public Ettos getEttos() {
		return mEttoC;
	}
	
	public ArrayList<Tozte_Intervalo> Mostrar(Tozte TozteInicial, int eQuantidade) {

		ArrayList<Tozte_Intervalo> ret = new ArrayList<Tozte_Intervalo>();

		Tozte ProcurarEscuridao = new Tozte(TozteInicial.getSuperarko(), TozteInicial.getHiperarko(),
				TozteInicial.getTronarko());

		boolean primeira = true;
		long ST = 0;
		long Intervalor = 0;

		for (int e = 0; e < eQuantidade; e++) {

			Tozte E1 = Proxima(ProcurarEscuridao);
			int Temp = Quantidade(E1);

			if (primeira == false) {

				long diff = E1.getSuperarkosTotal() - ST;
				Intervalor = diff;

			}

			ST = E1.getSuperarkosTotal();

			if (primeira) {

				Tozte A1 = Anterior(TozteInicial);
				long Tempa = Quantidade(A1);

				Tozte A2 = A1.adicionar_Superarko((int) Tempa);

				long difa = TozteInicial.getSuperarkosTotal() - A2.getSuperarkosTotal();

				// System.out.println(
				// "\n " + Nome + " : " + E1.toString() + " a " + (E1.adicionar_Superarko(Temp -
				// 1).toString())
				// + " com : " + Temp + " Superarkos ::: " + difa);

				ret.add(new Tozte_Intervalo(Nome, E1, (E1.adicionar_Superarko(Temp - 1))));

			} else {

				// System.out.println(
				// "\n " + Nome + " : " + E1.toString() + " a " + (E1.adicionar_Superarko(Temp -
				// 1).toString())
				/// + " com : " + Temp + " Superarkos ::: " + Intervalor);

				ret.add(new Tozte_Intervalo(Nome, E1, (E1.adicionar_Superarko(Temp - 1))));

			}

			ProcurarEscuridao = E1.adicionar_Superarko(Temp);
			primeira = false;
		}

		return ret;

	}

	public int getQuantidade(int eTronarko) {
		int ret = 0;

		Tozte ProcurarEscuridao = new Tozte(1, 1, eTronarko);
		while (true) {

			Tozte E1 = Proxima(ProcurarEscuridao);
			int Temp = Quantidade(E1);

			if (E1.getTronarko() == eTronarko) {

				ret += Temp;

			} else {
				break;
			}

			ProcurarEscuridao = E1.adicionar_Superarko(Temp);

		}

		return ret;
	}

	public long getIntervalo() {

		Tronarko TronarkoC = new Tronarko();

		Tozte ProcurarEscuridao = TronarkoC.getTozte();
		boolean primeira = true;
		long ST = 0;
		long Intervalor = 0;

		for (int e = 0; e < 5; e++) {

			Tozte E1 = Proxima(ProcurarEscuridao);
			long Temp = Quantidade(E1);

			if (primeira == false) {

				long diff = E1.getSuperarkosTotal() - ST;
				Intervalor = diff;

			}

			ST = E1.getSuperarkosTotal();

			ProcurarEscuridao = E1.adicionar_Superarko((int) Temp);
			primeira = false;
		}

		return Intervalor;

	}

	public Tozte Proxima(Tozte TozteCorrente) {

		Tozte Corrente = new Tozte(TozteCorrente.getSuperarko(), TozteCorrente.getHiperarko(),
				TozteCorrente.getTronarko());

		Tozte retorno;

		while (true) {

			int Escurecendo = 0;
			if (mAlluxC.getFase(Corrente) == mALLUX_FASE) {
				Escurecendo += 1;
			}
			if (mEttoC.getFase(Corrente) == mETTOS_FASE) {
				Escurecendo += 1;
			}
			if (mUnnoC.getFase(Corrente) == mUNNOS_FASE) {
				Escurecendo += 1;
			}

			if (Escurecendo == 3) {
				retorno = Corrente;
				break;
			}

			Corrente = Corrente.adicionar_Superarko(1);

		}

		return retorno;
	}

	public int Quantidade(Tozte TozteCorrente) {

		int ret = 0;

		Tozte Corrente = new Tozte(TozteCorrente.getSuperarko(), TozteCorrente.getHiperarko(),
				TozteCorrente.getTronarko());

		while (true) {

			int Escurecendo = 0;
			if (mAlluxC.getFase(Corrente) == mALLUX_FASE) {
				Escurecendo += 1;
			}
			if (mEttoC.getFase(Corrente) == mETTOS_FASE) {
				Escurecendo += 1;
			}
			if (mUnnoC.getFase(Corrente) == mUNNOS_FASE) {
				Escurecendo += 1;
			}

			if (Escurecendo == 3) {
				ret += 1;
			} else {
				break;
			}

			Corrente = Corrente.adicionar_Superarko(1);

		}

		return ret;
	}

	public Tozte Anterior(Tozte TozteCorrente) {

		Tozte Corrente = new Tozte(TozteCorrente.getSuperarko(), TozteCorrente.getHiperarko(),
				TozteCorrente.getTronarko());

		while (true) {

			int Escurecendo = 0;
			if (mAlluxC.getFase(Corrente) == mALLUX_FASE) {
				Escurecendo += 1;
			}
			if (mEttoC.getFase(Corrente) == mETTOS_FASE) {
				Escurecendo += 1;
			}
			if (mUnnoC.getFase(Corrente) == mUNNOS_FASE) {
				Escurecendo += 1;
			}

			if (Escurecendo == 3) {
				break;
			}

			Corrente = Corrente.adicionar_Superarko(-1);

		}

		while (true) {

			int Escurecendo = 0;
			if (mAlluxC.getFase(Corrente) == mALLUX_FASE) {
				Escurecendo += 1;
			}
			if (mEttoC.getFase(Corrente) == mETTOS_FASE) {
				Escurecendo += 1;
			}
			if (mUnnoC.getFase(Corrente) == mUNNOS_FASE) {
				Escurecendo += 1;
			}

			if (Escurecendo < 3) {
				break;
			}

			Corrente = Corrente.adicionar_Superarko(-1);

		}

		Corrente = Corrente.adicionar_Superarko(+1);

		return Corrente;
	}

}
