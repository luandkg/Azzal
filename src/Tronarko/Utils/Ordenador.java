package Tronarko.Utils;

import Tronarko.Hazde;
import Tronarko.Tozte;
import Tronarko.Tron;

import java.util.ArrayList;

public class Ordenador {

	public static void OrdenarToztes(ArrayList<Tozte> Entrada) {

		int n = Entrada.size();
		Tozte temp = null;

		for (int i = 0; i < n; i++) {
			for (int j = 1; j < (n - i); j++) {

				if (Entrada.get(j - 1).MaiorIgualQue(Entrada.get(j))) {
					temp = Entrada.get(j - 1);
					Entrada.set(j - 1, Entrada.get(j));
					Entrada.set(j, temp);

				}

			}
		}

	}

	public static void OrdenarHazdes(ArrayList<Hazde> Entrada) {

		int n = Entrada.size();
		Hazde temp = null;

		for (int i = 0; i < n; i++) {
			for (int j = 1; j < (n - i); j++) {

				if (Entrada.get(j - 1).isMaiorIgual(Entrada.get(j))) {
					temp = Entrada.get(j - 1);
					Entrada.set(j - 1, Entrada.get(j));
					Entrada.set(j, temp);

				}

			}
		}

	}

	public static void OrdenarTrons(ArrayList<Tron> Entrada) {

		int n = Entrada.size();
		Tron temp = null;

		for (int i = 0; i < n; i++) {
			for (int j = 1; j < (n - i); j++) {

				if (Entrada.get(j - 1).MaiorIgualQue(Entrada.get(j))) {
					temp = Entrada.get(j - 1);
					Entrada.set(j - 1, Entrada.get(j));
					Entrada.set(j, temp);

				}

			}
		}

	}

}
