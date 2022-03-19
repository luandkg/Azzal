package Tronarko.Eventos;

import java.util.ArrayList;

import Tronarko.Eventos.Avisar.AvisarGrandeEvento;
import Tronarko.Eventos.Avisar.AvisarPequenoEvento;
import Tronarko.Tozte;

public class Comunicum {

	private Eventum mEventum;

	public Comunicum() {
		
		mEventum = new Eventum();
		
	}
	
	
	public ArrayList<String> Comunicar_Tozte(Tozte TozteProcurar) {

		ArrayList<String> ret = new ArrayList<String>();

		ret.addAll(Comunicar_PequenosEventos_NOVO(TozteProcurar));
		ret.addAll(Comunicar_GrandesEventos_NOVO(TozteProcurar));

		return ret;
	}

	public ArrayList<String> Comunicar_PequenosEventos_NOVO(Tozte TozteProcurar) {
		ArrayList<String> ret = new ArrayList<String>();

		for (AvisarPequenoEvento AvisoC : mEventum.getAvisosPequenosEventos()) {

			//int TronarkoInicio = TozteProcurar.getTronarko();

			int TronarkoInicio = AvisoC.getTronarkoProximoDe(TozteProcurar.getTronarko());

			
			
			if (TronarkoInicio == TozteProcurar.getTronarko()) {

				Tozte eCorrente = new Tozte(AvisoC.getSuperarko(), AvisoC.getHiperarko(), TronarkoInicio);

		
				Tozte eAvisoInicio = eCorrente.adicionar_Superarko((-1) * AvisoC.getAntes());
				Tozte eAvisoFim = eCorrente.adicionar_Superarko((+1) * AvisoC.getDepois());

	
				if (TozteProcurar.isMaiorIgualQue(eAvisoInicio) && TozteProcurar.isMenorIgualQue(eAvisoFim)) {

					if (TozteProcurar.isIgual(eCorrente)) {

						ret.add("Estamos no " + AvisoC.getNome());

					} else {

						// ret.add(" - Deve Notificar !");

						if (TozteProcurar.isMaiorIgualQue(eAvisoInicio) && TozteProcurar.isMenorQue(eCorrente)) {
							// ret.add(" - Antes !");

							long Falta = eCorrente.getSuperarkosTotal() - TozteProcurar.getSuperarkosTotal();

							if (Falta == 1) {
								ret.add("Falta : " + (Falta) + " Superarko para " + AvisoC.getNome());
							} else if (Falta > 1) {
								ret.add("Faltam : " + (Falta) + " Superarkos para " + AvisoC.getNome());
							}

						} else if (TozteProcurar.isMenorIgualQue(eAvisoFim) && TozteProcurar.isMaiorQue(eCorrente)) {
							// ret.add(" - Depois !");

							long Passou = TozteProcurar.getSuperarkosTotal() - eCorrente.getSuperarkosTotal();

							if (Passou == 1) {
								ret.add("Passou : " + (Passou) + " Superarko após " + AvisoC.getNome());
							} else if (Passou > 1) {
								ret.add("Passaram : " + (Passou) + " Superarkos após " + AvisoC.getNome());
							}

						}

					}

				}

			}

		}

		return ret;
	}

	public ArrayList<String> Comunicar_GrandesEventos_NOVO(Tozte TozteProcurar) {
		ArrayList<String> ret = new ArrayList<String>();

		for (AvisarGrandeEvento AvisoC : mEventum.getAvisosGrandesEventos()) {


			int TronarkoInicio = AvisoC.getTronarkoProximoDe(TozteProcurar.getTronarko());
			
			
			if (TronarkoInicio == TozteProcurar.getTronarko()) {

				Tozte eInicio = new Tozte(AvisoC.getSuperarkoInicio(), AvisoC.getHiperarkoInicio(), TronarkoInicio);
				Tozte eFim = new Tozte(AvisoC.getSuperarkoFim(), AvisoC.getHiperarkoFim(), TronarkoInicio);

	
				Tozte eAvisoInicio = eInicio.adicionar_Superarko((-1) * AvisoC.getAntes());
				Tozte eAvisoFim = eFim.adicionar_Superarko((+1) * AvisoC.getDepois());

	
				if (TozteProcurar.isMaiorIgualQue(eAvisoInicio) && TozteProcurar.isMenorIgualQue(eAvisoFim)) {

					if (TozteProcurar.isMaiorIgualQue(eInicio) && TozteProcurar.isMenorIgualQue(eFim)) {

						// ret.add(" - Grande Evento Ocorrendo !");

						if (TozteProcurar.isIgual(eInicio)) {
							ret.add("Hoje é o Inicio de : " + AvisoC.getNome());

						} else if (TozteProcurar.isIgual(eFim)) {
							ret.add("Hoje é o Fim de : " + AvisoC.getNome());

						} else {
							ret.add("Estamos no " + AvisoC.getNome());

						}

					} else {

						// ret.add(" - Deve Notificar !");

						if (TozteProcurar.isMaiorIgualQue(eAvisoInicio) && TozteProcurar.isMenorQue(eInicio)) {
							// ret.add(" - Antes !");

							long Falta = eInicio.getSuperarkosTotal() - TozteProcurar.getSuperarkosTotal();

							if (Falta == 1) {
								ret.add("Falta : " + (Falta) + " Superarko para " + AvisoC.getNome());
							} else if (Falta > 1) {
								ret.add("Faltam : " + (Falta) + " Superarkos para " + AvisoC.getNome());
							}

						} else if (TozteProcurar.isMenorIgualQue(eAvisoFim) && TozteProcurar.isMaiorQue(eFim)) {
							// ret.add(" - Depois !");

							long Passou = TozteProcurar.getSuperarkosTotal() - eFim.getSuperarkosTotal();

							if (Passou == 1) {
								ret.add("Passou : " + (Passou) + " Superarko após " + AvisoC.getNome());
							} else if (Passou > 1) {
								ret.add("Passaram : " + (Passou) + " Superarkos após " + AvisoC.getNome());
							}

						}

					}

				}

			}

		}

		return ret;
	}

	public void Comunicar_Todos(int eTronarko) {


		Tozte TozteC = new Tozte(1, 1, eTronarko);

		for (int i = 0; i < 500; i++) {

			if (Comunicar_Tozte(TozteC).size() > 0) {

				System.out.printf("\n--------------------------------" + TozteC.toString()
						+ "------------------------------------------\n");
				System.out.printf("\n");

				for (String ProximoEventoHoje : Comunicar_Tozte(TozteC)) {
					System.out.println("\t - " + ProximoEventoHoje);
				}

			}

			TozteC = TozteC.adicionar_Superarko(1);
		}

	}
	
	public void Comunicar_TodosEFiltrarCom(String eFiltro,int eTronarko) {


		Tozte TozteC = new Tozte(1, 1, eTronarko);

		for (int i = 0; i < 500; i++) {

			if (Comunicar_Tozte(TozteC).size() > 0) {

				boolean listarEsse = false;
				
				for (String ProximoEventoHoje : Comunicar_Tozte(TozteC)) {
				if (ProximoEventoHoje.contains(eFiltro)) {
					listarEsse=true;
				}
				}
				
				if (listarEsse) {
					
					System.out.printf("\n--------------------------------" + TozteC.toString()
					+ "------------------------------------------\n");
			System.out.printf("\n");

			for (String ProximoEventoHoje : Comunicar_Tozte(TozteC)) {
				if (ProximoEventoHoje.contains(eFiltro)) {
					System.out.println("\t - " + ProximoEventoHoje);

				}
			}
			
				}
				
				

			}

			TozteC = TozteC.adicionar_Superarko(1);
		}

	}
	
}
