package Tronarko.Harrempluz;

import java.util.ArrayList;
import java.util.Random;

public class Colecao {

	private ArrayList<String> mItens;
	private String mNome;

	private ArrayList<String> mGuardados;
	private ArrayList<String> mRetirados;

	public Colecao(String eNome, String eConteudo) {

		mNome = eNome;

		mItens = new ArrayList<String>();
		mGuardados = new ArrayList<String>();
		mRetirados = new ArrayList<String>();

		int i = 0;
		int o = eConteudo.length();
		String Corrente = "";

		while (i < o) {
			char l = (eConteudo.charAt(i));
			if (l == ' ') {
				Corrente = Corrente.toLowerCase();

				Corrente = Corrente.substring(0, 1).toUpperCase().concat(Corrente.substring(1));

				mItens.add(Corrente);
				Corrente = "";
			} else {
				Corrente += l;
			}
			i += 1;
		}

	}

	public String getNome() {
		return mNome;
	}

	public void Incluir(String eItem) {
		mItens.add(eItem);
	}

	public String Obter(int eIndex) {
		return mItens.get(eIndex);
	}

	public int getQuantidade() {
		return mItens.size();
	}

	public final ArrayList<String> Listar() {
		return mItens;
	}

	private int mEscolhendo;

	public void IniciarEscolha() {
		mEscolhendo = 0;
		mGuardados.clear();
		mRetirados.clear();

		for (String eItem : mItens) {
			mGuardados.add(eItem);
		}
	}

	public String SorteieQualquer() {

		Random Sorteador = new Random();
		int numero = Sorteador.nextInt(getQuantidade());
		return mItens.get(numero);

	}

	public String Sorteie() {
		String ret = "";
		if (mEscolhendo >= 0 && mEscolhendo < getQuantidade()) {

			Random Sorteador = new Random();
			int numero = (Sorteador.nextInt(20) * getQuantidade()) + Sorteador.nextInt(100);

			while (numero >= mGuardados.size()) {
				numero -= mGuardados.size();
			}

			ret = mGuardados.get(numero);
			mRetirados.add(ret);
			mGuardados.remove(numero);

			mEscolhendo += 1;
		}
		return ret;
	}
}
