package Tronarko;

import Tronarko.Tronarko.Tron;

public class FusoHorario {

	private Tron mPadronizado;

	public FusoHorario(Tron ePadronizado) {

		mPadronizado = ePadronizado;

	}

	public void Sincronizar(Tron ePadronizado) {

		mPadronizado = ePadronizado;

	}

	public Tron AAC() {
		return mPadronizado;
	}

	// PASSADO
	public Tron NGR() {
		return mPadronizado.modificar_Arco(-1);
	}

	public Tron AGG() {
		return mPadronizado.modificar_Arco(-2);
	}

	public Tron EBZ() {
		return mPadronizado.modificar_Arco(-3);
	}

	public Tron UKC() {
		return mPadronizado.modificar_Arco(-4);
	}

	// FUTURO

	public Tron OZD() {
		return mPadronizado.modificar_Arco(+1);
	}

	public Tron LLH() {
		return mPadronizado.modificar_Arco(+2);
	}

	public Tron ITG() {
		return mPadronizado.modificar_Arco(+3);
	}

	public Tron RCC() {
		return mPadronizado.modificar_Arco(+4);
	}

	public Tron PPQ() {
		return mPadronizado.modificar_Arco(+5);
	}
}
