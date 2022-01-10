package Tronarko;

import java.util.ArrayList;

import Tronarko.Tronarko.Hazde;
import Tronarko.Tronarko.Tozte;
import Tronarko.Tronarko.Tron;

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

				if (Entrada.get(j - 1).MaiorIgualQue(Entrada.get(j))) {
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
