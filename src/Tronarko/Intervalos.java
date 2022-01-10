package Tronarko;

import Tronarko.Tronarko.Hazde;
import Tronarko.Tronarko.Tozte;
import Tronarko.Tronarko.Tron;

public class Intervalos {

	public static class Tozte_Intervalo {

		private String mNome;
		private Tozte mInicio;
		private Tozte mFim;

		public Tozte_Intervalo(String eNome, Tozte eInicio, Tozte eFim) {

			this.mNome = eNome;

			Organizar(eInicio, eFim);

		}

		private void Organizar(Tozte eInicio, Tozte eFim) {

			if (eInicio.MenorIgualQue(eFim)) {
				this.mInicio = eInicio;
				this.mFim = eFim;
			} else {
				this.mFim = eInicio;
				this.mInicio = eFim;
			}

		}

		public String getNome() {
			return mNome;
		}

		public Tozte getInicio() {
			Organizar(mInicio, mFim);

			return mInicio;
		}

		public Tozte getFim() {
			Organizar(mInicio, mFim);

			return mFim;
		}

		public long getSuperarkos() {
			Organizar(mInicio, mFim);

			return (mFim.getSuperarkosTotal() - mInicio.getSuperarkosTotal());
		}

		public long getSuperarkosComFim() {
			Organizar(mInicio, mFim);

			return ((mFim.getSuperarkosTotal() - mInicio.getSuperarkosTotal()) + 1);
		}

		public boolean Tronako_Igual() {
			Organizar(mInicio, mFim);

			return mInicio.getTronarko() == mFim.getTronarko();
		}

		public boolean Hiperarko_Igual() {
			Organizar(mInicio, mFim);

			return mInicio.getHiperarko() == mFim.getHiperarko();
		}

		public boolean TronarkoEHiperarko_Igual() {
			return (Hiperarko_Igual() == true) && (Tronako_Igual() == true);
		}

		public String toString() {
			Organizar(mInicio, mFim);

			return mInicio.toString() + " - " + mFim.toString();
		}

		// COMPARADORES

		public boolean MaiorQue(Tozte_Intervalo Outro) {
			boolean resposta = false;
			if (this.getSuperarkos() > Outro.getSuperarkos()) {
				resposta = true;
			}

			return resposta;
		}

		public boolean MenorQue(Tozte_Intervalo Outro) {
			boolean resposta = false;
			if (this.getSuperarkos() < Outro.getSuperarkos()) {
				resposta = true;
			}

			return resposta;
		}

		public boolean Igual(Tozte_Intervalo Outro) {
			boolean resposta = false;
			if (this.getSuperarkos() == Outro.getSuperarkos()) {
				resposta = true;
			}

			return resposta;
		}

		public boolean Diferente(Tozte_Intervalo Outro) {
			boolean resposta = false;
			if (this.getSuperarkos() != Outro.getSuperarkos()) {
				resposta = true;
			}

			return resposta;
		}

		public boolean MaiorIgualQue(Tozte_Intervalo Outro) {
			boolean resposta = false;
			if (this.getSuperarkos() >= Outro.getSuperarkos()) {
				resposta = true;
			}

			return resposta;
		}

		public boolean MenorIgualQue(Tozte_Intervalo Outro) {
			boolean resposta = false;
			if (this.getSuperarkos() <= Outro.getSuperarkos()) {
				resposta = true;
			}

			return resposta;
		}

	}

	public static class Hazde_Intervalo {

		private String mNome;
		private Hazde mInicio;
		private Hazde mFim;

		public Hazde_Intervalo(String eNome, Hazde eInicio, Hazde eFim) {

			this.mNome = eNome;

			Organizar(eInicio, eFim);

		}

		private void Organizar(Hazde eInicio, Hazde eFim) {

			if (eInicio.MenorIgualQue(eFim)) {
				this.mInicio = eInicio;
				this.mFim = eFim;
			} else {
				this.mFim = eInicio;
				this.mInicio = eFim;
			}

		}

		public String getNome() {
			return mNome;
		}

		public Hazde getInicio() {
			Organizar(mInicio, mFim);

			return mInicio;
		}

		public Hazde getFim() {
			Organizar(mInicio, mFim);

			return mFim;
		}

		public int getTotalEttons() {
			Organizar(mInicio, mFim);

			return (mFim.getTotalEttons() - mInicio.getTotalEttons());
		}

		public boolean Arco_Igual() {
			Organizar(mInicio, mFim);

			return mInicio.getArco() == mFim.getArco();
		}

		public int getArkos() {

			int mArkos = 0;
			int mEttons = getTotalEttons();

			int mIttas = 0;

			while (mEttons >= 100) {
				mEttons -= 100;
				mIttas += 1;
			}

			while (mIttas >= 100) {
				mIttas -= 100;
				mArkos += 1;
			}

			return mArkos;

		}

		public String getDiferenca() {

			int mArkos = 0;
			int mEttons = getTotalEttons();

			int mIttas = 0;

			while (mEttons >= 100) {
				mEttons -= 100;
				mIttas += 1;
			}

			while (mIttas >= 100) {
				mIttas -= 100;
				mArkos += 1;
			}

			return mArkos + ":" + mIttas + ":" + mEttons;

		}

		public String toString() {
			Organizar(mInicio, mFim);

			return mInicio.toString() + " - " + mFim.toString();
		}

		// COMPARADORES

		public boolean MaiorQue(Hazde_Intervalo Outro) {
			boolean resposta = false;
			if (this.getTotalEttons() > Outro.getTotalEttons()) {
				resposta = true;
			}

			return resposta;
		}

		public boolean MenorQue(Hazde_Intervalo Outro) {
			boolean resposta = false;
			if (this.getTotalEttons() < Outro.getTotalEttons()) {
				resposta = true;
			}

			return resposta;
		}

		public boolean Igual(Hazde_Intervalo Outro) {
			boolean resposta = false;
			if (this.getTotalEttons() == Outro.getTotalEttons()) {
				resposta = true;
			}

			return resposta;
		}

		public boolean Diferente(Hazde_Intervalo Outro) {
			boolean resposta = false;
			if (this.getTotalEttons() != Outro.getTotalEttons()) {
				resposta = true;
			}

			return resposta;
		}

		public boolean MaiorIgualQue(Hazde_Intervalo Outro) {
			boolean resposta = false;
			if (this.getTotalEttons() >= Outro.getTotalEttons()) {
				resposta = true;
			}

			return resposta;
		}

		public boolean MenorIgualQue(Hazde_Intervalo Outro) {
			boolean resposta = false;
			if (this.getTotalEttons() <= Outro.getTotalEttons()) {
				resposta = true;
			}

			return resposta;
		}

	}

	public static class Tron_Intervalo {

		private String mNome;
		private Tron mInicio;
		private Tron mFim;

		public Tron_Intervalo(String eNome, Tron eInicio, Tron eFim) {

			this.mNome = eNome;

			Organizar(eInicio, eFim);

		}

		private void Organizar(Tron eInicio, Tron eFim) {

			if (eInicio.MenorIgualQue(eFim)) {
				this.mInicio = eInicio;
				this.mFim = eFim;
			} else {
				this.mFim = eInicio;
				this.mInicio = eFim;
			}

		}

		public String getNome() {
			return mNome;
		}

		public Tron getInicio() {
			Organizar(mInicio, mFim);

			return mInicio;
		}

		public Tron getFim() {
			Organizar(mInicio, mFim);

			return mFim;
		}

		public long getTotalEttons() {
			Organizar(mInicio, mFim);

			return mFim.getTotal() - mInicio.getTotal();
		}

		public int getArkos() {

			int mArkos = 0;
			long mTotal = getTotalEttons();
			long UmArko = 1 * 100 * 100;

			while (mTotal >= UmArko) {
				mTotal -= UmArko;
				mArkos += 1;
			}

			return mArkos;
		}

		public int getArkosComFim() {

			return getArkos() + 1;
		}

		public int getSuperarkos() {

			int mArkos = getArkos();
			int mSuperarkos = 0;

			while (mArkos >= 10) {
				mArkos -= 10;
				mSuperarkos += 1;
			}

			return mSuperarkos;
		}

		public String getIntervalo() {

			int mArkos = 0;
			int mIttas = 0;
			int mEttons = 0;

			int mSuperarkos = 0;

			long mTotal = getTotalEttons();
			long UmArko = 1 * 100 * 100;

			while (mTotal >= UmArko) {
				mTotal -= UmArko;
				mArkos += 1;
			}

			while (mTotal >= 100) {
				mTotal -= 100;
				mEttons += 1;
			}

			while (mEttons >= 100) {
				mEttons -= 100;
				mIttas += 1;
			}

			while (mArkos >= 10) {
				mArkos -= 10;
				mSuperarkos += 1;
			}

			return mSuperarkos + " - " + mArkos + ":" + mIttas + ":" + mEttons;

		}

		public Hazde getHazde() {

			int mArkos = 0;
			int mIttas = 0;
			int mEttons = 0;

			long mTotal = getTotalEttons();
			long UmArko = 1 * 100 * 100;

			while (mTotal >= UmArko) {
				mTotal -= UmArko;
				mArkos += 1;
			}

			while (mTotal >= 100) {
				mTotal -= 100;
				mEttons += 1;
			}

			while (mEttons >= 100) {
				mEttons -= 100;
				mIttas += 1;
			}

			return new Hazde(mArkos, mIttas, mEttons);

		}

		public String toString() {
			Organizar(mInicio, mFim);

			return mInicio.toString() + " - " + mFim.toString();
		}

		// COMPARADORES

		public boolean MaiorQue(Tron_Intervalo Outro) {
			boolean resposta = false;
			if (this.getTotalEttons() > Outro.getTotalEttons()) {
				resposta = true;
			}

			return resposta;
		}

		public boolean MenorQue(Tron_Intervalo Outro) {
			boolean resposta = false;
			if (this.getTotalEttons() < Outro.getTotalEttons()) {
				resposta = true;
			}

			return resposta;
		}

		public boolean Igual(Tron_Intervalo Outro) {
			boolean resposta = false;
			if (this.getTotalEttons() == Outro.getTotalEttons()) {
				resposta = true;
			}

			return resposta;
		}

		public boolean Diferente(Tron_Intervalo Outro) {
			boolean resposta = false;
			if (this.getTotalEttons() != Outro.getTotalEttons()) {
				resposta = true;
			}

			return resposta;
		}

		public boolean MaiorIgualQue(Tron_Intervalo Outro) {
			boolean resposta = false;
			if (this.getTotalEttons() >= Outro.getTotalEttons()) {
				resposta = true;
			}

			return resposta;
		}

		public boolean MenorIgualQue(Tron_Intervalo Outro) {
			boolean resposta = false;
			if (this.getTotalEttons() <= Outro.getTotalEttons()) {
				resposta = true;
			}

			return resposta;
		}

	}

}
