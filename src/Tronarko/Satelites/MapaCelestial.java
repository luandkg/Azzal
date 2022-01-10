package Tronarko.Satelites;

import java.awt.Color;
import java.util.ArrayList;

import Tronarko.TozteCor;
import Tronarko.Tronarko.Tozte;

public class MapaCelestial {

	// TRES
	public Iluminacao getIluminacao() {
		return new Iluminacao();
	}

	public Escuridao getEscuridao() {
		return new Escuridao();
	}

	// DUAS
	public Allett getAllett() {
		return new Allett();
	}

	public Unnall getUnnall() {
		return new Unnall();
	}

	public Ettun getEttun() {
		return new Ettun();
	}

	// UMA
	public Allizz getAllizz() {
		return new Allizz();
	}

	public Ettizz getEttizz() {
		return new Ettizz();
	}

	public Unnizz getUnnizz() {
		return new Unnizz();
	}

	public ArrayList<TozteCor> getToztesComCor_Allux(int eTronarko) {

		ArrayList<TozteCor> ToztesComCor = new ArrayList<TozteCor>();

		Allux AlluxC = new Allux();

		for (int h = 1; h <= 10; h++) {

			for (int s = 1; s <= 50; s++) {

				Tozte TozteC = new Tozte(s, h, eTronarko);

				Fases FaseC = AlluxC.getFase(TozteC);

				switch (FaseC) {
				case CHEIA:
					ToztesComCor.add(new TozteCor("CHEIA", TozteC, Color.YELLOW));
					break;
				case NOVA:
					ToztesComCor.add(new TozteCor("NOVA", TozteC, Color.WHITE));
					break;
				case MINGUANTE:
					ToztesComCor.add(new TozteCor("MINGUANTE", TozteC, Color.RED));
					break;
				case CRESCENTE:
					ToztesComCor.add(new TozteCor("CRESCENTE", TozteC, Color.BLUE));
					break;
				}

			}

		}

		return ToztesComCor;
	}

	public ArrayList<TozteCor> getToztesComCor_Ettos(int eTronarko) {

		ArrayList<TozteCor> ToztesComCor = new ArrayList<TozteCor>();

		Ettos EttosC = new Ettos();

		for (int h = 1; h <= 10; h++) {

			for (int s = 1; s <= 50; s++) {

				Tozte TozteC = new Tozte(s, h, eTronarko);

				Fases FaseC = EttosC.getFase(TozteC);

				switch (FaseC) {
				case CHEIA:
					ToztesComCor.add(new TozteCor("CHEIA", TozteC, Color.YELLOW));
					break;
				case NOVA:
					ToztesComCor.add(new TozteCor("NOVA", TozteC, Color.WHITE));
					break;
				case MINGUANTE:
					ToztesComCor.add(new TozteCor("MINGUANTE", TozteC, Color.RED));
					break;
				case CRESCENTE:
					ToztesComCor.add(new TozteCor("CRESCENTE", TozteC, Color.BLUE));
					break;
				}

			}

		}

		return ToztesComCor;
	}

	public ArrayList<TozteCor> getToztesComCor_Todos(int eTronarko) {

		ArrayList<TozteCor> ToztesComCor = new ArrayList<TozteCor>();

		Allux FaseAllux = new Allux();
		Ettos FaseEttos = new Ettos();
		Unnos FaseUnnos = new Unnos();

		for (int h = 1; h <= 10; h++) {

			for (int s = 1; s <= 50; s++) {

				Tozte TozteC = new Tozte(s, h, eTronarko);

				Fases FaseAlluxC = FaseAllux.getFase(TozteC);
				Fases FaseEttosC = FaseEttos.getFase(TozteC);
				Fases FaseUnnosC = FaseUnnos.getFase(TozteC);

			
				
				// TRES
				if (FaseAlluxC == Fases.CHEIA && FaseEttosC == Fases.CHEIA && FaseUnnosC == Fases.CHEIA) {
					ToztesComCor.add(new TozteCor("ILUMUNAÇAO", TozteC, Color.GRAY));
				}

				if (FaseAlluxC == Fases.NOVA && FaseEttosC == Fases.NOVA && FaseUnnosC == Fases.NOVA) {
					ToztesComCor.add(new TozteCor("ESCURIDÃO", TozteC, Color.BLACK));
				}

				// DUAS
				if (FaseAlluxC == Fases.CHEIA && FaseEttosC == Fases.CHEIA && FaseUnnosC == Fases.NOVA) {
					ToztesComCor.add(new TozteCor("ALLET", TozteC, Color.ORANGE));
				}
				
				if (FaseAlluxC == Fases.CHEIA && FaseEttosC == Fases.NOVA && FaseUnnosC == Fases.CHEIA) {
					ToztesComCor.add(new TozteCor("UNNALL", TozteC, Color.GREEN));
				}
				if (FaseAlluxC == Fases.NOVA && FaseEttosC == Fases.CHEIA && FaseUnnosC == Fases.CHEIA) {
					ToztesComCor.add(new TozteCor("ETTUN", TozteC, new Color(135,31,120)));
				}
				
				// UM
				if (FaseAlluxC == Fases.CHEIA && FaseEttosC == Fases.NOVA && FaseUnnosC == Fases.NOVA) {
					ToztesComCor.add(new TozteCor("ALLIZZ", TozteC, Color.YELLOW));
				}
				
				if (FaseAlluxC == Fases.NOVA && FaseEttosC == Fases.CHEIA && FaseUnnosC == Fases.NOVA) {
					ToztesComCor.add(new TozteCor("ETTIZZ", TozteC, Color.RED));
				}
				if (FaseAlluxC == Fases.NOVA && FaseEttosC == Fases.NOVA && FaseUnnosC == Fases.CHEIA) {
					ToztesComCor.add(new TozteCor("UNNIZZ", TozteC, Color.BLUE));
				}
			}

		}

		return ToztesComCor;
	}

	public ArrayList<TozteCor> getToztesComCor_Unnos(int eTronarko) {

		ArrayList<TozteCor> ToztesComCor = new ArrayList<TozteCor>();

		Unnos UnnosC = new Unnos();

		for (int h = 1; h <= 10; h++) {

			for (int s = 1; s <= 50; s++) {

				Tozte TozteC = new Tozte(s, h, eTronarko);

				Fases FaseC = UnnosC.getFase(TozteC);

				switch (FaseC) {
				case CHEIA:
					ToztesComCor.add(new TozteCor("CHEIA", TozteC, Color.YELLOW));
					break;
				case NOVA:
					ToztesComCor.add(new TozteCor("NOVA", TozteC, Color.WHITE));
					break;
				case MINGUANTE:
					ToztesComCor.add(new TozteCor("MINGUANTE", TozteC, Color.RED));
					break;
				case CRESCENTE:
					ToztesComCor.add(new TozteCor("CRESCENTE", TozteC, Color.BLUE));
					break;
				}

			}

		}

		return ToztesComCor;
	}

	public static class Allux extends Satelite {

		public Allux() {
			super("ALLUX", new Tozte(31, 6, 3856), 10, "AMARELO");
		}

	}
	
	public static class Ettos extends Satelite {

		public Ettos() {
			super("ETTOS", new Tozte(12, 8, 4727), 33, "VERMELHO");
		}

	}

	public static class Unnos extends Satelite {

		public Unnos() {
			super("UNNOS", new Tozte(18, 10, 4838), 22, "AZUL");
		}

	}

	
	public static class Iluminacao extends Satelites {

		public Iluminacao() {

			super("Iluminação", Fases.CHEIA, Fases.CHEIA, Fases.CHEIA);
		}

	}

	public static class Escuridao extends Satelites {

		public Escuridao() {

			super("Escuridão", Fases.NOVA, Fases.NOVA, Fases.NOVA);
		}

	}

	public static class Allett extends Satelites {

		public Allett() {

			super("Allett", Fases.CHEIA, Fases.CHEIA, Fases.NOVA);
		}

	}

	public static class Unnall extends Satelites {

		public Unnall() {

			super("Unnall", Fases.CHEIA, Fases.NOVA, Fases.CHEIA);
		}

	}

	public static class Ettun extends Satelites {

		public Ettun() {

			super("Ettun", Fases.NOVA, Fases.CHEIA, Fases.CHEIA);
		}

	}

	public static class Allizz extends Satelites {

		public Allizz() {

			super("Allizz", Fases.CHEIA, Fases.NOVA, Fases.NOVA);
		}

	}

	public static class Ettizz extends Satelites {

		public Ettizz() {

			super("Ettizz", Fases.NOVA, Fases.CHEIA, Fases.NOVA);
		}

	}

	public static class Unnizz extends Satelites {

		public Unnizz() {

			super("Unnizz", Fases.NOVA, Fases.NOVA, Fases.CHEIA);
		}

	}

}
