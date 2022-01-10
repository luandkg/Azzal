package Tronarko.Harrempluz;

import java.util.ArrayList;

import Tronarko.Tronarko.Signos;

public class Harrempluz {

	private ArrayList<String> mSignos;
	private ArrayList<Colecao> mColecoes;
	private ArrayList<Harrem> mHarrems;

	public Harrempluz() {

		mSignos = new ArrayList<String>();
		mColecoes = new ArrayList<Colecao>();
		mHarrems = new ArrayList<Harrem>();

		mSignos.add(Signos.TIGRE.toString());
		mSignos.add(Signos.RAPOSA.toString());
		mSignos.add(Signos.LEOPARDO.toString());
		mSignos.add(Signos.LEAO.toString());
		mSignos.add(Signos.TOURO.toString());
		mSignos.add(Signos.LOBO.toString());
		mSignos.add(Signos.GATO.toString());
		mSignos.add(Signos.CARPA.toString());
		mSignos.add(Signos.GAVIAO.toString());
		mSignos.add(Signos.SERPENTE.toString());

		mColecoes.add(new Colecao("COR", "AMARELO VERDE VERMELHO AZUL CINZA PRETO ROSA ROXO BRANCO MARRON LARANJA "));
		mColecoes.add(new Colecao("ELEMENTO", "AGUA TERRA VENTO FOGO "));

		mColecoes.add(new Colecao("METAL", "LATaO COBRE ESTANHO ZINCO AaO FERRO BRONZE PRATA OURO "));
		mColecoes.add(new Colecao("QUALIDADE",
				"adoravel afavel afetivo agradavel ajuizado alegre altruasta amavel amigavel amoroso aplicado assertivo atencioso atento autantico aventureiro bacana benavolo bondoso brioso calmo carinhoso carismatico caritativo cavalheiro cavico civilizado companheiro compreensivo comunicativo confiante confiavel consciencioso corajoso cordial cortas credavel criativo criterioso cuidadoso curioso decente decoroso dedicado descontraado desenvolto determinado digno diligente disciplinado disponavel divertido doce educado eficiente eloquente empatico empenhado empreendedor encantador engraaado entusiasta escrupuloso esforaado esmerado esperanaoso esplandido excelente extraordinario extrovertido feliz fiel fofo forte franco generoso gentil genuano habilidoso honesto honrado honroso humanitario humilde idaneo imparcial independente inovador antegro inteligente inventivo justo leal legal livre maduro maravilhoso meigo modesto natural nobre observador organizado otimista ousado pacato paciente perfeccionista perseverante persistente perspicaz ponderado pontual preocupado preparado prestativo prestavel proativo produtivo prudente racional respeitador responsavel sabio sagaz sensato sensavel simpatico sincero solacito solidario sossegado ternurento tolerante tranquilo transparente valente valoroso verdadeiro zeloso "));
		mColecoes.add(new Colecao("DEFEITO",
				"agressivo ansioso antipatico antissocial apatico apressado arrogante atrevido autoritario avarento birrento bisbilhoteiro bruto calculista casmurro chato canico ciumento colarico comodista covarde cratico cruel debochado depressivo desafiador desbocado descarado descomedido desconfiado descortas desequilibrado desleal desleixado desmazelado desmotivado desobediente desonesto desordeiro despatico desumano discriminador dissimulado distraado egoasta estourado estressado exigente falso fingido fraco frio fravolo fatil ganancioso grosseiro grosso hipacrita ignorante impaciente impertinente impetuoso impiedoso imponderado impostor imprudente impulsivo incompetente inconstante inconveniente incorreto indeciso indecoroso indelicado indiferente infiel inflexavel injusto inseguro insensato insincero instavel insuportavel interesseiro intolerante intransigente irracional irascavel irrequieto irresponsavel irritadiao malandro maldoso malicioso malvado mandão manhoso maquiavalico medroso mentiroso mesquinho narcisista negligente nervoso neuratico obcecado odioso oportunista orgulhoso pedante pessimista pa-frio possessivo precipitado preconceituoso preguiaoso prepotente presunaoso problematico quezilento rancoroso relapso rigoroso rabugento rude sarcastico sedentario teimoso tamido tirano traiaoeiro traidor trapaceiro tendencioso trocista vagabundo vaidoso vulneravel vigarista xenafobo "));
		mColecoes.add(new Colecao("NUMERO_10", "0 1 2 3 4 5 6 7 8 9 "));
		mColecoes.add(new Colecao("NUMERO_100",
				"0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45 46 47 48 49 50 51 52 53 54 55 56 57 58 59 60 61 62 63 64 65 66 67 68 69 70 71 72 73 74 75 76 77 78 79 80 81 82 83 84 85 86 87 88 89 90 91 92 93 94 95 96 97 98 99 100 "));
		mColecoes.add(new Colecao("VOGAL", "A E I O U "));
		mColecoes.add(new Colecao("CONSOANTE", "B C D F G H J K L M N P Q R S T V W X Y Z "));
		mColecoes.add(new Colecao("LETRA", "A B C D E F G H I J K L M N O P Q R S T U V W X Y Z "));
		mColecoes.add(new Colecao("CRISTAL",
				"Alexandrita Ametista Aquamarine Citrino Diamante Esmeralda Granada Jade lazali Opala Turmalina Parolas Peridoto Rubi Safira Espinela Tanzanite Topazio Turmalina Turquesa Zircania Morganite agata Amazonita ambar Cornalina Fluorita Hematita Jaspe Malaquita Nacar Sodalita "));

		mColecoes.add(new Colecao("DIRECAO", "NORTE SUL LESTE OESTE NORDESTE SUDESTE NOROESTE SUDOESTE "));

	}

	public String Mostar_Harrem(Harrem HarremC) {

		return HarremC.Mostar_Harrem();

	}

	public String Mostrar_Megarko(Harrem HarremC, int eMegarko) {
		return HarremC.Mostrar_Megarko(eMegarko);

	}

	public String Mostrar_Signo(Harrem HarremC, int eMegarko, Signos eSigno) {
		return HarremC.Mostrar_Signo(eMegarko, eSigno);
	}

	public String Mostrar_SignoItem(Harrem HarremC, int eMegarko, Signos eSigno, Itens eItem) {
		return HarremC.Mostrar_SignoItem(eMegarko, eSigno, eItem);
	}

	public String Mostrar_SignoDetalhes(Harrem HarremC, int eMegarko, Signos eSigno) {
		return HarremC.Mostrar_SignoDetalhes(eMegarko, eSigno);
	}

	public Harrem Criar_Harrempluz(int eTronarko) {

		Harrem HarremC = new Harrem(eTronarko);

		for (int eMega = 1; eMega <= 50; eMega++) {

			HarremMegarko HarremMegarkoC = HarremC.HarremMegarko(eMega);

			for (String SignoC : mSignos) {

				HarremSigno HarremSignoC = HarremMegarkoC.Signo(SignoC);

				for (Colecao ColecaoC : mColecoes) {

					HarremSignoC.HarremItem(ColecaoC.getNome(), ColecaoC.SorteieQualquer());

				}

			}

		}

		return HarremC;
	}

	public void MostrarSignos() {

		int i = 1;

		for (String SignoC : mSignos) {

			System.out.println(" - " + i + " " + SignoC);
			i += 1;
		}

		System.out.println("");
		System.out.println("");

		for (Colecao ColecaoC : mColecoes) {

			System.out.println(" ------------ " + ColecaoC.getNome() + " ------------ ");
			for (String Cor : ColecaoC.Listar()) {

				// System.out.println( " - " + Cor);

			}

			System.out.println("");
			System.out.println(" ESCOLHENDO : ");

			ColecaoC.IniciarEscolha();
			for (int a = 0; a < 10; a++) {
				System.out.println(" Escolha : " + a + " -> " + ColecaoC.SorteieQualquer());
			}

		}

	}

	public static class Harrem {

		private int mTronarko;
		private ArrayList<HarremMegarko> mHarremMegarkos;

		public Harrem(int eTronarko) {
			mTronarko = eTronarko;

			mHarremMegarkos = new ArrayList<HarremMegarko>();
		}

		public int getTronarko() {
			return mTronarko;
		}

		public ArrayList<HarremMegarko> getHarremMegarkos() {
			return mHarremMegarkos;
		}

		public HarremMegarko HarremMegarko(int eMegarko) {

			HarremMegarko HarremMegarkoC = null;
			boolean enc = false;

			for (HarremMegarko HarremMegarkoI : mHarremMegarkos) {
				if (HarremMegarkoI.getMegarko() == eMegarko) {
					HarremMegarkoC = HarremMegarkoI;
					enc = true;
					break;
				}

			}

			if (enc == false) {
				HarremMegarkoC = new HarremMegarko(eMegarko);
				mHarremMegarkos.add(HarremMegarkoC);

			}

			return HarremMegarkoC;
		}

		public String Mostar_Harrem() {
			String ret = "";

			ret += "\n - Harrempluz : " + this.getTronarko();

			for (HarremMegarko HarremMegarkoC : this.getHarremMegarkos()) {

				ret += "\n   - Mega : " + HarremMegarkoC.getMegarko();

				for (HarremSigno HarremSignoC : HarremMegarkoC.getHarremSignos()) {

					ret += "\n       - " + HarremSignoC.getSigno() + " { " + HarremSignoC.getConteudo() + " } ";

				}

			}
			return ret;
		}

		public String Mostrar_Megarko(int eMegarko) {

			String ret = "";

			ret += "\n - Harrempluz : " + this.getTronarko();

			for (HarremMegarko HarremMegarkoC : this.getHarremMegarkos()) {

				if (eMegarko == HarremMegarkoC.getMegarko()) {

					ret += "\n   - Mega : " + HarremMegarkoC.getMegarko();

					for (HarremSigno HarremSignoC : HarremMegarkoC.getHarremSignos()) {

						ret += "\n       - " + HarremSignoC.getSigno() + " { " + HarremSignoC.getConteudo() + " } ";

					}

					break;

				}

			}

			return ret;
		}

		public String Mostrar_Signo(int eMegarko, Signos eSigno) {

			String ret = "";

			for (HarremMegarko HarremMegarkoC : this.getHarremMegarkos()) {

				if (eMegarko == HarremMegarkoC.getMegarko()) {

					for (HarremSigno HarremSignoC : HarremMegarkoC.getHarremSignos()) {

						if (HarremSignoC.getSigno().contentEquals(eSigno.toString())) {

							ret = HarremSignoC.getConteudo();

							break;
						}

					}

					break;

				}

			}

			return ret;
		}

		public String Mostrar_SignoItem(int eMegarko, Signos eSigno, Itens eItem) {

			String ret = "";

			for (HarremMegarko HarremMegarkoC : this.getHarremMegarkos()) {

				if (eMegarko == HarremMegarkoC.getMegarko()) {

					for (HarremSigno HarremSignoC : HarremMegarkoC.getHarremSignos()) {

						if (HarremSignoC.getSigno().contentEquals(eSigno.toString())) {

							ret = HarremSignoC.getItem(eItem.toString()).getValor();

							break;
						}

					}

					break;

				}

			}

			return ret;
		}

		public String Mostrar_SignoDetalhes(int eMegarko, Signos eSigno) {

			String ret = "";

			for (HarremMegarko HarremMegarkoC : this.getHarremMegarkos()) {

				if (eMegarko == HarremMegarkoC.getMegarko()) {

					for (HarremSigno HarremSignoC : HarremMegarkoC.getHarremSignos()) {

						if (HarremSignoC.getSigno().contentEquals(eSigno.toString())) {

							ret = " Cor : " + HarremSignoC.getItem(Itens.COR.toString()).getValor();
							ret += "\n Elemento : " + HarremSignoC.getItem(Itens.ELEMENTO.toString()).getValor();
							ret += "\n Metal : " + HarremSignoC.getItem(Itens.METAL.toString()).getValor();
							ret += "\n Qualidade: " + HarremSignoC.getItem(Itens.QUALIDADE.toString()).getValor();
							ret += "\n Defeito : " + HarremSignoC.getItem(Itens.DEFEITO.toString()).getValor();
							ret += "\n Numero 10 : " + HarremSignoC.getItem(Itens.NUMERO_10.toString()).getValor();
							ret += "\n Numero 100 : " + HarremSignoC.getItem(Itens.NUMERO_100.toString()).getValor();
							ret += "\n Vogal : " + HarremSignoC.getItem(Itens.VOGAL.toString()).getValor();
							ret += "\n Consoante : " + HarremSignoC.getItem(Itens.CONSOANTE.toString()).getValor();
							ret += "\n Letra : " + HarremSignoC.getItem(Itens.LETRA.toString()).getValor();
							ret += "\n Cristal : " + HarremSignoC.getItem(Itens.CRISTAL.toString()).getValor();
							ret += "\n Direcao : " + HarremSignoC.getItem(Itens.DIRECAO.toString()).getValor();

							break;
						}

					}

					break;

				}

			}

			return ret;
		}

	}

	public static class HarremMegarko {

		private int mMegarko;
		private ArrayList<HarremSigno> mHarremSignos;

		public HarremMegarko(int eMegarko) {
			mMegarko = eMegarko;
			mHarremSignos = new ArrayList<HarremSigno>();

		}

		public int getMegarko() {
			return mMegarko;
		}

		public ArrayList<HarremSigno> getHarremSignos() {
			return mHarremSignos;
		}

		public HarremSigno Signo(String eNome) {

			HarremSigno HarremSignoC = null;
			boolean enc = false;

			for (HarremSigno HarremSignoI : mHarremSignos) {
				if (HarremSignoI.getSigno().contentEquals(eNome)) {
					HarremSignoC = HarremSignoI;
					enc = true;
					break;
				}

			}

			if (enc == false) {
				HarremSignoC = new HarremSigno(eNome);
				mHarremSignos.add(HarremSignoC);

			}

			return HarremSignoC;
		}

	}

	public static class HarremSigno {

		private String mSigno;
		private ArrayList<HarremItem> mHarremItems;

		public HarremSigno(String eSigno) {
			mSigno = eSigno;
			mHarremItems = new ArrayList<HarremItem>();

		}

		public String getSigno() {
			return mSigno;
		}

		public ArrayList<HarremItem> getHarremItems() {
			return mHarremItems;
		}

		public String getConteudo() {

			String ret = "";
			boolean primeiro = true;

			for (HarremItem HarremItemC : mHarremItems) {

				if (primeiro) {

					ret += HarremItemC.getNome() + " = " + HarremItemC.getValor();

				} else {

					ret += " | " + HarremItemC.getNome() + " = " + HarremItemC.getValor();

				}

				primeiro = false;
			}

			return ret;
		}

		public HarremItem HarremItem(String eNome, String eValor) {

			HarremItem HarremItemC = null;
			boolean enc = false;

			for (HarremItem HarremItemI : mHarremItems) {
				if (HarremItemI.getNome().contentEquals(eNome)) {
					HarremItemC = HarremItemI;
					enc = true;
					break;
				}

			}

			if (enc == false) {
				HarremItemC = new HarremItem(eNome);
				mHarremItems.add(HarremItemC);

			}

			HarremItemC.setValor(eValor);

			return HarremItemC;
		}

		public HarremItem getItem(String eNome) {

			HarremItem HarremItemC = null;
			boolean enc = false;

			for (HarremItem HarremItemI : mHarremItems) {
				if (HarremItemI.getNome().contentEquals(eNome)) {
					HarremItemC = HarremItemI;
					enc = true;
					break;
				}

			}

			if (enc == false) {
				HarremItemC = new HarremItem(eNome);
				mHarremItems.add(HarremItemC);

			}

			return HarremItemC;
		}

	}

	public static class HarremItem {

		private String mNome;
		private String mValor;

		public HarremItem(String eNome) {
			mNome = eNome;
			mValor = "";

		}

		public String getNome() {
			return mNome;
		}

		public String getValor() {
			return mValor;
		}

		public void setValor(String eValor) {
			mValor = eValor;
		}

	}

	public static enum Itens {

		COR(0), ELEMENTO(1), METAL(2), QUALIDADE(3), DEFEITO(4), NUMERO_10(5), NUMERO_100(6), VOGAL(7), CONSOANTE(8),
		LETRA(9), CRISTAL(10), DIRECAO(11);

		private int mValor;

		Itens(int eValor) {
			mValor = eValor;
		}

		public int getValor() {
			return mValor;
		}

		public String toString() {
			String ret = "";

			if (mValor == 0) {
				ret = "COR";
			}
			if (mValor == 1) {
				ret = "ELEMENTO";
			}
			if (mValor == 2) {
				ret = "METAL";
			}
			if (mValor == 3) {
				ret = "QUALIDADE";
			}
			if (mValor == 4) {
				ret = "DEFEITO";
			}
			if (mValor == 5) {
				ret = "NUMERO_10";
			}
			if (mValor == 6) {
				ret = "NUMERO_100";
			}
			if (mValor == 7) {
				ret = "VOGAL";
			}
			if (mValor == 8) {
				ret = "CONSOANTE";
			}
			if (mValor == 9) {
				ret = "LETRA";
			}
			if (mValor == 10) {
				ret = "CRISTAL";
			}
			if (mValor == 11) {
				ret = "DIRECAO";
			}
			return ret;
		}
	}

}
