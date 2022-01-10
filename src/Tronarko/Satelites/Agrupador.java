package Tronarko.Satelites;

import java.util.ArrayList;

public class Agrupador {

	private int ACUMULADOR_Comum;
	private int ACUMULADOR_Escuridao;
	private int ACUMULADOR_Iluminacao;

	private int ACUMULADOR_Allett;
	private int ACUMULADOR_Ettun;
	private int ACUMULADOR_Unnall;

	private int ACUMULADOR_Allizz;
	private int ACUMULADOR_Ettizz;
	private int ACUMULADOR_Unnizz;

	private ArrayList<HiperFases> mHiperFases;

	public Agrupador() {

		mHiperFases = new ArrayList<HiperFases>();

		Zerar();

	}

	public void Adicionar(HiperFases eHiperFase) {

		mHiperFases.add(eHiperFase);

	}

	public void Zerar() {
		ACUMULADOR_Comum = 0;
		ACUMULADOR_Comum = 0;
		ACUMULADOR_Allett = 0;
		ACUMULADOR_Ettun = 0;
		ACUMULADOR_Unnall = 0;
		ACUMULADOR_Allizz = 0;
		ACUMULADOR_Ettizz = 0;
		ACUMULADOR_Unnizz = 0;
		ACUMULADOR_Escuridao = 0;
		ACUMULADOR_Iluminacao = 0;
	}

	public void Agrupar() {

		Zerar();

		for (HiperFases HiperFaseC : mHiperFases) {
			switch (HiperFaseC) {
			case COMUM:
				ACUMULADOR_Comum += 1;
				break;
			case ESCURIDAO:
				ACUMULADOR_Escuridao += 1;
				break;
			case ILUMINACAO:
				ACUMULADOR_Iluminacao += 1;
				break;

			case ALLETT:
				ACUMULADOR_Allett += 1;
				break;

			case ETTUN:
				ACUMULADOR_Ettun += 1;
				break;

			case UNNALL:
				ACUMULADOR_Unnall += 1;
				break;

			case ALLIZZ:
				ACUMULADOR_Allizz += 1;
				break;

			case ETTIZZ:
				ACUMULADOR_Ettizz += 1;
				break;

			case UNNIZZ:
				ACUMULADOR_Unnizz += 1;
				break;

			default:
				break;
			}

		}

	}

	public int getComum() {
		return ACUMULADOR_Comum;
	}

	public int getEscuridao() {
		return ACUMULADOR_Escuridao;
	}

	public int getIluminacao() {
		return ACUMULADOR_Iluminacao;
	}

	public int getAllett() {
		return ACUMULADOR_Allett;
	}

	public int getEttun() {
		return ACUMULADOR_Ettun;
	}

	public int getUnnall() {
		return ACUMULADOR_Unnall;
	}

	public int getAllizz() {
		return ACUMULADOR_Allizz;
	}

	public int getEttizz() {
		return ACUMULADOR_Ettizz;
	}

	public int getUnnizz() {
		return ACUMULADOR_Unnizz;
	}

	public int getTodos() {

		return (ACUMULADOR_Comum + ACUMULADOR_Allett + ACUMULADOR_Ettun + ACUMULADOR_Unnall + ACUMULADOR_Allizz
				+ ACUMULADOR_Ettizz + ACUMULADOR_Unnizz + ACUMULADOR_Escuridao + ACUMULADOR_Iluminacao);

	}
}
