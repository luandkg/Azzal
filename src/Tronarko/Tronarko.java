package Tronarko;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import Tronarko.Harrempluz.Harrempluz.Harrem;
import Tronarko.Intervalos.Tozte_Intervalo;
import Tronarko.Tronarko.Signos;
import Tronarko.Tronarko.Tozte;

// 		AUTOR : LUAN ALVES FREITAS
// 		DATA  : 21 09 2018
//
//       	ATUALIZACOES
//
//	CRIACAO		  : 21/09/2018 - Desenvolvimento do Sistema Temporal Tronarko
//
//	ORGANIZACAO 1 : 22/09/2018 
//  ORGANIZACAO 2 : 25/09/2018 
//  ORGANIZACAO 3 : 12/10/2018 
//  ORGANIZACAO 4 : 10/03/2019 
//
//
//	ATUALIZACAO 1 : 29/10/2018 - Implementacao Modarko e Periarko
//	ATUALIZACAO 2 : 01/12/2019 - Alteracao de Nomenclatura dos Superarkos 
//  ATUALIZACAO 3 : 29/12/2019 - Metodo de Sincronizacao
//	ATUALIZACAO 4 : 24/03/2020 - Organizacao dos Satelites
//	ATUALIZACAO 5 : 25/03/2020 - Aprimoramento do Metodo de Eventos e Criacao das Classes de Intervalo
// 	ATUALIZACAO 6 : 26/03/2020 - Metodo de Ordenacao , Fusos Horarios e Enumeradores : Hiperarkos, Superarkos, Signos, Periakos e Modarkos.
//	ATUALIZACAO 7 : 26/03/2020 - TozteCor e Interface UI
//	ATUALIZACAO 8 : 30/03/2020 - Avisos

public class Tronarko {

	private final String DATA_INICIO = "21/09/2018";
	private final int TRONARKO_INICIO = 7000;

	public String getAgora() {
		return getTronAgora().getTexto();
	}

	public Tron getTronAgora() {

		Hazde h = getHazde();
		Tozte t = getTozte();

		Tron ret = new Tron(h.getArco(), h.getItta(), h.getUzzon(), t.getSuperarko(), t.getHiperarko(),
				t.getTronarko());

		return ret;
	}

	public final String toString() {
		return getAgora();
	}

	public Tozte getTozte_Aleatorio() {
		Random rd = new Random();

		return new Tozte(rd.nextInt(50) + 1, rd.nextInt(10) + 1, rd.nextInt(8000));

	}
	
	public long getLong(Tozte T,Hazde H) {
		
	long tta = 10*100*100;
		
		long total =  (T.getSuperarkosTotal() * tta) +H.getTotalEttons() ;
		
		return total;
	}
	
	public String setLong(long total) {
		
		long tta = 10*100*100;

		long vcc = total;
		long supers = 0;
		
		while(vcc>=tta) {
			vcc-=tta;
			supers+=1;
		}
		
		int vt=0;
		int vh=0;
		int vs=0;
		
		while(supers>=500) {
			vt+=1;
			supers-=500;
		}
		while(supers>=50) {
			vh+=1;
			supers-=50;
		}
		
		vh+=1;
		
		
		int va1=0;
		int va2 = 0;
		
		while(vcc>=10000) {
			va1+=1;
			vcc-=10000;
		}
		
		while(vcc>=100) {
			va2+=1;
			vcc-=100;
		}
		
		va1+=1;
		
		
		String v1 = supers + "/" + vh + "/" + vt;
		
		String v2 = va1 + ":" + va2 + ":" + vcc;
		
		return v1 + " " + v2;
	}

	
	
	
	public Tozte getTozte_AleatorioDoTronarko(int eTronarko) {
		Random rd = new Random();

		return new Tozte(rd.nextInt(50) + 1, rd.nextInt(10) + 1, eTronarko);

	}

	public Tozte getTozte_AleatorioDoHiperarko(int eHiperarko, int eTronarko) {
		Random rd = new Random();

		return new Tozte(rd.nextInt(50) + 1, eHiperarko, eTronarko);

	}

	public Tozte getTozte() {

		Calendar c = Calendar.getInstance();

		int dia = c.getTime().getDay();
		int mes = c.getTime().getMonth();
		int ano = c.getTime().getYear();
		//

		dia = c.get(Calendar.DAY_OF_MONTH);
		mes = c.get(Calendar.MONTH) + 1;
		ano = c.get(Calendar.YEAR);

		return getData(dia + "/" + mes + "/" + ano);

	}

	public Tozte getData(String eData) {

		// System.out.printf("\nHCC : %s",eData);

		long Diferenca = 0;

		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		df.setLenient(false);
		try {
			Date DTI = df.parse(DATA_INICIO);
			Date DTE = df.parse(eData);
			Diferenca = (DTE.getTime() - DTI.getTime()) / 86400000L;

		} catch (java.text.ParseException evt) {
		}

		int iTronarko = TRONARKO_INICIO;
		int iHiperarko = 1;
		int iSuperarko = 1;

		if (Diferenca >= 0) {

			while (Diferenca >= 500) {
				Diferenca -= 500;
				iTronarko += 1;
			}

			iHiperarko = 1;
			while (Diferenca >= 50) {
				Diferenca -= 50;
				iHiperarko += 1;
			}
			iSuperarko = 1 + (int) Diferenca;

		} else if (Diferenca < 0) {

			iTronarko -= 1;
			while (Diferenca <= -500) {
				Diferenca += 500;
				iTronarko -= 1;
			}
			iHiperarko = 10;
			while (Diferenca <= -50) {
				Diferenca += 50;
				iHiperarko -= 1;
			}
			iSuperarko = 50 + 1 + (int) Diferenca;
		}

		return new Tozte(iSuperarko, iHiperarko, iTronarko);
	}

	public Hazde getHora(int eHora, int eMinuto, int eSegundo) {

		int iArco = 0;
		int iIttas = 0;
		int iUzzons = 0;

		int eMilissegundo = 0;

		long Tudo = eMilissegundo + (eSegundo * 1000) + (eMinuto * 1000 * 60) + (eHora * 1000 * 60 * 60);

		double Taxa1 = (24 * 1000 * 60 * 60);
		double Taxa2 = (10 * 1000 * 100 * 100);

		double Taxador = Taxa1 / Taxa2;

		double Entaxa = Tudo / Taxador;
		long IEntaxa = (long) Entaxa;

		iUzzons = (int) (IEntaxa / 1000);

		while (iUzzons >= 100) {
			iUzzons -= 100;
			iIttas += 1;
		}

		while (iIttas >= 100) {
			iIttas -= 100;
			iArco += 1;
		}

		//iArco += 1;

		return new Hazde(iArco, iIttas, iUzzons);
	}

	public Hazde getHazde() {

		Calendar c = Calendar.getInstance();

		int eSegundo = c.getTime().getSeconds();
		int eMinuto = c.getTime().getMinutes();
		int eHora = c.getTime().getHours();

		return getHora(eHora, eMinuto, eSegundo);

	}

	public int getSincronizanteHazde(Hazde Local, Hazde Origem) {
		return (Origem.getTotalEttons() - Local.getTotalEttons());
	}

	public long getSincronizanteTozte(Tozte Local, Tozte Origem) {
		return (Origem.getSuperarkosTotal() - Local.getSuperarkosTotal());
	}
	
	

	

	public static class Hazde {

		private int mArkos;
		private int mIttas;
		private int mUzzons;

		public int getArco() {
			return this.mArkos;
		}

		public int getItta() {
			return this.mIttas;
		}

		public int getUzzon() {
			return this.mUzzons;
		}

		public Hazde(int _arco, int _itta, int _uzzon) {
			this.mArkos = _arco;
			this.mIttas = _itta;
			this.mUzzons = _uzzon;
		}

		public Hazde getCopia() {
			return new Hazde(mArkos,mIttas,mUzzons);
		}
		
		public int getTotalEttons() {
			int ret = 0;

			ret += (this.getArco() - 1) * 100 * 100;
			ret += this.getItta() * 100;
			ret += this.getUzzon();

			return ret;
		}

		public String getTexto() {
			String texto = "";

			texto = this.getArco() + ":" + this.getItta() + ":" + this.getUzzon();

			return texto;
		}

		public String getTextoSemEttos() {
			String texto = "";

			texto = this.getArco() + ":" + this.getItta() + ":" + this.getUzzon();

			return texto;
		}

		public final String toString() {
			return getTexto();
		}

		public Periarkos getPeriarko() {
			Periarkos ret = null;

			if (getArco() >= 1 && getArco() <= 2) {
				ret = Periarkos.UD;
			}
			if (getArco() >= 3 && getArco() <= 5) {
				ret = Periarkos.AD;
			}
			if (getArco() >= 6 && getArco() <= 8) {
				ret = Periarkos.ED;
			}
			if (getArco() >= 9 && getArco() <= 10) {
				ret = Periarkos.OD;
			}

			return ret;
		}

		public String getPeriarko_Valor() {
			return getPeriarko().toString();
		}

		public String PeriarkoCompleto() {
			return String.valueOf(this.getArco()) + "º " + String.valueOf(this.getItta()) + " " + getPeriarko_Valor();
		}

		public Modarkos getModarko() {
			Modarkos ret = null;

			if (getArco() >= 1 && getArco() <= 2) {
				ret = Modarkos.OZZ;
			}
			if (getArco() >= 3 && getArco() <= 5) {
				ret = Modarkos.AZZ;
			}
			if (getArco() >= 6 && getArco() <= 8) {
				ret = Modarkos.AZZ;
			}
			if (getArco() >= 9 && getArco() <= 10) {
				ret = Modarkos.OZZ;
			}

			return ret;
		}

		public String getModarko_Valor() {
			return getModarko().toString();
		}

		public String ModarkoCompleto() {
			return String.valueOf(this.getArco()) + "º " + getModarko_Valor();
		}

		public Hazde adicionar_Arco(int a) {
			return modificar_Arco(this, a);
		}

		public Hazde adicionar_Itta(int i) {
			return modificar_Itta(this, i);
		}

		public Hazde adicionar_Uzzon(int u) {
			return modificar_Uzzon(this, u);
		}

		public Hazde modificar_Arco(Hazde sTron, int a) {

			int narco = sTron.getArco() + a;

			while (narco > 10) {
				narco -= 10;
			}

			while (narco <= 0) {
				narco += 10;
			}

			Hazde ret = new Hazde(narco, sTron.getItta(), sTron.getUzzon());

			return ret;

		}

		public Hazde modificar_Itta(Hazde sTron, int i) {

			int nitta = sTron.getItta() + i;
			int narco = 0;

			while (nitta >= 100) {
				nitta -= 100;
				narco += 1;
			}

			while (nitta < 0) {
				nitta += 100;
				narco -= 1;
			}

			Hazde ret = new Hazde(sTron.getArco(), nitta, sTron.getUzzon());

			return modificar_Arco(ret, narco);

		}

		public Hazde modificar_Uzzon(Hazde sTron, int u) {

			int nuzzon = sTron.getUzzon() + u;
			int nitta = 0;

			while (nuzzon >= 100) {
				nuzzon -= 100;
				nitta += 1;
			}

			while (nuzzon < 0) {
				nuzzon += 100;
				nitta -= 1;
			}

			Hazde ret = new Hazde(sTron.getArco(), sTron.getItta(), nuzzon);

			return modificar_Itta(ret, nitta);

		}

		// INTERNALIZAR METODOS

		public void internalizar_Arco(int a) {

			int narco = this.getArco() + a;

			while (narco > 10) {
				narco -= 10;
			}

			while (narco <= 0) {
				narco += 10;
			}

			this.mArkos = narco;

		}

		public void internalizar_Itta(int i) {

			int nitta = this.getItta() + i;
			int narco = 0;

			while (nitta >= 100) {
				nitta -= 100;
				narco += 1;
			}

			while (nitta < 0) {
				nitta += 100;
				narco -= 1;
			}

			this.mIttas = nitta;

			Hazde ret = modificar_Arco(this, narco);

			this.mArkos = ret.getArco();

			this.mUzzons = ret.getUzzon();
			this.mIttas = ret.getItta();

		}

		public void internalizar_Uzzon(int u) {

			int nuzzon = this.getUzzon() + u;
			int nitta = 0;

			while (nuzzon >= 100) {
				nuzzon -= 100;
				nitta += 1;
			}

			while (nuzzon < 0) {
				nuzzon += 100;
				nitta -= 1;
			}

			this.mUzzons = nuzzon;

			Hazde ret = modificar_Itta(this, nitta);

			this.mArkos = ret.getArco();

			this.mIttas = ret.getItta();

		}

		// COMPARADORES

		public int Compare(Hazde Outro) {
			int resposta = 0;
			
			if (this.getTotalEttons() == Outro.getTotalEttons()) {
				resposta=0;
			}
			if (this.getTotalEttons() < Outro.getTotalEttons()) {
				resposta = -1;
			}
			if (this.getTotalEttons() > Outro.getTotalEttons()) {
				resposta = +1;
			}
			
			return resposta;

		}
		
		public boolean MaiorQue(Hazde Outro) {
			boolean resposta = false;
			if (this.getTotalEttons() > Outro.getTotalEttons()) {
				resposta = true;
			}

			return resposta;
		}

		public boolean MenorrQue(Hazde Outro) {
			boolean resposta = false;
			if (this.getTotalEttons() < Outro.getTotalEttons()) {
				resposta = true;
			}

			return resposta;
		}

		public boolean Igual(Hazde Outro) {
			boolean resposta = false;
			if (this.getTotalEttons() == Outro.getTotalEttons()) {
				resposta = true;
			}

			return resposta;
		}

		public boolean Diferente(Hazde Outro) {
			boolean resposta = false;
			if (this.getTotalEttons() != Outro.getTotalEttons()) {
				resposta = true;
			}

			return resposta;
		}

		public boolean MaiorIgualQue(Hazde Outro) {
			boolean resposta = false;
			if (this.getTotalEttons() >= Outro.getTotalEttons()) {
				resposta = true;
			}

			return resposta;
		}

		public boolean MenorIgualQue(Hazde Outro) {
			boolean resposta = false;
			if (this.getTotalEttons() <= Outro.getTotalEttons()) {
				resposta = true;
			}

			return resposta;
		}

	}

	public static class Tozte {

		private int mSuperarkos;
		private int mHiperarkos;
		private int mTronarkos;

		public long getSuperarkosTotal() {

			long P1 = (long) (this.getSuperarko());
			long P2 = ((((long) (this.getHiperarko()) - 1) * 50));
			long P3 = ((long) (this.getTronarko()) * 500);

			return P1 + P2 + P3;

		}

		public int getSuperarko() {
			return this.mSuperarkos;
		}

		public int getHiperarko() {
			return this.mHiperarkos;
		}

		public int getTronarko() {
			return this.mTronarkos;
		}
		
		public Tozte getCopia() {
			return new Tozte(mSuperarkos,mHiperarkos,mTronarkos);
		}

		public Tozte(int _superarko, int _hiperarko, int _tronarko) {
			this.mSuperarkos = _superarko;
			this.mHiperarkos = _hiperarko;
			this.mTronarkos = _tronarko;
		}

		public String getTexto() {
			String texto = "";

			String p1 = String.valueOf(this.getSuperarko());
			if (p1.length() == 1) {
				p1 = "0" + p1;
			}

			String p2 = String.valueOf(this.getHiperarko());
			if (p2.length() == 1) {
				p2 = "0" + p2;
			}

			texto = p1 + "/" + p2 + "/" + this.getTronarko();

			return texto;
		}

		public final String toString() {
			return getTexto();
		}

		public int SuperarkoDoTronarko() {
			return ((getHiperarko() - 1) * 50) + getSuperarko();
		}

		public int getMegarkoDoTronarko() {
			int mega = 1;
			int ns = this.SuperarkoDoTronarko();

			while (ns > 10) {
				mega += 1;
				ns -= 10;
			}

			return mega;
		}

		public int getMegarko() {
			int mega = 1;
			int ns = this.mSuperarkos;

			while (ns > 10) {
				mega += 1;
				ns -= 10;
			}

			return mega;
		}

		// SIZARKO

		public int getHizarko_Valor() {
			int ret = 1;
			int sdt = SuperarkoDoTronarko();

			while (sdt > 125) {
				ret += 1;
				sdt -= 125;
			}

			return ret;
		}

		public int Hizarko_Duracao() {
			int sdt = SuperarkoDoTronarko();

			while (sdt > 125) {
				sdt -= 125;
			}

			return sdt;
		}

		public Tozte Hizarko_Inicio() {
			switch (getHizarko_Valor()) {

			case 1:
				return this.Hizarko_InicioH1();
			case 2:
				return this.Hizarko_InicioH2();

			case 3:
				return this.Hizarko_InicioH3();

			case 4:
				return this.Hizarko_InicioH4();

			}
			return null;
		}

		public Tozte Hizarko_Fim() {
			switch (getHizarko_Valor()) {

			case 1:
				return this.Hizarko_FimH1();
			case 2:
				return this.Hizarko_FimH2();

			case 3:
				return this.Hizarko_FimH3();

			case 4:
				return this.Hizarko_FimH4();

			}
			return null;
		}

		public Tozte Hizarko_InicioH1() {

			Tozte ret = new Tozte(1, 1, this.mTronarkos);

			return ret;
		}

		public Tozte Hizarko_InicioH2() {
			return Hizarko_InicioH1().adicionar_Superarko(125);
		}

		public Tozte Hizarko_InicioH3() {
			return Hizarko_InicioH1().adicionar_Superarko(250);
		}

		public Tozte Hizarko_InicioH4() {
			return Hizarko_InicioH1().adicionar_Superarko(375);
		}

		public Tozte Hizarko_FimH1() {
			return Hizarko_InicioH1().adicionar_Superarko(124);
		}

		public Tozte Hizarko_FimH2() {
			return Hizarko_InicioH2().adicionar_Superarko(124);
		}

		public Tozte Hizarko_FimH3() {
			return Hizarko_InicioH3().adicionar_Superarko(124);
		}

		public Tozte Hizarko_FimH4() {
			return Hizarko_InicioH4().adicionar_Superarko(124);
		}

		public int Hizarko_Acabar() {

			return 126 - Hizarko_Duracao();

		}

		public Hizarkos getHizarko() {
			Hizarkos ret = null;

			switch (getHizarko_Valor()) {
			case 1:
				ret = Hizarkos.HITTARIUM;
				break;
			case 2:
				ret = Hizarkos.DEGGOVIUM;
				break;
			case 3:
				ret = Hizarkos.NUZTIUM;
				break;
			case 4:
				ret = Hizarkos.HARBARIUM;
				break;

			}

			return ret;
		}

		public String Hizarko_nome() {
			return getHizarko().toString();
		}

		// SIGNOS
		public int getSigno_Valor() {
			int ret = 1;
			int sdt = SuperarkoDoTronarko();
			int faixa = 50;

			while (sdt > faixa) {
				ret += 1;
				sdt -= faixa;
			}

			return ret;
		}

		public String Signo_nome() {
			return getSigno().toString();
		}

		public Tozte_Intervalo getSignoIntervalo() {

			return new Tozte_Intervalo(Signo_nome(), new Tozte(1, this.getHiperarko(), this.getTronarko()),
					new Tozte(50, this.getHiperarko(), this.getTronarko()));

		}

		public Signos getSigno() {
			Signos ret = null;

			switch (getSigno_Valor()) {
			case 1:
				ret = Signos.TIGRE;
				break;
			case 2:
				ret = Signos.RAPOSA;
				break;
			case 3:
				ret = Signos.LEOPARDO;
				break;
			case 4:
				ret = Signos.LEAO;
				break;
			case 5:
				ret = Signos.TOURO;
				break;
			case 6:
				ret = Signos.LOBO;
				break;
			case 7:
				ret = Signos.GATO;
				break;
			case 8:
				ret = Signos.CARPA;
				break;
			case 9:
				ret = Signos.GAVIAO;
				break;
			case 10:
				ret = Signos.SERPENTE;
				break;

			}

			return ret;
		}

		public String Hiperarko_nome() {
			return getHiperarko_Status().toString();
		}

		public Hiperarkos getHiperarko_Status() {
			Hiperarkos ret = null;

			switch (getHiperarko()) {
			case 1:
				ret = Hiperarkos.ATLAS;
				break;
			case 2:
				ret = Hiperarkos.TERRON;
				break;
			case 3:
				ret = Hiperarkos.IGGROX;
				break;
			case 4:
				ret = Hiperarkos.OMPLO;
				break;
			case 5:
				ret = Hiperarkos.UXMO;
				break;
			case 6:
				ret = Hiperarkos.SALLU;
				break;
			case 7:
				ret = Hiperarkos.ITHUR;
				break;
			case 8:
				ret = Hiperarkos.GRAM;
				break;
			case 9:
				ret = Hiperarkos.HETTIZ;
				break;
			case 10:
				ret = Hiperarkos.ELLUN;
				break;
			}

			return ret;
		}

		public String Hiperarko_Cor() {
			String ret = "";

			switch (getHiperarko()) {
			case 1:
				ret = "Branco";
				break;
			case 2:
				ret = "Amarelo";
				break;
			case 3:
				ret = "Vermelho";
				break;
			case 4:
				ret = "Azul";
				break;
			case 5:
				ret = "Laraja";
				break;
			case 6:
				ret = "Rosa";
				break;
			case 7:
				ret = "Verde";
				break;
			case 8:
				ret = "Violeta";
				break;
			case 9:
				ret = "Preto";
				break;
			case 10:
				ret = "Cinza";
				break;

			}

			return ret;
		}

		public int SuperarkoEmMegarko() {
			int ret = this.getSuperarko();

			while (ret > 10) {
				ret -= 10;
			}

			return ret;
		}

		public String Superarko_info() {
			return Superarko_nome() + ", " + mSuperarkos + " de " + Hiperarko_nome();
		}

		public String Superarko_nome() {
			return getSuperarko_Status().toString();
		}

		public String Superarko_capital() {
			return getSuperarko_Status().getCapital();
		}
		
		public Superarkos getSuperarko_Status() {
			Superarkos ret = null;

			switch (SuperarkoEmMegarko()) {
			case 1:
				ret = Superarkos.ALFA;
				break;
			case 2:
				ret = Superarkos.BETA;
				break;
			case 3:
				ret = Superarkos.GAMA;
				break;
			case 4:
				ret = Superarkos.DELTA;
				break;
			case 5:
				ret = Superarkos.EPSILON;
				break;
			case 6:
				ret = Superarkos.IOTA;
				break;
			case 7:
				ret = Superarkos.KAPA;
				break;
			case 8:
				ret = Superarkos.ZETA;
				break;
			case 9:
				ret = Superarkos.SIGMA;
				break;
			case 10:
				ret = Superarkos.OMEGA;
				break;

			}

			return ret;
		}

		public String Superarko_Simbolo() {
			String ret = "";

			switch (SuperarkoEmMegarko()) {
			case 1:
				ret = "Circulo";
				break;
			case 2:
				ret = "Linha";
				break;
			case 3:
				ret = "Triangulo";
				break;
			case 4:
				ret = "Quadrado";
				break;
			case 5:
				ret = "Pentagono";
				break;
			case 6:
				ret = "Hexagono";
				break;
			case 7:
				ret = "Pentagono e Linha";
				break;
			case 8:
				ret = "Retangulo";
				break;
			case 9:
				ret = "3 Triangulos";
				break;
			case 10:
				ret = "Circulo e Linha Central";
				break;

			}

			return ret;
		}

		// DIVISAO DO TRONARKO

		public int getBimestre() {
			int b = getHiperarko();
			int r = 0;

			if (b == 1 || b == 2) {
				r = 1;
			}
			if (b == 3 || b == 4) {
				r = 2;
			}
			if (b == 5 || b == 6) {
				r = 3;
			}
			if (b == 7 || b == 8) {
				r = 4;
			}
			if (b == 9 || b == 10) {
				r = 5;
			}

			return r;

		}

		public int getSemestre() {
			int s = getHiperarko();
			int r = 0;

			if (s >= 1 && s <= 5) {
				r = 1;
			}
			if (s >= 6 && s <= 10) {
				r = 2;
			}

			return r;

		}

		public int getDecada() {

			return this.mTronarkos / 10;

		}

		public int getSeculo() {

			return this.mTronarkos / 100;

		}

		public int getMilenio() {

			return this.mTronarkos / 1000;

		}

		// ADICIONAR METODOS

		public Tozte adicionar_Superarko(int s) {
			return modificar_Superarko(this, s);
		}

		public Tozte adicionar_Hiperarko(int h) {
			return modificar_Hiperarko(this, h);
		}

		public Tozte adicionar_Tronarko(int t) {
			return modificar_Tronarko(this, t);
		}

		// ESPECIAIS

		public Tozte proximo_Superarko() {
			return modificar_Superarko(this, 1);
		}

		public Tozte anterior_Superarko() {
			return modificar_Superarko(this, -1);
		}

		// MODIFICAR METODOS

		public Tozte modificar_Superarko(Tozte sTron, int s) {

			int nsuperarko = sTron.getSuperarko() + s;
			int nhiperarko = 0;

			while (nsuperarko > 50) {
				nsuperarko -= 50;
				nhiperarko += 1;
			}

			while (nsuperarko <= 0) {
				nsuperarko += 50;
				nhiperarko -= 1;
			}

			Tozte ret = new Tozte(nsuperarko, sTron.getHiperarko(), sTron.getTronarko());

			if ((nhiperarko == 0) == false) {
				return modificar_Hiperarko(ret, nhiperarko);
			} else {
				return ret;
			}

		}

		public Tozte modificar_Hiperarko(Tozte sTron, int h) {

			int nhiperarko = sTron.getHiperarko() + h;
			int ntronarko = 0;

			while (nhiperarko > 10) {
				nhiperarko -= 10;
				ntronarko += 1;
			}

			while (nhiperarko <= 0) {
				nhiperarko += 10;
				ntronarko -= 1;
			}

			Tozte ret = new Tozte(sTron.getSuperarko(), nhiperarko, sTron.getTronarko());

			if ((ntronarko == 0) == false) {
				return modificar_Tronarko(ret, ntronarko);
			} else {
				return ret;
			}

		}

		public Tozte modificar_Tronarko(Tozte sTron, int t) {

			int ntronarko = sTron.getTronarko() + t;

			Tozte ret = new Tozte(sTron.getSuperarko(), sTron.getHiperarko(), ntronarko);

			return ret;

		}

		// INTERNALIZAR METODOS

		public void internalizar_Superarko(int s) {

			int nsuperarko = this.getSuperarko() + s;
			int nhiperarko = 0;

			while (nsuperarko > 50) {
				nsuperarko -= 50;
				nhiperarko += 1;
			}

			while (nsuperarko <= 0) {
				nsuperarko += 50;
				nhiperarko -= 1;
			}

			if ((nhiperarko == 0)) {
				this.mSuperarkos = nsuperarko;
			} else {
				Tozte r = modificar_Hiperarko(this, nhiperarko);

				this.mSuperarkos = nsuperarko;
				this.mHiperarkos = r.getHiperarko();
				this.mTronarkos = r.getTronarko();
			}

		}

		public void internalizar_Hiperarko(int h) {

			int nhiperarko = this.getHiperarko() + h;
			int ntronarko = 0;

			while (nhiperarko > 10) {
				nhiperarko -= 10;
				ntronarko += 1;
			}

			while (nhiperarko <= 0) {
				nhiperarko += 10;
				ntronarko -= 1;
			}

			if ((ntronarko == 0)) {
				this.mHiperarkos = nhiperarko;
			} else {
				this.mHiperarkos = nhiperarko;
				this.mTronarkos = modificar_Tronarko(this, ntronarko).getTronarko();
			}

		}

		public void internalizar_Tronarko(int t) {

			int ntronarko = this.getTronarko() + t;

			this.mTronarkos = ntronarko;
		}

		// COMPARADORES

		public int Compare(Tozte Outro) {
			int resposta = 0;
			
			if (this.getSuperarkosTotal() == Outro.getSuperarkosTotal()) {
				resposta=0;
			}
			if (this.getSuperarkosTotal() < Outro.getSuperarkosTotal()) {
				resposta = -1;
			}
			if (this.getSuperarkosTotal() > Outro.getSuperarkosTotal()) {
				resposta = +1;
			}
			
			return resposta;

		}
		
		public boolean MaiorQue(Tozte Outro) {
			boolean resposta = false;
			if (this.getSuperarkosTotal() > Outro.getSuperarkosTotal()) {
				resposta = true;
			}

			return resposta;
		}

		public boolean MenorrQue(Tozte Outro) {
			boolean resposta = false;
			if (this.getSuperarkosTotal() < Outro.getSuperarkosTotal()) {
				resposta = true;
			}

			return resposta;
		}

		public boolean Igual(Tozte Outro) {
			boolean resposta = false;
			if (this.getSuperarkosTotal() == Outro.getSuperarkosTotal()) {
				resposta = true;
			}

			return resposta;
		}

		public boolean Diferente(Tozte Outro) {
			boolean resposta = false;
			if (this.getSuperarkosTotal() != Outro.getSuperarkosTotal()) {
				resposta = true;
			}

			return resposta;
		}

		public boolean MaiorIgualQue(Tozte Outro) {
			boolean resposta = false;
			if (this.getSuperarkosTotal() >= Outro.getSuperarkosTotal()) {
				resposta = true;
			}

			return resposta;
		}

		public boolean MenorIgualQue(Tozte Outro) {
			boolean resposta = false;
			if (this.getSuperarkosTotal() <= Outro.getSuperarkosTotal()) {
				resposta = true;
			}

			return resposta;
		}

		public ArrayList<Tozte_Intervalo> getEpocas() {
			ArrayList<Tozte_Intervalo> Epocas = new ArrayList<Tozte_Intervalo>();

			Epocas.add((new Tozte_Intervalo("CRICAO", new Tozte(1, 1, 1), new Tozte(50, 10, 325))));
			Epocas.add((new Tozte_Intervalo("DEUSES", new Tozte(1, 1, 326), new Tozte(50, 10, 5299))));
			Epocas.add((new Tozte_Intervalo("REINOS", new Tozte(1, 1, 5300), new Tozte(50, 10, 6199))));
			Epocas.add((new Tozte_Intervalo("IMPERIOS", new Tozte(1, 1, 6200), new Tozte(50, 10, 10000))));

			return Epocas;
		}

		public Epocas getEpoca() {
			Epocas ret = null;

			if (getTronarko() >= 1 && getTronarko() <= 325) {
				ret = Epocas.CRIACAO;
			}
			if (getTronarko() >= 326 && getTronarko() <= 5299) {
				ret = Epocas.DEUSES;
			}
			if (getTronarko() >= 5300 && getTronarko() <= 6199) {
				ret = Epocas.REINOS;
			}
			if (getTronarko() >= 6200) {
				ret = Epocas.IMPERIOS;
			}

			return ret;
		}

		public int getTronarkosDaEpoca() {
			int ret = 0;

			switch (this.getEpoca()) {

			case CRIACAO:
				ret = this.getTronarko() - 0;
				break;
			case DEUSES:
				ret = this.getTronarko() - 326;
				break;
			case REINOS:
				ret = this.getTronarko() - 5300;
				break;
			case IMPERIOS:
				ret = this.getTronarko() - 6200;
				break;
			default:

				break;
			}

			return ret;
		}

	}

	public static class Tron {

		private Hazde mHazdeC;
		private Tozte mTozteC;

		public Tron(int _arco, int _itta, int _uzzon, int _superarko, int _hiperarko, int _tronarko) {

			mHazdeC = new Hazde(_arco, _itta, _uzzon);
			mTozteC = new Tozte(_superarko, _hiperarko, _tronarko);

		}

		public Tron getCopia() {
			return new Tron(mHazdeC.getArco(),mHazdeC.getItta(),mHazdeC.getUzzon(),mTozteC.getSuperarko(),mTozteC.getHiperarko(),mTozteC.getTronarko());
		}
		
		public Hazde getHazde() {
			return mHazdeC;
		}

		public Tozte getTozte() {
			return mTozteC;
		}

		public String getTexto() {
			return mHazdeC.getTexto() + " " + mTozteC.getTexto();
		}

		public String toString() {
			return getTexto();
		}

		public long getTotal() {

			long Total = 0;

			Total = (long) (mTozteC.getSuperarkosTotal()) * (10 * 100 * 100);

			Total += (long) mHazdeC.getTotalEttons();

			return Total;

		}

		// INTERNALIZAR METODOS

		public void internalizar_Superarko(int s) {
			mTozteC.internalizar_Superarko(s);
		}

		public void internalizar_Hiperarko(int h) {
			mTozteC.internalizar_Hiperarko(h);
		}

		public void internalizar_Tronarko(int t) {
			mTozteC.internalizar_Hiperarko(t);
		}

		public void internalizar_Arco(int a) {

			int narco = mHazdeC.getArco() + a;
			int nsuperarko = 0;

			while (narco > 10) {
				narco -= 10;
				nsuperarko += 1;
			}

			while (narco <= 0) {
				narco += 10;
				nsuperarko -= 1;
			}

			mHazdeC = new Hazde(narco, mHazdeC.getItta(), mHazdeC.getUzzon());

			mTozteC.internalizar_Superarko(nsuperarko);

		}

		public void internalizar_Itta(int i) {

			int nitta = this.getHazde().getItta() + i;
			int narco = 0;

			while (nitta >= 100) {
				nitta -= 100;
				narco += 1;
			}

			while (nitta < 0) {
				nitta += 100;
				narco -= 1;
			}

			mHazdeC = new Hazde(mHazdeC.getArco(), nitta, mHazdeC.getUzzon());

			internalizar_Arco(narco);

		}

		public void internalizar_Uzzon(int u) {

			int nuzzon = mHazdeC.getUzzon() + u;
			int nitta = 0;

			while (nuzzon >= 100) {
				nuzzon -= 100;
				nitta += 1;
			}

			while (nuzzon < 0) {
				nuzzon += 100;
				nitta -= 1;
			}

			mHazdeC = new Hazde(mHazdeC.getArco(), mHazdeC.getItta(), nuzzon);

			internalizar_Itta(nitta);

		}

		// MODIFICADORES

		public Tron modificar_Superarko(Tron sTron, int s) {

			int nsuperarko = sTron.getTozte().getSuperarko() + s;
			int nhiperarko = 0;

			while (nsuperarko > 50) {
				nsuperarko -= 50;
				nhiperarko += 1;
			}

			while (nsuperarko <= 0) {
				nsuperarko += 50;
				nhiperarko -= 1;
			}

			Tron ret = new Tron(sTron.getHazde().getArco(), sTron.getHazde().getItta(), sTron.getHazde().getUzzon(),
					nsuperarko, sTron.getTozte().getHiperarko(), sTron.getTozte().getTronarko());

			if ((nhiperarko == 0) == false) {
				return modificar_Hiperarko(ret, nhiperarko);
			} else {
				return ret;
			}

		}

		public Tron modificar_Hiperarko(Tron sTron, int h) {

			int nhiperarko = sTron.getTozte().getHiperarko() + h;
			int ntronarko = 0;

			while (nhiperarko > 10) {
				nhiperarko -= 10;
				ntronarko += 1;
			}

			while (nhiperarko <= 0) {
				nhiperarko += 10;
				ntronarko -= 1;
			}

			Tron ret = new Tron(sTron.getHazde().getArco(), sTron.getHazde().getItta(), sTron.getHazde().getUzzon(),
					sTron.getTozte().getSuperarko(), nhiperarko, sTron.getTozte().getTronarko());

			if ((ntronarko == 0) == false) {
				return modificar_Tronarko(ret, ntronarko);
			} else {
				return ret;
			}

		}

		public Tron modificar_Tronarko(Tron sTron, int t) {

			int ntronarko = sTron.getTozte().getTronarko() + t;

			Tron ret = new Tron(sTron.getHazde().getArco(), sTron.getHazde().getItta(), sTron.getHazde().getUzzon(),
					sTron.getTozte().getSuperarko(), sTron.getTozte().getHiperarko(), ntronarko);

			return ret;

		}

		public Tron modificar_Arco(int a) {

			Tron ret = new Tron(this.getHazde().getArco(), this.getHazde().getItta(), this.getHazde().getUzzon(),
					this.getTozte().getSuperarko(), this.getTozte().getHiperarko(), this.getTozte().getTronarko());

			ret.internalizar_Arco(a);

			return ret;

		}

		public Tron modificar_Itta(int i) {

			Tron ret = new Tron(this.getHazde().getArco(), this.getHazde().getItta(), this.getHazde().getUzzon(),
					this.getTozte().getSuperarko(), this.getTozte().getHiperarko(), this.getTozte().getTronarko());

			ret.internalizar_Itta(i);

			return ret;

		}

		public Tron modificar_Uzzon(int u) {

			Tron ret = new Tron(this.getHazde().getArco(), this.getHazde().getItta(), this.getHazde().getUzzon(),
					this.getTozte().getSuperarko(), this.getTozte().getHiperarko(), this.getTozte().getTronarko());

			ret.internalizar_Uzzon(u);

			return ret;

		}

		// ESPECIAIS

		public Tron proximo_Superarko() {
			return modificar_Superarko(this, 1);
		}

		public Tron anterior_Superarko() {
			return modificar_Superarko(this, -1);
		}

		// COMPARADORES

		public int Compare(Tron Outro) {
			int resposta = 0;
			
			if (this.getTotal() == Outro.getTotal()) {
				resposta=0;
			}
			if (this.getTotal() < Outro.getTotal()) {
				resposta = -1;
			}
			if (this.getTotal() > Outro.getTotal()) {
				resposta = +1;
			}
			
			return resposta;

		}
		
		
		public boolean MaiorQue(Tron Outro) {
			boolean resposta = false;
			if (this.getTotal() > Outro.getTotal()) {
				resposta = true;
			}

			return resposta;
		}

		public boolean MenorrQue(Tron Outro) {
			boolean resposta = false;
			if (this.getTotal() < Outro.getTotal()) {
				resposta = true;
			}

			return resposta;
		}

		public boolean Igual(Tron Outro) {
			boolean resposta = false;
			if (this.getTotal() == Outro.getTotal()) {
				resposta = true;
			}

			return resposta;
		}

		public boolean Diferente(Tron Outro) {
			boolean resposta = false;
			if (this.getTotal() != Outro.getTotal()) {
				resposta = true;
			}

			return resposta;
		}

		public boolean MaiorIgualQue(Tron Outro) {
			boolean resposta = false;
			if (this.getTotal() >= Outro.getTotal()) {
				resposta = true;
			}

			return resposta;
		}

		public boolean MenorIgualQue(Tron Outro) {
			boolean resposta = false;
			if (this.getTotal() <= Outro.getTotal()) {
				resposta = true;
			}

			return resposta;
		}
	}

	public static enum Hiperarkos {

		ATLAS(1), TERRON(2), IGGROX(3), OMPLO(4), UXMO(5), SALLU(6), ITHUR(7), GRAM(8), HETTIZ(9), ELLUN(10);

		private int mValor;

		Hiperarkos(int eValor) {
			mValor = eValor;
		}

		public int getValor() {
			return mValor;
		}
		
		public static Hiperarkos get(int eValor) {
			Hiperarkos ret = null;

			switch (eValor) {
			case 1:
				ret = Hiperarkos.ATLAS;
				break;
			case 2:
				ret = Hiperarkos.TERRON;
				break;
			case 3:
				ret = Hiperarkos.IGGROX;
				break;
			case 4:
				ret = Hiperarkos.OMPLO;
				break;
			case 5:
				ret = Hiperarkos.UXMO;
				break;
			case 6:
				ret = Hiperarkos.SALLU;
				break;
			case 7:
				ret = Hiperarkos.ITHUR;
				break;
			case 8:
				ret = Hiperarkos.GRAM;
				break;
			case 9:
				ret = Hiperarkos.HETTIZ;
				break;
			case 10:
				ret = Hiperarkos.ELLUN;
				break;
			}

			return ret;
		}

		public String toString() {
			String ret = "";

			if (mValor == 1) {
				ret = "ATLAS";
			}
			if (mValor == 2) {
				ret = "TERRON";
			}
			if (mValor == 3) {
				ret = "IGGROX";
			}
			if (mValor == 4) {
				ret = "OMPLO";
			}
			if (mValor == 5) {
				ret = "UXMO";
			}
			if (mValor == 6) {
				ret = "SALLU";
			}
			if (mValor == 7) {
				ret = "ITHUR";
			}
			if (mValor == 8) {
				ret = "GRAM";
			}
			if (mValor == 9) {
				ret = "HETTIZ";
			}
			if (mValor == 10) {
				ret = "ELLUN";
			}

			return ret;
		}
	}

	public static enum Superarkos {

		ALFA(1), BETA(2), GAMA(3), DELTA(4), EPSILON(5), IOTA(6), KAPA(7), ZETA(8), SIGMA(9), OMEGA(10);

		private int mValor;

		Superarkos(int eValor) {
			mValor = eValor;
		}

		public int getValor() {
			return mValor;
		}
		
		public static Superarkos get(int eValor) {
			Superarkos ret = null;

			switch (eValor) {
			case 1:
				ret = Superarkos.ALFA;
				break;
			case 2:
				ret = Superarkos.BETA;
				break;
			case 3:
				ret = Superarkos.GAMA;
				break;
			case 4:
				ret = Superarkos.DELTA;
				break;
			case 5:
				ret = Superarkos.EPSILON;
				break;
			case 6:
				ret = Superarkos.IOTA;
				break;
			case 7:
				ret = Superarkos.KAPA;
				break;
			case 8:
				ret = Superarkos.ZETA;
				break;
			case 9:
				ret = Superarkos.SIGMA;
				break;
			case 10:
				ret = Superarkos.OMEGA;
				break;
			}

			return ret;
		}

		public String toString() {
			String ret = "";

			if (mValor == 1) {
				ret = "ALFA";
			}
			if (mValor == 2) {
				ret = "BETA";
			}
			if (mValor == 3) {
				ret = "GAMA";
			}
			if (mValor == 4) {
				ret = "DELTA";
			}
			if (mValor == 5) {
				ret = "EPSILON";
			}
			if (mValor == 6) {
				ret = "IOTA";
			}
			if (mValor == 7) {
				ret = "KAPA";
			}
			if (mValor == 8) {
				ret = "ZETA";
			}
			if (mValor == 9) {
				ret = "SIGMA";
			}
			if (mValor == 10) {
				ret = "OMEGA";
			}

			return ret;
		}
		
		public String getCapital() {
			String ret = "";

			if (mValor == 1) {
				ret = "ALF";
			}
			if (mValor == 2) {
				ret = "BET";
			}
			if (mValor == 3) {
				ret = "GAM";
			}
			if (mValor == 4) {
				ret = "DEL";
			}
			if (mValor == 5) {
				ret = "EPS";
			}
			if (mValor == 6) {
				ret = "IOT";
			}
			if (mValor == 7) {
				ret = "KAP";
			}
			if (mValor == 8) {
				ret = "ZET";
			}
			if (mValor == 9) {
				ret = "SIG";
			}
			if (mValor == 10) {
				ret = "OME";
			}

			return ret;
		}
	}

	public static enum Signos {

		TIGRE(1), RAPOSA(2), LEOPARDO(3), LEAO(4), TOURO(5), LOBO(6), GATO(7), CARPA(8), GAVIAO(9), SERPENTE(10);

		private int mValor;

		Signos(int eValor) {
			mValor = eValor;
		}

		public int getValor() {
			return mValor;
		}

		public ArrayList<String> Listar() {

			ArrayList<String> mSignos = new ArrayList<String>();

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

			return mSignos;
		}

		public String toString() {
			String ret = "";

			if (mValor == 1) {
				ret = "TIGRE";
			}
			if (mValor == 2) {
				ret = "RAPOSA";
			}
			if (mValor == 3) {
				ret = "LEOPARDO";
			}
			if (mValor == 4) {
				ret = "LEAO";
			}
			if (mValor == 5) {
				ret = "TOURO";
			}
			if (mValor == 6) {
				ret = "LOBO";
			}
			if (mValor == 7) {
				ret = "GATO";
			}
			if (mValor == 8) {
				ret = "CARPA";
			}
			if (mValor == 9) {
				ret = "GAVIAO";
			}
			if (mValor == 10) {
				ret = "SERPENTE";
			}

			return ret;
		}
		
		public static Signos get(int eValor) {
			Signos ret = null;

			switch (eValor) {
			case 1:
				ret = Signos.TIGRE;
				break;
			case 2:
				ret = Signos.RAPOSA;
				break;
			case 3:
				ret = Signos.LEOPARDO;
				break;
			case 4:
				ret = Signos.LEAO;
				break;
			case 5:
				ret = Signos.TOURO;
				break;
			case 6:
				ret = Signos.LOBO;
				break;
			case 7:
				ret = Signos.GATO;
				break;
			case 8:
				ret = Signos.CARPA;
				break;
			case 9:
				ret = Signos.GAVIAO;
				break;
			case 10:
				ret = Signos.SERPENTE;
				break;
			}

			return ret;
		}
	}

	public static enum Hizarkos {

		HITTARIUM(1), DEGGOVIUM(2), NUZTIUM(3), HARBARIUM(4);

		private int mValor;

		Hizarkos(int eValor) {
			mValor = eValor;
		}

		public int getValor() {
			return mValor;
		}

		public String toString() {
			String ret = "";

			if (mValor == 1) {
				ret = "HITTARIUM";
			}
			if (mValor == 2) {
				ret = "DEGGOVIUM";
			}
			if (mValor == 3) {
				ret = "NUZTIUM";
			}
			if (mValor == 4) {
				ret = "HARBARIUM";
			}

			return ret;
		}
	}

	public static enum Periarkos {

		UD(1), AD(2), ED(3), OD(4);

		private int mValor;

		Periarkos(int eValor) {
			mValor = eValor;
		}

		public int getValor() {
			return mValor;
		}

		public String toString() {
			String ret = "";

			if (mValor == 1) {
				ret = "UD";
			}
			if (mValor == 2) {
				ret = "AD";
			}
			if (mValor == 3) {
				ret = "ED";
			}
			if (mValor == 4) {
				ret = "OD";
			}

			return ret;
		}
	}

	public static enum Modarkos {

		OZZ(0), AZZ(1);

		private int mValor;

		Modarkos(int eValor) {
			mValor = eValor;
		}

		public int getValor() {
			return mValor;
		}

		public String toString() {
			String ret = "";

			if (mValor == 0) {
				ret = "OZZ";
			}
			if (mValor == 1) {
				ret = "AZZ";
			}

			return ret;
		}
	}

	public static enum Epocas {

		CRIACAO(0), DEUSES(1), REINOS(2), IMPERIOS(3);

		private int mValor;

		Epocas(int eValor) {
			mValor = eValor;
		}

		public int getValor() {
			return mValor;
		}

		public String toString() {
			String ret = "";

			if (mValor == 0) {
				ret = "CRIACAO";
			}
			if (mValor == 1) {
				ret = "DEUSES";
			}
			if (mValor == 2) {
				ret = "REINOS";
			}
			if (mValor == 3) {
				ret = "IMPERIOS";
			}

			return ret;
		}
	}

}
