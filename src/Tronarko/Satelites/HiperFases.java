package Tronarko.Satelites;

import Tronarko.Tronarko.Tozte;
import Tronarko.Satelites.MapaCelestial.Allux;
import Tronarko.Satelites.MapaCelestial.Ettos;
import Tronarko.Satelites.MapaCelestial.Unnos;

public enum HiperFases {

	COMUM(0), ALLIZZ(1), ETTIZZ(2), UNNIZZ(3), ALLETT(4), UNNALL(5), ETTUN(6), ESCURIDAO(7), ILUMINACAO(8);

	private int mValor;

	HiperFases(int eValor) {
		mValor = eValor;
	}

	public int getValor() {
		return mValor;
	}

	public String toString() {
		String ret = "";

		if (mValor == 0) {
			ret = "COMUM";
		}
		if (mValor == 1) {
			ret = "ALLIZZ";

		}
		if (mValor == 2) {
			ret = "ETTIZZ";

		}
		if (mValor == 3) {
			ret = "UNNIZZ";
		}

		if (mValor == 4) {
			ret = "ALLETT";
		}

		if (mValor == 5) {
			ret = "UNNALL";
		}

		if (mValor == 6) {
			ret = "ETTUN";
		}

		if (mValor == 7) {
			ret = "ESCURIDAO";
		}

		if (mValor == 8) {
			ret = "ILUMINACAO";
		}
		return ret;
	}

	public static String Satelites(int eTronarko) {

		String ret = "";

		Allux AlluxC = new Allux();
		Ettos EttosC = new Ettos();
		Unnos UnnosC = new Unnos();

		Tozte TozteSatelite = new Tozte(1, 1, eTronarko);

		Agrupador AgrupadorC = new Agrupador();

		for (int g = 0; g < 500; g++) {

			ret += ("\n\t -    " + TozteSatelite.toString() + " :: { A : " + AlluxC.getFase(TozteSatelite).getValor()
					+ " :: E : " + EttosC.getFase(TozteSatelite).getValor() + " :: U : "
					+ UnnosC.getFase(TozteSatelite).getValor() + " }   ->   "
					+ HiperFases.getHiperFase(TozteSatelite).toString());

			AgrupadorC.Adicionar(HiperFases.getHiperFase(TozteSatelite));

			TozteSatelite = TozteSatelite.adicionar_Superarko(1);
		}

		AgrupadorC.Agrupar();

		ret += ("\n");

		ret += ("\n - Comum : " + AgrupadorC.getComum());
		ret += ("\n");

		ret += ("\n - Allet : " + AgrupadorC.getAllett());
		ret += ("\n - Ettun : " + AgrupadorC.getEttun());
		ret += ("\n - Unnall : " + AgrupadorC.getUnnall());
		ret += ("\n");

		ret += ("\n - Allizz : " + AgrupadorC.getAllizz());
		ret += ("\n - Ettizz : " + AgrupadorC.getEttizz());
		ret += ("\n - Unnizz : " + AgrupadorC.getUnnizz());
		ret += ("\n");

		ret += ("\n - Escuridao : " + AgrupadorC.getEscuridao());
		ret += ("\n - Iluminacao : " + AgrupadorC.getIluminacao());
		ret += ("\n");

		ret += ("\n - TODOS : " + AgrupadorC.getTodos());

		return ret;
	}

	public static String Satelites_Contadores(int eTronarko) {

		String ret = "";

		Tozte TozteSatelite = new Tozte(1, 1, eTronarko);

		Agrupador AgrupadorC = new Agrupador();

		for (int g = 0; g < 500; g++) {

			AgrupadorC.Adicionar(HiperFases.getHiperFase(TozteSatelite));

			TozteSatelite = TozteSatelite.adicionar_Superarko(1);
		}

		AgrupadorC.Agrupar();

		ret += ("\n");

		ret += ("\n - Comum : " + AgrupadorC.getComum());
		ret += ("\n");

		ret += ("\n - Allet : " + AgrupadorC.getAllett());
		ret += ("\n - Ettun : " + AgrupadorC.getEttun());
		ret += ("\n - Unnall : " + AgrupadorC.getUnnall());
		ret += ("\n");

		ret += ("\n - Allizz : " + AgrupadorC.getAllizz());
		ret += ("\n - Ettizz : " + AgrupadorC.getEttizz());
		ret += ("\n - Unnizz : " + AgrupadorC.getUnnizz());
		ret += ("\n");

		ret += ("\n - Escuridao : " + AgrupadorC.getEscuridao());
		ret += ("\n - Iluminacao : " + AgrupadorC.getIluminacao());
		ret += ("\n");

		ret += ("\n - TODOS : " + AgrupadorC.getTodos());

		return ret;
	}

	public static HiperFases getHiperFase(Tozte TozteC) {

		HiperFases ret = HiperFases.COMUM;

		Allux AlluxC = new Allux();
		Ettos EttosC = new Ettos();
		Unnos UnnosC = new Unnos();

		// As Tres

		if (AlluxC.getFase(TozteC) == Fases.NOVA && EttosC.getFase(TozteC) == Fases.NOVA
				&& UnnosC.getFase(TozteC) == Fases.NOVA) {

			ret = HiperFases.ILUMINACAO;

		}

		if (AlluxC.getFase(TozteC) == Fases.CHEIA && EttosC.getFase(TozteC) == Fases.CHEIA
				&& UnnosC.getFase(TozteC) == Fases.CHEIA) {

			ret = HiperFases.ESCURIDAO;

		}

		// Apenas Uma

		if (AlluxC.getFase(TozteC) == Fases.CHEIA && EttosC.getFase(TozteC) == Fases.NOVA
				&& UnnosC.getFase(TozteC) == Fases.NOVA) {

			ret = HiperFases.ALLIZZ;

		}

		if (AlluxC.getFase(TozteC) == Fases.NOVA && EttosC.getFase(TozteC) == Fases.CHEIA
				&& UnnosC.getFase(TozteC) == Fases.NOVA) {

			ret = HiperFases.ETTIZZ;

		}

		if (AlluxC.getFase(TozteC) == Fases.NOVA && EttosC.getFase(TozteC) == Fases.NOVA
				&& UnnosC.getFase(TozteC) == Fases.CHEIA) {

			ret = HiperFases.UNNIZZ;

		}

		// Com Duas

		if (AlluxC.getFase(TozteC) == Fases.CHEIA && EttosC.getFase(TozteC) == Fases.CHEIA
				&& UnnosC.getFase(TozteC) == Fases.NOVA) {

			ret = HiperFases.ALLETT;

		}

		if (AlluxC.getFase(TozteC) == Fases.CHEIA && EttosC.getFase(TozteC) == Fases.NOVA
				&& UnnosC.getFase(TozteC) == Fases.CHEIA) {

			ret = HiperFases.UNNALL;

		}

		if (AlluxC.getFase(TozteC) == Fases.NOVA && EttosC.getFase(TozteC) == Fases.CHEIA
				&& UnnosC.getFase(TozteC) == Fases.CHEIA) {

			ret = HiperFases.ETTUN;

		}

		return ret;
	}

}
