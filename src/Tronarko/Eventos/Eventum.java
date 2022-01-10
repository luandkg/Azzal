package Tronarko.Eventos;

import java.awt.Color;
import java.util.ArrayList;

import Tronarko.Ocorrencia;
import Tronarko.TozteCor;
import Tronarko.Ocorrencia.Modos;
import Tronarko.Tronarko;
import Tronarko.Eventos.Avisar.Avisar;
import Tronarko.Eventos.Avisar.AvisarGrandeEvento;
import Tronarko.Eventos.Avisar.AvisarPequenoEvento;
import Tronarko.Tronarko.Tozte;

public class Eventum {

	private ArrayList<AvisarPequenoEvento> mPequenosEventos;
	private ArrayList<AvisarGrandeEvento> mGrandesEventos;

	public Eventum() {

		mPequenosEventos = new ArrayList<AvisarPequenoEvento>();
		mGrandesEventos = new ArrayList<AvisarGrandeEvento>();

		ArrayList<Ocorrencia> mOcorrencias = new ArrayList<Ocorrencia>();

		mOcorrencias.add(new Ocorrencia("Festival Reciclum Tron", 1, 1, 1, 1, 1));
		mOcorrencias.add(new Ocorrencia("Festival do Vento", 26, 1, 6500, 1, 1));
		mOcorrencias.add(new Ocorrencia("Festival da Água", 41, 7, 6501, 1, 1));
		mOcorrencias.add(new Ocorrencia("Festival do Fogo", 15, 6, 6502, 1, 1));
		mOcorrencias.add(new Ocorrencia("Festival da Terra", 5, 8, 6503, 1, 1));
		mOcorrencias.add(new Ocorrencia("Festival Reciclum Tron", 50, 10, 1, 1, 1));

		 mOcorrencias.add(new Ocorrencia("Aposta do Trovão", 45, 2, 6507, 1, 3));

		mOcorrencias.add(new Ocorrencia("Corrida dos Ventos", 26, 1, 6500, 5, 3));
		mOcorrencias.add(new Ocorrencia("Olimpíadas de Trannor", 11, 3, 6444, 32, 33));
		mOcorrencias.add(new Ocorrencia("Grande Competião de Izzator", 11, 4, 6500, 25, 5));

		mOcorrencias.add(new Ocorrencia("Fuga do Labirinto", 1, 5, 5227, 20, 100));
		mOcorrencias.add(new Ocorrencia("Cruzada dos Águas", 41, 7, 6501, 5, 3));
		mOcorrencias.add(new Ocorrencia("Emboscada da Terra", 5, 8, 6503, 10, 3));

		mOcorrencias.add(new Ocorrencia("Jornada do Fogo", 15, 6, 6502, 5, 3));

		mOcorrencias.add(new Ocorrencia("Festival da Colheita", 26, 10, 6200, 5, 1));

		mOcorrencias.add(new Ocorrencia("Batalha dos Imperadores", 30, 1, 5382, 39, 50));
		mOcorrencias.add(new Ocorrencia("Torneio de Hazzo", 11, 9, 5431, 10, 2));

		for (Ocorrencia OcorrenciaC : mOcorrencias) {

			if (OcorrenciaC.getPeriodo() == Modos.PERIODICO) {

				if (OcorrenciaC.getQuantidade() == 1) {

					mPequenosEventos.add(new AvisarPequenoEvento(OcorrenciaC.getNome(), OcorrenciaC.getHiperarko(),
							OcorrenciaC.getSuperarko(), OcorrenciaC.getTronarkoInicio(), OcorrenciaC.getIntervaloTronarko(), 10, 10));

				} else if (OcorrenciaC.getQuantidade() > 1) {

					mGrandesEventos.add(new AvisarGrandeEvento(OcorrenciaC.getNome(), OcorrenciaC.getHiperarko(),
							OcorrenciaC.getSuperarko(), OcorrenciaC.getHiperarko(), OcorrenciaC.getSuperarkoFim(),
							OcorrenciaC.getIntervaloTronarko(), OcorrenciaC.getTronarkoInicio(), 100, 15));

				}

			}

			if (OcorrenciaC.getPeriodo() == Modos.CICLICO) {

				if (OcorrenciaC.getQuantidade() == 1) {


					mPequenosEventos.add(new AvisarPequenoEvento(OcorrenciaC.getNome(), OcorrenciaC.getHiperarko(),
							OcorrenciaC.getSuperarko(), OcorrenciaC.getTronarkoInicio(), OcorrenciaC.getIntervaloTronarko(), 10, 10));

					
				} else if (OcorrenciaC.getQuantidade() > 1) {

					mGrandesEventos.add(new AvisarGrandeEvento(OcorrenciaC.getNome(), OcorrenciaC.getHiperarko(),
							OcorrenciaC.getSuperarko(), OcorrenciaC.getHiperarko(), OcorrenciaC.getSuperarkoFim(),
							OcorrenciaC.getIntervaloTronarko(), OcorrenciaC.getTronarkoInicio(), 100, 15));

				}

			}

		}

	}

	public ArrayList<Ocorrencia> getOcorrencias() {
		ArrayList<Ocorrencia> ret = new ArrayList<Ocorrencia>();

		for (AvisarPequenoEvento P : mPequenosEventos) {

			ret.add(new Ocorrencia(P.getNome(), P.getSuperarko(), P.getHiperarko(), P.getTronarkoInicio(), 1, P.getIntervalo()));

		}

		for (AvisarGrandeEvento G : mGrandesEventos) {

			ret.add(new Ocorrencia(G.getNome(), G.getSuperarkoInicio(), G.getHiperarkoInicio(), G.getInicio(),
					G.getDuracao(), G.getIntervalo()));

		}

		return ret;
	}

	public void OrdenarPequenosEventos(ArrayList<AvisarPequenoEvento> Entrada) {

		int n = Entrada.size();
		AvisarPequenoEvento temp = null;

		for (int i = 0; i < n; i++) {
			for (int j = 1; j < (n - i); j++) {

				if (Entrada.get(j - 1).getOrdem() >= (Entrada.get(j).getOrdem())) {
					temp = Entrada.get(j - 1);
					Entrada.set(j - 1, Entrada.get(j));
					Entrada.set(j, temp);

				}

			}
		}

	}

	public void OrdenarGrandesEventos(ArrayList<AvisarGrandeEvento> Entrada) {

		int n = Entrada.size();
		AvisarGrandeEvento temp = null;

		for (int i = 0; i < n; i++) {
			for (int j = 1; j < (n - i); j++) {

				if (Entrada.get(j - 1).getOrdem() >= (Entrada.get(j).getOrdem())) {
					temp = Entrada.get(j - 1);
					Entrada.set(j - 1, Entrada.get(j));
					Entrada.set(j, temp);

				}

			}
		}

	}
	
	public void OrdenarGrandesAvisos(ArrayList<Avisar> Entrada) {

		int n = Entrada.size();
		Avisar temp = null;

		for (int i = 0; i < n; i++) {
			for (int j = 1; j < (n - i); j++) {

				if (Entrada.get(j - 1).getOrdem() >= (Entrada.get(j).getOrdem())) {
					temp = Entrada.get(j - 1);
					Entrada.set(j - 1, Entrada.get(j));
					Entrada.set(j, temp);

				}

			}
		}

	}

	public ArrayList<AvisarPequenoEvento> getAvisosPequenosEventos() {

		ArrayList<AvisarPequenoEvento> mOrdenados = new ArrayList<AvisarPequenoEvento>();

		for (AvisarPequenoEvento P : mPequenosEventos) {
			mOrdenados.add(P);
		}

		OrdenarPequenosEventos(mOrdenados);

		return mOrdenados;
	}

	public ArrayList<AvisarGrandeEvento> getAvisosGrandesEventos() {

		ArrayList<AvisarGrandeEvento> mBaguncados = new ArrayList<AvisarGrandeEvento>();

		for (AvisarGrandeEvento P : mGrandesEventos) {
			mBaguncados.add(P);
		}

		OrdenarGrandesEventos(mBaguncados);

		return mBaguncados;
	}

	public ArrayList<String> Eventos_De(int eTronarko) {

		ArrayList<String> ret = new ArrayList<String>();

		for (PequenoEvento P : ProximosPequenosEventos(eTronarko)) {
			ret.add(P.toString());

		}

		for (GrandeEvento P : ProximosGrandesEventos(eTronarko)) {
			ret.add(P.toString());
		}
		return ret;
	}

	public ArrayList<PequenoEvento> ProximosPequenosEventos(int eTronarko) {

		ArrayList<PequenoEvento> ret = new ArrayList<PequenoEvento>();
		ArrayList<AvisarPequenoEvento> mTags = new ArrayList<AvisarPequenoEvento>();

		for (AvisarPequenoEvento TozteCorrente : getAvisosPequenosEventos()) {

			mTags.add(TozteCorrente);

		}

		OrdenarPequenosEventos(mTags);

		for (AvisarPequenoEvento TozteCorrente : mTags) {

			
			int Edicao = 1;

			int TronarkoInicio = TozteCorrente.getInicio();
			while (TronarkoInicio < eTronarko) {
				TronarkoInicio += TozteCorrente.getIntervalo();
				Edicao += 1;
			}
			
			
			Tozte TozteC = new Tozte(TozteCorrente.getSuperarko(), TozteCorrente.getHiperarko(), TronarkoInicio);

			if (TozteC.getTronarko() == eTronarko) {

				ret.add(new PequenoEvento(TozteCorrente.getNome(), TozteC));

			}

		}

		return ret;
	}

	public void ListarPequenosEventosDe(String eGrandeEvento, int eAteTronarko) {

		int tamanho = mPequenosEventos.size();
		int indice = 0;

		while (indice < tamanho) {
			AvisarPequenoEvento TozteCorrente = mPequenosEventos.get(indice);

			if (TozteCorrente.getNome().contentEquals(eGrandeEvento)) {

				int Edicao = 1;

				int TronarkoInicio = TozteCorrente.getInicio();
				while (TronarkoInicio < eAteTronarko) {

					System.out.println(Edicao + " : " + eGrandeEvento + " - " + TronarkoInicio);

					TronarkoInicio += TozteCorrente.getIntervalo();
					Edicao += 1;
				}
			}
			indice += 1;
		}
	}
	
	public void ListarGrandesEventos(String eGrandeEvento, int eAteTronarko) {

		int tamanho = mGrandesEventos.size();
		int indice = 0;

		while (indice < tamanho) {
			AvisarGrandeEvento TozteCorrente = mGrandesEventos.get(indice);

			if (TozteCorrente.getNome().contentEquals(eGrandeEvento)) {

				int Edicao = 1;

				int TronarkoInicio = TozteCorrente.getInicio();
				while (TronarkoInicio < eAteTronarko) {

					System.out.println(Edicao + " : " + eGrandeEvento + " - " + TronarkoInicio);

					TronarkoInicio += TozteCorrente.getIntervalo();
					Edicao += 1;
				}
			}
			indice += 1;
		}
	}
	
	public void ListarEventos_Entre( int eDeTronarko, int eAteTronarko) {
	
	

		ArrayList<Avisar> Avisos = new ArrayList<Avisar>();
		
		for(AvisarPequenoEvento TozteCorrente:mPequenosEventos) {		

				int Edicao = 1;

				int TronarkoInicio = TozteCorrente.getInicio();
				while (TronarkoInicio < eAteTronarko) {

					if (TronarkoInicio >= eDeTronarko) {
						Avisos.add(new Avisar(TozteCorrente.getNome(),TozteCorrente.getSuperarko(),TozteCorrente.getHiperarko(),TronarkoInicio,Edicao));		
					}

					TronarkoInicio += TozteCorrente.getIntervalo();
					Edicao += 1;
				}
			
		}
		
		for(AvisarGrandeEvento TozteCorrente:mGrandesEventos) {		

			int Edicao = 1;

			int TronarkoInicio = TozteCorrente.getInicio();
			while (TronarkoInicio < eAteTronarko) {

				if (TronarkoInicio >= eDeTronarko) {
					Avisos.add(new Avisar(TozteCorrente.getNome(),TozteCorrente.getSuperarkoInicio(),TozteCorrente.getHiperarkoInicio(),TronarkoInicio,Edicao));		
				}

				TronarkoInicio += TozteCorrente.getIntervalo();
				Edicao += 1;
			}
		
	}
		
		OrdenarGrandesAvisos(Avisos);
		
		
		for(Avisar AvisoC:Avisos) {		
			System.out.println(AvisoC.toString());
		}
	}
	
	public void ListarPequenosEventos_Entre(String eGrandeEvento, int eDeTronarko, int eAteTronarko) {

		int tamanho = mPequenosEventos.size();
		int indice = 0;

		while (indice < tamanho) {
			AvisarPequenoEvento TozteCorrente = mPequenosEventos.get(indice);

			if (TozteCorrente.getNome().contentEquals(eGrandeEvento)) {

				int Edicao = 1;

				int TronarkoInicio = TozteCorrente.getInicio();
				while (TronarkoInicio < eAteTronarko) {

					if (TronarkoInicio >= eDeTronarko) {

						System.out.println(Edicao + " : " + eGrandeEvento + " - " + TronarkoInicio);

					}

					TronarkoInicio += TozteCorrente.getIntervalo();
					Edicao += 1;
				}
			}
			indice += 1;
		}
	}

	public void ListarGrandesEventos_Entre(String eGrandeEvento, int eDeTronarko, int eAteTronarko) {

		int tamanho = mGrandesEventos.size();
		int indice = 0;

		while (indice < tamanho) {
			AvisarGrandeEvento TozteCorrente = mGrandesEventos.get(indice);

			if (TozteCorrente.getNome().contentEquals(eGrandeEvento)) {

				int Edicao = 1;

				int TronarkoInicio = TozteCorrente.getInicio();
				while (TronarkoInicio < eAteTronarko) {

					if (TronarkoInicio >= eDeTronarko) {

						System.out.println(Edicao + " : " + eGrandeEvento + " - " + TronarkoInicio);

					}

					TronarkoInicio += TozteCorrente.getIntervalo();
					Edicao += 1;
				}
			}
			indice += 1;
		}
	}

	public ArrayList<GrandeEvento> ProximosGrandesEventos(int eTronarko) {

		ArrayList<AvisarGrandeEvento> SAT = new ArrayList<AvisarGrandeEvento>();
		for (AvisarGrandeEvento TozteCorrente : mGrandesEventos) {

			int Edicao = 1;

			int TronarkoInicio = TozteCorrente.getInicio();
			while (TronarkoInicio < eTronarko) {
				TronarkoInicio += TozteCorrente.getIntervalo();
				Edicao += 1;
			}

			AvisarGrandeEvento SATC = new AvisarGrandeEvento(TozteCorrente.getNome(),
					TozteCorrente.getHiperarkoInicio(), TozteCorrente.getSuperarkoInicio(),
					TozteCorrente.getHiperarkoFim(), TozteCorrente.getSuperarkoFim(), TozteCorrente.getIntervalo(),
					TozteCorrente.getInicio(), TozteCorrente.getAntes(), TozteCorrente.getDepois());

			SATC.setTronarko(TronarkoInicio);
			SATC.setEdicao(Edicao);

			SAT.add(SATC);

		}

		OrdenarGrandesEventos(SAT);

		ArrayList<GrandeEvento> mRetorno = new ArrayList<GrandeEvento>();

		for (AvisarGrandeEvento AvisoCorrente : SAT) {

			Tozte eInicio = new Tozte(AvisoCorrente.getSuperarkoInicio(), AvisoCorrente.getHiperarkoInicio(),
					AvisoCorrente.getTronarko());
			Tozte eFim = new Tozte(AvisoCorrente.getSuperarkoFim(), AvisoCorrente.getHiperarkoFim(),
					AvisoCorrente.getTronarko());

			if (eInicio.getTronarko() == eTronarko) {
				mRetorno.add(new GrandeEvento(AvisoCorrente.getNome(), AvisoCorrente.getIntervalo(),
						AvisoCorrente.getInicio(), eInicio, eFim, AvisoCorrente.getEdicao()));
			}

		}

		return mRetorno;
	}

	public ArrayList<String> EventosDoTozte(Tozte TozteC) {
		ArrayList<String> ret = new ArrayList<String>();

		for (AvisarPequenoEvento EventoCorrente : mPequenosEventos) {

			if (EventoCorrente.getSuperarko() == TozteC.getSuperarko()
					&& EventoCorrente.getHiperarko() == TozteC.getHiperarko()) {

				ret.add(EventoCorrente.getNome() + "  ->  [ " + EventoCorrente.getSuperarko() + "/"
						+ EventoCorrente.getHiperarko() + "/" + TozteC.getTronarko() + " ]");

			}

		}

		return ret;
	}

	public ArrayList<String> GrandesEventosDoTozte(Tozte TozteC, ArrayList<AvisarGrandeEvento> lsGrandesEventos) {
		ArrayList<String> ret = new ArrayList<String>();

		for (AvisarGrandeEvento GE : lsGrandesEventos) {

			if (GE.getSuperarkoInicio() == TozteC.getSuperarko() && GE.getHiperarkoInicio() == TozteC.getHiperarko()
					&& GE.getTronarko() == TozteC.getTronarko()) {

				ret.add(GE.getNome() + "  ->  [ " + GE.getSuperarkoInicio() + "/" + GE.getHiperarkoInicio() + "/"
						+ GE.getTronarko() + " a " + GE.getSuperarkoFim() + "/" + GE.getHiperarkoFim() + "/"
						+ GE.getTronarko() + " ] a cada " + GE.getIntervalo() + " desde " + GE.getInicio()
						+ " Edicao : " + GE.getEdicao());

			}

		}

		return ret;
	}

	private ArrayList<AvisarGrandeEvento> GrandesEventos_EntreTronarkos(int MinTronarko, int MaxTronarko) {

		int tamanho = mGrandesEventos.size();
		int indice = 0;

		ArrayList<AvisarGrandeEvento> SAT = new ArrayList<AvisarGrandeEvento>();

		while (indice < tamanho) {
			AvisarGrandeEvento TozteCorrente = mGrandesEventos.get(indice);

			int Edicao = 1;

			int TronarkoInicio = TozteCorrente.getInicio();
			while (TronarkoInicio <= MaxTronarko) {

				if (TronarkoInicio >= MinTronarko && TronarkoInicio <= MaxTronarko) {

					AvisarGrandeEvento SATC = new AvisarGrandeEvento(TozteCorrente.getNome(),
							TozteCorrente.getHiperarkoInicio(), TozteCorrente.getSuperarkoInicio(),
							TozteCorrente.getHiperarkoFim(), TozteCorrente.getSuperarkoFim(),
							TozteCorrente.getIntervalo(), TozteCorrente.getInicio(), TozteCorrente.getAntes(),
							TozteCorrente.getDepois());

					SATC.setTronarko(TronarkoInicio);
					SATC.setEdicao(Edicao);

					SAT.add(SATC);

				}

				TronarkoInicio += TozteCorrente.getIntervalo();
				Edicao += 1;
			}

			if (TronarkoInicio < MaxTronarko) {
				TronarkoInicio = TronarkoInicio + TozteCorrente.getIntervalo();
			}

			indice += 1;
		}

		return SAT;

	}

	public ArrayList<String> EventosDesde(Tozte TozteC, int eSuperarkos) {
		ArrayList<String> ret = new ArrayList<String>();

		ArrayList<AvisarGrandeEvento> GrandesEventosEntreTronarkos = GrandesEventos_EntreTronarkos(TozteC.getTronarko(),
				TozteC.adicionar_Superarko(eSuperarkos).getTronarko());

		for (int i = 0; i < eSuperarkos; i++) {

			Tozte Novo = TozteC.adicionar_Superarko(i);

			for (String eS : EventosDoTozte(Novo)) {
				String NovoString = eS;
				if (!ret.contains(NovoString)) {
					ret.add(NovoString);
				}
			}

			for (String eS : GrandesEventosDoTozte(Novo, GrandesEventosEntreTronarkos)) {
				String NovoString = eS;

				if (!ret.contains(NovoString)) {
					ret.add(NovoString);
				}
			}

		}

		return ret;

	}

	public ArrayList<TozteCor> getLegenda(ArrayList<TozteCor> ToztesComCor) {

		ArrayList<TozteCor> ToztesComCorUnico = new ArrayList<TozteCor>();
		for (TozteCor e : ToztesComCor) {
			boolean enc = false;

			for (TozteCor u : ToztesComCorUnico) {
				if (u.getNome().contentEquals(e.getNome())) {
					enc = true;
					u.adicionar_Tozte(e.getTozte());
			break;
				}
			}

			if (enc == false) {
				e.adicionar_Tozte(e.getTozte());
				ToztesComCorUnico.add(e);
			}
		}
		
		
		int n = ToztesComCorUnico.size();
		TozteCor temp = null;

		for (int i = 0; i < n; i++) {
			for (int j = 1; j < (n - i); j++) {

				if (ToztesComCorUnico.get(j - 1).getOrdem() >= (ToztesComCorUnico.get(j).getOrdem())) {
					temp = ToztesComCorUnico.get(j - 1);
					ToztesComCorUnico.set(j - 1, ToztesComCorUnico.get(j));
					ToztesComCorUnico.set(j, temp);

				}

			}
		}
		
		
		return ToztesComCorUnico;
	}

	public ArrayList<TozteCor> getToztesComCor(int eTronarko) {

		ArrayList<TozteCor> ToztesComCor = new ArrayList<TozteCor>();

		for (PequenoEvento e : ProximosPequenosEventos(eTronarko)) {

			Color eCor = getCor(e.getNome());

			ToztesComCor.add(new TozteCor(e.getNome(), e.getTozte(), eCor));

		}

		for (GrandeEvento e : ProximosGrandesEventos(eTronarko)) {

			boolean remover = false;
			String RemoverEsse = "";

			if (e.getNome().contentEquals("Corrida dos Ventos")) {
				remover = true;
				RemoverEsse = "Festival do Vento";
			}

			if (e.getNome().contentEquals("Cruzada das Águas")) {
				remover = true;
				RemoverEsse = "Festival da Água";
			}

			if (e.getNome().contentEquals("Emboscada da Terra")) {
				remover = true;
				RemoverEsse = "Festival da Terra";
			}

			if (e.getNome().contentEquals("Jornada do Fogo")) {
				remover = true;
				RemoverEsse = "Festival do Fogo";
			}

			if (remover) {

				for (TozteCor u : ToztesComCor) {
					if (u.getNome().contentEquals(RemoverEsse)) {
						ToztesComCor.remove(u);
						break;
					}
				}

			}

			Color eCor = getCor(e.getNome());

			for (Tozte T1 : e.getToztes()) {

				ToztesComCor.add(new TozteCor(e.getNome(), T1.getCopia(), eCor));

			}
		}

		return ToztesComCor;
	}


	public Color getCor(String eNome) {

		Color eCor = Color.BLACK;

		if (eNome.contains("Reciclum")) {
			eCor = Color.GRAY;
		}

		if (eNome.contains("Fogo")) {
			eCor = Color.red;
		}

		if (eNome.contains("Água")) {
			eCor = new Color(92, 80, 200);
		}

		if (eNome.contains("Terra")) {
			eCor = new Color(92, 140, 10);
		}

		if (eNome.contains("Trovão")) {
			eCor = new Color(92, 140, 10);
		}

		
		if (eNome.contains("Vento")) {
			eCor = new Color(92, 140, 208);
		}

		if (eNome.contains("Hazzo")) {
			eCor = new Color(255, 165, 23);
		}

		if (eNome.contains("Colheita")) {
			eCor = new Color(159, 95, 159);
		}

		if (eNome.contains("Trannor")) {
			eCor = new Color(107, 142, 35);
		}

		if (eNome.contains("Izzator")) {
			eCor = new Color(74, 118, 110);
		}

		if (eNome.contains("Labirinto")) {
			eCor = new Color(154, 205, 50);
		}
		
		return eCor;
	}

}
