package apps.app_tronarko;

import libs.tronarko.intervalos.Tozte_Intervalo;
import libs.tronarko.Satelites.Ceu;
import libs.tronarko.Satelites.Fases;
import libs.tronarko.Tozte;

import java.util.ArrayList;

public class ObservarCeu {

    public static void mostrar(Tozte TozteInicial, String eNome, Fases fase_allux, Fases fase_ettos, Fases fase_unnos) {

        ArrayList<Tozte_Intervalo> ret = new ArrayList<Tozte_Intervalo>();

        Tozte procurando = new Tozte(TozteInicial.getSuperarko(), TozteInicial.getHiperarko(), TozteInicial.getTronarko());

        boolean encontrou = false;
        boolean terminou = false;

        boolean executando = true;


        Tozte eComecou = null;
        Tozte eTerminou = null;

        Ceu mCeu = new Ceu();

        while (executando) {

            Tozte hoje = procurando;

            if (!encontrou) {

                int todas = 0;

                if (mCeu.getAllux().getFase(hoje) == fase_allux) {
                    todas += 1;
                }
                if (mCeu.getEttos().getFase(hoje) == fase_ettos) {
                    todas += 1;
                }
                if (mCeu.getUnnos().getFase(hoje) == fase_unnos) {
                    todas += 1;
                }

                if (todas == 3) {
                    eComecou = hoje.getCopia();
                    encontrou = true;
                }


            } else {

                int todas = 0;

                if (mCeu.getAllux().getFase(hoje) == fase_allux) {
                    todas += 1;
                }
                if (mCeu.getEttos().getFase(hoje) == fase_allux) {
                    todas += 1;
                }
                if (mCeu.getUnnos().getFase(hoje) == fase_allux) {
                    todas += 1;
                }

                if (todas != 3) {

                    eTerminou = hoje.getCopia();
                    terminou = true;

                    Tozte_Intervalo inter = new Tozte_Intervalo(eNome, eComecou, eTerminou);

                    System.out.println("\n " + eNome + " : " + eComecou.getTexto() + " a " + eTerminou.getTexto() + " com : " + inter.getSuperarkos() + " Superarkos  ");


                }

            }


            if (encontrou == true && terminou == true) {
                executando = false;
            }

            procurando = procurando.adicionar_Superarko(1);

        }


    }

}
